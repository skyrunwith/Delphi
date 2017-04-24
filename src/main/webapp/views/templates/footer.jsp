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
<script type="text/javascript" language="JavaScript">
    function showTitle(){
        var type = ${sessionScope.user.type};
        if(type == 1){
            $("#purchaseManage").hide();
            $("#sellManage").hide();
            $("#storageManage").hide();
        }else if(type == 2){
                $("#basicManage").hide();
                $("#sellManage").hide();
                $("#storageManage").hide();
        }
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
