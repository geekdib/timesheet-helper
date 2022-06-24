package com.geekyants.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.geekyants.dto.CommitRequest;
import com.geekyants.entity.TimesheetFillRequest;
import com.geekyants.exception.TimesheetException;
import com.geekyants.repository.TimesheetFillRequestRepository;
import com.geekyants.repository.TimesheetUrlRepository;

@Service
public class TimesheetServiceImpl implements TimesheetService {
	
	@Autowired
	private TimesheetFillRequestRepository fillRequestRepository;
	
	private RestTemplate restTemplate =  new RestTemplate();
	
	@Autowired
	private TimesheetUrlRepository timesheetUrlRepository;
	
	@Override
	public TimesheetFillRequest processRequest(CommitRequest commitRequest) {
		TimesheetFillRequest fillRequest = new TimesheetFillRequest();
		String logkey = "Log - ";
		String ticketKey = "TICKET-ID - ";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		commitRequest.getCommits().forEach(c -> {
			String loghours = "";
			if(c.getMessage().contains("Log - ")) {
				loghours = c.getMessage().substring(c.getMessage().indexOf(logkey) + logkey.length(),
						c.getMessage().indexOf(logkey) + logkey.length() + 2);
				fillRequest.setStatus("Success");
			}else {
				fillRequest.setStatus("NA - Log Hours Missing");
			}
			
			
			String cVal = loghours.replace("]", "");
			String tVal = cVal.trim();

			fillRequest.setLogHours(Integer.parseInt(tVal));
			fillRequest
					.setJIRA_TICKET_ID(c.getMessage().substring(c.getMessage().indexOf(ticketKey) + ticketKey.length(),
							c.getMessage().indexOf("]", c.getMessage().indexOf(ticketKey) + ticketKey.length())));
			fillRequest.setDescription(c.getMessage().substring(c.getMessage().indexOf(fillRequest.getJIRA_TICKET_ID())
					+ fillRequest.getJIRA_TICKET_ID().length() + 4, c.getMessage().length()));

			fillRequest.setEmail(c.getAuthor().getEmail());

			try {
				fillRequest.setDate(
						format2.format(format.parse(c.getTimestamp().substring(0, c.getTimestamp().indexOf("+")))));
			} catch (ParseException e) {
				throw new TimesheetException(HttpStatus.OK, e.getMessage(), fillRequest.getDescription(), fillRequest.getJIRA_TICKET_ID());
			}
			
		});

		fillRequestRepository.save(fillRequest);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(timesheetUrlRepository.findById(1).getUrl());
		Map<String, Object> resmap = new HashMap<>();
		resmap.put("email", fillRequest.getEmail());
		resmap.put("logHours", fillRequest.getLogHours());
		resmap.put("Description", fillRequest.getDescription());
		resmap.put("date", fillRequest.getDate());
		resmap.put("JIRA_TICKET_ID", fillRequest.getJIRA_TICKET_ID());
		HttpEntity<?> entity = new HttpEntity<>(resmap, headers);

		try {
			restTemplate.exchange(
					builder.toUriString(), 
					HttpMethod.POST, 
					entity, 
					String.class);
		}catch (Exception e) {
			throw new TimesheetException(HttpStatus.OK, e.getMessage(), fillRequest.getDescription(), fillRequest.getJIRA_TICKET_ID());
		}
		
		return fillRequest;
	}

}
