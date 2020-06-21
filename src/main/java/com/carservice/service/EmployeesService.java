package com.carservice.service;

import com.carservice.model.Employee;
import com.carservice.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository repository;


    public List<Employee> findAll() {
        return repository.findAll();
    }


    public Employee findById(int id) {
        return repository.findById(id).get();
    }


    public Employee create(Employee employee) {
        repository.save(employee);
        return repository.findById(employee.getId()).get();
    }


    public Employee update(int id, Employee employee) {
        Employee record = repository.findById(id).get();

        repository.save(employee);
        return record;
    }


    public Employee delete(int id) {
        Employee employee = repository.findById(id).get();
        repository.deleteById(id);
        return employee;
    }
}
