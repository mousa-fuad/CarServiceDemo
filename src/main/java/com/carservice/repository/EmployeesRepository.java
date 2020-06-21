package com.carservice.repository;

import com.carservice.model.Car;
import com.carservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

}
