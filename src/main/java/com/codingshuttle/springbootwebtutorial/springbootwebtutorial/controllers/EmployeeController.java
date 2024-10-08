package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/")
//    public String getMySuperSecretMessage(){
//        return "Secret Message: Hello Deepak";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
//        return new EmployeeDTO(id, "Deepak", "deepak@gmail.com", 23, LocalDate.of(2024, 8, 3), true);
//        return employeeRepository.findById(id).orElse(null);
        return employeeService.getEmployeeById(id);
    }

    //    http://localhost:8080/employees?age=23
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "age") Integer inputAge, @RequestParam(required = false) String sortBy) {
//        return "Hi age " + inputAge + "  " + sortBy;
//        return employeeRepository.findAll();
        return employeeService.getAllEmployees();
    }

    // we need to make sure in controller that we are not using EmployeeEntity


    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    // all  the above three methods are confirming to the MVC architecture

    @PutMapping String updateEmployeeByID(){
        return null;
    }

}
