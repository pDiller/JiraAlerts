package io.reflectoring.jiraalerts.issuerecognition;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class IssueRecognitionJob extends QuartzJobBean {

	@Autowired
	private IssueRecognizerService issueRecognizerService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		issueRecognizerService.recognize();
	}
}
