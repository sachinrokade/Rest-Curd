package com.example.controller;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exception.ListEmptyException;
import com.example.exception.NotFoundStudent;
import com.example.model.Student;
import com.example.service.StudentSER;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/data")
@Api(value = "DataController", description = "REST APIs related to Student Entity useing Spring DATA and Trancation!!!!")
public class DataController  {

	String PATH = "/error";

	@Autowired
	StudentSER ser;

	@PostMapping("/add")
	@ApiOperation(value = "Add Students in the System ", response = Iterable.class, tags = "Add Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 201, message = "Creteded!"), @ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"), @ApiResponse(code = 404, message = "not added!!!") })
	public ResponseEntity<Object> addStudent(@Valid @RequestBody Student student) {
		
		Student s=ser.saveStudent(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(s.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*
	 * if we comment @Transactional then SELECT * FROM STUDENT; ID NAME 1 Recode 1 2
	 * Recode 2 4 Recode 4
	 * 
	 * This recored inserted at time of 3 id get excption and all 3 recode get
	 * commit to Database
	 * 
	 * if we remove used @Transactional then then even 3 recored get excption and
	 * all 3 recode not commit to Database
	 */
	@GetMapping("/add_with_tr")
	@Transactional
	@ApiOperation(value = "To Test Trancation of Students  System ", tags = "Test Trancation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not Falied!!!") })
	public String add_with_tr() {

		List<Student> list = new LinkedList();
		list.add(new Student(1, "Recode 1"));
		list.add(new Student(2, "Recode 2"));
		list.add(new Student(4, "Recode 4"));
		list.add(new Student(3, "Recode 3"));
		list.add(new Student(5, "Recode 5"));

		for (Student s : list) {

			if (s.getId() == 3)
				throw new RuntimeException("Trancation error");
			else {
				ser.saveStudent(s);
			}

		}

		return "SAVES DATA";
	}

	@GetMapping("/gets")
	@ApiOperation(value = "Get  All Students in the System ", response = Iterable.class, tags = "Get All Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public List<Student> getsStudent() throws ListEmptyException {
		return ser.getsStudent();
	}

	@GetMapping("/get/{id}")
	@ApiOperation(value = "Get Students in the System ", response = Iterable.class, tags = "Get Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public Student getsStudent(@PathVariable int id) throws NotFoundStudent {
		return ser.getStudent(id);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Delete Students in the System ", response = Iterable.class, tags = "Delete Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	// public String deleteStudent(@RequestParam int id)
	// public String deleteStudent(@PathParam int id)
	public String deleteStudent(@PathVariable int id) throws NotFoundStudent {
		if (ser.deleteStudent(id))
			return "Deleted Succefully";
		else
			return "Recode not exist";
	}

	@GetMapping("/test")
	public String test() {
		return "it work fine";
	}
}
