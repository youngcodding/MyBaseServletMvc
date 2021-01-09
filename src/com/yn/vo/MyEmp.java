package com.yn.vo;

import java.io.Serializable;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-12:59
 */
public class MyEmp implements Serializable {
    private  Integer eid;
    private  String ename;
    private  Integer eage;
    private  String esex;

    public MyEmp() {
    }

    public MyEmp(Integer eid, String ename, Integer eage, String esex) {
        this.eid = eid;
        this.ename = ename;
        this.eage = eage;
        this.esex = esex;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    @Override
    public String toString() {
        return "MyEmp{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", eage=" + eage +
                ", esex='" + esex + '\'' +
                '}';
    }
}
