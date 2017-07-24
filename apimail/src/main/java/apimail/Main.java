package apimail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by fefe on 14/6/2017.
 */

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Main{

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
