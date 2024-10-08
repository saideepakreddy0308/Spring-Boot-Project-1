package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
//        return new EmployeeDTO(id, "Deepak", "deepak@gmail.com", 23, LocalDate.of(2024, 8, 3), true);
//        return employeeRepository.findById(id).orElse(null);
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(id);
        // if employeeDTO is null, as said in employeeService
        // we have two methods  notFound and ok
//        if (employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }  // we get correct status code 404, if not found, rather than 500

    //    http://localhost:8080/employees?age=23
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "age") Integer inputAge, @RequestParam(required = false) String sortBy) {
//        return "Hi age " + inputAge + "  " + sortBy;
//        return employeeRepository.findAll();
        return employeeService.getAllEmployees();
    }

    // we need to make sure in controller that we are not using EmployeeEntity


    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    // all  the above three methods are confirming to the MVC architecture

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeByID(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return employeeService.updateEmployeeById(employeeId, employeeDTO);  // here, passing the employeeDTO to particular employeeId
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployeeByID(@PathVariable Long employeeId) {   // no need to return anything, void or boolean is best
        employeeService.deleteEmployeeById(employeeId);  // no need of returning anything
    }

    @PatchMapping(path = "/{employeeId}")// we cant go for employeeDTO as input, as we really dont know the fields to update.
    // so we instead use a map of string to object
    public EmployeeDTO updatePartialEmployeeByID (@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, updates);  // here, passing the updates
        }
    }