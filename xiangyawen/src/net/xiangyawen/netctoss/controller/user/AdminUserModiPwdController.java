package net.xiangyawen.netctoss.controller.user;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class AdminUserModiPwdController {
	
	@Resource
	private AdminInfoMapperDao adminDao;
	
	@RequestMapping(value="/pwd",method=RequestMethod.GET)
	public String UserPwd(Model model) {
		return "user/user_modi_pwd";
	}
}
	
	

