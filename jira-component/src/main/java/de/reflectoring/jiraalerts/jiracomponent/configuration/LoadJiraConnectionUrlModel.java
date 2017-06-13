package de.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Model to show the configured JIRA-Connection-URL.
 */
public class LoadJiraConnectionUrlModel extends LoadableDetachableModel<String> {

    /**
     * https://github.com/pDiller/JiraAlerts/issues/13
     * First implementation allows just one configuration. Servicelayer is implemented to allow more.
     */
    private static final long JIRA_CONNECTION_DATA_ID = 1L;

    @SpringBean
    private JiraConnectionConfigurationService jiraConnectionConfigurationService;

    public LoadJiraConnectionUrlModel() {
        Injector.get().inject(this);
    }

    @Override
    protected String load() {
        return jiraConnectionConfigurationService.loadConnectionUrl(JIRA_CONNECTION_DATA_ID);
    }
}
