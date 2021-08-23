package io.github.bluething.spring.reactive.joshbook.bootstrap.scan;

import io.github.bluething.spring.reactive.joshbook.bootstrap.templates.TransactionTemplateCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Service
public class DiscoveredCustomerService extends TransactionTemplateCustomerService {
    DiscoveredCustomerService(DataSource ds, TransactionTemplate transactionTemplate) {
        super(ds, transactionTemplate);
    }
}
