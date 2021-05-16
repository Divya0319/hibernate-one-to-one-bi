package com.hibernatetutorial.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernatetutorial.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
	
			// start the transaction
			session.beginTransaction();

			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName="Clinton"
			theStudents = session.createQuery("from Student s where s.lastName='Clinton'").getResultList();
			
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			
			// query students: lastName:'Clinton' OR firtName='Steve'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Clinton' OR s.firstName='Steve'").getResultList();
			
			System.out.println("\n\nStudents who have last name Clinton or first name Steve");
			displayStudents(theStudents);
			
			
			// query students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s "
					+ "where s.email LIKE '%gmail.com'").getResultList();
			
			System.out.println("\n\nStudents who have email ending with luv2code.com");
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
