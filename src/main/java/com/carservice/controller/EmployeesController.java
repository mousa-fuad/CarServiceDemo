package com.carservice.controller;

import com.carservice.model.Employee;
import com.carservice.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/employees"})
public class EmployeesController {
    @Autowired
    private EmployeesService service;

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        service.create(employee);
        return service.findById(employee.getId());
    }

    @PutMapping(value = "/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee) {
        Employee record = service.findById(id);
        service.create(employee);
        return record;
    }

    @DeleteMapping(path = {"/{id}"})
    public Employee delete(@PathVariable("id") int id) {
        Employee employee = service.findById(id);
        service.delete(id);
        return employee;
    }
}
