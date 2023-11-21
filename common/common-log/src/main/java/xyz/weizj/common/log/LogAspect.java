package xyz.weizj.common.log;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import xyz.weizj.common.annotation.Log;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvise(ProceedingJoinPoint joinPoint, Log sysLog){
        String title = sysLog.title();
        log.info("LogAspect...doAroundAdvice方法执行了"+title);
        System.out.println("LogAspect...doAroundAdvice方法执行了"+title);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();              // 执行业务方法
        } catch (Throwable e) {                         // 代码执行进入到catch中，业务方法执行产生异常
            throw new RuntimeException(e);
        }
        return proceed ;
    }
}
