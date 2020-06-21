package com.carservice.service;

import com.carservice.model.Employee;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeesServiceTest {

    @Autowired
    @Mock
    EmployeesService employeesService;
    Employee employee;

    @BeforeClass
    public void init() {
        employee = new Employee();
        employee.setName("testName");
        employeesService = mock(EmployeesService.class);
    }

    @Test
    void findAll() {
        Employee appointment1 = new Employee();
        appointment1.setName("testName1");

        Employee appointment2 = new Employee();
        appointment2.setName("testName2");


        List<Employee> appointments = Arrays.asList(appointment1, appointment2);

        when(employeesService.findAll()).thenReturn(appointments);

        // when
        List<Employee> result = employeesService.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getId())
                .isEqualTo(appointment1.getId());

        assertThat(result.get(1).getId())
                .isEqualTo(appointment2.getId());

    }

    @Test
    void findById() {
        init();
        when(employeesService.create(any(Employee.class))).thenReturn(employee);
        when(employeesService.findById(employee.getId())).thenReturn(employee);

        employeesService.create(employee);

        Employee result = employeesService.findById(employee.getId());

        assertThat(result.getId())
                .isEqualTo(employee.getId());
    }

    @Test
    void create() {
        init();
        when(employeesService.create(any(Employee.class))).thenReturn(employee);
        when(employeesService.findById(employee.getId())).thenReturn(employee);

        employeesService.create(employee);

        Employee result = employeesService.findById(employee.getId());

        assertThat(result.getId())
                .isEqualTo(employee.getId());
    }

    @Test
    void update() {
        init();
        when(employeesService.update(employee.getId(), employee)).thenReturn(employee);
        when(employeesService.findById(employee.getId())).thenReturn(employee);

        employeesService.update(employee.getId(), employee);

        Employee result = employeesService.findById(employee.getId());

        assertThat(result.getId())
                .isEqualTo(employee.getId());
    }

    @Test
    void delete() {
        init();
        when(employeesService.create(any(Employee.class))).thenReturn(employee);
        when(employeesService.delete(employee.getId())).thenReturn(employee);

        employeesService.create(employee);
        Employee result = employeesService.delete(employee.getId());

        assertThat(result.getId())
                .isEqualTo(employee.getId());
    }
}

