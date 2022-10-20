package com.udld.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.udld.demo.entity.primary.UserEntity;
import com.udld.demo.impl.UserImpl;
import com.udld.demo.util.Common;
import com.udld.demo.util.RedisUtil;
import java.util.Date;
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

  @Autowired
  private RedisUtil redisUtil;

  @RequestMapping("/info")
  public JSONObject info() {
    Long id = Long.parseLong(request.getAttribute("id").toString());
    String redisKey = Common.generateUserRedisKey(id);
    if (redisUtil.hasKey(redisKey)) {
      JSONObject resTmp = JSONObject.parseObject(redisUtil.get(redisKey).toString());
      return formatUserInfoRespDateData(resTmp);
    } else {
      JSONObject res = userImpl.userInfo(id);
      redisUtil.set(redisKey, res.toString());
      return res;
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public JSONObject update(
      @RequestParam(value = "passwordSet", required = true) String passwordSet) {
    Long id = Long.parseLong(request.getAttribute("id").toString());
    String redisKey = Common.generateUserRedisKey(id);
    // update redis user info resp
    if (redisUtil.hasKey(redisKey)) {
      redisUtil.getRedisTemplate().delete(redisKey);
    }
    return userImpl.update(id, passwordSet);
  }

  public JSONObject formatUserInfoRespDateData(JSONObject sour) {
    UserEntity userEntity = new UserEntity();
    JSONObject body = sour.getJSONObject("body");
    userEntity.setUserName(body.getString("userName"));
    userEntity.setPassword(body.getString("password"));
    userEntity.setId(body.getLong("id"));
    userEntity.setCreateTime(new Date(body.getLong("createTime")));
    userEntity.setUpdateTime(new Date(body.getLong("updateTime")));
    sour.put("body", userEntity);
    return sour;
  }
}