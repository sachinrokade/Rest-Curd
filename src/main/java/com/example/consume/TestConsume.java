package com.example.consume;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.model.Student;

public class TestConsume {

	static RestTemplate restTemplate = new RestTemplate();
    
	public static void main(String[] args) {
		
		System.out.println("\n\n=============== Added Student Consume=======================");
		add("http://localhost:8777/data/add");
		
		System.out.println("\n\n=============== Get All Student Consume=======================");
		getAll("http://localhost:8777/data/gets");
		
		System.out.println("\n\n=============== Get Student Consume=======================");
		get("http://localhost:8777/data/get/1");
	}

	private static void add(String uri)
	{
	  
		try {
			 Student stud = new Student(100,"Added by Consume");
			    ResponseEntity<Student> result =restTemplate.postForEntity(uri, stud, Student.class);
			    System.out.println(result.getStatusCodeValue());
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
	   
	}
	
	private static void getAll(String uri) 
	{
		String result = restTemplate.getForObject(uri,String.class);
	    System.out.println("Get All "+result);
		
	}
	private static void get(String uri) 
	{
		String result = restTemplate.getForObject(uri,String.class);
	    System.out.println("Get Single "+result);
		
	}
}
