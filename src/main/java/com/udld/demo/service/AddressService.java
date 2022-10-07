package com.udld.demo.service;

import com.udld.demo.dao.AddressRepository;
import com.udld.demo.entity.AddressEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  public List<AddressEntity> getAllUAddress() {
    List<AddressEntity> addressEntities = new ArrayList<>();
    addressRepository.findAll().forEach(addressEntities::add);
    addressRepository.findAll(Sort.by("id")).subList(1, 10);
    return addressEntities;
  }

  public List<AddressEntity> findById(int id) {
    return addressRepository.findById(id);
  }

  public void addAddress(AddressEntity addressEntity) {
    addressRepository.save(addressEntity);
  }

  public void updateAddress(AddressEntity addressEntity) {
    addressRepository.updateById(addressEntity.getId(), addressEntity.getAddress(),
        addressEntity.getPrivateKey());
  }
}