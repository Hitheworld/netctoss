package net.xiangyawen.netctoss.controller.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.Module;
import net.xiangyawen.netctoss.entity.Role;
import net.xiangyawen.netctoss.vo.Page;

@Controller
@RequestMapping("/role")
public class RoleAddController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String execute(Model model){
		List<Module> list = dao.findAllModule();
		model.addAttribute("modules",list);
		return "role/role_add";
	}
	
	
}
