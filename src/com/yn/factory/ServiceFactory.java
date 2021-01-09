package com.yn.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.yn.proxy.ServiceProxy;

/**   
* 
* @Description: 
* @ClassName: ServiceFactory.java
* @author: yn 
* @date: 2020年12月23日 上午11:18:35 
*/
public class ServiceFactory {
	
	
	public static Object getInstance(Class<?> cls) {
		try {
			Object obj = cls.newInstance();
			//创建代理类
			InvocationHandler handler = new ServiceProxy(obj);
			//取得被代理之后的真实主体类
			Object proxyObjService = Proxy.newProxyInstance(handler.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
			return proxyObjService;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	} 
}
