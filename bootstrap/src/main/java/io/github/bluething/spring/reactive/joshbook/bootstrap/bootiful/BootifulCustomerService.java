package io.github.bluething.spring.reactive.joshbook.bootstrap.bootiful;

import io.github.bluething.spring.reactive.joshbook.bootstrap.enable.TransactionalCustomerService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class BootifulCustomerService extends TransactionalCustomerService {
    BootifulCustomerService(DataSource ds) {
        super(ds);
    }
}
