package com.geekyants.repository;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.entity.TimesheetFillRequest;

@Repository("timesheetFillRequestRepository")
public interface TimesheetFillRequestRepository extends JpaRepository<TimesheetFillRequest, Long> {
	
	
}
