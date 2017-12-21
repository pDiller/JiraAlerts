package io.reflectoring.jiraalerts.application;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import io.reflectoring.jiraalerts.issuerecognition.IssueRecognitionJob;

/**
 * Spring-configuration for Quartz.
 */
@Configuration
public class QuartzTimerConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public JobDetailFactoryBean issueRecignitionFactoryBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(IssueRecognitionJob.class);
		Map<String, Object> map = newHashMap();
		map.put("name", "RAM");
		factory.setJobDataAsMap(map);
		factory.setGroup("issueRecognition");
		factory.setName("issueRecognitionJob");
		return factory;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean issueRecognitionCronTriggerBean = new CronTriggerFactoryBean();
		issueRecognitionCronTriggerBean.setJobDetail(issueRecignitionFactoryBean().getObject());
		issueRecognitionCronTriggerBean.setStartDelay(3000);
		issueRecognitionCronTriggerBean.setName("issueRecognitionJobTrigger");
		issueRecognitionCronTriggerBean.setGroup("issueRecognition");
		issueRecognitionCronTriggerBean.setCronExpression("0 0/1 * 1/1 * ? *");
		return issueRecognitionCronTriggerBean;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();

		AutowiringBeanFactory jobFactory = new AutowiringBeanFactory();
		jobFactory.setApplicationContext(applicationContext);
		scheduler.setJobFactory(jobFactory);

		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}

}
