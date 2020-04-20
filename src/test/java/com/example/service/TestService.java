package com.example.service;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.example.model.Student;
@SpringBootTest
public class TestService extends AbstractTestNGSpringContextTests {
 
	@Autowired
	StudentSER service;

	@Test
	public void saveStudentTest() {
		System.out.println("------------- start saveStudentTest -------------------------");
		assertEquals(service.saveStudent(new Student(101, "Test 1")), "savede");
		assertEquals(service.saveStudent(new Student(102, "Test 2")), "savede");
		assertEquals(service.saveStudent(new Student(103, "Test 3")), "savede");
		System.out.println("------------- End saveStudentTest -------------------------");

	}


}
