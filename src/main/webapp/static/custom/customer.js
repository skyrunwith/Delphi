/**
 * Created by FZD on 2017/3/20.
 */
/**
 * Created by FZD on 2017/3/20.
 */

var customerPageIndex = 1;
var customerArr;
function customerCheckAll(oj){
    $("input[name = 'customerCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function customerCheck(oj){
    var customerCheck = $("input[name = 'customerCheck']");
    $("input[id = 'customerCheckAll']").prop("checked",customerCheck.length == $("input[name='customerCheck']:checked").length ? true : false);
}

//点击供应商信息导航
function onClickCustomerNav(){
    if(customerArr == null){
        getAllCustomer();
    }
}

//获取供应商列表
function getAllCustomer() {
    $.post({
        url:path+"/customer/getAll",
        data: {pageIndex: customerPageIndex},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                setCustomer(data.list.results); //显示供应商
            }
        }
    });
}

function setCustomer(list){
    customerArr = list;
    var tBody = '';
    $(list).each(function(i,item){
        //设置每行供应商内容
        var tr ="<tr><td>"+ item.companyName +"</td><td>" +item.phone+"</td><td>"+item.fax+
            "</td><td>"+item.contactName+"</td><td>"+item.contactPhone+"</td><td>"+item.address+"</td>"
            +"<td>" +item.postalCode+"</td><td>"+item.email+"</td>"
            +"<td>"+item.comment+"</td><td style='max-width: 20px;text-align: center'>"
            +"<input type='checkbox' name='customerCheck' onclick='customerCheck(this)' data-id='"+item.id+"'  /></td>"
            + "<td style='max-width: 20px;text-align: center'><p><span class='label label-info' onclick='updateCustomer(this)' data-index='" + i + "'>修改</span></p></td>"
            +"</tr>";
        tBody += tr;

    });
    $("#customerTb tbody").html(tBody);
}

//修改供应商
function updateCustomer(oj){
    var index = oj.getAttribute("data-index");
    var item = customerArr[index];
    $("#companyNameCustomer").val(item.companyName);
    $("#phoneCustomer").val(item.phone);
    $("#faxCustomer").val(item.fax);
    $("#contactNameCustomer").val(item.contactName);
    $("#contactPhoneCustomer").val(item.contactPhone);
    $("#postalCodeCustomer").val(item.postalCode);
    $("#emailCustomer").val(item.email);
    $("#bankCustomer").val(item.bank);
    $("#acctNoCustomer").val(item.acctNo);
    $("#addressCustomer").val(item.address);
    $("#commentCustomer").val(item.comment);
    $("#customerId").val(item.id);
    $("#addCustomerModal").modal('show');
}

function addOrUpdateCustomer(){
    var data = new Object();
    data.companyName = $("#companyNameCustomer").val();
    data.phone = $("#phoneCustomer").val();
    data.fax = $("#faxCustomer").val();
    data.contactName = $("#contactNameCustomer").val();
    data.contactPhone = $("#contactPhoneCustomer").val();
    data.postalCode = $("#postalCodeCustomer").val();
    data.email = $("#emailCustomer").val();
    data.bank = $("#bankCustomer").val();
    data.acctNo = $("#acctNoCustomer").val();
    data.address=$("#addressCustomer").val();
    data.comment = $("#commentCustomer").val();
    if($("#customerId").val() > 0) {
        data.id =$("#customerId").val() ;
        $.post({
            url: path+"/customer/update",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addCustomerModal").modal("hide");
                    getAllCustomer();
                    resetForm();
                }
            }
        });
    }else{
        $.post({
            url: path+"/customer/add",
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addCustomerModal").modal("hide");
                    getAllCustomer();
                    resetForm();
                }
            }
        });
    }
}

function resetForm(){
    $("#companyNameCustomer").val("");
    $("#phoneCustomer").val("");
    $("#faxCustomer").val("");
    $("#contactNameCustomer").val("");
    $("#contactPhoneCustomer").val("");
    $("#postalCodeCustomer").val("");
    $("#emailCustomer").val("");
    $("#bankCustomer").val("");
    $("#acctNoCustomer").val("");
    $("#addressCustomer").val("");
    $("#commentCustomer").val("");
    $("#customerId").val("");
}

function delCustomer(){
    var data = new Array();
    var checkedBtn =  $("input[name = 'customerCheck']:checked");
    for(var i = 0; i < checkedBtn.length; i++){
        data[i] = checkedBtn.get(i).getAttribute("data-id");
    }
    $.post({
        url:"http://localhost:8080/Delphi/customer/del",
        dataType:"json",
        data:{"id": data},
        traditional: true,
        success:function(data){
            if(data.success){
                getAllCustomer();
            }
        }
    });
}