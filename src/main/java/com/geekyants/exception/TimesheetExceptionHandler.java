package com.geekyants.exception;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class TimesheetExceptionHandler {
	
    @ExceptionHandler(TimesheetException.class)
    public ResponseEntity<TimesheetExceptionResponse> handleCrmsException(TimesheetException exception) {
    	return new ResponseEntity<>(new TimesheetExceptionResponse(exception), exception.getStatus());
    }

}
