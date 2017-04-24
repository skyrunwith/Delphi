<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/4/10
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-info" style="margin: 5px">
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
          <label for="beginTime1" class="control-label">起始时间</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='beginTime1' readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label for="endTime1" class="control-label">截止时间</label>

          <div class='input-group date' >
            <input type='text' class="form-control" id='endTime1' readonly/>
            <div class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <div class="form-group">
          <label  class="control-label"></label>
          <a class='btn btn-primary form-control'  onclick='getChartData1()' id="ds"  style="width: 50px;margin-top: 26px">查询</a>
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
<script type="text/javascript" src="<c:url value='/static/custom/goodsStatistical.js'/>"></script>
