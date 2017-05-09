/**
 * Created by FZD on 2017/3/20.
 */
var categoryArr = new Array();
var categoryPageIndex = 1;
var categoryPageCount = -1;
//更新分类列表
function setCategory(list){
    categoryArr = list;
    var tBody = '';
    var tCategoryListBody ='';
    $(list).each(function(i,item){
        //初始化商品信息管理中分类目录
        var tr = "<a href='#' class='list-group-item' style='border-radius: 0px;' onclick='chooseCategory(" + item.id + ")'>" + item.name + "</a>"
        tBody += tr;

        //初始化分类信息管理
        var tr1 = "<tr><td>"+item.name+"</td><td>"+item.comment+"</td>"+
            "<td style='max-width: 20px;text-align: center'>" + "<input type='checkbox' name='categoryCheck' onclick='categoryCheck(this)' data-id='"+item.id+"'  /></td>"
            //+"<td style='max-width: 20px;text-align: center'><button class='btn btn-primary' onclick='updateCategory(this)' data-index='"+i+"' >修改</button></td>"
            +"<td style='max-width: 20px;text-align: center'><p><span class='label label-info' onclick='updateCategory(this)' data-index='" + i + "'>修改</span></p></td>"
            +"</tr>";
        tCategoryListBody += tr1;

    });
    $("#categoryTb").html(tBody);
    $("#categoryList tbody").html(tCategoryListBody);
}

//复选框(全选)
function categoryCheckAll( oj){
    $("input[name = 'categoryCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function categoryCheck(oj){
    var categoryCheck = $("input[name = 'categoryCheck']");
    $("input[id = 'categoryCheckAll']").prop("checked",categoryCheck.length == $("input[name='categoryCheck']:checked").length ? true : false);
}

//修改分类
function updateCategory(oj){
    var index = oj.getAttribute("data-index");
    var item = categoryArr[index];
    $("#nameCategoryLab").val(item.name);
    $("#commentCategoryLab").val(item.comment);
    $("#categoryId").val(item.id);
    $("#addCategoryModal").modal('show');
}

function addOrUpdateCategory(){
    var data = new Object();
    data.name = $("#nameCategoryLab").val();
    data.comment = $("#commentCategoryLab").val();
    if($("#categoryId").val() > 0) {
        data.id =$("#categoryId").val() ;
        $.post({
            url: "http://localhost:8080/Delphi/category/update",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addCategoryModal").modal("hide");
                    getAllCategories();
                    resetCategoryForm();
                }
            }
        });
    }else{
        $.post({
            url: "http://localhost:8080/Delphi/category/add",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addCategoryModal").modal("hide");
                    getAllCategories();
                    resetCategoryForm();
                }
            }
        });
    }
}

function resetCategoryForm(){
    $("#nameCategoryLab").val("");
    $("#commentCategoryLab").val("");
    $("#categoryId").val("");
}

function deleteCategory(){
    var data = new Array();
    var checkedBtn =  $("input[name = 'categoryCheck']:checked");
    for(var i = 0; i < checkedBtn.length; i++){
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url:"http://localhost:8080/Delphi/category/del",
        dataType:"json",
        data:{"id": data},
        traditional: true,
        success:function(data){
            if(data.success){
                getAllCategories();
            }
        }
    });
}

function previousCategory(){
    if(categoryPageIndex > 1){
        categoryPageIndex--;
        getAllCategories();
    }
}

function nextCategory(){
    if(categoryPageIndex < categoryPageCount) {
        categoryPageIndex++;
        getAllCategories();
    }
}