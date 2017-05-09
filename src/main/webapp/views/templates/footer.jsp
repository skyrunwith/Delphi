<%-- Created by rayn on 05/14 2015 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="<c:url value='/static/js/jquery-3.1.1.js' />" ></script>
<script src="<c:url value='/static/js/bootstrap.js' />" ></script>
<script src="<c:url value='/static/js/moment.js'/>"></script>
<script src="<c:url value='/static/js/bootstrap-datetimepicker.js'/>"></script>
<script src="<c:url value='/static/js/locales/bootstrap-datetimepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/static/templates/navbar.js'/> "></script>
<script src="<c:url value='/static/js/Chart.js'/> "></script>
<script src="<c:url value='/static/js/jquery.validate.min.js'/>"></script>
<script src="<c:url value='/static/js/messages_zh.min.js'/>"></script>
<script type="text/javascript" language="JavaScript">
    function showTitle(){
        var type = ${sessionScope.user.type};
        if(type == 1){ //供应商
            $("#purchaseManage").hide();
//            $("#sellManage").hide();
            $("#storageManage").hide();
            $("#producerinfoTitle").hide();
            $("#clientinfoTitle").hide();
            $("#employeeTitle").hide();
        }else if(type == 2){   //客户
                $("#basicManage").hide();
                $("#sellManage").hide();
                $("#storageManage").hide();
        }else if(type == 3){  //员工
            $("#basicManage").hide();
            $("#purchaseManage").hide();
            $("#storageManage").hide();
        }
    }
    function sureNull(str){
        if(str == null){
            str = "";
        }
        return str;
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
</script>
<%--
<!-- /. FOOTER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="<c:url value='/assets/js/jquery-1.10.2.js'/>"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="<c:url value='/assets/js/bootstrap.js'/>"></script>
<!-- METISMENU SCRIPTS -->
<script src="<c:url value='/assets/js/jquery.metisMenu.js'/>"></script>
<!-- CUSTOM SCRIPTS -->
<script src="<c:url value='/assets/js/custom.js'/>"></script>--%>
