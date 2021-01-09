<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/1/9
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加雇员</title>
    <base href="/MyBaseServletMvc/">
</head>
<body>
        <h3>${errorMsg}</h3>
        <form action="myemp/add" method="post" >
            <fieldset>
                <legend>请添加雇员信息</legend>
                雇员编号：<input type="text" name="eid"><br><br>
                雇员姓名：<input type="text" name="ename"><br><br>
                雇员年龄：<input type="text" name="eage"><br><br>
                雇员性别：<input type="text" name="esex"><br><br>
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </fieldset>
        </form>
</body>
</html>
