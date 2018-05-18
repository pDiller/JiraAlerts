package io.reflectoring.jiraalerts.testsetup;

import io.reflectoring.jiraalerts.spring.PersistenceContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceContextConfiguration.class})
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class DbUnitTestConfiguration {
}
