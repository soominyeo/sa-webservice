package dev.soominyeo.web.services.account;

import dev.soominyeo.web.account.AccountConfiguration;
import dev.soominyeo.web.account.AccountRepository;
import dev.soominyeo.web.services.registration.RegistrationServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AccountConfiguration.class)
public class AccountServer {
    @Autowired
    AccountRepository accountRepository;

    protected Logger logger = Logger.getLogger(AccountServer.class.getName());

    public static void main(String[] args) {
        if (System.getProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME) == null)
            System.setProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME, "localhost");

        System.setProperty("spring.config.name", "account-server");

        SpringApplication.run(AccountServer.class, args);
    }
}
