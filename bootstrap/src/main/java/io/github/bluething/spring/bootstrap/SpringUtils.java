package io.github.bluething.spring.bootstrap;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

public class SpringUtils {
    public static ConfigurableApplicationContext run(Class<?> sources, String profile) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        if (StringUtils.hasText(profile)) {
            applicationContext.getEnvironment().setActiveProfiles(profile);
        }

        return applicationContext;
    }
}
