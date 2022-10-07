package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.Log.Log;
import com.udld.demo.job.manual.TaskDemo;
import com.udld.demo.util.RespGenerate;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class TaskController {

  @Autowired
  private TaskScheduler taskScheduler;

  // Tasks
  @Autowired
  private TaskDemo task;

  @RequestMapping(value = "/addressAdd", method = RequestMethod.POST)
  public JSONObject executeTask(
      @RequestParam(value = "addCount", required = false) String addCount) {
    try {
      Log.logger.info("manual trigger: add address " + addCount);
      if (addCount == null) {
        TaskDemo.countAdd = 1;
      } else {
        TaskDemo.countAdd = Integer.parseInt(addCount);
      }
      taskScheduler.schedule(task, new Date()); // schedule task for current time
    } catch (Exception e) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, false);
    }
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, true);
  }
}
