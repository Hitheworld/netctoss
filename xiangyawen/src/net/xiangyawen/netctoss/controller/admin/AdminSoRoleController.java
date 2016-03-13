package net.xiangyawen.netctoss.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;
import net.xiangyawen.netctoss.vo.AdminPage;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@Transactional(readOnly=true)
public class AdminSoRoleController {
	
	@Resource
	private AdminInfoMapperDao adminDao;
	
	@Resource
	private RoleMapperDao roleDao;
	
	@RequestMapping(value="/list/so",method=RequestMethod.POST)
	@ResponseBody
	public List<AdminInfo> find(AdminPage page,Model model) {
		
		//查询当前页的管理员
		List<AdminInfo> list = adminDao.findByPage(page);
		model.addAttribute("admins", list);
		
		return list;
	}
	
	
}
