package com.hegx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    //字段名字
    String value() default "";

    //是否是查询条件
    boolean isSearch() default false;

    //类型
    String[] types() default {};
}
