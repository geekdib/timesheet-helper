package com.geekyants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.entity.TimesheetUrl;

@Repository("timesheetUrlRepository")
public interface TimesheetUrlRepository extends JpaRepository<TimesheetUrl, Long> {
	
	TimesheetUrl findById(int id);
}
