<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">员工列表</h3>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-bordered table-striped" style="word-break:break-all; word-wrap:break-word;"
               id="employeeTb">
            <thead>
            <tr>
                <td>姓名</td>
                <td>电话</td>
                <td>职位</td>
                <td>住址</td>
                <td>年龄</td>
                <td>毕业学校</td>
                <td>专业</td>
                <td>工作经历</td>
                <td style="width:5%;text-align: center"><input type="checkbox" id="employeeCheckAll"
                                                               onclick="employeeCheckAll(this)"/></td>
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
                        <li><a  data-toggle="modal" data-target="#addEmployeeModal">新&nbsp;&nbsp;增</a></li>
                        <li><a  onclick="delEmployee()">删&nbsp;&nbsp;除</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<%--Goods Modal--%>
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="addEmployeeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="addEmployeeModalLabel">新增员工信息</h5>
            </div>
            <div class="modal-body">
                <form id="employeeForm">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input type="text" hidden="hidden" id="id" name="id" value="0">
                            <label for="name" class="control-label">姓名</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="phone" class="control-label">电话</label>
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="age" class="control-label">年龄</label>
                            <input type="number" class="form-control" id="age" name="age">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="position" class="control-label">职位</label>
                            <input type="text" class="form-control" id="position" name="position">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="addr" class="control-label">住址</label>
                            <input type="text" class="form-control" id="addr" name="addr">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="college" class="control-label">毕业学校</label>
                            <input type="text" class="form-control" id="college" name="college">
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="major" class="control-label">专业</label>
                            <input type="text" class="form-control" id="major" name="major">
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="career" class="control-label">工作经历</label>
                            <textarea class="form-control" id="career" name="career"></textarea>
                        </div>
                    </div>
                    <div style="visibility: hidden;">
                        <label class="control-label"></label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addOrUpdateEmployee()">保存</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value='/static/custom/employee.js'/>"></script>