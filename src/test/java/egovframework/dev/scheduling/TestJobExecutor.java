package egovframework.dev.scheduling;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 *스케쥴 Job 실행 클래스
 */
@Slf4j
public class TestJobExecutor extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		String jobName = context.getJobDetail().getJobDataMap().getString("jobName");
        
        log.debug("===>>> Run Job: {}", jobName);
		log.debug("===>>> Run Job!!!");
	}

}