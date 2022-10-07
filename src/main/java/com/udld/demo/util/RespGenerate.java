package com.udld.demo.util;

import com.alibaba.fastjson.JSONObject;

public class RespGenerate {

  public static int SUCCESS_CODE = 200;
  public static int ERROR_CODE = 100;

  public static JSONObject generateRes(int code, Object body) {
    JSONObject res = new JSONObject();
    res.put("code", code);
    res.put("body", body);
    return res;
  }

}
