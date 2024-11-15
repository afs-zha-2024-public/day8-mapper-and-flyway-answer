package com.oocl.springbootemployee.controller.mapper;

import com.oocl.springbootemployee.controller.dto.CompanyResponse;
import com.oocl.springbootemployee.controller.dto.EmployeeResponse;
import com.oocl.springbootemployee.model.Company;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    EmployeeMapper employeeMapper;

    public CompanyMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public CompanyResponse toResponse(Company company) {
        List<EmployeeResponse> employeeResponses = company.getEmployees()
            .stream()
            .map(employee -> employeeMapper.toResponse(employee))
            .collect(Collectors.toList());
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setName(company.getName());
        companyResponse.setEmployees(employeeResponses);
        return companyResponse;
    }

    public List<CompanyResponse> toResponses(List<Company> companies) {
        return companies.stream()
            .map(this::toResponse)
            .toList();
    }
}
