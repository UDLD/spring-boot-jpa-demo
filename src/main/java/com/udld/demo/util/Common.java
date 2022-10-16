package com.udld.demo.util;

public class Common {

  public static String USER_INFO_KEY_PRE = "userinfo_";

  public static boolean checkUpdateState(int code) {
    return code == 1;
  }

  public static String generateUserRedisKey(Long id) {
    return USER_INFO_KEY_PRE + id;
  }

}
