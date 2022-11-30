package com.example.car_park.service;

import com.example.car_park.entities.Employee;
import com.example.car_park.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface EmployeeService {

    public Map<String, Object> getAllEmployeeService(String address, String department, String name,
                                                     Date birthday, String phone,int page, int size);

    public Optional<Employee> getEmployeeById(int id);

    public void createEmployeeService(Employee employee);

    public Employee updateEmployeeService(int id , Employee employee);

    public void deleteEmployeeService(int id);












}
