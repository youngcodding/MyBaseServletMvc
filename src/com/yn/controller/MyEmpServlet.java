package com.yn.controller;

import com.alibaba.fastjson.JSON;
import com.yn.dao.IMyEmpDao;
import com.yn.factory.ServiceFactory;
import com.yn.service.IMyEmpService;
import com.yn.service.impl.MyEmpServiceImpl;
import com.yn.vo.MyEmp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-14:24
 */
@WebServlet(urlPatterns = {"/myemp/*"})
public class MyEmpServlet extends BaseServlet {

    private IMyEmpService service = (IMyEmpService)ServiceFactory.getInstance(MyEmpServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/get".equals(pathInfo)){
            getMyEmp(req,resp);
        }else if("/list".equals(pathInfo)){
            getMyEmpList(req,resp);
        }else if ("/add".equals(pathInfo)){
            addMyEmp(req,resp);
        }else if ("/update".equals(pathInfo)){
            update(req,resp);
        }else if ("/remove".equals(pathInfo)){
            remove(req,resp);
        }

    }
    public void remove(HttpServletRequest req, HttpServletResponse resp){
        String eid = req.getParameter("eid");
        try {
             if (service.removeById(Integer.parseInt(eid))){
                 super.print(resp,1);
             }else {
                 super.print(resp,0);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp){
        try {
            MyEmp myEmp = super.initObj(req, MyEmp.class);
            if (service.editMyemp(myEmp)){
                //添加成功
                resp.sendRedirect("/MyBaseServletMvc/index.jsp");
            }else {
                req.setAttribute("errorMsg", "修改失败，请重试！");
                req.getRequestDispatcher("/pages/update.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addMyEmp(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyEmp myEmp = super.initObj(req, MyEmp.class);
            if (service.addMyEmp(myEmp)) {
                //添加成功
                resp.sendRedirect("/MyBaseServletMvc/index.jsp");
            } else {
                req.setAttribute("errorMsg", "添加失败，请重试！");
                req.getRequestDispatcher("/pages/insert.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getMyEmpList(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<MyEmp> allList = service.findAllList();
            super.print(resp, JSON.toJSONString(allList));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getMyEmp(HttpServletRequest req, HttpServletResponse resp){
        String eid = req.getParameter("eid");
        try {
            MyEmp emp = service.findById(Integer.parseInt(eid));
            super.print(resp, JSON.toJSONString(emp));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
}
}
