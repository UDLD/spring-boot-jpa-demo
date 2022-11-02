package com.udld.demo.dao.secondary;

import com.udld.demo.entity.primary.UserEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserSecondRepository extends JpaRepository<UserEntity, Long> {

  @Query(value = "select * from user b where b.name=?1 ", nativeQuery = true)
  List<UserEntity> findBName(String name);

  @Modifying
  @Transactional
  @Query(value = "update user SET  password=?2 WHERE id=?1 ", nativeQuery = true)
  int updatePasswordById(long id, String password);


}
