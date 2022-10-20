package com.udld.demo.entity.primary;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "address")
@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
  private Long id;
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

}
