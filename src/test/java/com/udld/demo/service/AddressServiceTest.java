package com.udld.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.udld.demo.dao.primary.AddressRepository;
import com.udld.demo.entity.primary.AddressEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

  @InjectMocks
  private AddressService addressService;

  @Mock
  private AddressRepository addressRepository;

  @Test
  public void addressFindByIdTest() {

    List<AddressEntity> addressEntities = new ArrayList<AddressEntity>();
    AddressEntity addressEntityAdd = new AddressEntity();
    addressEntityAdd.setAddress("0x1234");
    addressEntityAdd.setPrivateKey("0x4321");
    addressEntityAdd.setId(1L);
    addressEntities.add(addressEntityAdd);
    given(addressRepository.findById(1)).willReturn(addressEntities);
    AddressEntity addressEntity = addressService.findById(1).get(0);
    assertThat(addressEntity.getAddress()).isEqualTo("0x1234");
    assertThat(addressEntity.getPrivateKey()).isEqualTo("0x4321");
  }

}
