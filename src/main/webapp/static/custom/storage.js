/**
 * Created by FZD on 2017/3/30.
 */

var pageIndex = 1;
var storageArr;
var option;
var pageCount = -1;
//获取所有库存信息
function getStorage(){
    var productName = $('#goodsName').val();
    var beginTime =  Date.parse($("#beginTime").val());
    var endTime =  Date.parse($("#endTime").val());
    $.post({
        url: path+"/storage/getAll",
        data: {pageIndex: pageIndex,productName: productName, beginTime: beginTime, endTime: endTime},
        dataType: "json",
        success: function (data) {
            if(data.success){
                refreshStorageInfo(data.list.results);
                $("#queryStorage").modal("hide");
                pageIndex = 1;
                //if(pageCount < 0){
                pageCount = data.list.pageCount;
                //}
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
            + "<td>" + item.goodsEntity.packaging + "</td><td>" +item.goodsEntity.comment+"</td><td>"+sureNull(item.goodsEntity.storage)+"</td><td>"+ sureNull(item.goodsEntity.lose) + "</td><td>" + sureNull(item.goodsEntity.totalStorage) + "</td>"
            + "<td>" + sureNull(item.totalStorageTime) + "</td>"
            + "<td>" + sureNull(item.totalSale) + "</td>"
            + "<td>" + sureNull(item.totalSaleTime) + "</td>"
            + "<td style='max-width: 20px;text-align: center'><p><span class='label label-info' data-index='" + i + "' " +
            " onclick=operateStorage("+item.goodsEntity.id +",'"+ item.goodsEntity.name+"','"+item.goodsEntity.lose+"')>修改</span></p></td>"
            + "</tr>";
        //onclick='operateStorage(" +item.goodsEntity.id+",'"+item.goodsEntity.name+"','"+item.goodsEntity.lose+"')
        tBody += tr;

    });

    var thead = "<tr><td>名称</td><td>产地</td><td>规格</td><td>包装</td><td>备注</td><td>库存量</td><td>亏损量</td><td >总进货量</td><td class='col-sm-1'>"+$("#beginTime").val().substr(0,10)+"  至"+ $("#endTime").val().substr(0,10)+"进货量</td><td>总销售量</td><td class='col-sm-1'>"+$("#beginTime").val().substr(0,10)+"  至"+ $("#endTime").val().substr(0,10)+"销售量</td><td>操作</td> </tr>"
    //var thead = "<tr><td>名称</td><td>产地</td><td>规格</td><td>包装</td><td>备注</td><td>库存量</td><td >总进货量</td>" +
    //    "<td>总销售量</td></tr>"
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
                getStorage();
                resetStorageForm();
                $("#updateStorage").modal("hide");
            }
        }
    });
}

function resetStorageForm(){
    $('#upGoodsName').val('');
    $('#upStorage').val('');
    $('#upGoodsId').val('');
}

/**统计图*/
var myChart;
function init(){
    var ctx = document.getElementById("myChart");
    var labels = new Array();
    var backgroundColor = new Array();
    var borderColor = new Array();
    var data1 = [65];
    var data = {
        labels: ["1月"],
        datasets: [
            {
                label: "进货量",
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)'
                ],
                borderWidth: 1,
                data: data1
            }

        ]
    };
    var options = {
        scales: {
            xAxes: [{
                stacked: true
            }],
            yAxes: [{
                stacked: true
            }]
        },
        title: {
            display: true,
            text: 'xxxx'
        }
    };
    var myChart1 = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
    });
    myChart = myChart1;
}

//获取所有库存信息
function getStorageChartData(){
    var beginTime =  Date.parse($("#beginTime1").val());
    var endTime =  Date.parse($("#endTime1").val());
    var goodsName =  $("#goodsName1").val();
    $.post({
        url: path+"/storage/getStorageChartData",
        data: {beginTime: beginTime, endTime: endTime,goodsName:goodsName},
        dataType: "json",
        success: function (data) {
            if(data.success){
                updateStorageChart(data.list);
            }
        }
    });
}

function updateStorageChart(list){
    var ctx = document.getElementById("myChart");
    var labels = new Array();
    var backgroundColor = new Array();
    var borderColor = new Array();
    var data1 = new Array();
    $(list).each(function(i,item){
        labels[i] = item.year +"年"+item.month +"月";
        backgroundColor[i] = 'rgba(255, 99, 132, 0.2)';
        borderColor[i] = 'rgba(255,99,132,1)';
        data1[i] = item.sell;
    });
    var label = list[0].year +"年"+list[0].month +"月到" + list[list.length - 1].year +"年"+list[list.length - 1].month +"销售统计";
    var data = {
        labels: labels,
        datasets: [
            {
                label: "销售量",
                backgroundColor: backgroundColor,
                borderColor: borderColor,
                borderWidth: 1,
                data: data1
            }

        ]
    };
    var options = {
        scales: {
            xAxes: [{
                stacked: true
            }],
            yAxes: [{
                stacked: true
            }]
        },
        title: {
            display: true,
            text: label
        }
    };
    if(myChart == null){
        myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    }else{
        //myChart.reset();
        //myChart.data.datasets[0].backgroundColor =backgroundColor;
        //myChart.data.datasets[0].borderColor =borderColor;
        //myChart.data.datasets[0].data1 =data1;
        ////myChart.data.datasets[0].data[0] = 50;
        //myChart.update();
        myChart.destroy();
        myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    }
    //init();
}