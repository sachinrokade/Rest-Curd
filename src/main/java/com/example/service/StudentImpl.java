package com.example.service;

import java.util.List;

import com.example.exception.ListEmptyException;
import com.example.exception.NotFoundStudent;
import com.example.model.Student;

public interface StudentImpl {

	public Student saveStudent(Student s);
	public List<Student> getsStudent() throws ListEmptyException;
	public List<Student> byStudentName(String name);
	
	public Student getStudent(int id) throws NotFoundStudent;
	public Student updateStudent(Student s);
	public boolean deleteStudent(int id) throws NotFoundStudent;
}
