<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: SRKJ
  Date: 2017/4/24
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String basePath = request.getServletPath(); %>
<jsp:include page="../templates/header.jsp"></jsp:include>
<%
    Date current = new Date();
    Date previous = new Date();
    previous.setMonth(previous.getMonth() - 1);
%>
<title>基础信息管理</title>
</head>
<body>
<%--<jsp:include page="../templates/navbar.jsp"></jsp:include>--%>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top" role="navigation"  >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">零食店进存销系统</a>
            <ul class="nav navbar-nav">
                <li id="basicManage" onclick="onTitleClick('basicManage')"><a href="<c:url value='/basic/info'/> ">基础信息</a></li>
                <li id="purchaseManage" onclick="onTitleClick('purchaseManage')"><a href="<c:url value='/basic/purchase'/> ">采购管理</a></li>
                <li id="sellManage" onclick="onTitleClick('sellManage')"><a href="<c:url value='/basic/sell'/> ">销售管理</a></li>
                <li id="storageManage" onclick="onTitleClick('storageManage')"><a href="<c:url value='/basic/storage'/> ">库存管理</a></li>
                <li id="statistical" onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/statistical'/> ">统计管理</a></li>
                <li class="active" id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/financial'/> ">财务管理</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="panel panel-info" style="margin: 5px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">商品流水账单</h3>
        </div>
    </div>
    <div class="panel-body">
        <form>
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" hidden="hidden" id="purchaseId">
                    <input type="text" hidden="hidden" id="goodsId">
                    <label for="goodsName" class="control-label">商品名称</label>
                    <input type="text" class="form-control" id="goodsName">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <label for="beginTime" class="control-label">起始时间</label>

                    <div class='input-group date' >
                        <input type='text' class="form-control" id='beginTime' value="<%=new SimpleDateFormat("yyyy-MM-dd").format(previous)%>" readonly/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <label for="endTime" class="control-label">截止时间</label>

                    <div class='input-group date' >
                        <input type='text' class="form-control" id='endTime' value="<%=new SimpleDateFormat("yyyy-MM-dd").format(current)%>" readonly/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <label  class="control-label"></label>
                    <a class='btn btn-primary form-control'  onclick='getFinancial()' id="ds"  style="width: 50px;margin-top: 26px">查询</a>
                </div>
            </div>
            <table class="table table-bordered" id="financialTb">
                <thead>
                <tr>
                    <td>商品名称</td><td>交易对象</td><td>交易时间</td><td>经办人</td><td>收支类型</td><td>交易金额</td><td>已转账金额</td><td>欠款金额</td>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <div>
                <div>
                    <nav aria-label="Page navigation col-sm-11" style="text-align: center" >
                        <ul class="pagination">
                            <li><a  onclick="previous()">上一页</a></li>
                            <li><a  onclick="next()">下一页</a></li>
                            <%--<li><a  data-target="#queryStorage" data-toggle="modal">查询</a></li>--%>
                        </ul>
                    </nav>
                </div>
            </div>
        </form>

    </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>

</body>
<script type="text/javascript" src="<c:url value="/static/custom/goods.js"/> "></script>
<script type="text/javascript" src="<c:url value='/static/custom/category.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/custom/financial.js'/>"></script>
<script type="text/javascript" language="JavaScript">

    $(document).ready(function () {
        showTitle();
        $('#beginTime').datetimepicker({
            format: "yyyy-mm-dd",
            language: 'zh-CN',
            autoclose: true,
            pickerPosition: "bottom-left",
            minView:3,
            changeYear:true,
            startView:3,
            maxView:3
        });
        $('#endTime').datetimepicker({
            format: "yyyy-mm-dd",
            language: 'zh-CN',
            autoclose: true,
            changeYear:true,
            startView:3,
            pickerPosition: "bottom-left",
            minView:3,
            maxView:3
        });
    });
</script>

</html>