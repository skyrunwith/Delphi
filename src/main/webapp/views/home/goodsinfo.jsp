<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="goodsinfo.jsp">COMPANY NAME</a>
            </div>

            <div class="header-right">

                <a href="message-task.html" class="btn btn-info" title="New Message"><b>30 </b><i class="fa fa-envelope-o fa-2x"></i></a>
                <a href="message-task.html" class="btn btn-primary" title="New Task"><b>40 </b><i class="fa fa-bars fa-2x"></i></a>
                <a href="login.html" class="btn btn-danger" title="Logout"><i class="fa fa-exclamation-circle fa-2x"></i></a>

            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation" style="width: 10%">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a class="active-menu" href="home.html"><i class="fa fa-dashboard "></i>基础信息</a>
                    </li>
                    <li>
                        <a  href="goodsinfo.jsp"><i class="fa fa-dashboard "></i>采购管理</a>
                    </li>
                    <li>
                        <a href="goodsinfo.jsp"><i class="fa fa-dashboard "></i>销售管理</a>
                    </li>
                    <li>
                        <a  href="goodsinfo.jsp"><i class="fa fa-dashboard "></i>库存管理</a>
                    </li>
                </ul>
            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">DASHBOARD</h1>
                        <h1 class="page-subhead-line">This is dummy text , you can replace it with your original text. </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <c:import url="../basic/goodsinfo.jsp"></c:import>
                    </div>
                </div>
                <!--/.ROW-->

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- /. FOOTER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="../../assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="../../assets/js/bootstrap.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="../../assets/js/jquery.metisMenu.js"></script>
       <!-- CUSTOM SCRIPTS -->
    <script src="../../assets/js/custom.js"></script>
</body>
<jsp:include page="../templates/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/static/custom/category.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/custom/producer.js'/>"></script>
<%--<script type="text/javascript" src="<c:url value='/static/custom/customer.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value="/static/custom/goods.js"/> "></script>
<script type="text/javascript" language="JavaScript">

    $(document).ready(function () {
        getAllCategories();


        $('#addProducerModal').on('hide.bs.modal', function () {
            resetProducerForm();
        });
    });
</script>
</html>
