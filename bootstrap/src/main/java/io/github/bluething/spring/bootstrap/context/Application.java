package io.github.bluething.spring.bootstrap.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataSourceConfiguration.class)
public class Application {

}
