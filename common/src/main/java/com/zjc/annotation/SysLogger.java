/**
 * @program: springcloud
 * @description:
 * @author: zhujunchen
 * @create: 2019-10-13
 **/
package com.zjc.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解@SysLogger
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}

