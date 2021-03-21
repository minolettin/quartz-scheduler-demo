package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.service;

import ch.minoletti.quartzschedulerdemo.rest.quartzjobs.job.ghesme.GhesmeJob;
import ch.minoletti.quartzschedulerdemo.rest.quartzjobs.model.JobResource;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OverviewQuartzJobsService {
    Logger logger = LoggerFactory.getLogger(GhesmeJob.class);

    @Autowired
    private Scheduler scheduler;

    public List<JobResource> getJobs() {
        List<JobResource> jobs = new ArrayList<>();
        try {
            // get jobGroupNames to get jobKeys
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

                    // get cronExpression
                    CronTrigger cronTrigger = (CronTrigger) triggers.get(0);
                    String cronExpr = cronTrigger.getCronExpression();
                    Date nextFireTime = triggers.get(0).getNextFireTime();

                    // get JobStatus
                    Trigger.TriggerState jobStatus = scheduler.getTriggerState(triggers.get(0).getKey());

                    jobs.add(new JobResource(jobName, jobStatus.toString(), cronExpr, nextFireTime));
                }
            }
        } catch (SchedulerException e) {
            logger.error("Can't handle scheduler", e);
        }
        return jobs;
    }

    public boolean executeJob(String jobName) {
        try {
            JobKey jobKey = new JobKey(jobName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.triggerJob(jobKey);
                logger.info("{} Job exists & is executed", jobKey.getName());
                return true;
            } else {
                logger.info("Job does not exist");
            }
        } catch (SchedulerException e) {
            logger.error("Can't handle scheduler", e);
        }
        return false;
    }

}
