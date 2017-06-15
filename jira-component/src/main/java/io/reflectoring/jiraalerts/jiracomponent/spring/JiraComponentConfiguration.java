package io.reflectoring.jiraalerts.jiracomponent.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "io.reflectoring.jiraalerts.jiracomponent" })
public class JiraComponentConfiguration {}
