package com.yn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
* 
* @Description: 
* @ClassName: Transactional.java
* @author: yn 
* @date: 2020年12月23日 上午10:50:47 
*/
//作用范围在方法
@Target(ElementType.METHOD)
//生命周期-运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {

}
