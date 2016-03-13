package net.xiangyawen.netctoss.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;
import net.xiangyawen.netctoss.entity.Role;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
@Transactional
public class AdminUpdataController {

	@Resource
	private AdminInfoMapperDao adminDao;

	@RequestMapping(value="/updata/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") int id, Model model) {
		//查询全部的角色，用于初始化checkbox
		List<Role> list = adminDao.findAllRole();
		model.addAttribute("roles", list);
		//查询出要修改的角色，用于显示默认值
		AdminInfo admin = adminDao.findByAdminId(id);
		List<Role> adminRoles= adminDao.findRoleByAdminId(id);
		model.addAttribute("adminRoles", adminRoles);
		model.addAttribute("admin", admin);
		return "admin/admin_modi";
	}
	
	
}
