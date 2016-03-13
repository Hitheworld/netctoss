package net.xiangyawen.netctoss.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;


@Controller
@RequestMapping("/login")
public class CheckLoginController {
	
	@Resource
	private AdminInfoMapperDao dao;
	
	
	//将表单参数封装成cost对象传入,jsp表单组件中name属性要与Cost中属性一致
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> login(
			@RequestHeader("code") String code,
			@RequestHeader("name") String name,
			@RequestHeader("password") String password,
			Model model,HttpSession session){ 
		Map<String, Object> map = new HashMap<String, Object>();
		AdminInfo info = dao.findByAdminCode(name);
		map.put("login", false);//将标识登录失败
		//model.addAttribute("admin", admin);
		//从sessin取出验证码
		String scode = (String) session.getAttribute("scode");
		if(code==null ||!code.equalsIgnoreCase(scode)){
			//验证码错误
			map.put("code_error", "验证码错误");
			return map;
		}
		if(info==null || !password.equals(info.getPassword())){
			map.put("error", "用户名或密码错误，请重试！");
		}else{
			//session.setAttribute("user", info.getName());
			session.setAttribute("user", info.getAdmin_code());
			session.setMaxInactiveInterval(30*60);//超时时间
				map.put("login", true);
		}
		return map;
	}

}
