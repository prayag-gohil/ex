package com.example.employee_p5.controller;

import com.example.employee_p5.entity.Department;
import com.example.employee_p5.entity.Employee;
import com.example.employee_p5.repository.DepartmentRepository;
import com.example.employee_p5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Department> showDepartment(@RequestBody Department department) {

        //for (class name/DataType variable name/object : collection)
        for (Employee employee : department.getEmployees()) {
            employee.setDepartment(department);
        }

        Department savedDepartment = departmentRepository.save(department);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id)
    {
        return departmentRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable Integer id)
    {
        Department department = departmentRepository.findById(id).orElse(null);
        return department.getEmployees();
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id,@RequestBody Department department)
    {
        Department oldDepartment = departmentRepository.findById(id).orElse(null);
        oldDepartment.setDeptName(department.getDeptName());
        return departmentRepository.save(oldDepartment);
    }

    @PutMapping("/{deptId}/employees/{empId}")
    public Employee updateEmployee(@PathVariable Integer deptId,
                                   @PathVariable Integer empId,
                                   @RequestBody Employee employee)
    {
        Department department = departmentRepository.findById(deptId).orElse(null);

        Employee oldEmployee = employeeRepository.findById(empId).orElse(null);

        oldEmployee.setEmpName(employee.getEmpName());
        oldEmployee.setSalary(employee.getSalary());
        oldEmployee.setDepartment(department);

        return employeeRepository.save(oldEmployee);
    }

    @DeleteMapping("/{deptId}/employees/{empId}")
    public String deleteEmployee(@PathVariable Integer deptId,@PathVariable Integer empId)
    {
        employeeRepository.deleteById(empId);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Integer id)
    {
        departmentRepository.deleteById(id);
        return "Department Deleted Successfully";
    }

}
