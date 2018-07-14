package com.zzq.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {
    @Before("execution(public * com.zzq.demo.controller.GirlController.*(..))")
    public void log(){
        System.out.println(11111);
    }
}
