package com.example.webfluxexample.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

}
