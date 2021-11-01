package com.weblogb.pwms.config.annotation;

import java.lang.annotation.*;

/**
* @author: Jiajiajia
* @Date: 2021/11/1
* @Description: 参数加解密注解，再控制层方法上使用
*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cryptogram {
    /**
     * 是否加密
     * @return
     */
    public boolean decrypt() default true;

    /**
     * 是否解密
     * @return
     */
    public boolean encrypt() default true;
}
