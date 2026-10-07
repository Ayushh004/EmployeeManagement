package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

// @Service tells Spring: "This class contains business logic, register it
// as a Bean." Functionally, @Service behaves the same as @Component, but
// using @Service makes the CODE'S INTENT clear to other developers reading
// it -- this is where business rules and decision-making live.
//
// WHY do we have a Service layer instead of calling the Repository
// directly from the Controller?
// 1. Separation of concerns: Controller should only handle HTTP stuff
//    (requests/responses), not business logic.
// 2. Reusability: If you ever add another entry point (e.g., a batch job,
//    a CLI command, a scheduled task), it can reuse this same Service
//    instead of duplicating logic.
// 3. Testability: It's much easier to unit-test business logic in
//    isolation when it's not tangled with HTTP request/response handling.
// 4. Future-proofing: If business rules grow complex (e.g., "salary
//    cannot be updated by more than 10% at once"), this is exactly
//    where that logic belongs -- NOT in the Controller, NOT in the
//    Repository.
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

   
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long id) {
        
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Employee not found with id: " + id));
    }

    
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        
        Employee existingEmployee = getEmployeeById(id);

        
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        
        return employeeRepository.save(existingEmployee);
    }

    
    public void deleteEmployee(Long id) {
       
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

  
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }
}
