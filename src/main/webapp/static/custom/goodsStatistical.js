/**
 * Created by FZD on 2017/4/10.
 */
var myChart;
var myPurchaseChart;
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
function getChartData1(){
    var beginTime =  Date.parse($("#beginTime").val());
    var endTime =  Date.parse($("#endTime").val());
    var goodsName =  $("#goodsName").val();
    $.post({
        url: path+"/sell/getSellChartData",
        data: {beginTime: beginTime, endTime: endTime,goodsName:goodsName},
        dataType: "json",
        success: function (data) {
            if(data.success){
                updateSellChart(data.list);
            }
        }
    });
}

function updateSellChart(list){
    var ctx = document.getElementById("myChart"); //cavas
    var labels = new Array(); //横坐标
    var backgroundColor = new Array(); //背景
    var borderColor = new Array(); //线颜色
    var data1 = new Array();
    $(list).each(function(i,item){
        labels[i] = item.year +"年"+item.month +"月";
        backgroundColor[i] = 'rgba(255, 99, 132, 0.2)';
        borderColor[i] = 'rgba(255,99,132,1)';
        data1[i] = item.sell;
    });
    var label = list[i].year +"年"+list[0].month +"月到" + list[list.length - 1].year +"年"+list[list.length - 1].month +"销售统计";
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
        //myChart.data.datasets[i].backgroundColor =backgroundColor;
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

function updatePurchaseChartData(list,goosName){
    var backgrounColorStr = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)']
    var borderColorStr = [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'];
    var ctx = document.getElementById("myChartPurchase");//cavas
    var labels = new Array(); //横坐标
    var backgroundColor = new Array(); //背景
    var borderColor = new Array(); //线颜色
    var data1 = new Array();
    $(list).each(function (i, item) {
        //backgroundColor[i] = backgrounColorStr[i % 6];
        //borderColor[i] = borderColorp[i % 6];
        //data1[i] = item.purchase;
        data1[i] = new Object();
        data1[i].label = item[i].goodName + '采购量';
        data1[i].fill = false;
        data1[i].lineTension = 0.1,
        data1[i].backgroundColor = backgrounColorStr[i % 6],
        data1[i].borderColor = borderColorStr[i % 6],
        data1[i].borderCapStyle = 'butt',
        data1[i].borderDash = [],
        data1[i].borderDashOffset = 0.0,
        data1[i].borderJoinStyle = 'miter',
        data1[i].pointBorderColor = "rgba(75,192,192,1)",
        data1[i].pointBackgroundColor = "#fff",
        data1[i].pointBorderWidth = 1,
        data1[i].pointHoverRadius = 5,
        data1[i].pointHoverBackgroundColor = backgrounColorStr[i % 6],
        data1[i].pointHoverBorderColor = borderColorStr[i % 6],
        data1[i].pointHoverBorderWidth = 2,
        data1[i].pointRadius = 1,
        data1[i].pointHitRadius = 10,
        data1[i].data = [];
        for(var j = 0; j<item.length; j++){
            if(i == 0){
                data1[i].data[j] = item[j].purchase
            }else{
                data1[i].data[j] = item[j].purchase - getPurchase(list[i-1].purchase);
            }
            labels[j] = item[j].year +"年"+item[j].month +"月";
        }
        data1[i].spanGaps = false
    });
    var data = {
        labels: labels,
        datasets:data1
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
            text: 'label'
        }
    };

    if(myPurchaseChart == null){
        myPurchaseChart = new Chart(ctx, {
            type: 'line',
            data: data,
            options: options
        });
    }else{
        myPurchaseChart.destroy();
        myPurchaseChart = new Chart(ctx, {
            type: 'line',
            data: data,
            options: options
        });
    }

}
//获取销售统计量
function getPurchaseChartData(){
    var beginTime =  Date.parse($("#beginTimePurchase").val());
    var endTime =  Date.parse($("#endTimePurchase").val());
    var goodsName =  $("#goodsNamePurchase").val();
    $.post({
        url: path+"/purchase/getPurchaseChartData",
        data: {beginTime: beginTime, endTime: endTime,goodsName:goodsName},
        dataType: "json",
        success: function (data) {
            if(data.success){
                updatePurchaseChartData(data.list, goodsName);
            }
        }
    });
}

function getPurchase(purchase){
    if(purchase == undefined){
       return purchase = 0;
    }
    return purchase;
}