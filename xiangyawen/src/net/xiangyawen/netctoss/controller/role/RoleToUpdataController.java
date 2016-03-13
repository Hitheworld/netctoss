package net.xiangyawen.netctoss.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.Role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/role")
public class RoleToUpdataController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/updata",method=RequestMethod.PUT)
	public String update(Role role,HttpSession session) {
		//修改角色
		dao.update(role);
		//删除该角色对应的中间表数据
		dao.deleteRoleModule(role.getRole_id());
		//插入该角色对应的中间表数据
		Integer roleId = role.getRole_id();
		List<Integer> moduleIds =  role.getModuleIds();
		if(moduleIds != null && moduleIds.size()>0) {
			for(Integer moduleId : moduleIds) {
				Map<String, Object> rm =  new HashMap<String, Object>();
				rm.put("role_id", roleId);
				rm.put("module_id", moduleId);
				dao.saveRoleModule(rm);
			}
		}		
		//获取session中存储的page信息
		Integer page = (Integer) session.getAttribute("rolePage");
		return "redirect:/role/list/"+page;
	}
}
