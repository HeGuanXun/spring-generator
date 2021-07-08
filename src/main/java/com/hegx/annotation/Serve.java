package com.hegx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Serve {
    /**
     * 应用名字
     * @return
     */
    String applicationName() default "";

    /**
     * 项目路径
     * @return
     */
    String projectPath() default "";

    /**
     * 包名
     * @return
     */
    String basePackage() default "";

}
