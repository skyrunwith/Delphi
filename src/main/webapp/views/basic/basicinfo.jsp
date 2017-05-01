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
                <li class="active" id="basicManage" onclick="onTitleClick('basicManage')"><a href="<c:url value='/basic/info'/> ">基础信息</a></li>
                <li id="purchaseManage" onclick="onTitleClick('purchaseManage')"><a href="<c:url value='/basic/purchase'/> ">采购管理</a></li>
                <li id="sellManage" onclick="onTitleClick('sellManage')"><a href="<c:url value='/basic/sell'/> ">销售管理</a></li>
                <li id="storageManage" onclick="onTitleClick('storageManage')"><a href="<c:url value='/basic/storage'/> ">库存管理</a></li>
                <li id="statistical" onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/statistical'/> ">统计管理</a></li>
                <li id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/financial'/> ">财务管理</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="panel panel-default" style="margin: 5px;">
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#categoryinfo" data-toggle="tab" id="categoryTab">分类信息管理</a>
            </li>
            <li class=""><a href="#goodsinfo" data-toggle="tab" id="goodsTab">商品信息管理</a>
            </li>
            <li class="" id = "producerinfoTitle"><a href="#producerinfo" data-toggle="tab" onclick="onClickProducerNav()">供应商信息管理</a>
            </li>
            <li class="" id = "clientinfoTitle"><a href="#clientinfo" data-toggle="tab" onclick="onClickCustomerNav()">客户信息管理</a>
            </li>
            <li class="" id = "employeeTitle"><a href="#employee" data-toggle="tab" onclick="onClickEmployeeNav()">员工信息管理</a>
            </li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade active in" id="categoryinfo">
                <c:import url="categoryinfo.jsp"></c:import>
            </div>
            <div class="tab-pane fade " id="goodsinfo">
                <c:import url="goodsinfo.jsp"></c:import>
            </div>
            <div class="tab-pane fade" id="producerinfo">
                <c:import url="producerinfo.jsp"></c:import>
            </div>
            <div class="tab-pane fade" id="clientinfo">
                <c:import url="customerinfo.jsp"></c:import>
            </div>

            <div class="tab-pane fade" id="employee">
                <c:import url="employee.jsp"></c:import>
            </div>
        </div>
    </div>
<jsp:include page="../templates/footer.jsp"></jsp:include>

</body>
<script type="text/javascript" src="<c:url value="/static/custom/goods.js"/> "></script>
<script type="text/javascript" src="<c:url value='/static/custom/category.js'/>"></script>
<script type="text/javascript" language="JavaScript">

    $(document).ready(function () {
        showTitle();
        getAllCategories();

        $('#addProducerModal').on('hide.bs.modal', function () {
            resetProducerForm();
        });

        hideTitle();
    });

    function hideTitle(){

    }
</script>

</html>