package com.example.employee_p3.controller;

import com.example.employee_p3.entity.Employee;
import com.example.employee_p3.repository.EmployeeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add Employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    // Show Employee
    @GetMapping
    public Iterable<Employee> showEmployee()
    {
        return employeeRepository.findAll();
    }

    // Find by Department
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable String department)
    {
        return employeeRepository.findByDepartment(department);
    }

    // Count by Department
    @GetMapping("/count/{department}")
    public long countDepartment(@PathVariable String department)
    {
        return employeeRepository.countByDepartment(department);
    }

    // Salary Greater Than
    @GetMapping("/salaryabove")
    public List<Employee> salaryAbove(@RequestParam double salary)
    {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    // Salary Range
    @GetMapping("/salaryrange")
    public List<Employee> salaryRange(@RequestParam double min,@RequestParam double max)
    {
        return employeeRepository.findBySalaryRange(min,max);
    }

    // Update Salary
    @PutMapping("/{empId}")
    public Employee updateSalary(@PathVariable Integer empId,@RequestBody Employee employee)
    {
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    // Delete Employee
    @DeleteMapping("/{empId}")
    public String deleteEmployee(@PathVariable Integer empId)
    {
        employeeRepository.deleteById(empId);
        return "Employee Deleted Successfully";
    }
}
