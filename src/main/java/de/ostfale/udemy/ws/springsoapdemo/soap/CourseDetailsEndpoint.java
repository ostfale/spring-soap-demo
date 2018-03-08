package de.ostfale.udemy.ws.springsoapdemo.soap;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import de.ostfale.udemy.ws.springsoapdemo.soap.bean.Course;
import de.ostfale.udemy.ws.springsoapdemo.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Web service endpoint
 * <ul>
 * <li>Request: {@link com.in28minutes.courses.GetCourseDetailsRequest}</li>
 * <li>Response: {@link com.in28minutes.courses.GetCourseDetailsResponse}</li>
 * </ul>
 * Created : 07.03.2018
 *
 * @author : usauerbrei
 */
@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processeRequest(@RequestPayload GetCourseDetailsRequest request) {
		return mapCourse(request);
	}

	private GetCourseDetailsResponse mapCourse(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		Course course = service.findById(request.getId());
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		response.setCourseDetails(courseDetails);
		return response;
	}
}
