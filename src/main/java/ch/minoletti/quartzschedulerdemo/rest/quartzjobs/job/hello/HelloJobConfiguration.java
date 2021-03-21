package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.job.hello;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class HelloJobConfiguration {
    @Bean
    public JobDetail helloJobDetail() {
        return newJob(HelloJob.class)
                .withIdentity("helloJob", "group1")
                .build();
    }

    @Bean
    public Trigger helloTrigger() {
        return newTrigger()
                .withIdentity("helloTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0/5 * * ? * * *"))
                .build();
    }
}
