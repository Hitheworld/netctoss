<%@ page import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global_color.css" /> 
        <script type="text/javascript" src="../../js/jquery-1.11.1.js"></script>
        <script type="text/javascript">
			 //将form转为AJAX提交
			function ajaxSubmit(frm, fn) {
				var dataPara = getFormJson(frm);
				$.ajax({
					url: frm.action,
					type: frm.method,
					data: dataPara,
					success: fn
				});
			}
		
			//将form中的值转换为键值对。
			function getFormJson(frm) {
				var o = {};
				var a = $(frm).serializeArray();
				$.each(a, function () {
					if (o[this.name] !== undefined) {
						if (!o[this.name].push) {
							o[this.name] = [o[this.name]];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				});
		
				return o;
			}
			
			
			//调用
		    $(document).ready(function(){
		    
				$('#form1').bind('submit', function(){
					ajaxSubmit(this, function(admins){
						var str = "";
						for(var i=0; i<admins.length;i++){
							str += "<tr><td>"+admins[i].name+"</td><td>"+admins[i].telphone+"</td></tr>";
						}
						$("#datalist").html(str);
						
					});
					return false;
				});
				
				
		    });
		
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
               // document.getElementById("operate_result_info").style.display = "block";
                 if(r){
                	$.ajax({
                		url:"../delete/"+id,
                		type:"DELETE",
                		success:function(ok){
                			if(ok){
                				location="../list/"+${aPage};
                			}
                		}
                	});
                }
            }
            
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }
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
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_on"></a></li>
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
            <form id="form1" action="so" method="POST">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <select id="selModules" class="select_search">
                            <option>全部</option>
                            <c:forEach items="${modules}" var="m">
                            <option value="${m.module_id}"
                            	<c:if test="${m.module_id==adminPage.moduleId }">selected</c:if>
                            >${m.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>角色：<input type="text" name="roleName" value="${adminPage.roleName}" class="text_search width200" /></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../toAdd';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${admins}" var="a">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${a.admin_id}</td>
                            <td>${a.name}</td>
                            <td>${a.admin_code}</td>
                            <td>${a.telphone}</td>
                            <td>${a.email}</td>
                            <td><fmt:formatDate value="${a.enrolldate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                                	<c:forEach items="${a.roles}" var="r" varStatus="s">
                                		<c:choose>
                                			<c:when test="${s.first}">
                                				${r.name }
                                			</c:when>
                                			<c:when test="${s.index==1}">
                                				...
                                			</c:when>
                                		</c:choose>
                                	</c:forEach>
                                </a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                    <c:forEach items="${a.roles}" var="r" varStatus="s">
                                   		<c:choose>
                                   			<c:when test="${s.last}">
                                   				${r.name }
                                   			</c:when>
                                   			<c:otherwise>
                                   				${r.name },
                                   			</c:otherwise>
                                   		</c:choose>
                                   </c:forEach>
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='../updata/${a.admin_id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${a.admin_id});" />
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
