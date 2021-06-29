package io.github.bluething.spring.bootstrap;

import java.util.Collection;

public interface CustomerService {
    Collection<Customer> save(String... name);
    Customer findById(long id);
    Collection<Customer> findAll();
}
