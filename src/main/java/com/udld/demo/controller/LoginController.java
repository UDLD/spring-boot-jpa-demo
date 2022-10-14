package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


  @Autowired
  private UserImpl userImpl;


  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public JSONObject login(@RequestParam(value = "name", required = true) String name,
      @RequestParam(value = "password", required = true) String password) {
    String passwordM = DigestUtils.md5DigestAsHex(password.getBytes());
    return userImpl.login(name, passwordM);
  }


  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public JSONObject register(@RequestParam(value = "name", required = true) String name,
      @RequestParam(value = "password", required = true) String password) {
    String passwordM = DigestUtils.md5DigestAsHex(password.getBytes());
    return userImpl.register(name, passwordM);
  }


}