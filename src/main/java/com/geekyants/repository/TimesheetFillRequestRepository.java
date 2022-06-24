package com.geekyants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.entity.TimesheetFillRequest;

@Repository("timesheetFillRequestRepository")
public interface TimesheetFillRequestRepository extends JpaRepository<TimesheetFillRequest, Long> {
	
	
}
