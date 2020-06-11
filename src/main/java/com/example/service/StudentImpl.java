package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface StudentImpl {

	public Student saveStudent(Student s);
	public List<Student> getsStudent();
	public List<Student> byStudentName(String name);
	
	public Student getStudent(int id);
	public Student updateStudent(Student s);
	public boolean deleteStudent(int id);
}
