package io.github.bluething.spring.bootstrap.templates;

import io.github.bluething.spring.bootstrap.BaseCustomerService;
import io.github.bluething.spring.bootstrap.Customer;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Collection;

public class TransactionTemplateCustomerService extends BaseCustomerService {
    private final TransactionTemplate transactionTemplate;
    protected TransactionTemplateCustomerService(DataSource ds, TransactionTemplate transactionTemplate) {
        super(ds);
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Collection<Customer> save(String... names) {
        return this.transactionTemplate.execute(s -> super.save(names));
    }

    @Override
    public Customer findById(long id) {
        return this.transactionTemplate.execute(s -> super.findById(id));
    }

    @Override
    public Collection<Customer> findAll() {
        return this.transactionTemplate.execute(s -> super.findAll());
    }
}