package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDate dateOfJoining;

    // can have different name too
    @JsonProperty("isActive")
    private Boolean isActive;

    // getters and setters get confused with serialization, as JsonProperty is being used [ active, Isactive ], we define using lombok only
}
