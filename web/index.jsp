<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/1/9
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
      $(function(){

          $.ajax({
              type:"get",
              url:"myemp/list",
              data:"",
              dataType:"json",
              success:function (data) {
                  var table = $("table");
                  $("table tr:gt(0)").remove();
                  $.each(data,function (index) {
                      table.append("<tr>"+
                          "<td>" +data[index].eid +"</td>"+
                          "<td>" +data[index].ename +"</td>"+
                          "<td>" +data[index].eage +"</td>"+
                          "<td>" +data[index].esex +"</td>"+
                          "<td><button class='update' type='button'>修改</button><button class='delete' type='button'>删除</button></td>"+
                          "</tr>");
                  });

                  $("table tr button[class='update']").click(function () {
                      var eid = $(this).parents("tr").find("td:eq(0)").text();
                      window.location='pages/update.jsp?eid='+eid;
                  });
                  $("table tr button[class='delete']").click(function () {
                      var eid = $(this).parents("tr").find("td:eq(0)").text();
                      var flag = window.confirm("确认删除编号为："+eid+" 的雇员吗？");
                      if (!flag){
                          return;
                      }
                      var tr = $(this).parents("tr");
                      tr.fadeOut(2000,function () {
                          tr.remove();
                          removeById(eid);
                      })
                  });
              }
          });
      });
      function removeById(eid) {
          $.ajax({
              type:"get",
              url:"myemp/remove",
              data:"eid="+eid,
              dataType:"json",
              success:function (data) {
                  if (data=="1"){
                      alert("删除成功！")
                  }else {
                      alert("删除失败！")
                  }
              }


          })
      }
    </script>
  </head>

  <body>
  <a href="pages/insert.jsp">增加雇员信息</a><br>
  <table border="1">
    <tr>
      <td>雇员编号</td><td>雇员姓名</td><td>雇员年龄</td><td>雇员性别</td><td>操作</td>
    </tr>
  </table>
  </body>
</html>
