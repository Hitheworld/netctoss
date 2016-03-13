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
        <script language="javascript" type="text/javascript">
            //保存结果的提示
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

            //切换资费类型
            function feeTypeChange(type) {
                var inputArray = document.getElementById("main").getElementsByTagName("input");
                if (type == 1) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = true;
                    inputArray[6].className += " readonly";
                    inputArray[6].value = "";
                }
                else if (type == 2) {
                    inputArray[4].readonly = false;
                    inputArray[4].className = "width100";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                }
                else if (type == 3) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = true;
                    inputArray[5].value = "";
                    inputArray[5].className += " readonly";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                }
            }
            
            
            var name_flag = false;//代表name是否通过检测
            
            function checkName(){
            	name_flag = false;
            	//检查资费名是否为空
            	var v_name = $("#name").val();
            	if(v_name == ""){
            		$("#name_error").html("资费名为空");
            		$("#name_error").addClass("error_msg");
            		return false;//此处将false作为doSubmit函数返回值
            	}
            	//检查资费名是否重复
            	//ajax(/fee/check/name)-->CheckNameController-->CostMapperDao-->json(返回boolean值)
            	$.ajax({
				   type: "GET",
				   async: false,
				   url: "check/"+v_name,
				  // data: {"name":v_name},
				   success: function(ok){
				     if(ok){
				     	$("#name_error").html("资费名可用");
				     	$("#name_error").removeClass("error_msg");
				     	name_flag = true;//允许提交
				     }else{
	            		$("#name_error").html("资费名被占用,换一个吧");
	            		$("#name_error").addClass("error_msg");
	            		name_flag = false;
	            		//注意:此处用return false只是退出当前回调函数,
				     	//没有将false作为doSubmit函数的返回值,
				     	//因此不能阻止表单提交
				     }
				   }
				});
            	
            	//通过检查返回true,未通过返回false
            	return name_flag;
            	//return false;//阻止提交
            	//return true;//允许提交
            }
            
            //检测基本时长
            var baseDuration_flag = false;
            function checkBaseDuration(){
            	baseDuration_flag = false;
            	//套餐类型时，不能为空
            	var v_type = $("input[name='cost_type']:checked").val();
            	if(v_type == "2"){
            		var v_duration = $("#base_duration").val();
            		if(v_duration == ""){
            			$("#base_duration_error").html("不能为空!");
            			$("#base_duration_error").addClass("error_msg");
            			baseDuration_flag = false;
            		}else{
	            		//有值时，必须在1-600之间
	            		//inNaN,检查一个字符串不是数值类型,false表示为数值,true表示不是数值
            			var isNumber = isNaN(v_duration);
            			if(!isNumber){
            				if(parseInt(v_duration) >= 1 && parseInt(v_duration) <=600){
            					$("#base_duration_error").html("格式正确!");
            					$("#base_duration_error").removeClass("error_msg");
            					baseDuration_flag = true;
            				}
            			}
            			if(!baseDuration_flag){
            				$("#base_duration_error").html("必须在1-600之间");
            				$("#base_duration_error").addClass("error_msg");
            				baseDuration_flag = false;
            			}
            		}
            	}
            	return baseDuration_flag;
            }
            
            
            function doSubmit(){
            	checkName();  //检测资费名
            	checkBaseDuration();  //检测基本时长
            	var form_flag = name_flag && baseDuration_flag;
            	return form_flag;
            }
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
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
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
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="../fee/add" method="post" class="main_form" onsubmit="return doSubmit();">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" name="name" id="name" />
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="name_error">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost_type" value="1" id="monthly" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="cost_type" value="2" checked="checked" id="package" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="cost_type" value="3" id="timeBased" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="base_duration" class="width100" id="base_duration" />
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="base_duration_error">1-600之间的整数</div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="base_cose" class="width100" />
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long error_msg">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="unit_cost" class="width100" />
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long error_msg">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="descr"></textarea>
                    <div class="validate_msg_short error_msg">100长度的字母、数字、汉字和下划线的组合</div>
                </div>                    
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
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
