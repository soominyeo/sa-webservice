package dev.soominyeo.web.services.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class RegistrationServer {
    public static final String REGISTRATION_SERVER_HOSTNAME = "registration.server.hostname";

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "registration-server");
        SpringApplication.run(RegistrationServer.class, args);
    }
}
