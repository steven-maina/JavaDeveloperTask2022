package com.stevenmaina.JavaDeveloperTask2022.service;

import com.stevenmaina.JavaDeveloperTask2022.exception.BadRequestException;
import com.stevenmaina.JavaDeveloperTask2022.exception.EmployeeNotFoundException;
import com.stevenmaina.JavaDeveloperTask2022.model.Employee;
import com.stevenmaina.JavaDeveloperTask2022.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        Boolean existsEmail = employeeRepository
                .selectExistsEmail(employee.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + employee.getEmail() + "Already Taken");
        }

        employeeRepository.save(employee);
            }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not exist with id: " + employeeId));

        employeeRepository.delete(employee);
    }
public Employee updateEmployee(Long employeeId){
        Employee employeeDetails = new Employee();
    Employee updateEmployee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee not exist with id: " + employeeId));

    updateEmployee.setFirstName(employeeDetails.getFirstName());
    updateEmployee.setLastName(employeeDetails.getLastName());
    updateEmployee.setEmail(employeeDetails.getEmail());
    employeeRepository.save(updateEmployee);

    return updateEmployee;
}
public List<Employee> getEmployee(Long employeeId){
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee not exist with id:" + employeeId));
    return Collections.singletonList(employee);
}
}
