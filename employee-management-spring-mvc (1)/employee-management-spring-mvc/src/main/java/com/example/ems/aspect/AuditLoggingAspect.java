package com.example.ems.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditLoggingAspect.class);

    @Around("execution(* com.example.ems.service..*(..))")
    public Object auditServiceCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        long started = System.currentTimeMillis();
        logger.info("AUDIT START method={}", method);
        try {
            Object result = joinPoint.proceed();
            logger.info("AUDIT SUCCESS method={} durationMs={}", method,
                System.currentTimeMillis() - started);
            return result;
        } catch (Throwable ex) {
            logger.warn("AUDIT FAILURE method={} errorType={}", method,
                ex.getClass().getSimpleName());
            throw ex;
        }
    }
}
