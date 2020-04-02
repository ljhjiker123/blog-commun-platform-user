package com.jiker.bcp.user.aspect;

import com.jiker.bcp.user.common.ResponseData;
import com.jiker.bcp.user.enums.ResponseCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Around("execution(* com.jiker.bcp.user.controller.*.*(..))")
    public Object process(ProceedingJoinPoint pjt){
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            Object[] args = pjt.getArgs();
            for (Object arg : args){
                if (arg instanceof BindingResult){
                    BindingResult rs = (BindingResult) arg;
                    if (rs.hasErrors()){
                        String errorMsg = rs.getFieldError().getDefaultMessage();
                        result = new ResponseData(ResponseCode.PRAM_ERROR.getCode(),ResponseCode.PRAM_ERROR.getMessage());
                        return result;
                    }
                    break;
                }
            }

            Object obj = pjt.proceed();
            if (obj instanceof ResponseData){
                result = obj;
            } else {
                result = new ResponseData(obj);
            }
        } catch (Throwable e){
            LOGGER.error(e.getMessage(),e);
            result = new ResponseData(ResponseCode.SERVER.getCode(),ResponseCode.SUCCESS.getMessage());
        }
        return result;
    }

}
