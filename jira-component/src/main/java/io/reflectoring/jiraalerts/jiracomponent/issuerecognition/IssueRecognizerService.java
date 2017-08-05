package io.reflectoring.jiraalerts.jiracomponent.issuerecognition;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class IssueRecognizerService {

	private static final Logger LOGGER = getLogger(IssueRecognizerService.class);

	public void recognize() {
		LOGGER.info("Searching for issues");
	}
}
