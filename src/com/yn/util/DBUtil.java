package com.yn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**   
* 
* @Description:  自定义的操作数据库的工具类
* @ClassName: DBUtil.java
* @author: yn 
* @date: 2020年12月22日 上午11:09:57 
*/
public class DBUtil {
	
	private static PreparedStatement pst;
	private static ResultSet rst;
	
	/**
	 * 查询单条数据
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public static <T> T selectOne(Connection conn,String sql,Class<T> clz,Object...params)throws Exception {
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i+1, params[i]);
		}
		rst = pst.executeQuery();
		T t = null;
		if (rst.next()) {
			//实例化 T
			t=clz.newInstance();
			Field[] fields = clz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				//取得属性名称
				String fname =  fields[i].getName();
				//取得set方法
				Method method = clz.getDeclaredMethod("set"+upperCase(fname),fields[i].getType());
				Object fvalue=null;
				try {
					fvalue = rst.getObject(fname);
				} catch (SQLException e) {
				}
				method.invoke(t, fvalue);
			}
		}
		return t;
	}
	
	/**
	 * 查询多条数据
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public static <T> List<T> selectList(Connection conn,String sql,Class<T> clz,Object...params)throws Exception {
		List<T> obList = new ArrayList<>();
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i+1, params[i]);
		}
		rst = pst.executeQuery();
		T t = null;
		while (rst.next()) {
			//实例化 T
			t=clz.newInstance();
			Field[] fields = clz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				//取得属性名称
				String fname =  fields[i].getName();
				//取得set方法
				Method method = clz.getDeclaredMethod("set"+upperCase(fname),fields[i].getType());
				Object fvalue=null;
				try {
					fvalue = rst.getObject(fname);
				} catch (SQLException e) {
				}
				method.invoke(t, fvalue);
			}
			obList.add(t);
		}
		return obList;
	}
	/**
	 * 查询多条数据
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public static  int selectCount(Connection conn,String sql,Object...params)throws Exception {
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i+1, params[i]);
		}
		rst = pst.executeQuery();
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}
	/**
	 * 删除数据 参数为可变数组
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static int remove(Connection conn,String sql,Object...params)throws Exception{
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i+1, params[i]);
		}
		return pst.executeUpdate();
	}
	
	/**
	 * 删除数据 参数为主键集合
	 * @param conn
	 * @param sql
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public static int remove(Connection conn,StringBuffer sql,Set<?> ids)throws Exception{
		Iterator<?> iterator = ids.iterator();
		while(iterator.hasNext()){
			sql.append(iterator.next()+",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		pst = conn.prepareStatement(sql.toString());
		return pst.executeUpdate();
	}
	
	/**
	 * 编辑数据的方法
	 * UPDATE emp SET job="XXXX" ,sal=1100 WHERE empno=7942
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param vo
	 * @return
	 */
	public static <T> int edit(Connection conn,String sql,T vo) throws Exception {
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		//处理SQL 获取表字段名称
		String[] columns = sql.split("SET")[1].split("WHERE")[0].split(",");
		//获取实体类class对象
		Class<?> clz = vo.getClass();
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i].split("=")[0].trim();
			Method method = clz.getDeclaredMethod("get"+upperCase(column));
			//获取这个属性值
			Object fvalue = method.invoke(vo);
			pst.setObject(i+1, fvalue);
		}
		String conditions = sql.split("WHERE")[1].split("=")[0].trim();
		Object cvalue = clz.getDeclaredMethod("get"+upperCase(conditions)).invoke(vo);
		pst.setObject(columns.length+1, cvalue);
		return pst.executeUpdate();
	} 
	
	/**
	 * 编辑数据 使用可变数组作为参数
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static <T> int edit(Connection conn,String sql,Object...params) throws Exception {
		//先获取预编译对象
		pst=conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i+1, params[i]);
		}
		return pst.executeUpdate();
	} 
	
	
	/**
	 * 自定义增加的方法
	 * "insert into emp(ename,job,mgr,hiredate,sal,comm,deptno) values (?,?,?,?,?,?,?);"
	 * @param <T>  传入的数据实体类类型
	 * @param conn 数据库连接对象
	 * @param sql  执行的sql
	 * @param vo   传入的数据实体类对象
	 * @param getAutoKey  是否需要返回自增长id
	 * @return
	 */
	public static <T> int save(Connection conn,String sql,T vo,boolean getAutoKey) throws Exception{
		//先获取预编译对象
		pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		//处理SQL 获取表字段名称  () 特殊字符要使用“\\”转译
		String[] columns =sql.split("\\(")[1].split("\\)")[0].split(",");
		//获取实体类的class对象
		Class<? extends Object> clz = vo.getClass();
		//遍历字段数组
		for (int i = 0; i < columns.length; i++) {
			//将vo属性值赋值到占位符中
			//通过反射执行get方法
			Method method = clz.getDeclaredMethod("get"+upperCase(columns[i].trim()));
			//获取这个属性值
			Object fvalue = method.invoke(vo);
			//把值赋值给占位符
			pst.setObject(i+1, fvalue);
		}
		int row = pst.executeUpdate();

		if (getAutoKey) {
			// 需要获取主键 
			rst=pst.getGeneratedKeys();
			if (rst.next()) {
				return rst.getInt(1);
			}
		}
		return row;
		 
		
	}
	
	/**
	 * 将字段名首字母大写 用于getXxx 和setXxx 方法
	 * @param column
	 * @return
	 */
	private static String upperCase(String column) {
		return column.substring(0,1).toUpperCase()+column.substring(1);
	}
}
