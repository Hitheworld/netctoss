<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global_color.css" /> 
       	<script type="text/javascript" src="/netctoss/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
        	//获取表单值
        	var idcardNo = $("#idcardNo").val();
        	var realName = $("#realName").val();
        	var loginName = $("#loginName").val();
        	var status = $("#status").val();
        	//发送请求
        	$.ajax({
        		url:"netctoss/account/list/*/*/*/-1/1",
        		type:"get",
        		beforeSend:function(xhr){
    					//将账号和密码放入HTTP请求的Header部分
    					xhr.setRequestHeader("idcardNo",idcardNo);
    					xhr.setRequestHeader("realName",realName);
    					xhr.setRequestHeader("loginName",loginName);
    					xhr.setRequestHeader("status",status);
    				},
    				success:function(data){//data是服务器返回内容
    					//TODO
    					var ok = data.login;
    					if(ok){//登录成功,进入index.jsp
    						window.location="/netctoss/account/list/1";
    					}else{
    						//登录未成功,将返回的错误信息显示
    						//$("#code_error").html(data.code_error);
    					}
    				}
        	});
        	
        	
        	
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此账务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            
            //搜索按钮处理
            function doSearch(page){
            	var idcardNo = $("#idcardNo").val();
            	var loginName = $("#loginName").val();
            	var realName = $("#realName").val();
            	var status = $("#status").val();
            	//根据条件变量状态生成一个rest风格查询的url
            	var url = "/netctoss/account/account_list";
            	//添加身份证条件
            	if(idcardNo == ""){
            		url = url +"/*"
            	}else{
            		url = url +"/"+idcardNo;
            	}
            	//添加姓名条件
            	if(realName == ""){
            		url = url +"/*"
            	}else{
            		url = url +"/"+realName;
            	}
            	//添加登录名条件
            	if(loginName == ""){
            		url = url +"/*"
            	}else{
            		url = url +"/"+loginName;
            	}
            	//添加状态条件
            	url = url +"/"+status;
            	//添加要显示的页数
            	url = url +"/"+page
            	//alert(url);
            	window.location = url;//对应get请求
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="/netctoss/images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_on"></a></li>
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
           <form:form commandName="page">
                <!--查询-->
                <div class="search_add">                        
                    <div>身份证：<form:input path="idcard_no" id="idcardNo" cssClass="text_search"/></div>                            
                    <div>姓名：<form:input path="real_name" id="realName" cssClass="width70 text_search"/></div>
                    <div>登录名：<form:input path="login_name" id="loginName" cssClass="text_search"/></div>
                    <div>
                        状态：
                        <form:select path="status" id="status" cssClass="select_search">
                        	<form:option value="-1">全部</form:option>
                        	<form:option value="0">开通</form:option>
                        	<form:option value="1">暂停</form:option>
                        	<form:option value="2">删除</form:option>
                        </form:select>
                        
                        
                    </div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="doSearch(1);"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='account_add.html';" />
                </div>  
                <!--删除等的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功，且已删除其下属的业务账号！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th>账号ID</th>
                        <th>姓名</th>
                        <th class="width150">身份证</th>
                        <th>登录名</th>
                        <th>状态</th>
                        <th class="width100">创建日期</th>
                        <th class="width150">上次登录时间</th>                                                        
                        <th class="width200"></th>
                    </tr>
          <c:forEach items="${accounts}" var="account">
                    <tr>
                        <td>${account.account_id}</td>
                        <td><a href="account_detail.html">${account.real_name}</a></td>
                        <td>${account.idcard_no}</td>
                        <td>${account.login_name}</td>
                        <td>${account.status}</td>
                        <td>${account.create_date}</td>
                        <td>${account.last_login_time}</td>                            
                        <td class="td_modi">
                            <input type="button" value="暂停" class="btn_pause" onclick="setState();" />
                            <input type="button" value="修改" class="btn_modify" onclick="location.href='account_modi.html';" />
                            <input type="button" value="删除" class="btn_delete" onclick="deleteAccount();" />
                        </td>
                    </tr>
       </c:forEach>
                </table>
                <p>业务说明：<br />
                1、创建则开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、暂停账务账号，同时暂停下属的所有业务账号；<br />                
                6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
                7、删除账务账号，同时删除下属的所有业务账号。</p>
                </div>                   
                <!--分页-->
                <div id="pages">
                    <a onclick="doSearch(1);" href="#">首页</a>
        	        <a href="javascript:doSearch(${page.page-1});">上一页</a>
                    
                    <c:forEach begin="1" end="${page.totalPage}" var="i">
                    	<c:choose>
                    		<c:when test="${i==page.page}">
                    		<a href="javascript:doSearch(${i});" class="current_page">${i}</a>
                    		</c:when>
                    		<c:otherwise>
                    		<a href="javascript:doSearch(${i});">${i}</a>
                    		</c:otherwise>
                    	</c:choose>
                    </c:forEach>
                    <a href="javascript:doSearch(${page.page+1});">下一页</a>
                    <a href="javascript:doSearch(${page.totalPage});">末页</a>
                </div>                    
            </form:form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
