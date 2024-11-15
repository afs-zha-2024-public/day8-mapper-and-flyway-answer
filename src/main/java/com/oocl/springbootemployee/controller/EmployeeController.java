package com.oocl.springbootemployee.controller;


import com.oocl.springbootemployee.controller.dto.EmployeeRequest;
import com.oocl.springbootemployee.controller.dto.EmployeeResponse;
import com.oocl.springbootemployee.controller.mapper.EmployeeMapper;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.service.EmployeeService;
import java.util.List;
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
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployeeList() {
        return employeeMapper.toResponses(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.findById(id));
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeesByGender(@RequestParam Gender gender) {
        return employeeMapper.toResponses(employeeService.findAll(gender));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employee) {
        return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employee)));
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employee) {
        return employeeMapper.toResponse(employeeService.update(id, employeeMapper.toEntity(employee)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployee(@PathVariable Integer id) {
       employeeService.delete(id);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeResponse> getAllByPageSize(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employeeMapper.toResponses(employeeService.findAll(pageIndex, pageSize).toList());
    }
}
