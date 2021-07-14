package io.github.bluething.spring.bootstrap.enable;

import io.github.bluething.spring.bootstrap.CustomerService;
import io.github.bluething.spring.bootstrap.DataSourceConfiguration;
import io.github.bluething.spring.bootstrap.Demo;
import io.github.bluething.spring.bootstrap.SpringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@Import(DataSourceConfiguration.class)
public class Application {
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringUtils
                .run(Application.class, "prod");
        CustomerService customerService = applicationContext
                .getBean(CustomerService.class);
        Demo.workWithCustomerService(Application.class, customerService);
    }
}
