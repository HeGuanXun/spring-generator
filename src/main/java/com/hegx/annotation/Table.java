package com.hegx.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {

    String value() default "";

    /**
     * 表备注
     * @return
     */
    String tableContext() default "";
}
