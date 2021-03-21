package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.jobs.ghesme;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class GhesmeJob implements Job {
    Logger logger = LoggerFactory.getLogger(GhesmeJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
    }
}
