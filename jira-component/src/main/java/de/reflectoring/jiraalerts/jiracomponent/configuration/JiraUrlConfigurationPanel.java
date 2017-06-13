package de.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Panel to configure the URL of JIRA instance.
 */
public class JiraUrlConfigurationPanel extends GenericPanel<JiraUrlConfigurationPanel> {

    /**
     * https://github.com/pDiller/JiraAlerts/issues/13
     * First implementation allows just one configuration. Servicelayer is implemented to allow more.
     */
    private static final long JIRA_CONNECTION_DATA_ID = 1L;

    @SpringBean
    private JiraConnectionConfigurationService jiraConnectionConfigurationService;

    public JiraUrlConfigurationPanel(String id) {
        super(id);

        Form<Void> connectionUrlForm = new Form<>("connectionUrlForm");

        LoadJiraConnectionUrlModel jiraConnectionUrlModel = new LoadJiraConnectionUrlModel();
        connectionUrlForm.add(new TextField<>("connectionInputField", jiraConnectionUrlModel));
        connectionUrlForm.add(new AjaxSubmitLink("submitNewConnectionUrlLink", connectionUrlForm) {

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                String newConnectionUrl = jiraConnectionUrlModel.getObject();
                jiraConnectionConfigurationService.saveConnectionUrl(JIRA_CONNECTION_DATA_ID, newConnectionUrl);
                if (target != null) {
                    target.add(connectionUrlForm);
                }
            }
        });

        add(connectionUrlForm);
    }

}
