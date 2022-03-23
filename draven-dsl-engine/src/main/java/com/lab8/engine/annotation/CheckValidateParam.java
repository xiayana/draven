package com.lab8.engine.annotation;

import java.lang.annotation.*;

@Deprecated
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CheckValidateParmaFactory.class)
public @interface CheckValidateParam{
    String key() default "";
}
