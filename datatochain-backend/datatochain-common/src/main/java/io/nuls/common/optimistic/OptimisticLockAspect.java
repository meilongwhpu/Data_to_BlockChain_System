package io.nuls.common.optimistic;

import io.nuls.common.pojo.po.Version;
import io.nuls.common.util.MessageSourceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 乐观锁AOP
 *
 * @author Long
 * @date 2020/03/16
 */
@Aspect
@Order(-1000)
public class OptimisticLockAspect {

    private final static Logger logger = LoggerFactory.getLogger(OptimisticLockAspect.class);

    /**
     * 拦截AbstractDAO的update方法
     * 用于抛出乐观锁冲突时的异常
     */
    @Pointcut("execution(int io.nuls.common.dao.DAO.update(io.nuls.common.pojo.po.AbstractPO))")
    public void daoPointcut() {
    }

    @Around("daoPointcut()")
    public Object doDAOAround(final ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] args = thisJoinPoint.getArgs();
        int count = (int) thisJoinPoint.proceed();
        if ((args[0] instanceof Version) && count <= 0){
            throw new OptimisticException(MessageSourceUtil.getMessage("error.optimistic_lock"));
        }
        return count;
    }

    /**
     * 拦截任何添加了@Tx注解的Service方法
     * 捕获乐观锁冲突异常，并重试
     */
    @Pointcut("execution(@io.nuls.common.optimistic.OptimisticLock * *(..))")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object doServiceAround(final ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Signature signature = thisJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final Method targetMethod = methodSignature.getMethod();
        OptimisticLock optimisticLock = targetMethod.getAnnotation(OptimisticLock.class);
        if (optimisticLock == null) {
            throw new RuntimeException("optimistic lock aop error");
        }

        Class<? extends Exception>[] catchTypes = optimisticLock.catchType();
        if(catchTypes == null || catchTypes.length == 0){
            throw new RuntimeException("optimistic lock aop error");
        }
        int retry = optimisticLock.retry();
        Object object = tryServiceProceed(thisJoinPoint, catchTypes, retry);
        return object;
    }

    private Object tryServiceProceed(ProceedingJoinPoint thisJoinPoint, Class<? extends Exception>[] catchTypes, int retry) throws Throwable {
        Object object = null;
        try {
            object = thisJoinPoint.proceed();
        } catch (Throwable throwable) {
            if (retry > 0) {
                for (Class<? extends Exception> catchType : catchTypes) {
                    if (catchType.isInstance(throwable)) {
                        try {
                            // 睡100毫秒再试
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                        logger.warn("乐观锁重试,retry=" + retry + ",method=" + thisJoinPoint.getSignature().getName());
                        return tryServiceProceed(thisJoinPoint, catchTypes, --retry);
                    }
                }
            }
            throw throwable;
        }
        return object;
    }

}

