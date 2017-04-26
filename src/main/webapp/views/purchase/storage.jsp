<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/30
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../templates/header.jsp"></jsp:include>
<title>库存管理</title>
<style type="text/css">
  .myBack div {
    background-color: #fff !important;
  }
  .myBorder div {
    border: 1px solid #ddd !important;
  }
  .myBorder2 div{
    border: 0.5px solid #ddd !important;
  }
</style>
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
        <li class="active"  id="storageManage" onclick="onTitleClick('storageManage')"><a href="<c:url value='/basic/storage'/> ">库存管理</a></li>
        <li id="statistical" onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/storage'/> ">统计管理</a></li>
        <li id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/storage'/> ">财务管理</a></li>

      </ul>
    </div>
  </nav>
</div>
<div class="panel panel-default" style="margin: 5px">
  <div class="panel-body">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#storageInfo" data-toggle="tab">商品库存统计</a>
      </li>
      <li class=""><a href="#storageModify" data-toggle="tab" onclick="">商品库存调整</a>
      </li>
      <li class=""><a href="#goodsStatistical" data-toggle="tab" onclick="">商品销售统计</a>
      </li>
    </ul>

    <div class="tab-content">
      <div class="tab-pane fade active in" id="storageInfo">
        <c:import url="storageinfo.jsp"></c:import>
      </div>
      <div class="tab-pane fade" id="storageModify">
        <c:import url="storagemodify.jsp"></c:import>
      </div>
      <div class="tab-pane fade" id="goodsStatistical">
        <c:import url="goodsStatistical.jsp"></c:import>
      </div>
    </div>
  </div>
  <jsp:include page="../templates/footer.jsp"></jsp:include>
</body>


<script type="text/javascript" language="JavaScript">
  $(document).ready(function () {
    $('#beginTime').datetimepicker({
      format: "yyyy-mm-dd hh:ii:ss",
      language: 'zh-CN',
      autoclose: true,
      todayBtn: true,
      pickerPosition: "bottom-left"
    });
    $('#endTime').datetimepicker({
      format: "yyyy-mm-dd hh:ii:ss",
      language: 'zh-CN',
      autoclose: true,
      todayBtn: true,
      pickerPosition: "bottom-left"
    });
    $('#beginTime1').datetimepicker({
      format: "yyyy-mm",
      language: 'zh-CN',
      autoclose: true,
      pickerPosition: "bottom-left",
      minView:3,
      changeYear:true,
      startView:3,
      maxView:3
    });
    $('#endTime1').datetimepicker({
      format: "yyyy-mm",
      language: 'zh-CN',
      autoclose: true,
      changeYear:true,
      startView:3,
      pickerPosition: "bottom-left",
      minView:3,
      maxView:3
    });
//    init();
    getStorage();
    getStorage1();
  });
</script>
</html>