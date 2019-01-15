package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student,Integer>{

	public List<Student> findByName(String name);
}
