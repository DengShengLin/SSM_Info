<%--
  Created by IntelliJ IDEA.
  User: dsl
  Date: 2019/5/6
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.min.js" ></script>

</head>
<body>


        <%--<a href="user/findAll">测试11</a><br>--%>

        <%--<form name="form1" action="user/login" method="post">--%>
            <%--用户名:<input type="text" name="username"/><br>--%>
            <%--密码:<input type="password" name="password"/><br>--%>
            <%--<input type="submit" value="登录"/>--%>
        <%--</form>--%>

        <h1>Ajax请求数据POST</h1>
        <form id="postForm">
            <input type="text" name="username" id="name">
            <input type="password" name="password" id="password" value="" />
            <input type="button" id="btn" value="提交">
        </form>

        <script type="text/javascript">

            $("#btn").click(function() {
                var username = $("#name").val();
                var password = $("#password").val();
                console.log(username + password + "这是html页面打印数据");
                $.ajax({
                    type: "post",
                    url: "http://localhost:8080/user/findAll",
                    async: true,
                    data: {
                        "username": username,
                        "password": password
                    },
                    success: function(data) {
                        var a = data.data.length;
                        console.log(a);
                        console.log("打印后台返回数据");

                    }
                });

            })
        </script>


</body>
</html>
