package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.configuration;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfiguration {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Scheduler scheduler(
            JobDetail helloJobDetail,
            Trigger helloTrigger,
            JobDetail ghesmeJobDetail,
            Trigger ghesmeTrigger) {
        Scheduler scheduler = null;
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            scheduler = sf.getScheduler();
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
