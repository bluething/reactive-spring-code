package io.github.bluething.spring.reactive.joshbook.bootstrap;

import java.util.Collection;

public interface CustomerService {
    Collection<Customer> save(String... names);
    Customer findById(long id);
    Collection<Customer> findAll();
}
