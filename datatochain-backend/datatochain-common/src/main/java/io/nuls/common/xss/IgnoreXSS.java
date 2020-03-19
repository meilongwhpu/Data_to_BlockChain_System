package io.nuls.common.xss;

import java.lang.annotation.*;

/**
 * 无视XSS脚本
 *
 * @author Long
 * @date 2020/03/16
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IgnoreXSS {


}

