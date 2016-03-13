package net.xiangyawen.netctoss.controller.role;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.Role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleCheckRoleNameController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/checkRoleName",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkRoleName(String name){
		Role roleName = dao.findByName(name);
		System.out.println(name);
		if(roleName == null){
			//角色名为空,没有重复
			return false;
		}else{
			return true;
		}
	}
	
	
}
