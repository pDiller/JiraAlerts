package io.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Model to show the configured JIRA-Connection-URL.
 */
public class LoadJiraConnectionUrlModel extends LoadableDetachableModel<String> {

    @SpringBean
    private JiraConnectionConfigurationService jiraConnectionConfigurationService;

    private IModel<Long> jiraConnectionDataIdModel;

    /**
     * Constructor.
     *
     * @param jiraConnectionDataIdModel
     *            The Id for JiraConnectionData to load.
     */
    public LoadJiraConnectionUrlModel(IModel<Long> jiraConnectionDataIdModel) {
        Injector.get().inject(this);
        this.jiraConnectionDataIdModel = jiraConnectionDataIdModel;
    }

    @Override
    protected String load() {
        Long jiraConnectionDataId = jiraConnectionDataIdModel.getObject();
        return jiraConnectionConfigurationService.loadConnectionUrl(jiraConnectionDataId);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        jiraConnectionDataIdModel.detach();
    }
}
