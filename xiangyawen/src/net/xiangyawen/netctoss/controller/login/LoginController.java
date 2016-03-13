package net.xiangyawen.netctoss.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/login")
public class LoginController {
	

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String toAdd(){
		return "login";
	}
	

}
