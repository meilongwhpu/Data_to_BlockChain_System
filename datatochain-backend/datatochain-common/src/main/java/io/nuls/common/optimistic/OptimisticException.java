package io.nuls.common.optimistic;


/**
 * 乐观锁异常
 *
 * @author Long
 * @date 2020/03/16
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException() {

    }

    public OptimisticException(String message) {
        super(message);
    }
}

