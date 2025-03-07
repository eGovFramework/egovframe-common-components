package egovframework.dev.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EgovConfigScheduling {

	/*
    // Job Detail Bean 설정
    @Bean(name="jobDetail")
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(QuartzJobBean.class); // QuartzJobBean 구현 클래스

        Map<String, Object> jobDataMap = new HashMap<>();
        jobDataMap.put("jobName", "TestJob");
        jobDetailFactory.setJobDataAsMap(jobDataMap);
        
        return jobDetailFactory;
    }
	
    // JobDetail 설정
    @Bean
    public MethodInvokingJobDetailFactoryBean testMethodInvokingJobDetail(TestJobService testJobService) {
        MethodInvokingJobDetailFactoryBean jobDetailFactory = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactory.setTargetObject(testJobService);
        jobDetailFactory.setTargetMethod("runJob");
        jobDetailFactory.setConcurrent(false);
        return jobDetailFactory;
    }

    // SimpleTrigger 설정
    @Bean
    public SimpleTriggerFactoryBean simpleTrigger(@Qualifier("jobDetail")JobDetailFactoryBean jobDetail) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setStartDelay(2000); // 2초 후에 시작 (milisecond)
        trigger.setRepeatInterval(10000); // 매 10초마다 실행 (milisecond)
        return trigger;
    }

    // CronTrigger 설정
    @Bean
    public CronTriggerFactoryBean cronTrigger(MethodInvokingJobDetailFactoryBean testMethodInvokingJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(testMethodInvokingJobDetail.getObject());
        trigger.setCronExpression("* * * * * ?");
        return trigger;
    }

    // Scheduler 설정
    @Bean
    public SchedulerFactoryBean schedulerFactory(SimpleTriggerFactoryBean simpleTrigger) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(simpleTrigger.getObject());
        return schedulerFactory;
    }
*/
}
