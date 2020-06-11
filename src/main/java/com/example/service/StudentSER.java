package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDAO;
import com.example.model.Student;

@Service
public class StudentSER implements StudentImpl {
	
	@Autowired
	StudentDAO dao;

	@Override
	public Student saveStudent(Student s) 
	{
		return dao.save(s);
	}

	@Override
	public List<Student> getsStudent() {
		return dao.findAll();
	}
	
	@Override
	public List<Student> byStudentName(String name)
	{
		return dao.findByName(name);
	}
	
	@Override
	public boolean deleteStudent(int id)
	{
		if(dao.existsById(id))
		{
			dao.deleteById(id);
			return true;
		}
		else

			return false;
		
	}
	@Override
	public Student getStudent(int id)
	{
		return dao.findById(id).get();
	}
	
	@Override
	public Student updateStudent(Student s)
	{
		Optional<Student> stud = dao.findById(s.getId());
		if(stud.isPresent()) {
			return dao.save(stud.get());
		}
		return null;
	}
	
}
