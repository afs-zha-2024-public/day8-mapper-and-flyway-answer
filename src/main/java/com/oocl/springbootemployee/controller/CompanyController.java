package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.controller.dto.CompanyResponse;
import com.oocl.springbootemployee.controller.mapper.CompanyMapper;
import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.service.CompanyService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyMapper.toResponses(companyService.findAll());
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Integer id) {
        return companyMapper.toResponse(companyService.findById(id));
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> getCompaniesByPagination(@RequestParam Integer pageIndex,
                                                  @RequestParam Integer pageSize) {
        return companyMapper.toResponses(companyService.findAll(pageIndex, pageSize).toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody Company company) {
        return companyMapper.toResponse(companyService.create(company));
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        return companyMapper.toResponse(companyService.update(id, company));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCompany(@PathVariable Integer id) {
        companyService.delete(id);
    }
}
