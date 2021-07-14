package io.github.bluething.spring.bootstrap.enable;

import io.github.bluething.spring.bootstrap.BaseCustomerService;
import io.github.bluething.spring.bootstrap.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collection;

@Service
@Transactional
public class TransactionalCustomerService extends BaseCustomerService {
    protected TransactionalCustomerService(DataSource ds) {
        super(ds);
    }

    @Override
    public Collection<Customer> save(String... names) {
        return super.save(names);
    }

    @Override
    public Customer findById(long id) {
        return super.findById(id);
    }

    @Override
    public Collection<Customer> findAll() {
        return super.findAll();
    }
}
