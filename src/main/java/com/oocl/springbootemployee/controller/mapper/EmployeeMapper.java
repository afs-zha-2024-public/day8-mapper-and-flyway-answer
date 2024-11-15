package com.oocl.springbootemployee.controller.mapper;

import com.oocl.springbootemployee.controller.dto.EmployeeRequest;
import com.oocl.springbootemployee.controller.dto.EmployeeResponse;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setAge(request.getAge());
        employee.setName(request.getName());
        employee.setGender(Gender.valueOf(request.getGender()));
        employee.setSalary(request.getSalary());
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setAge(employee.getAge());
        response.setName(employee.getName());
        response.setGender(employee.getGender().name());
        return response;
    }

    public List<EmployeeResponse> toResponses(List<Employee> employees) {
        return employees.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
