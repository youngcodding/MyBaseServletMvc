package com.yn.util;
/**   
* 
* @Description: 
* @ClassName: DruidConnection.java
* @author: yn 
* @date: 2020年12月23日 上午11:41:18 
*/

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidConnection {
	//实例化一个数据原对象
	private static DruidDataSource dataSource = new DruidDataSource();
	//使用静态代码块微数据原初始化值
	static {
		//配置连接
		dataSource.setUrl("jdbc:mysql://192.168.0.110:3306/demo?useSSL=true&useUnicode=true&characterEncoding=UTF-8");
		//用户名
		dataSource.setUsername("root");
		//密码
		dataSource.setPassword("123456");
		//驱动地址
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//初始化连接大小
		dataSource.setInitialSize(10);
		//连接池最大使用连接数量
		dataSource.setMaxActive(20);
		//获取连接最大等待时间
		dataSource.setMaxWait(3000);
		
	}
	/**
	 * 从池中获取连接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 使用完毕 归还到池中
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn!=null) {
			try {
				//从池中取出来的连接 会归还到池中
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
}
