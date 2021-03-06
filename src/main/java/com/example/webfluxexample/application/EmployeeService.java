package com.example.webfluxexample.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Employee save(Employee employee);

    Flux<Employee> findAll();

    Mono<Employee> findById(Long id);

    Employee findFirst();
}
