package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.configuration;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfiguration {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Scheduler scheduler(
            @Qualifier("helloJobDetail") JobDetail helloJobDetail,
            @Qualifier("helloTrigger") Trigger helloTrigger,
            @Qualifier("ghesmeJobDetail") JobDetail ghesmeJobDetail,
            @Qualifier("ghesmeTrigger") Trigger ghesmeTrigger) {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // Tell quartz to schedule the job using the corresponding trigger
            scheduler.scheduleJob(helloJobDetail, helloTrigger);
            scheduler.scheduleJob(ghesmeJobDetail, ghesmeTrigger);
        } catch (SchedulerException e) {
            logger.error("Can't init Scheduler", e);
        }
        return scheduler;
    }
}
