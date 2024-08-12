package com.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
