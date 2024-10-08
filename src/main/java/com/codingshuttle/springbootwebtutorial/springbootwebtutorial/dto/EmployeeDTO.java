package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min=3, max = 10, message = "Number of characters in name should be in the range: [3,10]")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 10, message = "Salary of Employee cannot be less than 10")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be either USER or ADMIN")
    private Integer role; // ADMIN, USER
    // If some more complex, then we need to have custom annotation

    @NotNull(message = "Salary of Employee should be not null")
    @Positive(message = "Salary of Employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

    // custom annotations if like we need to have input as prime number
    // Department is added here, then the @Valid goes to the department too, and gets its validation before finalizing input
}
