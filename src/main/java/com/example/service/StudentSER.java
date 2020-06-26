package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDAO;
import com.example.exception.ListEmptyException;
import com.example.exception.NotFoundStudent;
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
	public List<Student> getsStudent() throws ListEmptyException {
		
		List<Student> list=dao.findAll();
		if(list.isEmpty()||list==null)
			throw new ListEmptyException("....No Student added in List...");
		return dao.findAll();
	}
	
	@Override
	public List<Student> byStudentName(String name)
	{
		return dao.findByName(name);
	}
	
	@Override
	public boolean deleteStudent(int id) throws NotFoundStudent
	{
		if(dao.existsById(id))
		{
			dao.deleteById(id);
			return true;
		}
		else
			throw new NotFoundStudent("....Student Not Found for Delete with..."+id);
		
	}
	@Override
	public Student getStudent(int id) throws NotFoundStudent
	{
		return dao.findById(id).orElseThrow(()->new NotFoundStudent("....Student NotFound for Updated with ..."+id));
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
