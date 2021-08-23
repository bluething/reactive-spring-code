package io.github.bluething.spring.reactive.joshbook.bootstrap.bootiful;

import io.github.bluething.spring.reactive.joshbook.bootstrap.Customer;
import io.github.bluething.spring.reactive.joshbook.bootstrap.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BootifulRestController {
    private final CustomerService customerService;

    public BootifulRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/csutomers")
    Collection<Customer> get() {
        return this.customerService.findAll();
    }
}
