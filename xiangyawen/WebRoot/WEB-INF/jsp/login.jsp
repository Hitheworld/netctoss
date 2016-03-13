<%@ page import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
    	<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
    	<script type="text/javascript">
    	 $(document).ready(function(){
    	
    		$("#doLoginSubmit").click(function (){
	    		//提交表单
	   			var name = $("#admin_code").val();
	   			var pwd = $("#password").val();
	   			var code = $("#code").val();
	   			//TODO 格式检查
	   			//发送请求
	   			$.ajax({
	   				url:"login",
	   				type:"get",
	   				dataType: "json",
	   				beforeSend:function(xhr){
	   					//将账号和密码放入HTTP请求的Header部分
	   					xhr.setRequestHeader("name",name);
	   					xhr.setRequestHeader("password",pwd);
	   					xhr.setRequestHeader("code",code);
	   				}, 
	   				success:function(data){//data是服务器返回内容
	   					//TODO
	   					var ok = data.login;
	   					if(ok){//登录成功,进入index.jsp
	   						window.location="/netctoss/admin/index";
	   					}else{
	   						//登录未成功,将返回的错误信息显示
	   						$("#code_error").html(data.code_error);
	   						$("#error").html(data.error);
	   						//刷新验证码信息
	   						var t = (new Date()).getTime();
	   						$("#codeImg").attr("src","getCode?"+t);
	   					}
	   				}
	   			});
    			//表单数据js检查
    			//提交表单
    			//$("#loginForm").submit();
    		}); 
    		
    	
    		/* 
    		
    		$("#code").click(function(){
    			var t = (new Date()).getTime();
	    			$.ajax({
	    			type:"GET",
	    			url:"getCode",
	    			contentType: "image/jpeg",
	    			success:function(){
	    				$("#codeImg").attr("src","getCode?"+t);
	    			}
    			});
    		});
    		 */
    		
    	});
    	</script>
    </head>
    <body class="index">
    	<form id="loginForm">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2">
                    <input name="admin_code" type="text" id="admin_code" class="width150" /></td>
                    <td class="login_error_info">
                    <span class="required">30长度的字母、数字和下划线</span>
                    </td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2">
                    <input name="password" type="password" id="password" class="width150" /></td>
                    <td><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70">
                    	<input name="code" type="text" id="code" class="width70" /></td>
                    <td>
                    	<a href="javascript:;" id="code">
                    		<img src="getCode" alt="验证码" title="点击更换"  id="codeImg"/>
                    	</a>
                    </td>  
                    <td>
                    	<span class="required" id="code_error"></span>
                    </td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:;" id="doLoginSubmit">
                        	<img src="../images/login_btn.png" />
                        </a>
                    </td>    
                    <td>
                    	<span class="required" id="error"></span>
                    </td>                
                </tr>
            </table>
        </div>
        </form>
    </body>
</html>
