package com.udld.demo.service;

import com.udld.demo.dao.primary.UserRepository;
import com.udld.demo.dao.secondary.UserSecondRepository;
import com.udld.demo.entity.primary.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserSecondRepository userSecondaryRepository;

  public List<UserEntity> findByName(String name) {
    return userRepository.findBName(name);
  }

  public UserEntity findById(Long id) {
    return userSecondaryRepository.findById(id.longValue()).get();
  }
  public UserEntity insertAccount(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  public int updatePasswordById(Long id, String password) {
    return userRepository.updatePasswordById(id, password);
  }






}