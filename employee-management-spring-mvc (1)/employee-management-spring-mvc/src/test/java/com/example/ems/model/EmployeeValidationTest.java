package com.example.ems.model;

import java.util.Set;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidationTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void createValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        factory.close();
    }

    @Test
    void validEmployeeHasNoConstraintViolations() {
        Employee employee = new Employee(null, "Asha Kumar", "asha@example.com", "IT", 45000.0);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidEmployeeHasConstraintViolations() {
        Employee employee = new Employee(null, "", "not-an-email", "", 0.0);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertTrue(violations.size() >= 4);
    }
}
