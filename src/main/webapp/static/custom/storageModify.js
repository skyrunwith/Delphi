/**
 * Created by FZD on 2017/4/9.
 */
var pageIndex1 = 1;
var storageArr;
var option;
var pageCount = -1;
//获取所有库存信息
function getStorage1(){
    var beginTime =  Date.parse(new Date(1491730000));
    var endTime =  Date.parse(new Date());
    $.post({
        url: path+"/storage/getAll",
        data: {pageIndex: pageIndex1, beginTime: beginTime, endTime: endTime},
        dataType: "json",
        success: function (data) {
            if(data.success){
                refreshStorageInfo1(data.list.results);
                if(pageCount < 0){
                    pageCount = data.list.pageCount;
                }
            }
        }
    });
    return storageArr;
}

function refreshStorageInfo1(list){
    storageArr = list;
    var tBody = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
        var tr = "<tr ondblclick="+"operateStorage("+item.goodsEntity.id+",'"+item.goodsEntity.name+"','"+item.goodsEntity.lose+"')"+">"
            + "<td>" + item.goodsEntity.name + "</td><td>" + sureNull(item.goodsEntity.totalStorage) +"</td>"
            + "<td>" + sureNull(item.totalSale) + "</td><td>"+sureNull(item.goodsEntity.lose)+"</td><td>" + sureNull(item.goodsEntity.storage) + "</td>"
            + "<td>" + sureNull(item.totalPayout)
            + "<td>" + sureNull(item.totalNotPayout) +"</td><td>" +sureNull(item.totalReceive)+ "</td><td>" + sureNull(item.totalNotReceive) + "</td><td>" + sureNull(item.totalReceive)
            + "</td></tr>";
        tBody += tr;

    });

    $("#storageModifyList tbody").html(tBody);
}
function previousModify(){
    if(pageIndex1 > 1){
        pageIndex1--;
        getStorage1();
    }
}

function nextModify(){
    if(pageIndex1 < pageCount) {
        pageIndex1++;
        getStorage1();
    }
}


function resetStorageForm(){
    $('#upGoodsName').val('');
    $('#upStorage').val('');
    $('#upGoodsId').val('');
}

function sureNull(str){
    if(str == null || str == 0){
        str = "";
    }
    return str;
}


function operateStorage(id,name,lose){
    $('#updateStorage').modal('show');
    $('#upGoodsName').val(name);
    $('#upLose').val(lose);
    $('#upGoodsId').val(id);
}

function upStorage(){
    var upGoodsId = $('#upGoodsId').val();
    var upStorage = $('#upLose').val();
    var data = new Object();
    data.id = upGoodsId;
    data.lose = upStorage;
    $.post({
        url: path+"/storage/update",
        dataType: "json",
        data: data,
        success: function (data) {
            if (data.success) {
                getStorage1();
                resetStorageForm();
                $("#updateStorage").modal("hide");
            }
        }
    });
}