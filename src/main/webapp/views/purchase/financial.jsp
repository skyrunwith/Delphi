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
<div class="panel panel-default" style="margin: 5px;">

    <jsp:include page="../templates/footer.jsp"></jsp:include>

</body>
<script type="text/javascript" src="<c:url value="/static/custom/goods.js"/> "></script>
<script type="text/javascript" src="<c:url value='/static/custom/category.js'/>"></script>
<script type="text/javascript" language="JavaScript">

    $(document).ready(function () {
//        showTitle();
//        getAllCategories();
//
//        $('#addProducerModal').on('hide.bs.modal', function () {
//            resetProducerForm();
//        });
    });
</script>

</html>