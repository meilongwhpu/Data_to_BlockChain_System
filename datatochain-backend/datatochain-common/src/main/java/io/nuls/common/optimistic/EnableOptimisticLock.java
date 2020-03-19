package io.nuls.common.optimistic;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用乐观锁
 *
 * @author Long
 * @date 2020/03/16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OptimisticLockConfiguration.class})
public @interface EnableOptimisticLock {


}

