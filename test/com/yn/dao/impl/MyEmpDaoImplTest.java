package com.yn.dao.impl;

import com.yn.dao.impl.MyEmpDaoImpl;
import com.yn.util.DruidConnection;
import com.yn.vo.MyEmp;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* MyEmpDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>01/09/2021</pre> 
* @version 1.0 
*/ 
public class MyEmpDaoImplTest {

    MyEmpDaoImpl myEmpDao =   new MyEmpDaoImpl(DruidConnection.getConnection());

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: insert(MyEmp myEmp) 
* 
*/ 
@Test
public void testInsert() throws Exception {
    MyEmp myEmp = new MyEmp(1234, "张三", 14, "男");
    TestCase.assertTrue(myEmpDao.insert(myEmp)>0);
} 

/** 
* 
* Method: update(MyEmp myEmp) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    MyEmp myEmp = new MyEmp(1234, "李四", 14, "男");
    TestCase.assertTrue(myEmpDao.update(myEmp)>0);
} 

/** 
* 
* Method: delete(Integer eid) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    TestCase.assertTrue(myEmpDao.delete(1234)>0);
} 

/** 
* 
* Method: getList() 
* 
*/ 
@Test
public void testGetList() throws Exception {
    System.out.println(myEmpDao.getList());
} 

/** 
* 
* Method: getOne(Integer eid) 
* 
*/ 
@Test
public void testGetOne() throws Exception {
    System.out.println(myEmpDao.getOne(7788));
}


} 
