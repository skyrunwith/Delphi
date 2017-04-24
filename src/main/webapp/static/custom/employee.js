/**
 * Created by FZD on 2017/4/24.
 */

var employeePageIndex = 1;
var employeeArr;
function employeeCheckAll(oj){
    $("input[name = 'employeeCheck']").prop("checked",oj.checked);
}
//单个复选框选择,判断(全选)复选框是否需要选择
function employeeCheck(oj){
    var employeeCheck = $("input[name = 'employeeCheck']");
    $("input[id = 'employeeCheckAll']").prop("checked",employeeCheck.length == $("input[name='employeeCheck']:checked").length ? true : false);
}

//点击供应商信息导航
function onClickEmployeeNav(){
    if(employeeArr == null){
        getAllEmployee();
    }
}

//获取供应商列表
function getAllEmployee() {
    $.post({
        url:path+"/employee/getAll",
        data: {pageIndex: employeePageIndex},
        dataType: "json",
        success: function (data) {
            if(data.success) {
                setEmployee(data.list.results); //显示供应商
            }
        }
    });
}

function setEmployee(list){
    employeeArr = list;
    var tBody = '';
    $(list).each(function(i,item){
        //设置每行供应商内容
        var tr ="<tr><td>"+ item.name +"</td><td>" +item.phone+"</td><td>"+item.position+
            "</td><td>"+item.addr+"</td><td>"+item.age+"</td><td>"+item.college+"</td>"
            +"<td>" +item.major+"</td><td>"+item.career+"</td>"
            +"<td style='max-width: 20px;text-align: center'>"
            +"<input type='checkbox' name='employeeCheck' onclick='employeeCheck(this)'  data-name='"+item.name+"' data-id='"+item.id+"'  /></td>"
            + "<td style='max-width: 20px;text-align: center'><button class='btn btn-primary' onclick='updateEmployee(this)' data-index='"+i+"'>修改</button></td>"
            +"</tr>";
        tBody += tr;

    });
    $("#employeeTb tbody").html(tBody);
}

//修改供应商
function updateEmployee(oj){
    var index = oj.getAttribute("data-index");
    var item = employeeArr[index];
    $("#id").val(item.id);
    $("#name").val(item.name);
    $("#phone").val(item.phone);
    $("#addr").val(item.addr);
    $("#age").val(item.age);
    $("#major").val(item.major);
    $("#college").val(item.college);
    $("#position").val(item.position);
    $("#career").val(item.career);
    $("#addEmployeeModal").modal('show');
}

function addOrUpdateEmployee(){
    var data = new Object();
    if($("#id").val() > 0) {
        data.id =$("#id").val() ;
        $.post({
            url: path+"/employee/update",
            data: $("#employeeForm").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addEmployeeModal").modal("hide");
                    getAllEmployee();
                    resetForm();
                }
            }
        });
    }else{
        $.post({
            url: path+"/employee/add",
            data: $("#employeeForm").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#addEmployeeModal").modal("hide");
                    getAllEmployee();
                    resetForm();
                }
            }
        });
    }
}

function resetForm(){
    $("#name").val("");
    $("#phone").val("");
    $("#addr").val("");
    $("#age").val("");
    $("#major").val("");
    $("#college").val("");
    $("#position").val("");
    $("#career").val("");
    $("#id").val("");
}

function delEmployee(){
    var data = new Array();
    var dataName = [];
    var checkedBtn =  $("input[name = 'employeeCheck']:checked");
    for(var i = 0; i < checkedBtn.length; i++){
        data[i] = checkedBtn.get(i).getAttribute("data-id");
        dataName[i] = checkedBtn.get(i).getAttribute("data-name");
    }
    $.post({
        url:"http://localhost:8080/Delphi/employee/del",
        dataType:"json",
        data:{"id": data, "names":dataName},
        traditional: true,
        success:function(data){
            if(data.success){
                getAllEmployee();
            }
        }
    });
}