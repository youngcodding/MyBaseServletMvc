<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/1/9
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改雇员信息</title>
    <base href="/MyBaseServletMvc/">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            var url = location.search;
            var eid = url.split("=")[1];
            $.ajax({
                type:"get",
                url:"myemp/get",
                data:"eid="+eid,
                dataType:"json",
                success:function (data) {
                    $("input[name='eid']").val(data.eid);
                    $("input[name='ename']").val(data.ename);
                    $("input[name='eage']").val(data.eage);
                    $("input[name='esex']").val(data.esex);
                }
            })
        })
    </script>
</head>
<body>
        <h3>${errorMsg}</h3>
        <form action="myemp/update" method="post" >
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
