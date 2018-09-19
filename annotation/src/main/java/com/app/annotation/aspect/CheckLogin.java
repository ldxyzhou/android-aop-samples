package com.app.annotation.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：xinyu.zhou
 * @version: 2018/9/13
 * @ClassName:
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface CheckLogin {
}
