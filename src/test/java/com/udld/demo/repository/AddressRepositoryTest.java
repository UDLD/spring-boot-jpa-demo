package com.udld.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.udld.demo.dao.primary.AddressRepository;
import com.udld.demo.entity.primary.AddressEntity;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/eth_chain_data?characterEncoding=utf8&useSSL=true",
    "spring.datasource.username=root",
    "spring.datasource.password=root",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver"

})
public class AddressRepositoryTest {

  @Autowired
  private AddressRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void addressRepositoryFindByIdTest() {

    AddressEntity addressEntityAdd = new AddressEntity();
    addressEntityAdd.setAddress("0x12345");
    addressEntityAdd.setPrivateKey("0x54321");
    addressEntityAdd.setCreateTime(new Date(100L));
    addressEntityAdd.setUpdateTime(new Date(200L));
    AddressEntity savedAdd = entityManager.persistFlushFind(addressEntityAdd);
    Long id = savedAdd.getId();
    //assertThat(savedAdd.getId()).isNotNull().isNotNegative();
    AddressEntity user = repository.findById(id).get();
    assertThat(user.getAddress()).isEqualTo("0x12345");

  }

}
