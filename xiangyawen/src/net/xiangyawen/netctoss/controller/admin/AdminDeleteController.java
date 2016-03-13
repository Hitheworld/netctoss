package net.xiangyawen.netctoss.controller.admin;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@Transactional
public class AdminDeleteController {

	@Resource
	private AdminInfoMapperDao adminDao;

	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean execute(@PathVariable("id") Integer id) {
		if(id != null){
			adminDao.deleteAdminRole(id);
			adminDao.deleteAdmin(id);
		}
		return true;
	}
	
	
}
