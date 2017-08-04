package io.reflectoring.jiraalerts.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "io.reflectoring.jiraalerts" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = { TestConfiguration.class }) })
@EnableJpaRepositories(basePackages = { "io.reflectoring.jiraalerts" })
@EntityScan(basePackages = "io.reflectoring.jiraalerts")
public class PersistenceContextConfiguration {}
