package com.example.app.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.example.app.model.Employee;

public interface EmployeeRepository {

	Collection<Employee> findByFirstName(String firstName) throws DataAccessException;
	
    Collection<Employee> findByLastName(String lastName) throws DataAccessException;

    Employee findById(int id) throws DataAccessException;

    void save(Employee employee) throws DataAccessException;
    
	Collection<Employee> findAll() throws DataAccessException;
	

	void delete(Employee employee) throws DataAccessException;


}
