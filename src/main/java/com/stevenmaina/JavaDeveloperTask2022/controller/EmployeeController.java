package com.stevenmaina.JavaDeveloperTask2022.controller;

import com.stevenmaina.JavaDeveloperTask2022.exception.EmployeeNotFoundException;
import com.stevenmaina.JavaDeveloperTask2022.model.Employee;
import com.stevenmaina.JavaDeveloperTask2022.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping({"/", "/all"})
    public String getAllEmployees(Model model){
        model.addAttribute("allemplist",employeeService.getAllEmployees());
        return "index";
    }

    // create employee REST API
    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newemployee";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    // get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable  long id){

        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    // update employee REST API
    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable(value = "id") long id,Model model) {
        model.addAttribute("employee", employeeService.updateEmployee(id));
        return "update";
    }

    //delete employee REST API
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
       employeeService.deleteEmployee(id);
       return "redirect:/";

    }
}
