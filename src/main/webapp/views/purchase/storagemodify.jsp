<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/30
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered" id="storageModifyList">
  <thead>
  <tr>
    <td>名称</td><td>总进货量</td><td>总销售量</td><td>亏损量</td><td>库存量</td><td>应付款</td><td>未付款</td><td>应收款</td><td>未收款</td><td>营业收入</td>
  </tr>
  </thead>
  <tbody>

  </tbody>
</table>
<div>
  <div>
    <nav aria-label="Page navigation col-sm-11" style="text-align: center" >
      <ul class="pagination">
        <li><a  onclick="previousModify()">上一页</a></li>
        <li><a  onclick="nextModify()">下一页</a></li>
      </ul>
    </nav>
  </div>
</div>

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

<script type="text/javascript" src="<c:url value='/static/custom/storageModify.js'/>"></script>