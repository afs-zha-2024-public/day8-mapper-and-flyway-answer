package com.oocl.springbootemployee.controller.dto;

import java.util.List;

public class CompanyResponse {
    private String name;
    private List<EmployeeResponse> employees;

    public CompanyResponse() {
    }

    public CompanyResponse(String name, List<EmployeeResponse> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
