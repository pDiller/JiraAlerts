package de.reflectoring.jiraalerts.jiracomponent.configuration;

import java.io.Serializable;

/**
 * Configuration-Object for Jira-Conntection
 */
public class JiraConnectionConfiguration implements Serializable{

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
