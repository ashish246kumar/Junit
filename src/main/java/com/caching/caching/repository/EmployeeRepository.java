package com.caching.caching.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caching.caching.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);

}
