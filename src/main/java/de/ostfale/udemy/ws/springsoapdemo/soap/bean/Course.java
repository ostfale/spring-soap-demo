package de.ostfale.udemy.ws.springsoapdemo.soap.bean;

/**
 * Implements the course details service
 * Created : 08.03.2018
 *
 * @author : usauerbrei
 */
public class Course {

	private int id;
	private String name;
	private String description;

	public Course(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
