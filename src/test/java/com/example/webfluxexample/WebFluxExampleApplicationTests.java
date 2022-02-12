package com.example.webfluxexample;

import com.example.webfluxexample.application.Employee;
import com.example.webfluxexample.application.EmployeeController;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Log4j2
class WebFluxExampleApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    void contextLoads() {
        assertTrue(true, "Application context start successfully");
    }

    @Test
    void testGetEmployees() {
        Flux<Employee> allEmployees = employeeController.getAllEmployees();
        StepVerifier
                .create(allEmployees)
                .assertNext(account -> {
                    assertThat(account).isNotNull();
                    assertThat(account.getName()).isEqualTo("name1");
                })
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

}
