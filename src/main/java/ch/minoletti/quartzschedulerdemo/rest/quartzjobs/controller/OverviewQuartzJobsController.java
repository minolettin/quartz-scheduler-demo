package ch.minoletti.quartzschedulerdemo.rest.quartzjobs.controller;

import ch.minoletti.quartzschedulerdemo.rest.quartzjobs.model.JobResource;
import ch.minoletti.quartzschedulerdemo.rest.quartzjobs.service.OverviewQuartzJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/quartzjobs/overview")
public class OverviewQuartzJobsController {

    @Autowired
    private OverviewQuartzJobsService service;

    @GetMapping("/jobs")
    public List<JobResource> getJobs() {
        return service.getJobs();
    }

    @PostMapping("")
    public boolean executeJob(@RequestParam(name = "jobName") String job) {
        return service.executeJob(job);
    }
}
