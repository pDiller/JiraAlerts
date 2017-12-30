package io.reflectoring.jiraalerts.issuerecognition;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;
import org.junit.Test;

public class IssueRecognizerServiceTest {


    @Test
    public void name() throws Exception {
        JiraRestClientService jiraRestClientService = new JiraRestClientService();
        JiraRestClient jiraRestClient = jiraRestClientService.getJiraRestClient("http://localhost:1337", "diller", "JiraAlerts2018_");

        Promise<SearchResult> searchResultPromise = jiraRestClient.getSearchClient().searchJql("priority = Highest AND createdDate > '2018-12-26 22:16'");
        try {
            SearchResult claim = searchResultPromise.claim();
            for (Issue issue : claim.getIssues()) {
                System.out.println(issue.getCreationDate());
            }
        } catch (RestClientException ex) {
            System.out.println("error!");
        }


    }
}