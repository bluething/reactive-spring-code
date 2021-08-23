package io.github.bluething.spring.reactive.joshbook.bootstrap.harcoded;

import io.github.bluething.spring.reactive.joshbook.bootstrap.Demo;

public class Application {
    public static void main(String[] args) {
        DevelopmentOnlyCustomerService customerService = new DevelopmentOnlyCustomerService();
        Demo.workWithCustomerService(Application.class, customerService);
    }
}
