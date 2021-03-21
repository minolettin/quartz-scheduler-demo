package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.model;

import java.util.Date;

public class JobResource {
    private String name;
    private String state;
    private String cronExpression;
    private Date nextFireTime;

    public JobResource(String name, String state, String cronExpression, Date nextFireTime) {
        this.name = name;
        this.state = state;
        this.cronExpression = cronExpression;
        this.nextFireTime = nextFireTime;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }
}
