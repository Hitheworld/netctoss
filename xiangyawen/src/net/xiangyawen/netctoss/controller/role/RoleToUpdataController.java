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
		//�޸Ľ�ɫ
		dao.update(role);
		//ɾ���ý�ɫ��Ӧ���м������
		dao.deleteRoleModule(role.getRole_id());
		//����ý�ɫ��Ӧ���м������
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
		//��ȡsession�д洢��page��Ϣ
		Integer page = (Integer) session.getAttribute("rolePage");
		return "redirect:/role/list/"+page;
	}
}
