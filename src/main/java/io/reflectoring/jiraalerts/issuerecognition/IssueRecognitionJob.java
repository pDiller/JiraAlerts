package io.reflectoring.jiraalerts.issuerecognition;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Job that encapsulates the recognition of new issues and the trigger on the reaction.
 */
@Component
public class IssueRecognitionJob extends QuartzJobBean {

	@Autowired
	private IssueRecognizerService issueRecognizerService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		issueRecognizerService.recognize();
	}
}
