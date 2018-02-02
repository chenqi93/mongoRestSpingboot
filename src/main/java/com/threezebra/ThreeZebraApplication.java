package com.threezebra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   Logger log=LoggerFactory.getLogger(ThreeZebraApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ThreeZebraApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<ThreeZebraApplication> applicationClass = ThreeZebraApplication.class;

}
