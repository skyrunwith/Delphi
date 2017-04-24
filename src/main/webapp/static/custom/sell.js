/**
 * Created by FZD on 2017/3/21.
 */
var customerPageIndex = 1
var pageIndex = 1;
var sellArr;
var option;
var pageCount = -1;
//新增或修改采购单
function addOrUpdateSell(){
    var sell = new Object();
    var data = new Object();
    sell.id = $("#sellId").val();
    sell.unitPrice = $("#unitPrice").val();
    sell.totalNumber =  $("#totalNumber").val();
    sell.totalPrice =  $("#totalPrice").val();
    sell.paid =  $("#paid").val();
    sell.sellTime =  Date.parse($("#sellTime").val());
    sell.comment =  $("#comment").val();
    data.sell = sell;
    data.customerId = $("#customerName").val();
    data.goodsId = $("#goodsId").val();

    $.post({
        url: path + "/sell/add",
        dataType: "json",
        data: {"data":JSON.stringify(data),"option":option},
        success:function(data){
            if(data.success){
                resetSellForm();
                getSellByParams();
            }
        }
    });
}

//判断新增、删除、修改
function upOption(str){
    option = str;
    if(str == 'DELETE'){
        delSell();
    }
}

function delSell() {
    var data = new Array();
    var checkedBtn = $("input[name = 'sellCheck']:checked");
    for (var i = 0; i < checkedBtn.length; i++) {
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url: "http://localhost:8080/Delphi/sell/del",
        dataType: "json",
        data: {"id": data},
        traditional: true,
        success: function (data) {
            if (data.success) {
                getSellByParams();
            }
        }
    });
}

function update(oj) {
    var index = oj.getAttribute("data-index");
    var item = sellArr[index];
    $("#unitPrice").val(item.sell.unitPrice);
    $("#totalNumber").val(item.sell.totalNumber);
    $("#totalPrice").val(item.sell.totalPrice);
    $("#paid").val(item.sell.paid);
    $("#sellTime").val(formateTimestap(item.sell.sellTime));
    $("#comment").val(item.sell.comment);
    $("#storage").val(item.goodsEntity.storage);
    $("#goodsName").val(item.goodsEntity.name);
    $("#goodsId").val(item.goodsEntity.id);
    $("#customerName").val(item.customerEntity.id);
    $("#debt").val((item.sell.totalPrice - item.sell.paid));
    $("#sellId").val(item.sell.id);
    $("#sales").val(item.goodsEntity.sales);
    upOption("UPDATE")
}
function getSellByParams(){
    var goodsName = $("#queryGoodsName").val();
    var customerName = $("#queryCustomerName").val();
    $.post({
        url: path + "/sell/getByParams",
        dataType: "json",
        data:{"goodsName":goodsName, "customerName":customerName,pageIndex:pageIndex},
        success:function(data){
            if(data.success){
                $("#query").modal("hide");
                refreshSells(data.list);
                if(pageCount < 0){
                    pageCount = data.pageCount;
                }
            }
        }
    });
}

//查询后刷新采购列表
function refreshSells(list) {
    sellArr = list;
    var tBody = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
        var tr = "<tr><td>" + item.goodsEntity.name + "</td><td>" + item.customerEntity.companyName + "</td><td>" + formateTimestap(item.sell.sellTime) + "</td>"
            + "<td>" + item.sell.unitPrice + "</td><td>" +item.sell.totalNumber+"</td><td>"+ item.sell.totalPrice + "</td><td>" + item.sell.paid + "</td>"
            + "<td>" + (item.sell.totalPrice - item.sell.paid)+ "</td><td>" + item.goodsEntity.storage + "</td><td>" +item.goodsEntity.sales+ "</td>"
            + "<td>" + item.sell.comment + "</td><td style='max-width: 20px;text-align: center'>"
            + "<input type='checkbox' name='sellCheck' onclick='sellCheck(this)' data-id='" + item.sell.id + "'  /></td>"
            + "<td style='max-width: 20px;text-align: center'><button class='btn btn-primary' onclick='update(this)' data-index='"+i+"' >修改</button></td>"
            + "</tr>";
        tBody += tr;

    });
    $("#sellTb tbody").html(tBody);
}

function sellCheckAll(oj){
    $("input[name = 'sellCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function sellCheck(oj){
    var $sellCheck = $("input[name = 'sellCheck']");
    $("input[id = 'sellCheckAll']").prop("checked",$sellCheck.length == $("input[name='sellCheck']:checked").length ? true : false);
}


//初始化供应商下拉框
function initCustomerSelect(){
    $.post({
        url:path+"/customer/getAll",
        data: {pageIndex: customerPageIndex},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                var customer =data.list.results;
                var select = '';
                $(customer).each(function(i,item){
                    var option = "<option value='"+item.id+"'>"+item.companyName+"</option>";
                    select+=option;
                });
                $("#customerName").append("<option data-index=''></option>");
                $("#customerName").append(select);
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
    var paid =  $("#paid").val();
    if(totalPrice.length != 0 && paid.length != 0 ){
        $("#debt").val(totalPrice-paid);
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

function resetSellForm() {
    $("#queryGoodsName").val("");
    $("#queryCustomerName").val("");
    $("#goodsName").val("");
    $("#customerName").val("");
    $("#sellTime").val("");
    $("#unitPrice").val("");
    $("#totalNumber").val("");
    $("#totalPrice").val("");
    $("#paid").val("");
    $("#debt").val("");
    $("#storage").val("");
    $("#sales").val("");
    $("#comment").val("");
}

function previous(){
    if(pageIndex > 1){
        pageIndex--;
        getSellByParams();
    }
}

function next(){
    if(pageIndex < pageCount) {
        pageIndex++;
        getSellByParams();
    }
}