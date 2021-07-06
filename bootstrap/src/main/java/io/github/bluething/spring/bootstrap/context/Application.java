package io.github.bluething.spring.bootstrap.context;

import io.github.bluething.spring.bootstrap.CustomerService;
import io.github.bluething.spring.bootstrap.DataSourceConfiguration;
import io.github.bluething.spring.bootstrap.Demo;
import io.github.bluething.spring.bootstrap.SpringUtils;
import io.github.bluething.spring.bootstrap.templates.TransactionTemplateCustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfiguration.class)
public class Application {
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    TransactionTemplateCustomerService transactionTemplateCustomerService(DataSource dataSource, TransactionTemplate transactionTemplate) {
        return new TransactionTemplateCustomerService(dataSource, transactionTemplate);
    }

    @Bean
    TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringUtils.run(Application.class, "prod");

        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        Demo.workWithCustomerService(Application.class, customerService);
    }

}
