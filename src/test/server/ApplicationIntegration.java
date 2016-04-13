package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
public class ApplicationIntegration {

    private static ConfigurableApplicationContext run;

    public static String SERVERPORT = "9898";

     public static void main(String[] args) {
         System.setProperty("server.port", "9898");
         System.setProperty("spring.datasource.url", "jdbc:hsqldb:mem:testdb");
         System.setProperty("spring.datasource.username", "");
         System.setProperty("spring.datasource.password", "");
         System.setProperty("spring.jpa.database", "hsql");
         System.setProperty("spring.jpa.show-sql", "true");
         System.setProperty("spring.jpa.hibernate.ddl-auto", "create");
         run = SpringApplication.run(ApplicationIntegration.class, args);
     }

    public static void exit(int code) {
        SpringApplication.exit(run, () -> code);
    }
}
