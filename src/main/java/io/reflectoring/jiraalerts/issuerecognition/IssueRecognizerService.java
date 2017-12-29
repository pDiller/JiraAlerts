package io.reflectoring.jiraalerts.issuerecognition;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * Recognizes new issues.
 */
@Service
public class IssueRecognizerService {

	private static final Logger LOGGER = getLogger(IssueRecognizerService.class);

	/**
	 * Asks the interface for new issues.
	 */
	public void recognize() {
		LOGGER.info("Searching for issues");
		StopWatch issueSearchStopWatch = new StopWatch();
		issueSearchStopWatch.start();

		// TODO Search for Issues
		List<Object> issues = Lists.newArrayList();

		issueSearchStopWatch.stop();
		String formatedDuration = formatDuration(issueSearchStopWatch.getTime(), "HH:mm:ss,SSS");
		LOGGER.info("found {} issue(s) in {}.", issues.size(), formatedDuration);
	}
}
