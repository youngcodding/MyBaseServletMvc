package com.yn.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/**
 * @author yn
 * @class MyBaseServletMvc
 * @describe
 * @date 2021/1/9-15:30
 */
public abstract class BaseServlet extends HttpServlet {


    public void print(HttpServletResponse resp, Object msg) {
        PrintWriter out =null;
        try {
            out = resp.getWriter();
            out.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
    public <T> T initObj(HttpServletRequest req, Class<T> clz) {
        T t = null;
        try {
            t = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                String fname = fields[i].getName();
                String fvalue = req.getParameter(fname);
                if ("Double".equals(fields[i].getType().getSimpleName())) {
                    fields[i].set(t, Double.parseDouble(fvalue));
                }else if("Integer".equals(fields[i].getType().getSimpleName())) {
                    fields[i].set(t, Integer.parseInt(fvalue));
                }else if("Date".equals(fields[i].getType().getSimpleName())) {
                    fields[i].set(t, new SimpleDateFormat("yyyy-MM-dd").parse(fvalue));
                }else {
                    fields[i].set(t, fvalue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


}
