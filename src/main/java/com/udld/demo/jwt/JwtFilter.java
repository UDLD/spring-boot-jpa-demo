package com.udld.demo.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.udld.demo.util.RespGenerate;
import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JWT Interceptor，intercept /user request
 */
@Slf4j
@WebFilter(filterName = "jwtFilter", urlPatterns = "/user/*")
public class JwtFilter implements Filter {

  @Autowired
  public JwtProperties jwtProperties;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) req;
    final HttpServletResponse response = (HttpServletResponse) res;

    response.setCharacterEncoding("UTF-8");
    //get token in header
    final String token = request.getHeader("authorization");

    if ("OPTIONS".equals(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
      chain.doFilter(request, response);
    }
    // Except OPTIONS, other request should be checked by JWT
    else {

      if (token == null) {
        response.getWriter().write(
            RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "no token !!!").toString());
        return;
      }

      Map<String, Claim> userData = JwtUtil.verifyToken(token, jwtProperties.getPassword());
      if (userData == null) {
        response.getWriter().write(
            RespGenerate.generateRes(RespGenerate.ERROR_CODE, null, "token invalid !!!")
                .toString());
        return;
      }
      Integer id = userData.get("id").asInt();
      String userName = userData.get("userName").asString();
      String password = userData.get("password").asString();
      //interceptor， get the user information and put it in the request
      request.setAttribute("id", id);
      request.setAttribute("userName", userName);
      request.setAttribute("password", password);
      chain.doFilter(req, res);
    }
  }

  @Override
  public void destroy() {
  }
}