package dev.soominyeo.web.account;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("dev.soominyeo.web.account")
@EnableJpaRepositories("dev.soominyeo.web.account")
@PropertySource("classpath:db-config.properties")
public class AccountConfiguration {
    protected Logger logger;

    public AccountConfiguration() {
        logger = Logger.getLogger(getClass().getName());
    }

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
                .build();
        logger.info("datasource = " + dataSource);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT id FROM ACCOUNT");
        logger.info("System has " + accounts.size() + " accounts");

        return dataSource;
    }
}
