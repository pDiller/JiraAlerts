package de.reflectoring.jiraalerts.jiracomponent.configuration;

import java.net.URISyntaxException;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraUrlConfigurationPanel.class);

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
                jiraConnectionConfigurationService.writeConnectionUrl(newConnectionUrl);

                if(target != null){
                    target.add(connectionUrlForm);
                }
            }
        });

        add(connectionUrlForm);
    }

}
