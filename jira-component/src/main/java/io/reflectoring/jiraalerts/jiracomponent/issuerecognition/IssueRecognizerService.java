package io.reflectoring.jiraalerts.jiracomponent.issuerecognition;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class IssueRecognizerService {

	private static final Logger LOGGER = getLogger(IssueRecognizerService.class);

	public void recognize() {
		LOGGER.info("Searching for issues");
		StopWatch issueSearchStopWatch = new StopWatch();
		issueSearchStopWatch.start();

		// TODO Search for Issues
		List<Object> issues = Lists.newArrayList();

		issueSearchStopWatch.stop();
		LOGGER.info("found {} issue(s) in {}.", issues.size(), formatDuration(issueSearchStopWatch.getTime(), "HH:mm:ss,SSS"));
	}
}
