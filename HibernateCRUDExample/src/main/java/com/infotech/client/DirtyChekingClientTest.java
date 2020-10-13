package com.infotech.client;

import java.util.Date;

import org.hibernate.Session;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class DirtyChekingClientTest {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = (Employee)session.get(Employee.class, 2);
			if(employee!=null) {
				
				session.beginTransaction();
				employee.setSalary(85000.00);
				//session.update(employee);  // if we comment this line then also Hibernate will update database automatically
				session.getTransaction().commit();
			}
			else {
				System.out.println("Employee does not exist with provided Id...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void getEmployeeById(Session session) {
		Employee employee = (Employee)session.get(Employee.class, 20);
		if(employee!=null) {
			System.out.println("====="+employee);
		}
		else {
			System.out.println("Employee does not exist with provided Id...");
		}
		
	}

	private static void createEmployee(Session session) {
		//Employee employee = getEmployee();
		session.beginTransaction();
		 Integer id  = (Integer)session.save(getEmployee());  //save method methods return type is Serializable and it returns primary key
		 System.out.println("Employee is Created with: "+id);
		 			 
		session.getTransaction().commit();
	}

	private static Employee getEmployee() {

		Employee employee = new Employee();
		employee.setEmployeeName("abc");
		employee.setEmail("abc@gmail.com");
		employee.setSalary(60000.00);
		employee.setDoj(new Date());
		return employee;
	}

}
