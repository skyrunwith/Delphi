<%--
  Created by IntelliJ IDEA.
  User: SRKJ
  Date: 2017/4/24
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered" style="word-break:break-all; word-wrap:break-word;" id="customerTb">
    <thead>
    <tr>
        <td>公司</td><td>公司电话</td><td>传真</td><td>联系人</td><td>联系人电话</td><td>地址</td><td>邮编</td><td>电子邮件</td><td>备注</td>
        <td style="width:5%;text-align: center"><input type="checkbox" id="customerCheckAll" onclick="customerCheckAll(this)" /></td>
        <td style="width:5%;text-align: center">操作</td>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
<div>
    <div>
        <nav aria-label="Page navigation col-sm-11" style="text-align: center">
            <ul class="pagination">
                <li><a href="#">上一页</a></li>
                <li><a href="#">下一页</a></li>
                <li><a href="#" data-toggle="modal" data-target="#addCustomerModal">新&nbsp;&nbsp;增</a></li>
                <li><a href="#" onclick="delCustomer()">删&nbsp;&nbsp;除</a></li>
            </ul>
        </nav>
    </div>
</div>

<%--Goods Modal--%>
<div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addGoodsModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="addGoodsModalLabel">新增分类信息</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input type="text" hidden="hidden" id="customerId">
                            <label for="companyNameCustomer" class="control-label">公司</label>
                            <input type="text" class="form-control" id="companyNameCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="phoneCustomer" class="control-label">公司电话</label>
                            <input type="text" class="form-control" id="phoneCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="faxCustomer" class="control-label">传真</label>
                            <input type="text" class="form-control" id="faxCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="contactNameCustomer" class="control-label">联系人</label>
                            <input type="text" class="form-control" id="contactNameCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="contactPhoneCustomer" class="control-label">联系人电话</label>
                            <input type="text" class="form-control" id="contactPhoneCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="addressCustomer" class="control-label">地址</label>
                            <input type="text" class="form-control" id="addressCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="postalCodeCustomer" class="control-label">邮编</label>
                            <input type="text" class="form-control" id="postalCodeCustomer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="emailCustomer" class="control-label">电子邮件</label>
                            <input type="text" class="form-control" id="emailCustomer">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="commentCustomer" class="control-label">备注</label>
                        <textarea class="form-control" id="commentCustomer"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addOrUpdateCustomer()">保存</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value='/static/custom/customer.js'/>"></script>