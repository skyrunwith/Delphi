<%--
  Created by IntelliJ IDEA.
  User: FZD
  Date: 2017/3/20
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel panel-info" style="margin: 10px">
    <div class="panel-heading" style="min-height: 40px">
        <div>
            <h3 class="panel-title col-sm-2">分类列表</h3>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-bordered" id="categoryList">
            <thead>
            <tr>
                <td>名称</td>
                <td>备注</td>
                <td style="max-width: 20px;text-align: center"><input type="checkbox" id="categoryCheckAll"
                                                                      onclick="categoryCheckAll(this)"/></td>
                <td style="max-width: 20px;text-align: center">操作</td>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <div>
            <div>
                <nav aria-label="Page navigation col-sm-11" style="text-align: center">
                    <ul class="pagination">
                        <li><a  onclick="previousCategory()">上一页</a></li>
                        <li><a  onclick="nextCategory()">下一页</a></li>
                        <li><a  data-target="#addCategoryModal" data-toggle="modal">新增</a></li>
                        <li><a  onclick="deleteCategory()">删除</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<%--Category modal--%>
<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="addCategoryModalLabel">新增分类</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="text" hidden="hidden" id="categoryId"/>
                        <label for="nameCategoryLab" class="control-label">名称</label>
                        <input type="text" class="form-control" id="nameCategoryLab">
                    </div>
                    <div class="form-group">
                        <label for="commentCategoryLab" class="control-label">备注</label>
                        <textarea class="form-control" id="commentCategoryLab"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addCategoryBtn" onclick="addOrUpdateCategory()">保存
                </button>
            </div>
        </div>
    </div>
</div>