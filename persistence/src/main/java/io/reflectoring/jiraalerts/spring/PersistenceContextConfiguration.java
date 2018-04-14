package io.reflectoring.jiraalerts.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Scans packages for repositories and entities. Enables transactionmanagement.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "io.reflectoring.jiraalerts" })
@EnableJpaRepositories(basePackages = { "io.reflectoring.jiraalerts" })
@EntityScan(basePackages = "io.reflectoring.jiraalerts")
public class PersistenceContextConfiguration {}
