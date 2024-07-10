package test.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.example.demo.exception.ResourceNotFoundException;
import test.example.demo.model.Employee;
import test.example.demo.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") // Allow specific origin for this controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employee
    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //add employee
    @PostMapping("/addemployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //get employee by id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exsit with id:"+id));
        return ResponseEntity.ok(employee);
    }

    //update Employee by Id
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> upDateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exsit with id:"+id));
         employee.setFirstName(employeeDetails.getFirstName());
         employee.setLastName(employeeDetails.getLastName());
         employee.setEmailId(employeeDetails.getEmailId());

         Employee upDateEmployee = employeeRepository.save(employee);
         return ResponseEntity.ok(upDateEmployee);
    }

    //delete Employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable String id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exsit with id:"+id));
        employeeRepository.delete(employee);

        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
