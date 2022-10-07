package com.udld.demo.impl;


import com.alibaba.fastjson.JSONObject;
import com.udld.demo.entity.AddressEntity;
import com.udld.demo.service.AddressService;
import com.udld.demo.util.RespGenerate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class AddressImpl {

  @Autowired
  private AddressService addressService;

  public JSONObject addUserExecute(String address, String privateKey) {
    try {
      AddressEntity addressEntity = new AddressEntity();
      addressEntity.setAddress(address);
      addressEntity.setPrivateKey(privateKey);
      addressService.addAddress(addressEntity);
    } catch (Exception e) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, address + "added failed");
    }
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, address + "added success");
  }

  public JSONObject updateUserExecute(int id, String address, String privateKey) {
    try {
      AddressEntity addressEntity = new AddressEntity();
      addressEntity.setAddress(address);
      addressEntity.setPrivateKey(privateKey);
      addressEntity.setId(id);
      addressService.updateAddress(addressEntity);
    } catch (Exception e) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, address + "added failed");
    }
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, address + "added success");
  }

  public JSONObject getUserInfo(String id) {
    List<AddressEntity> result;
    try {
      if (id == null) {
        result = addressService.getAllUAddress();
      } else {
        result = addressService.findById(Integer.parseInt(id));
      }
    } catch (Exception e) {
      return RespGenerate.generateRes(RespGenerate.ERROR_CODE, null);
    }
    return RespGenerate.generateRes(RespGenerate.SUCCESS_CODE, result);
  }
}
