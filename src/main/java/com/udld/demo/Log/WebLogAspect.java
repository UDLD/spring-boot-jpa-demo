package com.udld.demo.Log;


import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(1)
public class WebLogAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

  @Pointcut("execution(public * com.udld.demo.controller.*.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
  }

  @AfterReturning(value = "webLog()", returning = "ret")
  public void doAfterReturning(Object ret) throws Throwable {
  }

  public String getBodyTxt(HttpServletRequest request) throws IOException {
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while ((str = br.readLine()) != null) {
      wholeStr += str;
    }
    return wholeStr;
  }

  @Around("webLog()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    //获取当前请求对象
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    //记录请求信息(通过Logstash传入Elasticsearch)
    WebLog webLog = new WebLog();
    Object result = joinPoint.proceed();
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    long endTime = System.currentTimeMillis();
    String urlStr = request.getRequestURL().toString();
    webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
    webLog.setIp(request.getRemoteUser());
    webLog.setSpendTime((int) (endTime - startTime));
    webLog.setParameter(getBodyTxt(request));
    webLog.setResult(result);
    webLog.setMethod(method.toString());
    LOGGER.info("{}", JSONUtil.parse(webLog));
    return result;
  }
}

