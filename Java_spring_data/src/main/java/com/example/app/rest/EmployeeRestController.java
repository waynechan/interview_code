package com.example.app.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.app.model.Employee;
import com.example.app.service.EmployeeService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/employees")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/search/firstOrLast/{firstOrLast}/prefix/{prefix}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployeesList(@PathVariable("firstOrLast") int firstOrLast, @PathVariable("prefix") String prefix) {
		if (prefix == null) {
			prefix = "";
		}
		
		Collection<Employee> employees = null;
		if (firstOrLast == 1) {
			employees = this.employeeService.findEmployeeByFirstName(prefix);
		} else {
			employees = this.employeeService.findEmployeeByLastName(prefix);
		}
		
		if (employees.isEmpty()) {
			return new ResponseEntity<Collection<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployees() {
		Collection<Employee> employees = this.employeeService.findAllEmployees();
		if (employees.isEmpty()) {
			return new ResponseEntity<Collection<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") int employeeId) {
		Employee employee = null;
		employee = this.employeeService.findEmployeeById(employeeId);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (employee == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Employee>(headers, HttpStatus.BAD_REQUEST);
		}
		this.employeeService.saveEmployee(employee);
		headers.setLocation(ucBuilder.path("/api/employees/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") int employeeId, @RequestBody @Valid Employee employee,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (employee == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Employee>(headers, HttpStatus.BAD_REQUEST);
		}
		Employee currentEmployee = this.employeeService.findEmployeeById(employeeId);
		if (currentEmployee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		currentEmployee.setFirstName(employee.getFirstName());
		currentEmployee.setLastName(employee.getLastName());
		
		this.employeeService.saveEmployee(currentEmployee);
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") int employeeId) {
		Employee employee = this.employeeService.findEmployeeById(employeeId);
		if (employee == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.employeeService.deleteEmployee(employee);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
