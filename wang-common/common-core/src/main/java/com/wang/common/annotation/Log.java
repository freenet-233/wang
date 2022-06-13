package com.wang.common.annotation;

import java.lang.annotation.*;

/**
 * @author wangguangpeng
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String title() default "title";
    String msg() default "";
}
