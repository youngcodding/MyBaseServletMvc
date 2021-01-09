package com.yn.dao.impl;

import com.yn.dao.IMyEmpDao;
import com.yn.util.DBUtil;
import com.yn.vo.MyEmp;

import java.sql.Connection;
import java.util.List;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-13:13
 */
public class MyEmpDaoImpl implements IMyEmpDao {

    private Connection conn;

    public MyEmpDaoImpl() {
    }

    public MyEmpDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(MyEmp myEmp) throws Exception {
        String sql = "insert into myemp(eid,ename,eage,esex) values(?,?,?,?)";
        return DBUtil.save(conn,sql,myEmp,false);
    }

    @Override
    public int update(MyEmp myEmp) throws Exception {
        String sql="update myemp SET ename=?,eage=?,esex=? WHERE eid=?";
        return DBUtil.edit(conn,sql,myEmp);
    }

    @Override
    public int delete(Integer eid) throws Exception {
        String sql="delete from myemp where eid = ?";
        return DBUtil.remove(conn,sql,eid);
    }

    @Override
    public List<MyEmp> getList() throws Exception {
        String sql="select eid , ename ,eage, esex from myemp";
        return DBUtil.selectList(conn,sql,MyEmp.class);
    }

    @Override
    public MyEmp getOne(Integer eid) throws Exception {
        String sql="select eid , ename ,eage, esex from myemp where eid=?";
        return DBUtil.selectOne(conn,sql,MyEmp.class,eid);
    }
}
