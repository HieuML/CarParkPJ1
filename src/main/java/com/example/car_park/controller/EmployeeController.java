package com.example.car_park.controller;


import com.example.car_park.Exception.PasswordException;
import com.example.car_park.Exception.ResourceNotFoundException;
import com.example.car_park.entities.Employee;
import com.example.car_park.service.EmployeeService;
import com.example.car_park.service.ValidatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")
    public ResponseEntity<Map<String,Object>> getAllEmployees(@RequestParam(required = false) String address,
                                                              @RequestParam(required = false) String department,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) Date birthday,
                                                              @RequestParam(required = false) String phone,
                                                              @RequestParam(defaultValue = "0" ) int page,
                                                              @RequestParam(defaultValue = "3") int size
    ) throws ResourceNotFoundException{


            Map<String,Object> response = employeeService.getAllEmployeeService(address,department,name,birthday,phone,page,size);

            return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {

        Optional<Employee> employeeData = employeeService.getEmployeeById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            throw  new ResourceNotFoundException("Not found Employee with id = " + id);
        }
    }



    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) throws PasswordException {


        ValidatorService.passwordCheck(employee.getPassword());

           employeeService.createEmployeeService(employee);

            return new ResponseEntity<>("Add employee successfully",HttpStatus.OK);


    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id,@Valid @RequestBody Employee employee) throws PasswordException{

        ValidatorService.passwordCheck(employee.getPassword());
        employeeService.updateEmployeeService(id,employee);

            return new ResponseEntity<>("Update seccesfully",HttpStatus.OK);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws ResourceNotFoundException {

            employeeService.deleteEmployeeService(id);

            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }




}
