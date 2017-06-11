package de.reflectoring.jiraalerts.jiracomponent.connection.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "JIRA_CONNECTION_DATA")
public class JiraConnectionData {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "MODIFIED_AT")
    private Date modifiedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
