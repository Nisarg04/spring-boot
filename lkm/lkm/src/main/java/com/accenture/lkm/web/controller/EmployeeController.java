package com.accenture.lkm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.Employee;
import com.accenture.lkm.dao.EmployeeDAO;

@RestController
@RequestMapping(value="emp/controller")
public class EmployeeController {

	@Autowired
	private EmployeeDAO dao;
	
	@RequestMapping(value="getDetails", 
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployeeDetails() {
		List<Employee> empList = new ArrayList<>(dao.getAllEmployees());
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}
	
	@RequestMapping(value="addEmployee", 
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee emp, Errors err) {
		if(err.hasErrors()) {
			return new ResponseEntity<>("Validation failure " + err.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		Integer empId = dao.addEmployee(emp);
		return new ResponseEntity<>("Employee added with id : " + empId, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="getWithId/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer empId) {
		Employee emp = dao.getEmployeeById(empId);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
}
