package com.begin;


import com.begin.ExeclService.FirstServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by mozs on 17-8-30.
 */
@SpringBootApplication
//@EnableCaching
//@PropertySource("application.properties")
//@EnableAutoConfiguration
//@RestController
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = "com.begin.Repository")
//@EnableJpaRepositories(basePackages = "com.begin.Repository")

public class Application implements ApplicationRunner{

    public static void main(String[] args) throws Exception {
        SpringApplication sapp = new  SpringApplication(Application.class);
        ConfigurableApplicationContext context = sapp.run(args);
        ConfigurableEnvironment environment = context.getEnvironment();
        FirstServer firstserver = context.getBean(FirstServer.class);
        String path="1111";
        firstserver.start2(environment.getActiveProfiles(),path);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
