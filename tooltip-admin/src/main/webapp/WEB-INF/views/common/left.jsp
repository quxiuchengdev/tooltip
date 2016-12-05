<%--
  User: 曲修成
  Date: 2016/11/23
  Time: 16:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入 taglib页面--%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<div id="sidebar" class="sidebar responsive">
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <!-- #section:basics/sidebar.layout.shortcuts -->
            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>

            <!-- /section:basics/sidebar.layout.shortcuts -->
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- /.sidebar-shortcuts -->

    <ul class="nav nav-list" id="menu">
        <!-- 遍历一级菜单开始 -->
        <c:forEach items="${fns:getChildTreeMenuList()}" var="menu" varStatus="index">
            <!-- 判断是否为顶级菜单 开始-->
            <c:if test="${menu.parent.id eq '1' && menu.isShow eq '1'}">
                <li class="">
                    <!-- 连接 -->
                    <%-- 判断a标签是否有链接 --%>
                    <a href="${not empty menu.childList ? 'javascript:;':(fn:indexOf(menu.href, '://') eq -1 ? ctx : '')}${menu.href}"
                       class="${not empty menu.childList ? 'dropdown-toggle':''} ${empty menu.childList?'enda':''}"
                       id="${menu.id }" >
                        <!-- 图标 -->
                        <i class="menu-icon ${not empty menu.icon ?menu.icon:'menu-icon glyphicon glyphicon-circle-arrow-right'}"></i>
                        <span class="menu-text">${menu.name}</span>
                        <!-- 向下的箭头 -->
                        <c:if test="${not empty menu.childList}">
                            <b class="arrow fa fa-angle-down"></b>
                        </c:if>
                    </a>
                    <!-- 分割线 -->
                    <b class="arrow"></b>
                    <!-- 判断一级菜单是否有子菜单 开始 -->
                    <c:if test="${not empty menu.childList}">
                        <!-- 二级子菜单 -->
                        <ul class="submenu">
                            <!-- 遍历二级子菜单开始 -->
                            <c:forEach items="${menu.childList}" var="menu2" varStatus="index2">
                                <li class="angleli">
                                    <a href="${not empty menu2.childList ? 'javascript:;':(fn:indexOf(menu2.href, '://') eq -1 ? ctx : '')}${menu2.href}"
                                       class="${not empty menu2.childList ? 'dropdown-toggle':''} ${empty menu2.childList?'enda':''}"
                                       id="${menu2.id }" >
                                        <!-- 图标 -->
                                        <i class="menu-icon ${not empty menu.icon ?menu.icon:'menu-icon fa fa-caret-right'}"></i>
                                        <span class="menu-text">${menu2.name}</span>
                                        <!-- 向下的箭头 -->
                                        <c:if test="${not empty menu2.childList}">
                                            <b class="arrow fa fa-angle-down"></b>
                                        </c:if>
                                    </a>
                                    <!-- 分割线 -->
                                    <b class="arrow"></b>
                                    <!-- 判断二级菜单是否有子菜单 开始 -->
                                    <c:if test="${not empty menu2.childList}">
                                        <!-- 三级级子菜单 -->
                                        <ul class="submenu" style="${index.count eq 1 && index2.count eq 1 ?'display: block;':''}">
                                            <!-- 遍历三级子菜单开始 -->
                                            <c:forEach items="${menu2.childList}" var="menu3" varStatus="index3">
                                                <li class="">
                                                    <a href="${not empty menu3.childList ? 'javascript:;':(fn:indexOf(menu3.href, '://') eq -1 ? ctx : '')}${menu3.href}"
                                                       class="${not empty menu3.childList ? 'dropdown-toggle':''} ${empty menu3.childList?'enda':''}"
                                                       id="${menu3.id }" >
                                                        <!-- 图标 -->
                                                        <i class="menu-icon ${not empty menu.icon ?menu.icon:'menu-icon fa fa-location-arrow green'}"></i>
                                                        <span class="menu-text">${menu3.name}</span>
                                                        <!-- 向下的箭头 -->
                                                        <c:if test="${not empty menu3.childList}">
                                                            <b class="arrow fa fa-angle-down"></b>
                                                        </c:if>
                                                    </a>
                                                    <!-- 分割线 -->
                                                    <b class="arrow"></b>
                                                    <!-- 判断三级菜单是否有子菜单 开始 -->
                                                    <c:if test="${not empty menu3.childList}">
                                                        <!-- 四级子菜单 -->
                                                        <ul class="submenu" style="${index.count eq 1 && index2.count eq 1 && index3.count eq 1 ?'display: block;':''}">
                                                            <!-- 遍历四级子菜单开始 -->
                                                            <c:forEach items="${menu3.childList}" var="menu4" varStatus="index4">
                                                                <li class="">
                                                                    <a href="${not empty menu4.childList ? 'javascript:;':(fn:indexOf(menu4.href, '://') eq -1 ? ctx : '')}${menu4.href}"
                                                                       class="${not empty menu4.childList ? 'dropdown-toggle':''} ${empty menu4.childList?'enda':''}"
                                                                       id="${menu4.id }" >
                                                                        <!-- 图标 -->
                                                                        <i class="menu-icon ${not empty menu.icon ?menu.icon:'menu-icon fa fa-location-arrow green'}"></i>
                                                                        <span class="menu-text">${menu4.name}</span>
                                                                        <!-- 向下的箭头 -->
                                                                        <c:if test="${not empty menu4.childList}">
                                                                            <b class="arrow fa fa-angle-down"></b>
                                                                        </c:if>
                                                                    </a>
                                                                    <!-- 分割线 -->
                                                                    <b class="arrow"></b>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                    <!-- 判断二级菜单是否有子菜单 结束 -->
                                </li>
                            </c:forEach>
                            <!-- 遍历二级子菜单结束 -->
                        </ul>
                    </c:if>
                    <!-- 判断一级菜单是否有子菜单 结束 -->
                </li>
            </c:if>
            <!-- 判断是否为顶级菜单 结束-->
        </c:forEach>
        <!-- 遍历一级菜单结束 -->
        <!-- 提示菜单 结束-->
    </ul><!-- /.nav-list -->

    <!-- #section:basics/sidebar.layout.minimize -->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>

    <!-- /section:basics/sidebar.layout.minimize -->
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
    </script>
</div>
