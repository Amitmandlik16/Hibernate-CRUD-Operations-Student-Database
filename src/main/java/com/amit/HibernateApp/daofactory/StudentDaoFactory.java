package com.amit.HibernateApp.daofactory;

import com.amit.HibernateApp.persistence.IStudentDao;
import com.amit.HibernateApp.persistence.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {
	}

	private static IStudentDao studentDao = null;

	public static IStudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}

}