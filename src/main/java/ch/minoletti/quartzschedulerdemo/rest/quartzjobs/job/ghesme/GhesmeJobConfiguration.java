package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.job.ghesme;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class GhesmeJobConfiguration {
    @Bean
    public JobDetail ghesmeJobDetail() {
        return newJob(GhesmeJob.class)
                .withIdentity("ghesmeJob")
                .build();
    }

    @Bean
    public Trigger ghesmeTrigger() {
        return newTrigger()
                .withIdentity("ghesmeTrigger")
                .startNow()
                .withSchedule(cronSchedule("0/10 * * ? * * *"))
                .build();
    }
}
