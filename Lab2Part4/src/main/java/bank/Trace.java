package bank;


import bank.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class Trace {
    Logger logger = new Logger();

    @Before("execution(* bank.dao.*.*(..))")
    public void logDaoMethodCall(JoinPoint joinPoint) {
        logger.log("DAO method called: " + joinPoint.getSignature());
    }

    @Around("execution(* bank.service.*.*(..))")
    public Object measureServiceMethodDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(joinPoint.getSignature().getName());
        Object retVal = joinPoint.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        logger.log(sw.prettyPrint());

        System.out.println("Method " + joinPoint.getSignature().getName() + " executed in " + totaltime + " ms");
        System.out.println(sw.prettyPrint());
        return retVal;
    }

    @After("execution(* bank.jms.*.sendJMSMessage(..)) && args(message)")
    public void logJmsMessage(JoinPoint joinPoint, String message) {
        logger.log("JMS message sent: " + message);
    }
}
