package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
public class ApplicationIntegration {

     public static void main(String[] args) {
         System.setProperty("spring.datasource.url", "jdbc:hsqldb:mem:testdb");
         System.setProperty("spring.datasource.username", "");
         System.setProperty("spring.datasource.password", "");
         System.setProperty("spring.jpa.database", "hsql");
         System.setProperty("spring.jpa.show-sql", "true");
         System.setProperty("spring.jpa.hibernate.ddl-auto", "create");
         SpringApplication.run(ApplicationIntegration.class, args);
     }
}
