package com.yn.proxy;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

import com.yn.annotation.Transactional;
import com.yn.util.DruidConnection;

/**   
* 
* @Description: 
* @ClassName: ServiceProxy.java
* @author: yn 
* @date: 2020年12月23日 上午9:45:26 
*/
public class ServiceProxy implements InvocationHandler {
	//代理的真实主体类
	private Object obj;

	public ServiceProxy(Object obj) {
		super();
		this.obj = obj;
	}
	public ServiceProxy() {
		super();
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		//取得连接
		Connection  conn = DruidConnection.getConnection();
		//取得真实主体类的class类对象（业务层的实现类对象）
		Class<? extends Object> clsClass = this.obj.getClass();
		//取得业务层实现类执行的方法
		Method m = clsClass.getMethod(method.getName(), method.getParameterTypes());
		//取得业务层实现类执行的方法的事务注解
		Transactional transactional = m.getAnnotation(Transactional.class);
		
		try {
			if (transactional==null) {
				//没有加事务
				for(Field fservice : clsClass.getDeclaredFields()) {
					//取消私有封装
					fservice.setAccessible(true);
					//获取的是dao层对象
					Object odao = fservice.get(this.obj);
					//获取的是dao对象的conn属性
					Field fdao = odao.getClass().getDeclaredField("conn");
					fdao.setAccessible(true);
					fdao.set(odao, conn);
				}
				//调用方法
				result=method.invoke(obj, args);
			}else {
				//有事务注解
				//取消事务的自动提交
				conn.setAutoCommit(false);
				for(Field fservice : clsClass.getDeclaredFields()) {
					//取消私有封装
					fservice.setAccessible(true);
					//获取的是dao层对象
					Object odao = fservice.get(this.obj);
					//获取的是dao对象的conn属性
					Field fdao = odao.getClass().getDeclaredField("conn");
					fdao.setAccessible(true);
					fdao.set(odao, conn);
				}
				//调用方法
				result=method.invoke(obj, args);
				//提交事务
				conn.commit();
			}
			
		} catch (Exception e) {
			//回滚事务
			conn.rollback();
			e.printStackTrace();
		}finally {
			//关闭连接
			DruidConnection.close(conn);
		}
		return result;
	}

}
