/**
 * Created by FZD on 2017/3/30.
 */

var pageIndex = 1;
var storageArr;
var option;
var pageCount = -1;
//获取所有库存信息
function getStorage(){
    var beginTime =  Date.parse($("#beginTime").val());
    var endTime =  Date.parse($("#endTime").val());
    $.post({
        url: path+"/storage/getAll",
        data: {pageIndex: pageIndex, beginTime: beginTime, endTime: endTime},
        dataType: "json",
        success: function (data) {
            if(data.success){
                refreshStorageInfo(data.list.results);
                $("#queryStorage").modal("hide");
                if(pageCount < 0){
                    pageCount = data.list.pageCount;
                }
            }
        }
    });
}

function refreshStorageInfo(list){
    storageArr = list;
    var tBody = '';
    $(list).each(function (i, item) {
        //设置每行供应商内容
        var tr = "<tr ><td>" + item.goodsEntity.name + "</td><td>" + item.goodsEntity.birthplace + "</td><td>" + item.goodsEntity.specification + "</td>"
            + "<td>" + item.goodsEntity.packaging + "</td><td>" +item.goodsEntity.comment+"</td><td>"+ sureNull(item.goodsEntity.storage) + "</td><td>" + sureNull(item.goodsEntity.totalStorage) + "</td>"
            + "<td>" + sureNull(item.totalStorageTime) + "</td>"
            + "<td>" + sureNull(item.totalSale) + "</td>"
            + "<td>" + sureNull(item.totalSaleTime) + "</td>"
            + "</tr>";
        tBody += tr;

    });

    var thead = "<tr><td>名称</td><td>产地</td><td>规格</td><td>包装</td><td>备注</td><td>库存量</td><td >总进货量</td><td class='col-sm-1'>"+$("#beginTime").val().substr(0,10)+"  至"+ $("#endTime").val().substr(0,10)+"进货量</td><td>总销售量</td><td class='col-sm-1'>"+$("#beginTime").val().substr(0,10)+"  至"+ $("#endTime").val().substr(0,10)+"销售量</td> </tr>"
    $("#storageInfoList thead").html(thead);
    $("#storageInfoList tbody").html(tBody);
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
        getStorage();
    }
}

function next(){
    if(pageIndex < pageCount) {
        pageIndex++;
        getStorage();
    }
}