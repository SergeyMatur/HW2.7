package com.example.hw2_7.service;


import com.example.hw2_7.exception.EmployeeAlreadyAddedException;
import com.example.hw2_7.exception.EmployeeNotFoundException;
import com.example.hw2_7.exception.EmployeeStorageIsFullException;
import com.example.hw2_7.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static int LIMIT = 10;
    private static final List<Employee> employees = new ArrayList<>();


    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee findEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee removeEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public static List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
