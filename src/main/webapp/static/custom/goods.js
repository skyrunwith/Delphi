/**
 * Created by FZD on 2017/3/20.
 */
var goodsPageIndex = 1;
var categoryId = 0;
var goodsArr = new Array();
var pageCount = -1;
//获取分类列表,商品信息
function getAllCategories() {
    $.post({
        url: path+"/category/getAll",
        data: {pageIndex: categoryPageIndex},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                setCategory(data.list.results); //显示分类信息
                if(data.list.results.length != 0){
                    //refreshGoods(data.list.results[0].goodsesById);
                    categoryId = data.list.results[0].id;
                    getAllGoods();
                    if(categoryPageCount < 0){
                        categoryPageCount = data.list.pageCount;
                    }
                }
            }
        }
    });
}



//获取所有商品
//param：分类id
function getAllGoods(){
    $.post({
        url: path+"/goods/getAll",
        data: {pageIndex: goodsPageIndex, categoryId: categoryId},
        dataType: "json",
        success: function (data) {
            if(data.success){
                refreshGoods(data.list.results)
                if(pageCount < 0){
                    pageCount = data.list.pageCount;
                }
            }
        }
    });
}

function chooseCategory(id){
    categoryId = id;
    getAllGoods();
}

//刷新商品
function refreshGoods(goods){
    goodsArr = goods;
    if(goods.length == 0){
        $("#goodsTb tbody").html("");
    }

    var tBody = '';
    $(goods).each(function(i,item){
        var tr ="<tr><td>"+ item.name +"</td><td>" +item.birthplace+"</td><td>"+item.specification+
            "</td><td>"+item.packaging+"</td><td>" + sureNull(item.purchasePrice)+"</td><td>"+ sureNull(item.sellPrice)
            +"</td><td>" +item.comment+"</td>"+"<td style='max-width: 20px;text-align: center'>" +
            "<input type='checkbox' name='goodsCheck' onclick='goodsCheck(this)' data-id='"+item.id+"'  /></td>"
            //+ "<td style='max-width: 20px;text-align: center'><button class='btn btn-primary' onclick='updateGoods(this)' data-index='"+i+"' >修改</button></td>"
            + "<td style='max-width: 20px;text-align: center'><p><span class='label label-info' onclick='updateGoods(this)' data-index='" + i + "'>修改</span></p></td>"
            +"</tr>";
        tBody += tr;
        $("#goodsTb tbody").html(tBody);
    });
}

//复选框(全选)
function goodsCheckAll( oj){
    $("input[name = 'goodsCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function goodsCheck(oj){
    var $goodsCheck = $("input[name = 'goodsCheck']");
    $("input[id = 'goodsCheckAlls']").prop("checked",$goodsCheck.length == $("input[name='goodsCheck']:checked").length ? true : false);
}

//修改商品
function updateGoods(oj){
    var index = oj.getAttribute("data-index");
    var good = goodsArr[index];
    $("#nameGoodsLab").val(good.name);
    $("#birthPlace").val(good.birthplace);
    $("#specification").val(good.specification);
    $("#packaging").val(good.packaging);
    $("#commentGoodsLab").val(good.comment);
    $("#goodsId").val(good.id);
    $("#purchasePrice").val(good.purchasePrice);
    $("#sellPrice").val(good.sellPrice);
    $("#addGoodsModal").modal('show');
}

function resetGoodsForm(){
    $("#nameGoodsLab").val("");
    $("#birthPlace").val("");
    $("#specification").val("");
    $("#packaging").val("");
    $("#commentGoodsLab").val("");
    $("#purchasePrice").val("");
    $("#sellPrice").val("");
    $("#goodsId").val("");
}

function delGoods(){
    var data = new Array();
    var checkedBtn =  $("input[name = 'goodsCheck']:checked");
    for(var i = 0; i < checkedBtn.length; i++){
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url:path+"/goods/del",
        dataType:"json",
        data:{"id": data},
        traditional: true,
        success:function(data){
            if(data.success){
                getAllGoods(categoryId);
            }
        }
    });
}

function addOrUpdateGoods(){
    var data = new Object();
    data.name = $("#nameGoodsLab").val();
    data.birthplace = $("#birthPlace").val();
    data.specification = $("#specification").val();
    data.packaging = $("#packaging").val();
    data.comment = $("#commentGoodsLab").val();
    data.purchasePrice = $("#purchasePrice").val();
    data.sellPrice = $("#sellPrice").val();
    data.categoryId = categoryId;
    if ($("#goodsId").val() > 0) {
        data.id = $("#goodsId").val();
        $.post({
            url: path+"/goods/update",
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.success) {
                    getAllGoods(categoryId);
                    resetGoodsForm();
                    $("#addGoodsModal").modal("hide");
                }
            }
        });
    }else{
        $.post({
            url: path+"/goods/add",
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.success) {
                    getAllGoods(categoryId);
                    resetGoodsForm();
                    $("#addGoodsModal").modal("hide");
                }
            }
        });
    }
}

function previous(){
    if(goodsPageIndex > 1){
    goodsPageIndex--;
    getAllGoods();
}
}

function next(){
    if(goodsPageIndex < pageCount) {
        goodsPageIndex++;
        getAllGoods();
    }
}
function sureNull(str){
    if(str == null){
        str = "";
    }
    return str;
}