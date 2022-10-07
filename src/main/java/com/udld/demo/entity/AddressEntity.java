package com.udld.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "address")
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
  private int id;
  @Column(name = "address", columnDefinition = "varchar(200) NOT NULL")
  private String address;
  @Column(name = "private_key", columnDefinition = "varchar(200) NOT NULL")
  private String privateKey;
  @Column(name = "used", columnDefinition = "int(11) NOT NULL")
  private int used;
  @Column(name = "type", columnDefinition = "int(11) NOT NULL")
  private int type;
  @CreatedDate
  @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
  private Date createTime;
  @LastModifiedDate
  @Column(name = "update_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  private Date updateTime;

  public int getUsed() {
    return used;
  }

  public void setUsed(int used) {
    this.used = used;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }
}
