package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.impl.AddressImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  private AddressImpl addressImpl;

  @RequestMapping("")
  public JSONObject getAddById(@RequestParam(value = "id", required = false) String id) {
    return addressImpl.getUserInfo(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public JSONObject updateById(@RequestParam(value = "id", required = true) int id,
      @RequestParam(value = "address", required = true) String address,
      @RequestParam(value = "privateKey", required = true) String privateKey) {

    return addressImpl.updateUserExecute(id, address, privateKey);

  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public JSONObject addUser(@RequestParam(value = "address", required = true) String address,
      @RequestParam(value = "privateKey", required = true) String privateKey) {

    return addressImpl.addUserExecute(address, privateKey);

  }

}