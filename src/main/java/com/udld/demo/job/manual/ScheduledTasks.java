package com.udld.demo.job.manual;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  @Autowired
  private TaskScheduler taskScheduler;

  // Tasks
  @Autowired
  private TaskDemo task; // autowired in case the task has own autowired dependencies

  @PostConstruct
  public void scheduleTasks() {
    taskScheduler.schedule(task, new CronTrigger("0/30 * * * * *"));
  }
}
