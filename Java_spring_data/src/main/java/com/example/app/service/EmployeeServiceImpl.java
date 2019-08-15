package com.example.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.model.Employee;

import com.example.app.repository.EmployeeRepository;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    
    @Autowired
     public EmployeeServiceImpl(
    		 EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	@Override
	@Transactional(readOnly = true)
	public Collection<Employee> findAllEmployees() throws DataAccessException {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee employee) throws DataAccessException {
		employeeRepository.delete(employee);
	}

	

	@Override
	@Transactional(readOnly = true)
	public Employee findEmployeeById(int id) throws DataAccessException {
		Employee employee = null;
		try {
			employee = employeeRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
		// just ignore not found exceptions for Jdbc/Jpa realization
			return null;
		}
		return employee;
	}
	
	@Override
	@Transactional
	public void saveEmployee(Employee employee) throws DataAccessException {
		employeeRepository.save(employee);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Employee> findEmployeeByFirstName(String firstName) throws DataAccessException {
		return employeeRepository.findByFirstName(firstName);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException {
		return employeeRepository.findByLastName(lastName);
	}
}
