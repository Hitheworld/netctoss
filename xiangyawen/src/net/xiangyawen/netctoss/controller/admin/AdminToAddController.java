package net.xiangyawen.netctoss.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.entity.Role;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
@Transactional(readOnly=true)
public class AdminToAddController {
	
	@Resource
	private AdminInfoMapperDao adminDao;

	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String execute(Model model){
		List<Role> list = adminDao.findAllRole();
		model.addAttribute("roles",list);
		return "admin/admin_add";
	}
	
	
}
