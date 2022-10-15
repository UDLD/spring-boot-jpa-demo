package com.udld.demo.config.nacos;


import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@NacosConfigurationProperties(prefix = "test.demo", dataId = "JSON_DATA_ID", groupId = "DEFAULT_GROUP", autoRefreshed = true, ignoreNestedProperties = true, type = ConfigType.JSON)
public class JsonNacos {


  /*
  nacos json config demo
  {
    "test": {
        "demo": {
            "id": 10,
            "name": "nick",
            "value": 3.1415
        }
    }
  }
   */

  @NacosValue(value = "${id:1}")
  private int id;
  @NacosValue(value = "${name:root}")
  private String name;
  @NacosValue(value = "${value:2.13}")
  private double value;

  @Override
  public String toString() {
    return "Json{" + "id=" + id + ", name='" + name + '\'' + ", value=" + value + '}';
  }
}
