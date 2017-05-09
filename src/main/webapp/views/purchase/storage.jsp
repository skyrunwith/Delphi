<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/30
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  Date current = new Date();
  Date previous = new Date();
  previous.setMonth(previous.getMonth() - 1);
%>
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
        <li id="statistical" onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/statistical'/> ">统计管理</a></li>
        <li id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/financial'/> ">财务管理</a></li>

      </ul>
    </div>
  </nav>
</div>
<%--<div class="panel panel-default" style="margin: 5px">--%>
  <%--<div class="panel-body">--%>
    <%--<ul class="nav nav-tabs">--%>
      <%--<li class="active"><a href="#storageInfo" data-toggle="tab">商品库存统计</a>--%>
      <%--</li>--%>
      <%--<li class=""><a href="#storageModify" data-toggle="tab" onclick="">商品库存调整</a>--%>
      <%--</li>--%>
      <%--<li class=""><a href="#goodsStatistical" data-toggle="tab" onclick="">商品销售统计</a>--%>
      <%--</li>--%>
    <%--</ul>--%>

    <%--<div class="tab-content">--%>
      <%--<div class="tab-pane fade active in" id="storageInfo">--%>
        <%--<c:import url="storageinfo.jsp"></c:import>--%>
      <%--</div>--%>
      <%--<div class="tab-pane fade" id="storageModify">--%>
        <%--<c:import url="storagemodify.jsp"></c:import>--%>
      <%--</div>--%>
      <%--<div class="tab-pane fade" id="goodsStatistical">--%>
        <%--<c:import url="goodsStatistical.jsp"></c:import>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
<div class="panel panel-info" style="margin: 10px">
  <div class="panel-heading" style="min-height: 40px">
    <div>
      <h3 class="panel-title col-sm-2">商品库存统计</h3>

    </div>
  </div>
  <div class="panel-body">
    <form>
      <div class="col-sm-2">
        <div class="form-group">
          <input type="text" hidden="hidden" id="purchaseId">
          <input type="text" hidden="hidden" id="goodsId">
          <label for="goodsName" class="control-label">商品名称</label>
          <input type="text" class="form-control" id="goodsName" value="">
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="beginTime" class="control-label">起始日期</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='beginTime' value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(previous)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="endTime" class="control-label" >截止日期</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='endTime' value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(current)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label  class="control-label"></label>
          <a class='btn btn-primary form-control'  onclick='getStorage()'  style="width: 50px;margin-top: 26px">查询</a>
        </div>
      </div>
      <div class="col-sm-4">
        <div style="visibility: hidden">
          <label class="control-label"></label>
        </div>
      </div>
      <jsp:include page="storageinfo.jsp"/>
    </form>
  </div>
</div>
<div class="panel panel-info" style="margin: 10px">
  <div class="panel-heading" style="min-height: 40px">
    <div>
      <h3 class="panel-title col-sm-2">商品库存统计图</h3>

    </div>
  </div>
  <div class="panel-body">
    <form>
      <div class="col-sm-2">
        <div class="form-group">
          <%--<input type="text" hidden="hidden" id="purchaseId">--%>
          <%--<input type="text" hidden="hidden" id="goodsId">--%>
          <label for="goodsName" class="control-label">商品名称</label>
          <input type="text" class="form-control" id="goodsName1" value="">
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="beginTime1" class="control-label">起始日期</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='beginTime1' value="<%=new SimpleDateFormat("yyyy-MM").format(previous)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="endTime1" class="control-label" >截止日期</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='endTime1' value="<%=new SimpleDateFormat("yyyy-MM").format(current)%>" readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label  class="control-label"></label>
          <a class='btn btn-primary form-control'  onclick='getStorageChartData()'  style="width: 50px;margin-top: 26px">查询</a>
        </div>
      </div>
      <div class="col-sm-4">
        <div style="visibility: hidden">
          <label class="control-label"></label>
        </div>
      </div>
      <div class="col-sm-5" >
        <canvas id="myChart" height="450" width="600"></canvas>
        <div id="legend"></div>
      </div>
    </form>
  </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>

<%--updateStorage modal--%>
<div class="modal fade" id="updateStorage" tabindex="-1" role="dialog" aria-labelledby="updateStorage">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title" >修改库存信息</h5>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <input type="text" id="upGoodsId" hidden="hidden">
            <label for="upGoodsName" class="control-label">商品名称</label>
            <input type='text' class="form-control" id='upGoodsName' readonly/>

          </div>
          <div class="form-group">
            <label for="upLose" class="control-label">亏损量</label>
            <input type='number' class="form-control" id='upLose' />
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="upStorage()">确定</button>
      </div>
    </div>
  </div>
</div>
</body>

<%--<script type="text/javascript" src="<c:url value='/static/custom/goodsStatistical.js'/>"></script>--%>
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
//    getStorage1();
  });
</script>
</html>