package com.example.hw2_7.service;


import com.example.hw2_7.exception.EmployeeAlreadyAddedException;
import com.example.hw2_7.exception.EmployeeNotFoundException;
import com.example.hw2_7.exception.EmployeeStorageIsFullException;
import com.example.hw2_7.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private static int LIMIT = 10;
    private static final Map<String, Employee> employees = new HashMap<>();


    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put (key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee findEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public Employee removeEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public static List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private String getKey(String name, String surname) {
        return name + "|" + surname;
    }


}
