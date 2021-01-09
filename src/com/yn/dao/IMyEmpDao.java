package com.yn.dao;

import com.yn.vo.MyEmp;

import java.util.List;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-13:08
 */
public interface IMyEmpDao {

    int insert(MyEmp myEmp) throws Exception;

    int update(MyEmp myEmp) throws Exception;

    int delete(Integer eid) throws Exception;

    List<MyEmp>  getList() throws Exception;

    MyEmp getOne(Integer eid) throws Exception;
}
