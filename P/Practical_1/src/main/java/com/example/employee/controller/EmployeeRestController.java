package com.example.employee.controller;

import java.util.Optional;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Post API - Add Employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    //Get API - Display all employee
    @GetMapping
    public Iterable<Employee> showEmployee()
    {
        return employeeRepository.findAll();
    }

    //Get API - Display Employee By ID
    @GetMapping("/{empId}")
    public Optional<Employee> showEmployeeId(@PathVariable Integer empId)
    {
        return employeeRepository.findById(empId);
    }

    //Put API - Update Employee
    @PutMapping("/{empId}")
    public Employee updateEmployee(@PathVariable Integer empId,@RequestBody Employee employee)
    {
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    //Delete API - Delete Employee
    @DeleteMapping("/{empId}")
    public String deleteEmployee(@PathVariable Integer empId)
    {
        employeeRepository.deleteById(empId);
        return "Employee Deleted Successfully";
    }

}
