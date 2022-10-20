package com.udld.demo.job;


import com.udld.demo.log.Log;
import com.udld.demo.impl.AddressImpl;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

@Component
public class AddressJob {

  @Autowired
  private AddressImpl addressImpl;

  @Async
  //corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期中的日期 年份
  @Scheduled(cron = "0/100 * * * * *")
  public void addAddress()
      throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

    ECKeyPair ecKeyPair = Keys.createEcKeyPair();
    BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
    String privateKeyInHex = "0x" + privateKeyInDec.toString(16);
    Credentials credentials = Credentials.create(ecKeyPair);
    String addressInHex = credentials.getAddress();
    addressImpl.addUserExecute(addressInHex, privateKeyInHex);
    Log.logger.info("add address: " + addressInHex);
  }

  public void addAddressL(int count)
      throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
    for (int i = 0; i < count; i++) {
      addAddress();
    }
  }


}
