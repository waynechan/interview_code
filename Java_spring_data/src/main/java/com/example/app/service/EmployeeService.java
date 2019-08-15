package com.example.app.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.example.app.model.Employee;

public interface EmployeeService {

	Employee findEmployeeById(int id) throws DataAccessException;
	Collection<Employee> findAllEmployees() throws DataAccessException;
	void saveEmployee(Employee employee) throws DataAccessException;
	void deleteEmployee(Employee employee) throws DataAccessException;
	Collection<Employee> findEmployeeByFirstName(String firstName) throws DataAccessException;
	Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException;
}
