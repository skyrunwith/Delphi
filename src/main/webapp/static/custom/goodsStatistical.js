/**
 * Created by FZD on 2017/4/10.
 */
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
function getChartData1(){
    var beginTime =  Date.parse($("#beginTime1").val());
    var endTime =  Date.parse($("#endTime1").val());
    var goodsName =  $("#goodsName").val();
    $.post({
        url: path+"/sell/getChartData",
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