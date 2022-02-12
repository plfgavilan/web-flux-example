package com.example.webfluxexample.application;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> repository = Arrays.asList(
            Employee.builder().id(1L).name("name1").build(),
            Employee.builder().id(2L).name("name2").build(),
            Employee.builder().id(3L).name("name3").build()
    );

    @Override
    public Mono<Employee> save(Employee employee) {
        employee.setId(repository.size() + 1L);
        this.repository.add(employee);
        return Mono.just(employee).delayElement(Duration.ofSeconds(1));
    }

    @Override
    public Flux<Employee> findAll() {
        return Flux.fromIterable(repository).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Employee> findById(Long id) {
        return Mono.just(getEmployeeById(id)).delayElement(Duration.ofSeconds(1));
    }

    private Employee getEmployeeById(Long id) {
        return repository.stream()
                .filter(employee -> Objects.equals(employee.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
