package com.example.webfluxexample.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/first")
    public Employee getFirstEmployee() {
        return employeeService.findFirst();
    }

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        Flux<Employee> all = employeeService.findAll();
        all.subscribe(log::info);
        return all;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

}
