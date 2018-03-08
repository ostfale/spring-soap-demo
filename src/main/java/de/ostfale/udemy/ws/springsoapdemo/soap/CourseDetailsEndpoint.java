package de.ostfale.udemy.ws.springsoapdemo.soap;

import com.in28minutes.courses.*;
import de.ostfale.udemy.ws.springsoapdemo.soap.bean.Course;
import de.ostfale.udemy.ws.springsoapdemo.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

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

	private final CourseDetailsService service;

	@Autowired
	public CourseDetailsEndpoint(CourseDetailsService courseDetailsService) {
		this.service = courseDetailsService;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processeRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		int result = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(result);
		return response;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processeAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails courseDetails = mapCourse(course);
			response.getCourseDetails().add(courseDetails);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
}
