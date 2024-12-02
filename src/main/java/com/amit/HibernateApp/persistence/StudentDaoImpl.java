package com.amit.HibernateApp.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.amit.HibernateApp.dto.Student;
import com.amit.HibernateApp.util.HibernateUtil;

//Persistence logic using Hibernate API
public class StudentDaoImpl implements IStudentDao {

	Session session=HibernateUtil.getSession();
	@Override
	public String addStudent(String sname, Integer sage, String address) {
		Transaction transaction=session.beginTransaction();
		boolean flag=false;
		String status="failure";//consider status=failure initially as transaction is not done
		Integer sid=-1;
		try {
			if(transaction!=null)
			{
				Student student=new Student();
				student.setSid(sid);
				student.setSname(sname);
				student.setSage(sage);
				student.setAddress(address);
				
				sid=(Integer)session.save(student);
				flag=true;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				status=String.valueOf(sid);
			}
			else {
				transaction.rollback();
				status="failure";
			}
			
		}
		return status;
	}

	@Override
	public Student searchStudent(Integer sid) {
		Student student=session.get(Student.class, sid);
		if(student!=null)
		{
			return student;
		}
		else {
			return null;
		}

		

	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String address) {
		
		Transaction transaction=session.beginTransaction();
		boolean flag=false;
		String status="";
		try {
			if(transaction!=null)
			{
				Student student=session.get(Student.class,sid);
				if(student!=null)
				{
//					
//					student.setSid(sid);
//					student.setSname(sname);
//					student.setSage(sage);
//					student.setAddress(address);
//					session.update(student);
					
					Student stud=new Student();
					stud.setSid(sid);
					stud.setSname(sname);
					stud.setSage(sage);
					stud.setAddress(address);
					session.merge(stud);
					flag=true;
				}
				else {
					status="not found";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				status="success";
			}
			else {
				transaction.rollback();
				status="failure";
			}
		
		}
		return status;
	}


	@Override
	public String deleteStudent(Integer sid) {
		Student student=searchStudent(sid);
		Transaction transaction=session.beginTransaction();
		boolean flag=false;
		String status="";
		try {
			if(student!=null)
			{
				flag=true;	
				session.delete(student);
			}
			else {
				return "not found";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			if(flag)
			{
				transaction.commit();
				status="success";
			}
			else if(flag==false && status=="not found")
			{
				transaction.rollback();
				status="failure";
			}
		}
	return status;
	}	
}
	
