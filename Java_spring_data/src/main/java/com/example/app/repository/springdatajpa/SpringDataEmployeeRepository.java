package com.example.app.repository.springdatajpa;

import java.util.Collection;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.app.model.Employee;
import com.example.app.repository.EmployeeRepository;

@Profile("spring-data-jpa")
public interface SpringDataEmployeeRepository extends EmployeeRepository, Repository<Employee, Integer> {

    @Override
    @Query("SELECT employee FROM Employee employee WHERE employee.firstName LIKE :firstName% order by employee.firstName")
    public Collection<Employee> findByFirstName(@Param("firstName") String firstName);
    
    @Override
    @Query("SELECT employee FROM Employee employee WHERE employee.lastName LIKE :lastName% order by employee.lastName")
    public Collection<Employee> findByLastName(@Param("lastName") String lastName);
}
