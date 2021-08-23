package io.github.bluething.spring.reactive.joshbook.bootstrap.templates;

import io.github.bluething.spring.reactive.joshbook.bootstrap.DataSourceUtils;
import io.github.bluething.spring.reactive.joshbook.bootstrap.Demo;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public class Application {
    public static void main(String[] args) {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).build();
        DataSource initializeDataSource = DataSourceUtils.initializeDdl(dataSource);
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(initializeDataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        TransactionTemplateCustomerService customerService = new TransactionTemplateCustomerService(initializeDataSource, transactionTemplate);

        Demo.workWithCustomerService(Application.class, customerService);
    }
}
