package hello;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yunus on 18.02.17.
 */
@Aspect
@Component
public class ServiceMonitor {
    private Logger log = LoggerFactory.getLogger(ServiceMonitor.class);
//    @AfterReturning("execution(* hello..*Rest.*(..))")
//    public void logServiceAccess(JoinPoint joinPoint) {
//        log.debug("Completed: " + joinPoint);
//    }

    @AfterReturning("execution(* hello..*Rest.*u*(..))")
    public void logOnlyFu(JoinPoint joinPoint) {
        log.debug("After returning Fu: " + joinPoint);
    }

    @AfterThrowing("execution(* hello..*Rest.*u*(..))")
    public void logAfterTHrowingFu(JoinPoint joinPoint) {
        log.debug("after throwing Fu: " + joinPoint);
    }

    @After("execution(* hello..*Rest.*u*(..))")
    public void logafterFu(JoinPoint joinPoint) {
        log.debug("after Fu: " + joinPoint);
    }


    @Before("execution(* hello..*Rest.*u*(..))")
    public void logBeforeFu(JoinPoint joinPoint) {
        log.debug("Before Fu: " + joinPoint);
    }

    @Around("execution(* hello..*Rest.*u*(..))")
    public Object aroundFu(ProceedingJoinPoint joinPoint) throws Throwable {
        LoggerFactory.getLogger(joinPoint.getTarget().getClass()).debug("Around0 Fu: " + joinPoint);
//        log.debug();
        Object retVal = joinPoint.proceed();
        log.debug("Around1 Fu: " + joinPoint);
        Random ran = new Random();
        int random = ran.nextInt(2);
        log.debug(Thread.currentThread().getName() + "-->"  + random);
//        if ( random % 2 == 0)
//            throw new RuntimeException("e");
        return retVal;
    }

    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void anno(JoinPoint joinPoint) {
        LoggerFactory.getLogger(joinPoint.getTarget().getClass()).debug("Before anno: " + joinPoint);
    }
}

