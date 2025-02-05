package com.amit.HibernateApp.persistence;

import com.amit.HibernateApp.dto.Student;

//Need of DTO in projects
//DTO -> It stands for Data Transfer Object.
//This object is used for transferring the data from one layer to another layer in realtime applications.

public interface IStudentDao {
	Object StudentDaoFactory = null;

	// operations to be implemented
	public String addStudent(String sname, Integer sage, String address);

	public Student searchStudent(Integer sid);

	public String updateStudent(Integer sid, String sname, Integer sage, String saddress);

	public String deleteStudent(Integer sid);
}
