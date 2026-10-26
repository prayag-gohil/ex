package com.example.user.controller;


import com.example.user.entity.Employee;
import com.example.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        Employee saveEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
    }

    /*@GetMapping("/{empId}")
    public ResponseEntity<Employee> showEmployee(@PathVariable Integer empId)
    {
        Employee employee = employeeRepository.findById(empId).orElse(null);
        return ResponseEntity.ok(employee);
    }*/

    @GetMapping("/{empId}")
    public Optional<Employee> showEmployee(@PathVariable Integer empId)
    {
        return employeeRepository.findById(empId);
    }

    @PutMapping("/{empId}")
    public Employee updateEmployee(@PathVariable Integer empId, @RequestBody Employee employee)
    {
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId)
    {
        employeeRepository.deleteById(empId);
        return ResponseEntity.ok("Employee and Passport Deleted Successfully");
    }
}
