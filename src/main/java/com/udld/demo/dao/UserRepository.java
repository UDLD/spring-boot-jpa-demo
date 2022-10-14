package com.udld.demo.dao;

import com.udld.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query(value = "select * from user b where b.name=?1 ", nativeQuery = true)
  List<UserEntity> findBName(String name);

  @Modifying
  @Transactional
  @Query(value = "update user SET  password=?2 WHERE id=?1 ", nativeQuery = true)
  int updatePasswordById(long id, String password);


}
