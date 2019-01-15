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
		add("http://localhost:8777/data/add");
		
		System.out.println("\n\n=============== Get All Student Consume=======================");
		getAll("http://localhost:8777/data/gets");
		
		System.out.println("\n\n=============== Get Student Consume=======================");
		get("http://localhost:8777/data/get/100");
	}

	private static void add(String uri)
	{
		List<HttpMessageConverter<?>> list=restTemplate.getMessageConverters();
		Iterator<HttpMessageConverter<?>>  it=list.iterator();
		while (it.hasNext())
		{
			HttpMessageConverter<?> httpMessageConverter = (HttpMessageConverter<?>) it.next();
			List<MediaType>  mt=httpMessageConverter.getSupportedMediaTypes();
			for(MediaType m:mt)
			{
				System.out.println("Midea Type   "+m);
			}
			
		}
		
		try {
			 Student stud = new Student(100,"Added by Consume");
			    ResponseEntity<String> result =restTemplate.postForEntity(uri, stud, String.class);
			  
			    System.out.println("Code......."+result.getStatusCodeValue());
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
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
