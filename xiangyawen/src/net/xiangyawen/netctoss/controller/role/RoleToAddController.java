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
public class RoleToAddController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String execute(Role role,Module module,HttpSession session){
		dao.saveRole(role);
		//�����м��
		Integer roleId = role.getRole_id();
		List<Integer> moduleIds = role.getModuleIds();
		if(moduleIds != null && moduleIds.size()>0){
			for(Integer moduleId: moduleIds){
				Map<String, Object> rm = new HashMap<String, Object>();
				rm.put("role_id", roleId);
				rm.put("module_id", moduleId);
				dao.saveRoleModule(rm);
			}
		}
		//��ȡsession�д洢��page��Ϣ
		Integer page = (Integer) session.getAttribute("rolePage");
		return "redirect:/role/list/"+page;
	}
	
	
}
