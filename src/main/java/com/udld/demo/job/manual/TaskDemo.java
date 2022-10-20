package com.udld.demo.job.manual;


import com.udld.demo.log.Log;
import com.udld.demo.job.AddressJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDemo implements Runnable {

  public static int countAdd = 1;
  @Autowired
  private AddressJob addressJob;

  @Override
  public void run() {
    int countAddA = countAdd;
    Log.logger.info("********************** job start **********************");
    try {
      addressJob.addAddressL(countAdd);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Log.logger.info("********************** job end **********************");
  }

}
