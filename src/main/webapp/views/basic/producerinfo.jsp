<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">厂商清单</h3>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-bordered" id="producerTb" style="word-break:break-all; word-wrap:break-word;">
            <thead>
            <tr>
                <td>公司</td>
                <td>公司电话</td>
                <td>传真</td>
                <td>联系人</td>
                <td>联系人电话</td>
                <td>地址</td>
                <td>邮编</td>
                <td>电子邮件</td>
                <td>开户银行</td>
                <td>银行卡号</td>
                <td>备注</td>
                <td style="width:5%;text-align: center"><input type="checkbox" id="producerCheckAll"
                                                               onclick="producerCheckAll(this)"/></td>
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
                        <li><a >上一页</a></li>
                        <li><a >下一页</a></li>
                        <li><a  data-toggle="modal" data-target="#addProducerModal">新增</a></li>
                        <li><a  onclick="delProducer()">删除</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<%--Goods Modal--%>
<div class="modal fade" id="addProducerModal" tabindex="-1" role="dialog" aria-labelledby="addGoodsModalLabel">
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
                            <input type="text" hidden="hidden" id="producerId">
                            <label for="companyNameProducer" class="control-label">公司</label>
                            <input type="text" class="form-control" id="companyNameProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="phoneProducer" class="control-label">公司电话</label>
                            <input type="text" class="form-control" id="phoneProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="faxProducer" class="control-label">传真</label>
                            <input type="text" class="form-control" id="faxProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="contactNameProducer" class="control-label">联系人</label>
                            <input type="text" class="form-control" id="contactNameProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="contactPhoneProducer" class="control-label">联系人电话</label>
                            <input type="text" class="form-control" id="contactPhoneProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="addressProducer" class="control-label">地址</label>
                            <input type="text" class="form-control" id="addressProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="postalCodeProducer" class="control-label">邮编</label>
                            <input type="text" class="form-control" id="postalCodeProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="emailProducer" class="control-label">电子邮件</label>
                            <input type="text" class="form-control" id="emailProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="bankProducer" class="control-label">开户银行</label>
                            <input type="text" class="form-control" id="bankProducer">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="acctNoProducer" class="control-label">银行卡号</label>
                            <input type="text" class="form-control" id="acctNoProducer">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="commentProducer" class="control-label">备注</label>
                        <textarea class="form-control" id="commentProducer"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addOrUpdateProducer()">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value='/static/custom/producer.js'/>"></script>