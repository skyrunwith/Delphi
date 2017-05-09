<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/20
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../templates/header.jsp"></jsp:include>
<title>采购管理</title>
</head>
<body>
<%--<jsp:include page="../templates/navbar.jsp"></jsp:include>--%>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">零食店进存销系统</a>
            <ul class="nav navbar-nav">
                <li id="basicManage" onclick="onTitleClick('basicManage')"><a
                        href="<c:url value='/basic/info'/> ">基础信息</a></li>
                <li id="purchaseManage" class="active" onclick="onTitleClick('purchaseManage')"><a
                        href="<c:url value='/basic/sell/sell_customer'/> ">采购管理</a></li>
                <li  id="sellManage" onclick="onTitleClick('sellManage')"><a
                        href="<c:url value='/basic/sell'/> ">销售管理</a></li>
                <li id="storageManage" onclick="onTitleClick('storageManage')"><a
                        href="<c:url value='/basic/storage'/> ">库存管理</a></li>
                <li id="statistical" onclick="onTitleClick('statistical')"><a href="<c:url value='/basic/statistical'/> ">统计管理</a></li>
                <li id="financial" onclick="onTitleClick('financial')"><a href="<c:url value='/basic/financial'/> ">财务管理</a></li>

            </ul>
        </div>
    </nav>
</div>
<%--<div class="panel panel-info" style="margin: 10px">--%>
    <%--<div class="panel-heading" style="min-height: 40px">--%>
        <%--<div>--%>
            <%--<h3 class="panel-title col-sm-2">商品销售</h3>--%>
            <%--<div class="col-sm-10">--%>
                <%--<a  style="float: right;margin: 5px" data-toggle="modal" data-target="#add"--%>
                   <%--onclick="upOption('INSERT')">新增</a>--%>
                <%--<a  style="float: right;margin: 5px" onclick="addOrUpdateSell()">保存</a>--%>
                <%--<a  style="float: right;margin: 5px" onclick="upOption('DELETE')">删除</a>--%>
                <%--<a  style="float: right;margin: 5px" data-toggle="modal" data-target="#query">查询</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="panel-body">--%>
        <%--<form>--%>
            <%--<div class="col-sm-4">--%>
                <%--<div class="form-group">--%>
                    <%--<input type="text" hidden="hidden" id="sellId">--%>
                    <%--<input type="text" hidden="hidden" id="goodsId">--%>
                    <%--<label for="goodsName" class="control-label">商品名称</label>--%>
                    <%--<input type="text" class="form-control" id="goodsName" disabled>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="customerName" class="control-label">客户</label>--%>
                    <%--<select type="text" class="form-control" id="customerName"></select>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="sellTime" class="control-label">销售时间</label>--%>

                    <%--<div class='input-group date'>--%>
                        <%--<input type='text' class="form-control" id='sellTime' readonly/>--%>

                        <%--<div class="input-group-addon">--%>
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-2">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="unitPrice" class="control-label">单价</label>--%>
                    <%--<input type="number" class="form-control" id="unitPrice" onchange="countTotalPrice()" min="0" readonly>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-2">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="totalNumber" class="control-label">销售数量</label>--%>
                    <%--<input type="number" class="form-control" id="totalNumber" onchange="countTotalPrice()" min="0">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-2">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="totalPrice" class="control-label">总价</label>--%>
                    <%--<input type="number" disabled class="form-control" id="totalPrice">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-3">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="paid" class="control-label">已支付</label>--%>
                    <%--<input type="number" class="form-control" id="paid" min="0" onchange="countDebt()">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-3">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="debt" class="control-label">欠款</label>--%>
                    <%--<input type="text" disabled class="form-control" id="debt">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<div class="col-sm-3">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="storage" class="control-label">库存量</label>--%>
                        <%--<input type="number" disabled class="form-control" id="storage">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="sales" class="control-label">销售量</label>--%>
                        <%--<input type="number" disabled class="form-control" id="sales">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-sm-6">--%>
                    <%--<div class="form-group" style="visibility: hidden">--%>
                        <%--<label for="zhanwei" class="control-label">销售量</label>--%>
                        <%--<input type="text" class="form-control" id="zhanwei">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
                <%--<label for="comment" class="control-label">备注</label>--%>
                <%--<textarea class="form-control" id="comment"></textarea>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">未完成销售清单</h3>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-bordered" id="sellTbUn" style="word-break:break-all; word-wrap:break-word;">
            <thead>
            <tr>
                <td>商品名称</td>
                <td>客户</td>
                <td>销售时间</td>
                <td>单价</td>
                <td>销售数量</td>
                <td>总价</td>
                <td>已支付</td>
                <td>欠款</td>
                <td>库存量</td>
                <td>销售量</td>
                <td>备注</td>
                <%--<td style="width:5%;text-align: center"><input type="checkbox" id="sellCheckAllUn"--%>
                                                               <%--onclick="sellCheckAll(this)"/></td>--%>
                <%--<td>操作</td>--%>
            </tr>
            </thead>
            <tbody>

            </tbody>
            <%--<tfoot>--%>
            <%--<tr>--%>
            <%--<td>--%>

            <%--</td>--%>
            <%--</tr>--%>
            <%--</tfoot>--%>
        </table>
        <nav aria-label="Page navigation col-sm-11" style="text-align: center">
            <ul class="pagination">
                <li>
                    <a  aria-label="Previous" onclick="previousUn()">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
                <li>
                    <a  aria-label="Next" onclick="nextUn()">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
                <li > <a  aria-label="confrim" onclick="confirmComplete()">
                    <span aria-hidden="true">确认</span>
                </a></li>
            </ul>
        </nav>
    </div>
</div>

<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">已完成销售清单</h3>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-bordered" id="sellTb" style="word-break:break-all; word-wrap:break-word;">
            <thead>
            <tr>
                <td>商品名称</td>
                <td>客户</td>
                <td>销售时间</td>
                <td>单价</td>
                <td>销售数量</td>
                <td>总价</td>
                <td>已支付</td>
                <td>欠款</td>
                <td>库存量</td>
                <td>销售量</td>
                <td>备注</td>
                <%--<td style="width:5%;text-align: center"><input type="checkbox" id="sellCheckAll"--%>
                                                               <%--onclick="sellCheckAll(this)"/></td>--%>
                <%--<td>操作</td>--%>
            </tr>
            </thead>
            <tbody>

            </tbody>
            <%--<tfoot>--%>
            <%--<tr>--%>
            <%--<td>--%>

            <%--</td>--%>
            <%--</tr>--%>
            <%--</tfoot>--%>
        </table>
        <nav aria-label="Page navigation col-sm-11" style="text-align: center">
            <ul class="pagination">
                <li>
                    <a  aria-label="Previous" onclick="previous()">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
                <li>
                    <a  aria-label="Next" onclick="next()">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<%--Sell add modal--%>
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" >
    <div class="modal-dialog" role="document" >
        <div class="modal-content" style="min-height: 611px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="addLabel">商品信息</h5>
            </div>
            <div class="modal-body">
                <div class="col-sm-2" style="padding: 0px;">
                    <div class="list-group" style="margin-bottom: -1px;text-align: center">
                        <a  class="list-group-item active "
                           style="border-bottom-left-radius: 0px;border-bottom-right-radius: 0px;font-size: 10px;padding: 0px;min-height: 35px;text-align: center">
                            分类
                        </a>
                    </div>
                    <div class="list-group" style="margin-bottom: -1px;" id="categoryTb">

                    </div>
                    <div style="margin-top: 0px;padding-top: 0px">
                        <nav aria-label="Page navigation col-sm-11" style="text-align: center;">
                            <ul class="pagination" style="margin-top: 0px;padding-top: 0px;">
                                <li>
                                    <a  aria-label="Previous" onclick="previousCategory()">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li>
                                    <a  aria-label="Next" onclick="nextCategory()">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-sm-10" style="padding: 0px;">
                    <table class="table table-bordered table-hover" id="goodsTb">
                        <thead>
                        <tr style="font-size: 10px">
                            <td>商品名称</td>
                            <td>生产地址</td>
                            <td>规格</td>
                            <td>包装</td>
                            <td>售价(元)</td>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <nav aria-label="Page navigation col-sm-11" style="text-align: center">
                        <ul class="pagination" style="margin-top: 0px;padding-top: 3px;">
                            <li>
                                <a  aria-label="Previous" onclick="previous1()">
                                    <span aria-hidden="true">上一页</span>
                                </a>
                            </li>
                            <li>
                                <a  aria-label="Next" onclick="next1()">
                                    <span aria-hidden="true">下一页</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div style="visibility: hidden">
                    <div class="form-group">
                        <label class="control-label">备注</label>
                    </div>
                </div>
            </div>
            <%--<div class="modal-footer">--%>

            <%--</div>--%>
        </div>
    </div>
</div>
<%--Sell query modal--%>
<div class="modal fade" id="query" tabindex="-1" role="dialog" aria-labelledby="add">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="queryLabel">查询是销售信息</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="queryGoodsName" class="control-label">商品名称</label>
                            <input type="text" class="form-control" id="queryGoodsName">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="queryCustomerName" class="control-label">客户</label>
                            <input type="text" class="form-control" id="queryCustomerName">
                        </div>
                    </div>
                    <div style="visibility: hidden;">
                        <label class="control-label"></label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="query()">确定</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="<c:url value='/static/custom/goods2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/custom/category1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/custom/customer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/custom/sell_customer.js'/>"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function () {
        showTitle();
//        $('#sellTime').datetimepicker({
//            format: "yyyy/mm/dd hh:ii:ss",
//            language: 'zh-CN',
//            autoclose: true,
//            todayBtn: true,
//            pickerPosition: "bottom-left"
//        });
//        getAllCategories();
//        initCustomerSelect();
        getSellByParams();
        getSellByParamsUn();
    });
</script>
</html>