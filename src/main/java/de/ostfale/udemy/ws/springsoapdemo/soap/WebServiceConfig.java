package de.ostfale.udemy.ws.springsoapdemo.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration for Webservice
 * Created : 07.03.2018
 *
 * @author : usauerbrei
 */
@EnableWs
@Configuration
public class WebServiceConfig {

	/**
	 * Handles all requests and connects the servlet to an URI
	 *
	 * @param context spring context
	 * @return servlet object
	 */
	@Bean
	ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		// map servlet to  "/ws/*" url
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");                           // PortType = CoursePort
		definition.setTargetNamespace("http://in28minutes.com/courses");    // set name space
		definition.setSchema(coursesSchema);
		definition.setLocationUri("/ws");
		return definition;
	}

	// /ws/courses.wsdl from xsd
	@Bean
	XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
}
