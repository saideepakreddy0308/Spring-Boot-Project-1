package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        // how do I now convert my Entity now to DTO
//        return new EmployeeDTO(EmployeeEntity.getId(), employeeEntity.getName(), employeeEntity.getEmail());
//        this is how we can map the Entity to DTO, but this is not optimized
        // Model mapper can be used here, so now the code after the above is
        ModelMapper mapper = new ModelMapper();
        mapper.map(employeeEntity, EmployeeDTO.class);

    }

    public List<EmployeeDTO > getAllEmployees() {
        // now here also we need to create an instance of model mapper, so this is not the spring way of doing.
        // so we define bean of model mapper, in the AppConfig to ease the process.
        return employeeRepository.findAll();
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }
}
