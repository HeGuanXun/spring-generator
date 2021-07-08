package com.hegx.annotation;

import com.hegx.enums.ItemEnum;

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

    //表单项类型
    ItemEnum itemType() default ItemEnum.Input;

    //类型
    String[] options() default {};
}
