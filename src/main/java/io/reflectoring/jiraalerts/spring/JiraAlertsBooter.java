package io.reflectoring.jiraalerts.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JiraAlertsBooter {

  public static void main(String[] args) {
    SpringApplication.run(JiraAlertsBooter.class, args);
  }
}
