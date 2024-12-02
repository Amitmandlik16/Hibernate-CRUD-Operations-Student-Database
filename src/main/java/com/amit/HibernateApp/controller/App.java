package com.amit.HibernateApp.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.amit.HibernateApp.dto.Student;

//all functionalities mentioned/defined here
//***it is Testing App only
public class App {
	public static void main(String[] args) {
		Integer id=null;
		// 1.Inform jvm to activate hibernate
		Configuration configuration = new Configuration();
		configuration.configure();

		// creating a SessionFactory object to hold many other objects required for
		// hibernate
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		// using sessionFactory object get only one session object to perform our
		// persistence operation
		Session session = sessionFactory.openSession();// connection object,ORM-dialect,L1-cache,Txt management

		// begin the transaction as operation is non select
		Transaction transaction = session.beginTransaction();

		try {

			// 2.Create Persistence Object
			Student student = new Student();
			student.setSid(5);
			student.setAddress("Akole");
			student.setSage(19);
			student.setSname("Amit");

			// 3.Perform persistence operation using Entity/model/POJO object
			id=(Integer)session.save(student);

			// 4.Generate the query and send to the database for execution
			// by default connection object present in connection pool autocommit mode=false

			transaction.commit();
			System.out.println("Object saved to the database with id "+id);
		} catch (Exception e) {
			// if transaction is failed rollback it
			transaction.rollback();
		}
		session.close();
		sessionFactory.close();

	}
}
