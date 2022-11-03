package com.udld.demo.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udld.demo.config.data.PrimaryConfig;
import com.udld.demo.entity.primary.UserEntity;
import com.udld.demo.impl.UserImpl;
import com.udld.demo.service.UserService;
import com.udld.demo.util.RedisUtil;
import com.udld.demo.util.RespGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RedisUtil redisUtil;
  @MockBean
  private UserService userService;

  @MockBean
  private PrimaryConfig primaryConfig;
  @MockBean
  private UserImpl userimpl;

  @Test
  public void infoTest() throws Exception {

    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);
    userEntity.setUserName("admin");
    userEntity.setPassword("12345678");
    given(userimpl.userInfo(anyLong()))
        .willReturn(RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, userEntity));

    mockMvc.perform(MockMvcRequestBuilders.get("/user/info").requestAttr("id", 1L))
        .andExpect(status().isOk())
        .andExpect(jsonPath("code").value("200"))
        .andExpect(jsonPath("$.body.userName").value("admin"));

  }
}
