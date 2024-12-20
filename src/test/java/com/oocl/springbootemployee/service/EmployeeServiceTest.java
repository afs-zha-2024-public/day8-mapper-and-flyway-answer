package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.EmployeeAgeNotValidException;
import com.oocl.springbootemployee.exception.EmployeeAgeSalaryNotMatchedException;
import com.oocl.springbootemployee.exception.EmployeeInactiveException;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        when(mockedEmployeeRepository.findAll()).thenReturn(List.of(new Employee("Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.findAll();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_saved_employee_when_save_given_a_employee() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        Employee lucy = new Employee("Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.save(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee savedEmployee = employeeService.create(lucy);

        //then
        assertEquals("Lucy", savedEmployee.getName());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_save_given_a_employee_with_age_17() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        Employee kitty = new Employee("Kitty", 6, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeRepository, never()).save(any());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_save_given_a_employee_with_age_66() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        Employee kitty = new Employee("Kitty", 66, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeRepository, never()).save(any());
    }

    @Test
    void should_saved_employee_active_when_save_employee() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee lucy = new Employee("Lucy", 18, Gender.FEMALE, 8000.0);
        //when
        employeeService.create(lucy);
        /* then */
        verify(mockedEmployeeRepository).save(argThat(Employee::getActive));
    }

    @Test
    void should_throw_EmployeeAgeSalaryNotMatchedException_when_save_given_a_employee_with_age_over_30_and_salary_below_20K() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        Employee bob = new Employee("Bob", 31, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        //when
        //then
        assertThrows(EmployeeAgeSalaryNotMatchedException.class, () -> employeeService.create(bob));
        verify(mockedEmployeeRepository, never()).save(any());
    }

    @Test
    void should_throw_EmployeeInactiveException_when_update_inactive_employee() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        Employee inactiveEmployee = new Employee("Bob", 31, Gender.FEMALE, 8000.0);
        inactiveEmployee.setActive(false);
        when(mockedEmployeeRepository.findById(1)).thenReturn(Optional.of(inactiveEmployee));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        //when
        //then
        assertThrows(EmployeeInactiveException.class, () -> employeeService.update(1, inactiveEmployee));
        verify(mockedEmployeeRepository, never()).save(any());
    }
}
