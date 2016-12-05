
<%--
  后台管理,整体页面的布局
  User: quxiucheng
  Date: 2016/11/18
  Time: 15:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入 taglib页面--%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <%--引入css页面--%>
        <%@include file="/WEB-INF/views/include/includeCss.jsp"%>
        <%@ include file="/WEB-INF/views/include/includeJs.jsp"%>
        <sitemesh:write property='head'></sitemesh:write>
    </head>
    <body class="no-skin">
        <jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try{ace.settings.check('main-container' , 'fixed')}catch(e){}
            </script>
            <div class="main-content">
                <jsp:include page="/WEB-INF/views/common/left.jsp"></jsp:include>
                <div class="main-content">
                    <div class="main-content-inner">
                        <!-- #section:basics/content.breadcrumbs -->
                        <div class="breadcrumbs" id="breadcrumbs">
                            <script type="text/javascript">
                                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                            </script>

                            <ul class="breadcrumb">
                                <li>
                                    <i class="ace-icon fa fa-home home-icon"></i>
                                    <a href="#">Home</a>
                                </li>

                                <li>
                                    <a href="#">Other Pages</a>
                                </li>
                                <li class="active">Blank Page</li>
                            </ul><!-- /.breadcrumb -->

                            <!-- #section:basics/content.searchbox -->
                            <div class="nav-search" id="nav-search">
                                <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                                </form>
                            </div><!-- /.nav-search -->

                            <!-- /section:basics/content.searchbox -->
                        </div>

                        <div class="page-content">
                            <jsp:include page="/WEB-INF/views/common/settings.jsp"></jsp:include>
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- PAGE CONTENT BEGINS -->
                                    <sitemesh:write property='body'></sitemesh:write>
                                    <!-- PAGE CONTENT ENDS -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
        </div>
        <%--在页脚引入javascript页面--%>
       <%-- <%@ include file="/WEB-INF/views/include/includeJs.jsp"%>--%>
        <!--用户自定义javascript脚本1-->
        <sitemesh:write property='footScriptTag'></sitemesh:write>
    </body>
</html>