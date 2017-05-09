<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/4/10
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../templates/header.jsp"></jsp:include>
<%
  Date current = new Date();
  Date previous = new Date();
  previous.setYear(previous.getYear() - 1);
%>
<title>统计管理</title>
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
        <li  id="basicManage" onclick="onTitleClick('basicManage')"><a href="<c:url value='/basic/info'/> ">基础信息</a></li>
        <li  id="purchaseManage" onclick="onTitleClick('purchaseManage')"><a href="<c:url value='/basic/purchase'/> ">采购管理</a></li>
        <li id="sellManage" onclick="onTitleClick('sellManage')"><a href="<c:url value='/basic/sell'/> ">销售管理</a></li>
        <li id="storageManage" onclick="onTitleClick('storageManage')"><a href="<c:url value='/basic/storage'/> ">库存管理</a></li>
        <li id="statistical" class="active"  onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/statistical'/> ">统计管理</a></li>
        <li id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/financial'/> ">财务管理</a></li>
      </ul>
    </div>
  </nav>
</div>
<div class="panel panel-info" style="margin: 5px">
  <div class="panel-heading" style="min-height: 40px">
    <div>
      <h3 class="panel-title col-sm-2">商品销售统计</h3>
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
            <input type='text' class="form-control" id='beginTime' value="<%=new SimpleDateFormat("yyyy-MM").format(previous)%>" readonly/>
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
            <input type='text' class="form-control" id='endTime' value="<%=new SimpleDateFormat("yyyy-MM").format(current)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label  class="control-label"></label>
          <a class='btn btn-primary form-control'  onclick='getSellChartData()' id="ds"  style="width: 50px;margin-top: 26px">查询</a>
        </div>
      </div>
      <div class="col-sm-4">
        <div style="visibility: hidden">
          <label class="control-label"></label>
        </div>
      </div>
      <div class="col-sm-5" >
        <canvas id="mySellChart" height="450" width="600"></canvas>
        <div id="legend"></div>
      </div>
    </form>
  </div>
</div>

<div class="panel panel-info" style="margin: 5px">
  <div class="panel-heading" style="min-height: 40px">
    <div>
      <h3 class="panel-title col-sm-2">商品采购统计</h3>
    </div>
  </div>
  <div class="panel-body">
    <form>
      <div class="col-sm-2">
        <div class="form-group">
          <%--<input type="text" hidden="hidden" id="purchaseId">--%>
          <%--<input type="text" hidden="hidden" id="goodsId">--%>
          <label for="goodsName" class="control-label">商品名称</label>
          <input type="text" class="form-control" id="goodsNamePurchase">
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="beginTime" class="control-label">起始时间</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='beginTimePurchase' value="<%=new SimpleDateFormat("yyyy-MM").format(previous)%>" readonly/>
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
            <input type='text' class="form-control" id='endTimePurchase' value="<%=new SimpleDateFormat("yyyy-MM").format(current)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label  class="control-label"></label>
          <a class='btn btn-primary form-control'  onclick='getPurchaseChartData()' id=""  style="width: 50px;margin-top: 26px">查询</a>
        </div>
      </div>
      <div class="col-sm-4">
        <div style="visibility: hidden">
          <label class="control-label"></label>
        </div>
      </div>
      <div class="col-sm-5" >
        <canvas id="myChartPurchase" height="450" width="600"></canvas>
        <div id="legendPurchase"></div>
      </div>
    </form>
  </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="<c:url value='/static/custom/goodsStatistical.js'/>"></script>
<script type="text/javascript" language="JavaScript">
  $(document).ready(function () {
    showTitle();
    $('#beginTime').datetimepicker({
      format: "yyyy-mm",
      language: 'zh-CN',
      autoclose: true,
      pickerPosition: "bottom-left",
      minView:3,
      changeYear:true,
      startView:3,
      maxView:3
    });
    $('#endTime').datetimepicker({
      format: "yyyy-mm",
      language: 'zh-CN',
      autoclose: true,
      changeYear:true,
      startView:3,
      pickerPosition: "bottom-left",
      minView:3,
      maxView:3
    });
    $('#beginTimePurchase').datetimepicker({
      format: "yyyy-mm",
      language: 'zh-CN',
      autoclose: true,
      pickerPosition: "bottom-left",
      minView:3,
      changeYear:true,
      startView:3,
      maxView:3
    });
    $('#endTimePurchase').datetimepicker({
      format: "yyyy-mm",
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