package de.reflectoring.jiraalerts.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Spring entrypoint for loading the @Configuration marked class.
 */
public class JiraAlertsWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(JiraAlertsConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
