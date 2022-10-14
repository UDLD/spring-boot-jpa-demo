package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.impl.UserImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserImpl userImpl;

  @Autowired
  private HttpServletRequest request;

  @RequestMapping("/info")
  public JSONObject info() {
    Long id = Long.parseLong(request.getAttribute("id").toString());
    return userImpl.userInfo(id);
  }

  @RequestMapping(value = "/update", method= RequestMethod.POST)
  public JSONObject update( @RequestParam(value = "passwordSet", required = true) String passwordSet) {
    Long id = Long.parseLong(request.getAttribute("id").toString());
    return userImpl.update(id, passwordSet);
  }

}