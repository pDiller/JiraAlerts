package io.reflectoring.jiraalerts.shared.wickettests;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

	@Bean
	public WebApplication wicketApplication() {
		return new TestApplication();
	}

	@Bean
	public WicketTester wicketTester() {
		return new WicketTester(wicketApplication());
	}
}
