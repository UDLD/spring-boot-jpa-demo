package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.config.nacos.JsonNacos;
import com.udld.demo.config.nacos.YamlNacos;
import com.udld.demo.util.RespGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/nacos")

public class NacController {

  @Autowired
  JsonNacos jsonNacos;

  @Autowired
  YamlNacos yamlNacos;

  @RequestMapping("/getTxt")
  public JSONObject getTxt() {
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, "url", "url value in nacos");
  }

  @RequestMapping("/getYaml")
  public JSONObject getYaml() {
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, yamlNacos.toString());
  }

  @RequestMapping("/getJson")
  public JSONObject getJson() {
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, jsonNacos.toString());
  }

}