package io.github.bluething.spring.bootstrap;

import io.github.bluething.spring.bootstrap.harcoded.DevelopmentOnlyCustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Log4j2
public class Demo {
    public static void workWithCustomerService(Class<?> label, DevelopmentOnlyCustomerService customerService) {
        log.info(" ======");
        log.info(label.getName());
        log.info("======");

        Stream.of("A", "B", "C").map(customerService::save)
                .forEach(customers -> log.info("saved " + customers.toString()));

        customerService.findAll().forEach(customer -> {
            Long customerId = customer.getId();
            Customer byId = customerService.findById(customerId);
            Assert.notNull(byId, "the resulting customer should not be null");
            Assert.isTrue(byId.equals(customer), "we should be able to query for this result");
        });
    }
}