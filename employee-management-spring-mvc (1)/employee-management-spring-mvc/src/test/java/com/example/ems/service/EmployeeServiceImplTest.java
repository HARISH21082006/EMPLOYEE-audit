package com.example.ems.service;

import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {
    private EmployeeRepository repository;
    private EmployeeServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    void registerEmployeeSavesEmployee() {
        Employee employee = new Employee(null, "Asha Kumar", "asha@example.com", "IT", 45000.0);
        service.registerEmployee(employee);
        verify(repository, times(1)).save(employee);
    }

    @Test
    void registerEmployeeRejectsNull() {
        IllegalArgumentException error = assertThrows(
            IllegalArgumentException.class, () -> service.registerEmployee(null));
        assertEquals("Employee information is required.", error.getMessage());
        verifyNoInteractions(repository);
    }

    @Test
    void getAllEmployeesReturnsRepositoryResults() {
        Employee employee = new Employee(1L, "Asha Kumar", "asha@example.com", "IT", 45000.0);
        when(repository.findAll()).thenReturn(Arrays.asList(employee));
        assertEquals(1, service.getAllEmployees().size());
        assertEquals("Asha Kumar", service.getAllEmployees().get(0).getName());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void getAllEmployeesCanReturnEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.getAllEmployees().isEmpty());
    }
}
