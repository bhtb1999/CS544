package customers;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Configuration
@Aspect
public class Trace {

    @After("execution(* customers.EmailSender.sendEmail(..)) && args(email, message)")
    public void traceAfterMethod(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();
        String outgoingMailServer = emailSender.getOutgoingMailServer();
        System.out.println(LocalDateTime.now() + " method=" + joinPoint.getSignature().getName() + " address=" + email
                + " message= " + message + " outgoing mail server= " + outgoingMailServer);
    }

    @Around("execution(* customers.CustomerDAO.*(..))")
    public Object traceAroundDaoMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        return invoke(joinPoint);
    }

    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        System.out.println(totaltime);
        System.out.println(sw.prettyPrint());
        return retVal;
    }
}
