<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/4/9
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String basePath = request.getServletPath(); %>
<jsp:include page="templates/header.jsp"></jsp:include>
<title>登录</title>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<!-- BOOTSTRAP STYLES-->
<%--<link href="../assets/css/bootstrap.css" rel="stylesheet"/>--%>
<!-- FONTAWESOME STYLES-->
<link href="<c:url value='/assets/css/font-awesome.css'/>" rel="stylesheet"/>
<!--CUSTOM BASIC STYLES-->
<link href="<c:url value='/assets/css/basic.css'/>" rel="stylesheet"/>
<!--CUSTOM MAIN STYLES-->
<link href="<c:url value='/assets/css/custom.css'/>" rel="stylesheet"/>
<!-- GOOGLE FONTS-->
<%--<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>--%>
<body>
<body style="background-color: #E2E2E2;">
<div class="container">
    <div class="row text-center " style="padding-top:100px;">
        <div class="col-md-12">
            <%--<img src="assets/img/logo-invoice.png"/>--%>
                <h2>零 食 店 进 存 销 系 统</h2>
        </div>
    </div>
    <div class="row ">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

            <div class="panel-body">
                <form role="form">
                    <hr/>
                    <%--<h5>     登  录</h5>--%>
                    <br/>

                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                        <input type="text" class="form-control" placeholder="账号 " id="username"/>
                    </div>
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" class="form-control" placeholder="密码" id="password"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox"/> 记住密码
                        </label>
                                            <%--<span class="pull-right">--%>
                                                   <%--<a href="index.html">Forget password ? </a>--%>
                                            <%--</span>--%>
                    </div>

                    <a class="btn btn-primary " onclick="login()">登录</a>
                    <%--<hr/>--%>
                    <%--Not register ? <a href="index.html">click here </a> or go to <a href="index.html">Home</a>--%>
                </form>
            </div>

        </div>


    </div>
</div>

</body>
<jsp:include page="templates/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    function login(){
        var data = new Object();
        data.username = $("#username").val();
        data.password = $("#password").val();
        $.post({
            url: "http://localhost:8080/Delphi/login/",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    window.location.href="http://localhost:8080/Delphi/basic/";
                }
            }
        });
    }
</script>
</html>
