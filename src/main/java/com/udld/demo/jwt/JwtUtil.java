package com.udld.demo.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.udld.demo.entity.UserEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jline.utils.Log;
import org.springframework.stereotype.Component;

/**
 * @description: Jwt tool class，generate and verify
 * @author: nick
 */
@Component
public class JwtUtil {

  /**
   * expiration time
   **/
  private static final long EXPIRATION = 1800L;// s

  /**
   * generate token, set token expired date
   */
  public static String createToken(UserEntity user, String secret) {
    // generate expired date
    Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    Map<String, Object> map = new HashMap<>();
    map.put("alg", "HS256");
    map.put("typ", "JWT");
    String token = JWT.create()
        .withHeader(map)// add head
        //put basic information in claims
        .withClaim("id", user.getId())//userId
        .withClaim("userName", user.getUserName())//userName
        .withClaim("password", user.getPassword())//password
        .withExpiresAt(expireDate) // set expire date
        .withIssuedAt(new Date()) // set generate date
        .sign(Algorithm.HMAC256(secret)); // use secret encryption
    return token;
  }

  /**
   * verify token and analysis token
   */
  public static Map<String, Claim> verifyToken(String token, String secret) {
    DecodedJWT jwt = null;
    try {
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
      jwt = verifier.verify(token);
      //decodedJWT.getClaim("{attribute}").asString()  get attribute values

    } catch (Exception e) {
      Log.error(e.getMessage());
      Log.error("token invalid");
      return null;
    }
    return jwt.getClaims();
  }

}