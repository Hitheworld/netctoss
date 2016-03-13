<%@ page import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
    	<script type="text/javascript">
   			//校验权限
           function check_module() {
           	//获取选中的checkbox
           	var checkObjs = $(":checkbox[name='moduleIds']:checked");
           	//判断是否选中了任何一个checkbox
           	if(checkObjs.length==0) {
           		//没有选中任何checkbox，给予错误提示
           		$("#module_msg").addClass("error_msg");
           		return false;
           	} else {
           		//选中了checkbox
           		$("#module_msg").removeClass("error_msg");
           		return true;
           	}
           }
    	
    	//检验角色名
    	function check_name(){
   			alert("111");
   			nameFlag = null;
           	//获取名称
           	var name = $("#name").val();
           	//校验名称格式
           	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
           	if(!reg.test(name)) {
           		$("#name_msg").text("请输入1-20长度的字母、数字和汉字的组合.").addClass("error_msg");
           		nameFlag = false;
           		return;
           	}
   			$.ajax({
			   type: "POST",
			   url: "checkRoleName",
			   data:{"name":name},
			   success: function(data){
			     	if(data) {
           				//返回true，角色名重复
           				$("#name_msg").text("角色名已存在 .").addClass("error_msg");
           				nameFlag = false;
           			} else {
           				//返回false，角色名不重复
           				$("#name_msg").text("角色名有效.").removeClass("error_msg");
           				nameFlag = true;
           			}
			   }
   				
   			});
   		}
    	
    	$(document).ready(function(){
    		
    		$("#save").click(function(){
    		
    			//调用非异步请求函数，执行校验
            	if(!check_module()) {
            		return;
            	}
            	
            	//调用异步请求函数，执行校验
           	 	check_name();
           	 	//每隔100ms执行一次function
            	var timer = setInterval(function(){
            		//如果nameFlag为空，则继续循环
            		//alert(nameFlag);
            		if(nameFlag!=null) {
	            		//如果nameFalg不为空，
	            		//说明check_name执行完毕，结束循环
            			clearInterval(timer);
            			//如果返回值为true，则提交表单
            			if(nameFlag) {
            				document.forms[0].submit();
            			}
            		}
            	}, 100);
            	//校验表单数据
            	//if(check_module() && check_name()) {
	            	//提交表单
	            //	document.forms[0].submit();
            	//}
    		});
    		
    		//保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
                
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
    		
    	});
    	</script>
        <script language="javascript" type="text/javascript">
            
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，角色名称重复！-->
            <form action="add" method="POST" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" name="name" id="name" onblur="check_name();" />
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="name_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                        	<c:forEach items="${modules}" var="m">
                            <li><input type="checkbox" name="moduleIds" value="${m.module_id}" />${m.name}</li>
                        	</c:forEach>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
