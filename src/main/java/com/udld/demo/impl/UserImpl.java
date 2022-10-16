package com.udld.demo.impl;


import com.alibaba.fastjson.JSONObject;
import com.udld.demo.entity.UserEntity;
import com.udld.demo.jwt.JwtProperties;
import com.udld.demo.jwt.JwtUtil;
import com.udld.demo.service.UserService;
import com.udld.demo.util.Common;
import com.udld.demo.util.RedisUtil;
import com.udld.demo.util.RespGenerate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UserImpl {

  @Autowired
  public JwtProperties jwtProperties;

  @Autowired
  private UserService userService;

  @Autowired
  private RedisUtil redisUtil;


  public UserEntity getUserInfoById(Long id) {
    UserEntity result;
    try {
      result = userService.findById(id);
    } catch (Exception e) {
      return null;
    }
    return result;
  }

  public UserEntity getUserInfoByName(String name) {
    List<UserEntity> result;
    try {
      result = userService.findByName(name);
    } catch (Exception e) {
      return null;
    }
    if (result.size() == 1) {
      return result.get(0);
    } else {
      return null;
    }
  }

  public JSONObject userInfo(Long id) {
    UserEntity userEntity = getUserInfoById(id);
    if (userEntity == null) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "account info get failed");
    } else {
      userEntity.setPassword(null);
      return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, userEntity);
    }
  }

  public JSONObject login(String name, String password) {

    UserEntity userEntity = getUserInfoByName(name);
    if (userEntity == null) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "account not found");
    }
    if (userEntity.getPassword().equals(password)) {
      return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE,
          JwtUtil.createToken(userEntity, jwtProperties.getPassword()));
    } else {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "password not right");
    }
  }

  public JSONObject register(String name, String password) {
    UserEntity userEntity = getUserInfoByName(name);
    if (userEntity != null) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "name have registered");
    } else {
      userEntity = new UserEntity();
      userEntity.setUserName(name);
      userEntity.setPassword(password);
      try {
        UserEntity body = userService.insertAccount(userEntity);
        String redisKey = Common.generateUserRedisKey(body.getId());
        // update redis user info resp
        if (redisUtil.hasKey(redisKey)) {
          redisUtil.delete(redisKey);
        }
        return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, body, "register success");
      } catch (Exception e) {
        return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "register failed");
      }
    }
  }

  public JSONObject update(Long id, String password) {
    try {
      int code = userService.updatePasswordById(id, password);
      if (!Common.checkUpdateState(code)) {
        return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "update failed");
      }
    } catch (Exception e) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "update failed");
    }
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, null, "update success");
  }

}
