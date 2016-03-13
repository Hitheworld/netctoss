package net.xiangyawen.netctoss.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
public class RoleUpdataController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/updata/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") int id, Model model) {
		//��ѯȫ����ģ�飬���ڳ�ʼ��checkbox
		List<Module> list = dao.findAllModule();
		model.addAttribute("modules", list);
		//��ѯ��Ҫ�޸ĵĽ�ɫ��������ʾĬ��ֵ
		Role role = dao.findById(id);
		 List<Module> roleModules= dao.findModuleByRoleId(id);
		model.addAttribute("roleModules", roleModules);
		model.addAttribute("role", role);
		return "role/role_modi";
	}
	
	
}
