<%@ page import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global_color.css" /> 
        <script type="text/javascript" src="../../js/jquery-1.11.1.js"></script>
    	<script type="text/javascript">
    	//$(document).ready(function(){
    	
    		function deleteRole(id) {
                var r = window.confirm("确定要删除此角色吗？");
           //     document.getElementById("operate_result_info").style.display = "block";
                 if(r){
                	//确定删除
                	//window.location="../"+id;  //属于get请求,而实际是delete请求
                	$.ajax({
                		url:"../delete/"+id,
                		type:"DELETE",
                		success:function(ok){
                			if(ok){
                				location="../list/1";
                			}
                		}
                	});
                }
            }
    		
    		
    		
    	//});
    	</script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_on"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../add';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr> 
                        <c:forEach items="${roles}" var="r">                   
                        <tr>
                            <td>${r.role_id}</td>
                            <td>${r.name}</td>
                            <td>
                            
                            	<!-- 
                            		通过varStatus声明本次循环的状态对象，
                            		该状态对象有如下几个属性：
                            		index：指当前循环第几次；
                            		first：当前是否是第一次循环；
                            		last：当前是否是最后一次循环
                            	 -->
                            	<c:forEach items="${r.modules}" var="m" varStatus="s">
                            		<c:choose>
                            			<c:when test="${s.last}">
                            				${m.name}
                            			</c:when>
                            			<c:otherwise>
                            				${m.name}、
                            			</c:otherwise>
                            		</c:choose>
                            	</c:forEach>
                            
                            </td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='../updata/${r.role_id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole(${r.role_id});" />
                            </td>
                        </tr>
                        </c:forEach>  
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
                	<c:choose>
	                	<c:when test="${page.page>1}">
	        	        	<a href="${page.page-1}">上一页</a>
	                    </c:when>
	                    <c:otherwise>
	                    	<span>上一页</span>
	                    </c:otherwise>
                	</c:choose>
                	
                	<c:forEach var="i" begin="1" end="${page.totalPage}">
                		<c:choose>
                			<c:when test="${i==page.page}">
                				<a href="${i}" class="current_page">${i}</a>
                			</c:when>
                			<c:otherwise>
                				<a href="${i}">${i}</a>
                			</c:otherwise>
                		</c:choose>
                	</c:forEach>
                    
                    <c:choose>
                    	<c:when test="${page.page<page.totalPage}">
                    		<a href="${page.page+1}">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<span>下一页</span>
                    	</c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
