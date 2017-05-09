<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">商品列表</h3>
        </div>
    </div>
    <div class="panel-body">
        <div class="col-sm-1">
            <div class="list-group" style="margin-bottom: -1px">
                <a  class="list-group-item active "
                   style="border-bottom-left-radius: 0px;border-bottom-right-radius: 0px">
                    分类
                </a>
            </div>
            <div class="list-group" style="margin-bottom: -1px;" id="categoryTb">

            </div>
            <div>
                <nav aria-label="Page navigation col-sm-11" style="text-align: center">
                    <ul class="pagination">
                        <li><a  onclick="previousCategory()"> <span aria-hidden="true">&laquo;</span></a></li>
                        <li><a  onclick="nextCategory()"> <span aria-hidden="true">&raquo;</span></a></li>
                    </ul>
                </nav>
            </div>
            <div class="list-group">
                <%-- <div  class="list-group-item col-sm-12"  style="text-align: center;border-top-left-radius: 0px;border-top-right-radius: 0px"
                       data-toggle="modal" data-target="#addCategoryModal">
                     <img src="<c:url value="/static/img/add.png"/>" style="max-height: 42px; max-width: 42px;"/>
                 </div>--%>
            </div>
        </div>
        <div class="col-sm-11">
            <table class="table table-bordered table-hover" id="goodsTb">
                <thead>
                <tr>
                    <td>商品名称</td>
                    <td>生产地址</td>
                    <td>规格</td>
                    <td>包装</td>
                    <td>单价</td>
                    <td>售价</td>
                    <td>备注</td>
                    <td style="max-width: 20px;text-align: center"><input type="checkbox" id="goodsCheckAlls"
                                                                          onclick="goodsCheckAll(this)"/></td>
                    <td style="max-width: 20px;text-align: center">操作</td>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
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
                <li><a  data-toggle="modal" data-target="#addGoodsModal">新增</a></li>
                <li><a  id="delGoods" onclick="delGoods()">删除</a></li>
            </ul>
        </nav>

    </div>
</div>

<%--Goods Modal--%>
<div class="modal fade" id="addGoodsModal" tabindex="-1" role="dialog" aria-labelledby="addGoodsModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="addGoodsModalLabel">新增分类信息</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input type="text" hidden="hidden" id="goodsId"/>
                            <label for="nameGoodsLab" class="control-label">名称</label>
                            <input type="text" class="form-control" id="nameGoodsLab">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="birthPlace" class="control-label">产地</label>
                            <input type="text" class="form-control" id="birthPlace">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="specification" class="control-label">规格</label>
                            <input type="text" class="form-control" id="specification">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="packaging" class="control-label">包装</label>
                            <input type="text" class="form-control" id="packaging">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="purchasePrice" class="control-label">进价</label>
                            <input type="text" class="form-control" id="purchasePrice">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="sellPrice" class="control-label">售价</label>
                            <input type="text" class="form-control" id="sellPrice">
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <label for="commentGoodsLab" class="control-label">备注</label>
                            <textarea class="form-control" id="commentGoodsLab"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addGoodsBtn" onclick="addOrUpdateGoods()">保存</button>
            </div>
        </div>
    </div>
</div>

