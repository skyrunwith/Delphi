/**
 * Created by FZD on 2017/3/20.
 */

var producerPageIndex = 1;
var producerArr;
function producerCheckAll(oj){
    $("input[name = 'producerCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function producerCheck(oj){
    var producerCheck = $("input[name = 'producerCheck']");
    $("input[id = 'producerCheckAll']").prop("checked",producerCheck.length == $("input[name='producerCheck']:checked").length ? true : false);
}

//点击供应商信息导航
function onClickProducerNav(){
    if(producerArr == null){
        getAllProducer();
    }
}

//获取供应商列表
function getAllProducer() {
    $.post({
        url:path+"/producer/getAll",
        data: {pageIndex: producerPageIndex},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                setProducer(data.list.results); //显示供应商
            }
        }
    });
}

function setProducer(list){
    producerArr = list;
    var tBody = '';
    $(list).each(function(i,item){
        //设置每行供应商内容
        var tr ="<tr><td>"+ item.companyName +"</td><td>" +item.phone+"</td><td>"+item.fax+
            "</td><td>"+item.contactName+"</td><td>"+item.contactPhone+"</td><td>"+item.address+"</td>"
            +"<td>" +item.postalCode+"</td><td>"+item.email+"</td><td>"+item.bank+"</td><td>"+item.acctNo+"</td>"
            +"<td>"+item.comment+"</td><td style='max-width: 20px;text-align: center'>"
            +"<input type='checkbox' name='producerCheck' onclick='producerCheck(this)' data-id='"+item.id+"'  /></td>"
            + "<td style='max-width: 20px;text-align: center'><button class='btn btn-primary' onclick='updateProducer(this)' data-index='"+i+"' >修改</button></td>"
            +"</tr>";
        tBody += tr;

    });
    $("#producerTb tbody").html(tBody);
}

//修改供应商
function updateProducer(oj){
    var index = oj.getAttribute("data-index");
    var item = producerArr[index];
    $("#companyNameProducer").val(item.companyName);
    $("#phoneProducer").val(item.phone);
    $("#faxProducer").val(item.fax);
    $("#contactNameProducer").val(item.contactName);
    $("#contactPhoneProducer").val(item.contactPhone);
    $("#postalCodeProducer").val(item.postalCode);
    $("#emailProducer").val(item.email);
    $("#bankProducer").val(item.bank);
    $("#acctNoProducer").val(item.acctNo);
    $("#addressProducer").val(item.address);
    $("#commentProducer").val(item.comment);
    $("#producerId").val(item.id);
    $("#addProducerModal").modal('show');
}

function addOrUpdateProducer(){
    var data = new Object();
    data.companyName = $("#companyNameProducer").val();
    data.phone = $("#phoneProducer").val();
    data.fax = $("#faxProducer").val();
    data.contactName = $("#contactNameProducer").val();
    data.contactPhone = $("#contactPhoneProducer").val();
    data.postalCode = $("#postalCodeProducer").val();
    data.email = $("#emailProducer").val();
    data.bank = $("#bankProducer").val();
    data.acctNo = $("#acctNoProducer").val();
    data.address=$("#addressProducer").val();
    data.comment = $("#commentProducer").val();
    if($("#producerId").val() > 0) {
        data.id =$("#producerId").val() ;
        $.post({
            url: path+"/producer/update",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addProducerModal").modal("hide");
                    getAllProducer();
                    resetProducerForm();
                }
            }
        });
    }else{
        $.post({
            url: path+"/producer/add",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addProducerModal").modal("hide");
                    getAllProducer();
                    resetProducerForm();
                }
            }
        });
    }
}

function resetProducerForm(){
    $("#companyNameProducer").val("");
    $("#phoneProducer").val("");
    $("#faxProducer").val("");
    $("#contactNameProducer").val("");
    $("#contactPhoneProducer").val();
    $("#postalCodeProducer").val("");
    $("#emailProducer").val("");
    $("#bankProducer").val("");
    $("#acctNoProducer").val("");
    $("#addressProducer").val("");
    $("#commentProducer").val("");
    $("#producerId").val("");
}

function delProducer(){
    var data = new Array();
    var checkedBtn =  $("input[name = 'producerCheck']:checked");
    for(var i = 0; i < checkedBtn.length; i++){
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url:"http://localhost:8080/Delphi/producer/del",
        dataType:"json",
        data:{"id": data},
        traditional: true,
        success:function(data){
            if(data.success){
                getAllProducer();
            }
        }
    });
}