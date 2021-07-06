package io.github.bluething.spring.bootstrap;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfiguration {
    @Configuration
    @Profile("prod")
    public static class ProductionConfiguration {

    }
}
