package com.yn.service;

import com.yn.vo.MyEmp;

import java.util.List;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-14:06
 */
public interface IMyEmpService {

    boolean addMyEmp(MyEmp myEmp) throws Exception;

    boolean editMyemp(MyEmp myEmp) throws Exception;

    boolean removeById(Integer eid) throws Exception;

    List<MyEmp> findAllList() throws Exception;

    MyEmp findById(Integer cid) throws Exception;
}
