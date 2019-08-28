package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
@RequestMapping("/employee")
public class EmployeeRescource {

	private List<Employee> employees = new ArrayList<Employee>();

	@GetMapping(path = "")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAll() {
		return employees;
	}

	@GetMapping(path = "{id}")

	public ResponseEntity<Employee> getOne(@PathVariable int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody Employee employee) {
		employees.add(employee);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id ,@RequestBody Employee inEmployee) {
		for(Employee employee:employees) {
			if(employee.getId() == id) {
				employee.setId(id);
				employee.setName(inEmployee.getName());
				employee.setAge(inEmployee.getAge());
				employee.setGender(inEmployee.getGender());
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id) {
		for(Employee employee:employees) {
			if(employee.getId() == id) {
				employees.remove(employee);
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);		
	}
}
