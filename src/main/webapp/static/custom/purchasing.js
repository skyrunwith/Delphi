/**
 * Created by FZD on 2017/3/21.
 */
var pageIndex = 1
var purchaseArr;
var pageCount = -1;
var option;
var producerPageIndex = 1;

var pageIndexUn = 1;
var purchaseArrUn;
var pageCountUn = -1;

//新增或修改采购单
function addOrUpdatePurchase(){
    var purchase = new Object();
    var data = new Object();
    var goodsId;
    var producerId;
    purchase.id = $("#purchaseId").val();
    purchase.unitPrice = $("#unitPrice").val();
    purchase.totalNumber =  $("#totalNumber").val();
    purchase.totalPrice =  $("#totalPrice").val();
    purchase.payout =  $("#payout").val();
    purchase.putInTime =  Date.parse($("#putInTime").val());
    purchase.comment =  $("#comment").val();
    data.purchase = purchase;
    data.producerId = $("#producerName").val();
    data.goodsId = $("#goodsId").val();

    $.post({
        url: path + "/purchase/add",
        dataType: "json",
        data: {"data":JSON.stringify(data),"option":option},
        success:function(data){
            if(data.success){
                resetPurchaseForm();
                getPurchaseByParamsUn();
            }
        }
    });
}

//判断新增、删除、修改
function upOption(str){
    option = str;
    if(str == 'DELETE'){
        delPurchase();
    }
}

function delPurchase() {
    var data = new Array();
    var checkedBtn = $("input[name = 'purchaseCheck']:checked");
    for (var i = 0; i < checkedBtn.length; i++) {
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url: "http://localhost:8080/Delphi/purchase/del",
        dataType: "json",
        data: {"id": data},
        traditional: true,
        success: function (data) {
            if (data.success) {
                getPurchaseByParamsUn();
            }
        }
    });
}

function confirmComplete(){
    var data = new Array();
    var checkedBtn = $("input[name = 'purchaseCheck']:checked");
    for (var i = 0; i < checkedBtn.length; i++) {
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url: "http://localhost:8080/Delphi/purchase/confirmComplete",
        dataType: "json",
        data: {"id": data},
        traditional: true,
        success: function (data) {
            if (data.success) {
                getPurchaseByParams();
                getPurchaseByParamsUn();
            }
        }
    });
}
function update(oj) {
    var index = oj.getAttribute("data-index");
    var item = purchaseArrUn[index];
    initProducerSelect(item.goodsEntity.id);
    $("#unitPrice").val(item.purchase.unitPrice);
    $("#totalNumber").val(item.purchase.totalNumber);
    $("#totalPrice").val(item.purchase.totalPrice);
    $("#payout").val(item.purchase.payout);
    $("#putInTime").val(formateTimestap(item.purchase.putInTime));
    $("#comment").val(item.purchase.comment);
    $("#storage").val(item.goodsEntity.storage);
    $("#goodsName").val(item.goodsEntity.name);
    $("#goodsId").val(item.goodsEntity.id);
    $("#producerName").val(item.producerEntity.id);
    $("#debt").val((item.purchase.totalPrice - item.purchase.payout));
    $("#purchaseId").val(item.purchase.id);
    $("#sales").val(item.goodsEntity.sales);
    upOption("UPDATE");

}
//获取已完成采购订单
function getPurchaseByParams(){
    var goodsName = $("#queryGoodsName").val();
    var producerName = $("#queryProducerName").val();
    $.post({
        url: path + "/purchase/getByParams",
        dataType: "json",
        data:{"goodsName":goodsName, "producerName":producerName, "pageIndex":pageIndex, state: 1},
        success:function(data){
            if(data.success){
                $("#query").modal("hide");
                refreshPurchases(data.list);
                resetPurchaseForm();
                //if(pageCount < 0){
                    pageCount = data.pageCount;
                //}
            }
        }
    });
}

//获取未完成采购订单
function getPurchaseByParamsUn(){
    var goodsName = $("#queryGoodsName").val();
    var producerName = $("#queryProducerName").val();
    $.post({
        url: path + "/purchase/getByParams",
        dataType: "json",
        data:{"goodsName":goodsName, "producerName":producerName, "pageIndex":pageIndexUn, state: 0},
        success:function(data){
            if(data.success){
                $("#query").modal("hide");
                refreshPurchasesUn(data.list);
                resetPurchaseForm();
                //if(pageCountUn < 0){
                    pageCountUn = data.pageCount;
                //}
            }
        }
    });
}
//查询后刷新已完成采购列表
function refreshPurchases(list) {
    purchaseArr = list;
    var tBody = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
            var tr = "<tr><td>" + item.goodsEntity.name + "</td><td>" + item.producerEntity.companyName + "</td><td>" + formateTimestap(item.purchase.putInTime) + "</td>"
                + "<td>" + item.purchase.unitPrice + "</td><td>" + item.purchase.totalNumber + "</td><td>" + item.purchase.totalPrice + "</td><td>" + item.purchase.payout + "</td>"
                + "<td>" + (item.purchase.totalPrice - item.purchase.payout) + "</td><td>" + item.goodsEntity.storage + "</td><td>" + sureNull(item.goodsEntity.sales) + "</td>"
                + "<td>" + item.purchase.comment + "</td>" +
                //"<td style='max-width: 20px;text-align: center'>"
                //+ "<input type='checkbox' name='purchaseCheck' onclick='purchaseCheck(this)' data-id='" + item.purchase.id + "'  /></td>"
                + "<td style='max-width: 20px;text-align: center'></td>"
                + "</tr>";
            tBody += tr;
    });
    $("#purchaseTb tbody").html(tBody);
}

//查询后刷新未完成采购列表
function refreshPurchasesUn(list) {
    purchaseArrUn = list;
    var tBodyUn = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
        if (item.purchase.state == 0) {
            var tr = "<tr><td>" + item.goodsEntity.name + "</td><td>" + item.producerEntity.companyName + "</td><td>" + formateTimestap(item.purchase.putInTime) + "</td>"
                + "<td>" + item.purchase.unitPrice + "</td><td>" + item.purchase.totalNumber + "</td><td>" + item.purchase.totalPrice + "</td><td>" + item.purchase.payout + "</td>"
                + "<td>" + (item.purchase.totalPrice - item.purchase.payout) + "</td><td>" + sureNull(item.goodsEntity.storage) + "</td><td>" + sureNull(item.goodsEntity.sales) + "</td>"
                + "<td>" + item.purchase.comment + "</td>" +
                "<td style='max-width: 20px;text-align: center'>"
                + "<input type='checkbox' name='purchaseCheck' onclick='purchaseCheck(this)' data-id='" + item.purchase.id + "'  /></td>"
                + "<td style='max-width: 20px;text-align: center'><p><a href='#' class='label label-info' onclick='update(this)' data-index='" + i + "'>修改</a></p></td>"
                + "</tr>";
            tBodyUn += tr;
        }
    });
    $("#purchaseTbUn tbody").html(tBodyUn);
}

function purchaseCheckAll(oj){
    $("input[name = 'purchaseCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function purchaseCheck(oj){
    var $purchaseCheck = $("input[name = 'purchaseCheck']");
    $("input[id = 'purchaseCheckAllUn']").prop("checked",$purchaseCheck.length == $("input[name='purchaseCheck']:checked").length ? true : false);
}


//初始化供应商下拉框
//参数：商品id
function initProducerSelect(id){

    $('#putInTime').val(formateTimestap(new Date().getTime()));
    $.post({
        url:path+"/producer/getAll",
        data: {pageIndex: producerPageIndex,id:id},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                var producer =data.list.results;
                var select = '';
                $(producer).each(function(i,item){
                    var option = "<option value='"+item.id+"'>"+item.companyName+"</option>";
                    select+=option;
                });
                $("#producerName").html("<option data-index=''></option>");
                $("#producerName").html(select);
            }
        }
    });
}

//计算总价
function countTotalPrice(){
    var unitPrice = $("#unitPrice").val();
    var totalNumber =  $("#totalNumber").val();
    if(unitPrice.length != 0 && totalNumber.length != 0){
        $("#totalPrice").val(unitPrice * totalNumber);
    }
}
//计算欠款
function countDebt(){
    var totalPrice =  $("#totalPrice").val();
    var payout =  $("#payout").val();
    if(totalPrice.length != 0 && payout.length != 0 ){
        $("#debt").val(totalPrice-payout);
    }
}

function formateTimestap(dateStr){
    var time = new Date(dateStr);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    var h = time.getHours();//时
    var mm = time.getMinutes();//分
    var s = time.getSeconds();//秒
    return y+"/"+m+"/"+d+" "+h+":"+mm+":"+s;
}

function resetPurchaseForm() {
    //$("#queryGoodsName").val("");
    //$("#queryProducerName").val("");
    $("#goodsName").val("");
    $("#producerName").val("");
    $("#putInTime").val("");
    $("#unitPrice").val("");
    $("#totalNumber").val("");
    $("#totalPrice").val("");
    $("#payout").val("");
    $("#debt").val("");
    $("#storage").val("");
    $("#sales").val("");
    $("#comment").val("");
}
function sureNull(str){
    if(str == null){
        str = "";
    }
    return str;
}

function previous(){
    if(pageIndex > 1){
        pageIndex--;
        getPurchaseByParams();
    }
}

function next(){
    if(pageIndex < pageCount) {
        pageIndex++;
        getPurchaseByParams();
    }
}

function previousUn(){
    if(pageIndexUn > 1){
        pageIndexUn--;
        getPurchaseByParamsUn();
    }
}

function nextUn(){
    if(pageIndexUn < pageCountUn) {
        pageIndexUn++;
        getPurchaseByParamsUn();
    }
}



function query(){
    getPurchaseByParams();
    getPurchaseByParamsUn();
}