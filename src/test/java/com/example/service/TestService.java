package com.example.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.dao.StudentDAO;
import com.example.exception.ListEmptyException;
import com.example.exception.NotFoundStudent;
import com.example.model.Student;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class TestService  {

	@InjectMocks
    StudentSER manager;
     
    @Mock
    StudentDAO dao;
 
    List<Student> list;
	
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
    }

    @Test
    public void findAllSt() throws ListEmptyException {
    
    	dao.findAll().forEach(s->System.out.println(s));
    }
    
    @Test(expected =ListEmptyException.class )
    public void findByIdExpTest() throws ListEmptyException {
    	manager.getsStudent().forEach(s->System.out.println(s));
    	
    }
    
    @Test(expected = NotFoundStudent.class)
    public void deletExpTest()
    {
    	manager.deleteStudent(3);
    }
    
    @Test
    public void deletTest()
    {
    //	dao.deleteById(3);
    }
    @Test
    public void createEmployeeTest()
    {
    	
    	Student emp = new Student(10,"Lokesh");
        manager.saveStudent(emp);
        verify(dao, times(1)).save(emp);
    }
}
