/**
 * Created by FZD on 2017/5/9.
 */

var pageIndex = 1;
var financialArr;
var option;
var pageCount = -1;
//获取所有库存信息
function getFinancial(){
    //var productName = $('#goodsName').val();
    var beginTime =  Date.parse($("#beginTime").val());
    var endTime =  Date.parse($("#endTime").val());
    $.post({
        url: path+"/financial/getByParams",
        data: {pageIndex: pageIndex, beginTime: beginTime, endTime: endTime},
        dataType: "json",
        success: function (data) {
            if(data.success){
                refreshFinancialInfo(data.list);
                pageIndex = 1;
                //if(pageCount < 0){
                pageCount = data.list.pageCount;
                //}
            }
        }
    });
}
function refreshFinancialInfo(list){
    financialArr = list;
    var tBody = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
        var tr = "<tr ><td>" + item.name + "</td><td>" + item.transcationName + "</td><td>" + item.employee + "</td>"
            + "<td>" + item.type + "</td><td>" +sureNull(item.transcationMoney)+"</td><td>"+sureNull(item.paid)+"</td><td>"+ sureNull(item.paidUn) + "</td><td>" + formateTimestap(item.time) + "</td>"
            + "</tr>";
        //onclick='operateStorage(" +item.goodsEntity.id+",'"+item.goodsEntity.name+"','"+item.goodsEntity.lose+"')
        tBody += tr;
    });
    $("#financialTb tbody").html(tBody);
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