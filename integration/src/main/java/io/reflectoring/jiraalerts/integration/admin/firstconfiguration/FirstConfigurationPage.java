package io.reflectoring.jiraalerts.integration.admin.firstconfiguration;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.FirstConfigurationPanel;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTO;
import org.apache.wicket.model.Model;

/**
 * Page for the first configuration for JiraAlerts.
 */
public class FirstConfigurationPage extends BasePage {

    public FirstConfigurationPage(){
        Model<JiraConnectionDataDTO> jiraConnectionDataDTOModel = new Model<>();
        add(new FirstConfigurationPanel("firstConfigurationPanel", jiraConnectionDataDTOModel));
    }
}
