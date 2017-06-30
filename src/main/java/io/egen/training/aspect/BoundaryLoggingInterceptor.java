package io.egen.training.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/*
* Aspect for providing logging signature
* */
@Aspect
@Component
public class BoundaryLoggingInterceptor {
    private static Logger logger = Logger.getLogger(BoundaryLoggingInterceptor.class);

    @Pointcut(value = "execution( public  * io.egen.training.controller.*.*(..))")
    public void logging() {
    }

    @Before("logging() && @annotation(BoundaryLogger)")
    public void loggingAdvice(JoinPoint joinPoint) {
        log(joinPoint, "Enter: ");
    }

    @AfterReturning("logging() && @annotation(BoundaryLogger) ")
    public void logServiceAccess(JoinPoint joinPoint) {
        log(joinPoint, "Exit: ");
    }

    private void log(final JoinPoint jp,final String String) {
        if (logger.isInfoEnabled()) {
            String Method = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
            StringBuilder builder = new StringBuilder(String);
            builder.append(Method).append("()");
            logger.info(builder.toString());
        }
    }

}
