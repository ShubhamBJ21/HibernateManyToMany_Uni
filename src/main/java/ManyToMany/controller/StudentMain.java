package ManyToMany.controller;

import java.util.ArrayList;
import java.util.List;

import ManyToMany.dao.StudentDao;
import ManyToMany.dto.Course;
import ManyToMany.dto.Student;

public class StudentMain {

	public static void main(String[] args) {
		Course course1 = new Course();
		
		course1.setName("J2EE");
		course1.setFees(15000);
		course1.setDuration(25);
		
		Course course2 = new Course();
		
		course2.setName("Hibernate");
		course2.setFees(15000);
		course2.setDuration(25);
		
		Course course3 = new Course();
		
		course3.setName("Spring");
		course3.setFees(20000);
		course3.setDuration(15);
		
		List<Course> list = new ArrayList<Course>();
		
		list.add(course1); list.add(course2);
		
		Student student1 = new Student();
		
		student1.setName("Shubham Jadhav");
		student1.setPhone(8830063145L);
		student1.setAddress("Satara");
		student1.setListCourse(list);
		
		StudentDao dao = new StudentDao();
		//dao.saveStudent(student1);
		
		Student student2 = new Student();
		
		student2.setName("Ajinkya Ghisare");
		student2.setPhone(9021458674L);
		student2.setAddress("Katraj");
		
		List<Course> list1 = new ArrayList<Course>();
		list1.addAll(list);
		list1.add(course3);
		
		student2.setListCourse(list1);
		
		//dao.saveStudent(student2);
	 	//dao.removeStudent(3);	
		
		Student student3 = new Student();
		
		student3.setName("Shubham");
		student3.setPhone(8830086429L);
		student3.setAddress("Bangalore");
		student3.setListCourse(list1);
		
		dao.updateStudent(1, student3);
	}
}
