package com.udld.demo.dao.primary;

import com.udld.demo.entity.primary.AddressEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

  @Query(value = "select * from address b where b.id=?1 ", nativeQuery = true)
  List<AddressEntity> findById(int id);

  @Modifying
  @Transactional
  @Query(value = "update address SET address=?2, private_key=?3 WHERE id=?1 ", nativeQuery = true)
  int updateById(long id, String address, String privateKey);

}
