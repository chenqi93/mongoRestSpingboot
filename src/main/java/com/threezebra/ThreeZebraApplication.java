package com.threezebra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;



/**
 * @author vikas.sharma
 *
 */
@SpringBootApplication
public class ThreeZebraApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ThreeZebraApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<ThreeZebraApplication> applicationClass = ThreeZebraApplication.class;

}
