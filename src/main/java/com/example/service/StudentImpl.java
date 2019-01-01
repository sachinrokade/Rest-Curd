package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface StudentImpl {

	public String saveStudent(Student s);
	public List<Student> getsStudent();
	public Student getStudent(int id);
	public Student updateStudent(Student s);
	public boolean deleteStudent(int id);
}
