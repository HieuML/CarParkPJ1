package com.example.car_park.service;

import com.example.car_park.Exception.ResourceNotFoundException;
import com.example.car_park.entities.Employee;
import com.example.car_park.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Map<String, Object> getAllEmployeeService(String address, String department,
                                                     String name, Date birthday, String phone, int page, int size) {



        List<Employee> employees;

        Order order = new Order(Sort.Direction.ASC,"id");
        Pageable pagingSort = PageRequest.of(page,size,Sort.by(order));
        Page<Employee> pageEmpls;


        if(address != null) {
            pageEmpls = employeeRepository.findByAddressContaining(address,pagingSort);

        }
        else if(department != null) {
            pageEmpls =employeeRepository.findByDepartment(department,pagingSort);

        }
        else if(name != null) {
            pageEmpls = employeeRepository.findByNameLike(name,pagingSort);

        }
        else if(birthday != null) {
            pageEmpls = employeeRepository.findByBirthday(birthday,pagingSort);

        }
        else if(phone != null) {
            pageEmpls = employeeRepository.findByPhone(phone,pagingSort);

        }
        else
        {
            pageEmpls = employeeRepository.findAll(pagingSort);

        }
        employees = pageEmpls.getContent();
        if(employees.isEmpty()) {
            throw new ResourceNotFoundException("No matches");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employees);
        response.put("currentPage", pageEmpls.getNumber());
        response.put("totalIteams", pageEmpls.getTotalElements());
        response.put("totalPages", pageEmpls.getTotalPages());

        return response;

    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public  void createEmployeeService(Employee employee) {
        employeeRepository.save(new Employee(employee.getAccount(), employee.getDepartment(),employee.getAddress(),
                employee.getBirthday(),employee.getEmail(), employee.getName(),employee.getPhone(),
                employee.getPassword(), employee.getSex()));

    }

    @Override
    public Employee updateEmployeeService(int id, Employee employee) {

        Optional<Employee> employeeData = employeeRepository.findById(id);

        if(employeeData.isPresent()) {
            Employee _employee = employeeData.get();
            _employee.setAccount(employee.getAccount());
            _employee.setDepartment(employee.getDepartment());
            _employee.setAddress(employee.getAddress());
            _employee.setBirthday(employee.getBirthday());
            _employee.setEmail(employee.getEmail());
            _employee.setName(employee.getName());
            _employee.setPhone(employee.getPhone());
            _employee.setPassword(employee.getPassword());
            _employee.setSex(employee.getSex());

            return employeeRepository.save(_employee);
        }
        else {
            throw new ResourceNotFoundException("Không có nhân viên có id là: " + id );
        }
    }

    @Override
    public void deleteEmployeeService(int id) {

        Optional<Employee> employeeData = employeeRepository.findById(id);
        if(employeeData.isPresent()) {
            employeeRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Không có nhân viên có id là " + id);
        }
    }


}
