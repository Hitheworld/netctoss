package net.xiangyawen.netctoss.controller.role;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.RoleMapperDao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleDeleteController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean execute(@PathVariable("id") Integer id) {
		if(id != null){
			dao.deleteRoleModule(id);
			dao.deleteRoel(id);
		}
		return true;
	}
	
	
}
