package de.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Model to show the configured JIRA-Connection-URL.
 */
public class LoadJiraConnectionUrlModel extends LoadableDetachableModel<String> {

    @SpringBean
    private JiraConnectionConfigurationService jiraConnectionConfigurationService;

    public LoadJiraConnectionUrlModel() {
        Injector.get().inject(this);
    }

    @Override
    protected String load() {
        return jiraConnectionConfigurationService.loadConnectionUrl();
    }
}
