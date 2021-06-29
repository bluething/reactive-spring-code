package io.github.bluething.spring.bootstrap.basiccdi;

import io.github.bluething.spring.bootstrap.CustomerService;
import io.github.bluething.spring.bootstrap.DataSourceUtils;
import io.github.bluething.spring.bootstrap.Demo;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class Application {
    public static void main(String[] args) {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();

        //we can change datasource implementation via constructor
        DataSource initializeDataSource = DataSourceUtils.initializeDdl(dataSource);
        CustomerService customerService = new DataSourceCustomerService(initializeDataSource);
        Demo.workWithCustomerService(Application.class, customerService);
    }
}
