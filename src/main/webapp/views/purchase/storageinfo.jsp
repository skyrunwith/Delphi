<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/30
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  Date current = new Date();
  Date previous = new Date();
  previous.setMonth(previous.getMonth() - 1);
%>
<table class="table table-bordered" id="storageInfoList">
  <thead>
  <tr>
    <td>名称</td><td>产地</td><td>规格</td><td>包装</td><td>备注</td><td>库存量</td><td>总进货量</td><td>进货量</td><td>总销售量</td><td>销售量</td>
  </tr>
  </thead>
  <tbody>

  </tbody>
</table>
<div>
  <div>
    <nav aria-label="Page navigation col-sm-11" style="text-align: center" >
      <ul class="pagination">
        <li><a href="#" onclick="previous()">上一页</a></li>
        <li><a href="#" onclick="next()">下一页</a></li>
        <li><a href="#" data-target="#queryStorage" data-toggle="modal">查询</a></li>
      </ul>
    </nav>
  </div>
</div>

<%--queryStorage modal--%>
<div class="modal fade" id="queryStorage" tabindex="-1" role="dialog" aria-labelledby="queryStorage">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title" >查询库存信息</h5>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
              <label for="beginTime" class="control-label">起始日期</label>

              <div class='input-group date' >
                <input type='text' class="form-control" id='beginTime' value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(previous)%>" readonly/>
                <div class="input-group-addon">
                  <span class="glyphicon glyphicon-calendar"></span>
                </div>
              </div>
          </div>
          <div class="form-group">
            <label for="endTime" class="control-label" >截止日期</label>

            <div class='input-group date' >
              <input type='text' class="form-control" id='endTime' value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(current)%>" readonly/>
              <div class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="getStorage()">确定</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="<c:url value='/static/custom/storage.js'/>"></script>