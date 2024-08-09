package ManyToMany.dao;

import java.util.List;

import ManyToMany.dto.Course;
import ManyToMany.dto.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class StudentDao {
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("shubham").createEntityManager();
	}
	
	public void saveStudent(Student student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		List<Course> course = student.getListCourse();
		
		for(Course subject : course) {
			entityManager.persist(subject);
		}
		
		entityManager.persist(student);
		entityTransaction.commit();		
		System.out.println("student saved successfully");
	}
	
	public void removeStudent(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Student student = entityManager.find(Student.class, id);
		List<Course> course = student.getListCourse();
		
		for(Course subject : course) {
			entityManager.remove(subject);
		}
		
		entityManager.remove(student);
		entityTransaction.commit();
		System.out.println("Student removed successfully");
	}
	
	public void updateStudent(int id, Student student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Student existedStudent = entityManager.find(Student.class, id);
		
		if(existedStudent != null) {
			existedStudent.setId(id);
			existedStudent.setName(student.getName());
			existedStudent.setPhone(student.getPhone());
			existedStudent.setAddress(student.getAddress());
			
			for(Course subject : student.getListCourse()) {
				if(subject.getId() == 0) {
					entityManager.persist(subject);
				}else {
					entityManager.merge(subject);
				}
			}
			
			//update course
			existedStudent.setListCourse(student.getListCourse());
			entityManager.merge(existedStudent);
			System.out.println("Student updated successfully");
		}
		else {
			System.out.println("Student is not from our college");
		}
		
		entityTransaction.commit();
	}
	
	public void getStudent(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Student existedStudent = entityManager.find(Student.class, id);
		if(existedStudent != null) {
			System.out.println(existedStudent);
		}else {
			System.out.println("Student with this id is not present");
		}
	}
	
	public void getAllStudent() {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Query query = entityManager.createQuery("SELECT s FROM student s");
		
		List<Student> list = query.getResultList();
		for(Student student : list) {
			System.out.println(student);
		}
		System.out.println("end!....");
	}
}
