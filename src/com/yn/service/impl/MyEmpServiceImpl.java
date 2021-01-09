package com.yn.service.impl;

import com.yn.dao.IMyEmpDao;
import com.yn.dao.impl.MyEmpDaoImpl;
import com.yn.service.IMyEmpService;
import com.yn.vo.MyEmp;

import java.util.List;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-14:15
 */
public class MyEmpServiceImpl implements IMyEmpService {

    private static IMyEmpDao myEmpDao = new MyEmpDaoImpl();
    @Override
    public boolean addMyEmp(MyEmp myEmp) throws Exception {
        return myEmpDao.insert(myEmp)>0;
    }

    @Override
    public boolean editMyemp(MyEmp myEmp) throws Exception {
        return myEmpDao.update(myEmp)>0;
    }

    @Override
    public boolean removeById(Integer eid) throws Exception {
        return myEmpDao.delete(eid)>0;
    }

    @Override
    public List<MyEmp> findAllList() throws Exception {
        return myEmpDao.getList();
    }

    @Override
    public MyEmp findById(Integer cid) throws Exception {
        return myEmpDao.getOne(cid);
    }
}
