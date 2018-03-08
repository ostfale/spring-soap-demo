package de.ostfale.udemy.ws.springsoapdemo.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Service specific exception
 * Created : 08.03.2018
 *
 * @author : usauerbrei
 */
//@SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CUSTOM,
		customFaultCode = "{http://in28minutes.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5539752918315913638L;

	public CourseNotFoundException(String message) {
		super(message);
	}
}
