package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Student {

	@Id
    @ApiModelProperty(notes = "ID of the Student",name="name",required=true,value="Id name")
	@NotNull
	@PositiveOrZero(message="Id Should be Positive Or Zero")
	int id;

    @ApiModelProperty(notes = "Name of the Student",name="name",required=true,value="test name")
    @Size(min=2,message="Name should max 2 char")
	String name;
	
    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "\nStudent [id=" + id + ", name=" + name + "]";
	}
}
