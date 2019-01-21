package com.example.consume;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.model.Student;

public class TestConsume {

	static RestTemplate restTemplate = new RestTemplate();
    
	public static void main(String[] args) {
		
		
		
		System.out.println("\n\n=============== Added Student Consume=======================");
		Student st[]= {new Student(101,"Added by Consume"),
						new Student(102,"Added by Consume"),
						new Student(103,"Added by Consume"),
						new Student(104,"Added by Consume")
					  };
		
		add("http://localhost:8777/data/add",st);
		
		System.out.println("\n\n=============== Get All Student Consume=======================");
		getAll("http://localhost:8777/data/gets");
		
		System.out.println("\n\n=============== Get Student Consume=======================");
		get("http://localhost:8777/data/get/101");
	}

	private static void add(String uri,Student...students )
	{
			for(Student s:students)
			{
				ResponseEntity<String> result1 =restTemplate.postForEntity(uri, s, String.class);
				System.out.println("Code......."+result1.getStatusCodeValue());
			}
	}
	private static void getAll(String uri) 
	{
		List result = restTemplate.getForObject(uri,List.class);
	    System.out.println("Get All "+result);
		
	}
	private static void get(String uri) 
	{
		String result = restTemplate.getForObject(uri,String.class);
	    System.out.println("Get Single "+result);
		
	}
}
