package com.geekyants.service;

/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
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

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private TimesheetUrlRepository timesheetUrlRepository;

	private static final String NOT_FOUND = "Not Found";

	@Override
	public TimesheetFillRequest processRequest(CommitRequest commitRequest) {
		TimesheetFillRequest fillRequest = new TimesheetFillRequest();
		String logkey = "Log - ";
		String ticketKey = "TICKET-ID - ";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		commitRequest.getCommits().forEach(c -> {

			try {
				String loghours = "";
				if (c.getMessage().contains("Log - ")) {

					fillRequest.setEmail(c.getAuthor().getEmail());

					loghours = c.getMessage().substring(c.getMessage().indexOf(logkey) + logkey.length(),
							c.getMessage().indexOf(logkey) + logkey.length() + 2);
					fillRequest.setStatus("Success");

					String cVal = loghours.replace("]", "");
					String tVal = cVal.trim();

					fillRequest.setLogHours(Integer.parseInt(tVal));

					if (c.getMessage().contains(ticketKey)) {
						fillRequest.setJIRA_TICKET_ID(c.getMessage().substring(
								c.getMessage().indexOf(ticketKey) + ticketKey.length(),
								c.getMessage().indexOf("]", c.getMessage().indexOf(ticketKey) + ticketKey.length())));
						fillRequest
								.setDescription(c.getMessage().substring(
										c.getMessage().indexOf(fillRequest.getJIRA_TICKET_ID())
												+ fillRequest.getJIRA_TICKET_ID().length() + 4,
										c.getMessage().length()));
					}

					try {
						fillRequest.setDate(format2
								.format(format.parse(c.getTimestamp().substring(0, c.getTimestamp().indexOf("+")))));
					} catch (ParseException e) {
						throw new TimesheetException(HttpStatus.BAD_REQUEST, "Date format incorrect");
					}
				} else {
					throw new TimesheetException(HttpStatus.BAD_REQUEST,
							"Timesheet didn't get logged because Loghours in missing in commit message.");
				}
			} catch (Exception e) {
				fillRequest.setStatus("Failed - Invalid Commit Message");
				fillRequest.setEmail(fillRequest.getEmail() != null && !fillRequest.getEmail().equalsIgnoreCase("")
						? fillRequest.getEmail()
						: NOT_FOUND);
				fillRequest.setDescription(
						fillRequest.getDescription() != null && !fillRequest.getDescription().equalsIgnoreCase("")
								? fillRequest.getDescription()
								: NOT_FOUND);
				fillRequest.setJIRA_TICKET_ID(
						fillRequest.getJIRA_TICKET_ID() != null && !fillRequest.getJIRA_TICKET_ID().equalsIgnoreCase("")
								? fillRequest.getJIRA_TICKET_ID()
								: NOT_FOUND);
				fillRequest.setLogHours(0);
				fillRequest.setDate(fillRequest.getDate() != null && !fillRequest.getDate().equalsIgnoreCase("")
						? fillRequest.getDate()
						: NOT_FOUND);
				fillRequestRepository.save(fillRequest);
				throw new TimesheetException(HttpStatus.BAD_REQUEST,
						"Timesheet didn't get logged because commit message format is incorrect.");
			}

		});

		if (fillRequest.getEmail() == null || fillRequest.getEmail().equalsIgnoreCase("")) {
			fillRequest.setStatus("Failed - Email ID missing");
			fillRequest.setEmail(NOT_FOUND);
			fillRequestRepository.save(fillRequest);
			throw new TimesheetException(HttpStatus.BAD_REQUEST,
					"Timesheet didn't get logged because commit message format is incorrect & email is missing.");
		}

		if (fillRequest.getDescription() == null || fillRequest.getDescription().equalsIgnoreCase("")) {
			fillRequest.setStatus("Failed - Description missing");
			fillRequest.setDescription(NOT_FOUND);
			fillRequestRepository.save(fillRequest);
			throw new TimesheetException(HttpStatus.BAD_REQUEST,
					"Timesheet didn't get logged because commit message format is incorrect & description is missing.");
		}

		if (fillRequest.getJIRA_TICKET_ID() == null || fillRequest.getJIRA_TICKET_ID().equalsIgnoreCase("")) {
			fillRequest.setJIRA_TICKET_ID(NOT_FOUND);
		}

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
			restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
		} catch (Exception e) {
			fillRequest.setStatus("Failed - Invalid Timesheet Endpoint");
			fillRequestRepository.save(fillRequest);
			throw new TimesheetException(HttpStatus.BAD_GATEWAY,
					"Unable to fill the timesheet as endpoint is incorrect or not responding.");
		}

		fillRequest.setStatus("Success");
		fillRequestRepository.save(fillRequest);

		return fillRequest;
	}

}
