package ExcelServer00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by mozs on 17-8-30.
 */
//@PropertySource("application.properties")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication sapp = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = sapp.run(args);
//        SpringApplication.run(Application.class, args);
        FirstServer bean = context.getBean(FirstServer.class);
        bean.start();
    }
}
