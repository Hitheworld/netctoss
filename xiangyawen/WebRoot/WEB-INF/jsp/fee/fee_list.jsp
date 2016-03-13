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
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            
            
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){
                	//确定删除
                	//window.location="../"+id;  //属于get请求,而实际是delete请求
                	$.ajax({
                		url:"../"+id,
                		type:"DELETE",
                		success:function(ok){
                			if(ok){
                				location="../list/1";
                			}
                		}
                	});
                }
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../../images/logo.png" alt="logo" class="left"/>
			<span>当前账号：<b>scott</b></span>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../../index" class="index_off"></a></li>
                <li><a href="../../role/list" class="role_off"></a></li>
                <li><a href="../../admin/list" class="admin_off"></a></li>
                <li><a href="../../fee/list" class="fee_on"></a></li>
                <li><a href="../../account/list" class="account_off"></a></li>
                <li><a href="../../service/list" class="service_off"></a></li>
                <li><a href="../../bill/list" class="bill_off"></a></li>
                <li><a href="../../report/list" class="report_off"></a></li>
                <li><a href="../../user/user" class="information_off"></a></li>
                <li><a href="../../user/pwd" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../toadd';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr> 
                        <c:forEach items="${costs}" var="c">                     
                        <tr>
                            <td>${c.cost_id}</td>
                            <td><a href="../content/${c.cost_id}">${c.name}</a></td>
                            <td>${c.base_duration}小时</td>
                            <td>${c.base_cost}元</td>
                            <td>${c.unit_cost}元/小时</td>
                            <td>${c.creatime}</td>
                            <td>${c.startime}</td>
                            <td>${c.status=="1"?"暂停":"开通"}</td>
                            <td>  
                            	<c:if test='${c.status=="1"}'>  <!-- 暂停状态才允许出现几个按钮操作 -->                            
                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='../${c.cost_id}/toedit';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${c.cost_id});" />
                            	</c:if>
                            </td>
                        </tr>
                       	</c:forEach>
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
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
