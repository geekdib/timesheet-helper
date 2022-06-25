package com.geekyants.repository;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.entity.TimesheetUrl;

@Repository("timesheetUrlRepository")
public interface TimesheetUrlRepository extends JpaRepository<TimesheetUrl, Long> {
	
	TimesheetUrl findById(int id);
}
