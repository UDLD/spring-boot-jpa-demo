package com.udld.demo.config.data;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactorySecondary",
    transactionManagerRef = "transactionManagerSecondary",
    basePackages = {"com.udld.demo.dao.secondary"}) //设置Repository所在位置
public class SecondaryConfig {

  @Autowired
  @Qualifier("secondaryDataSource")
  private DataSource secondaryDataSource;

  @Autowired
  private JpaProperties jpaProperties;
  @Autowired
  private HibernateProperties hibernateProperties;

  private Map<String, Object> getVendorProperties() {
    return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(),
        new HibernateSettings());
  }


  @Bean(name = "entityManagerSecondary")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactorySecondary(builder).getObject().createEntityManager();
  }


  @Bean(name = "entityManagerFactorySecondary")
  public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(secondaryDataSource)
        .packages("com.udld.demo.entity.primary") //设置实体类所在位置
        .persistenceUnit("secondaryPersistenceUnit")
        .properties(getVendorProperties())
        .build();
  }

  @Bean(name = "transactionManagerSecondary")
  public PlatformTransactionManager transactionManagerSecondary(
      EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
  }

}
