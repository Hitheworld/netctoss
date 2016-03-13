package net.xiangyawen.netctoss.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
@Transactional
public class AdminToUpdataController {

	@Resource
	private AdminInfoMapperDao adminDao;

	@RequestMapping(value="/updata",method=RequestMethod.PUT)
	public String update(AdminInfo admin,HttpSession session) {
		//修改管理员信息
		adminDao.update(admin);
		//删除该管理员对应的中间表数据
		System.out.println(admin.getAdmin_id());
		adminDao.deleteAdminRole(admin.getAdmin_id());
		//插入该管理员对应的中间表数据
		Integer adminId = admin.getAdmin_id();
		List<Integer> roleIds =  admin.getRoleIds();
		if(roleIds != null && roleIds.size()>0) {
			for(Integer roleId : roleIds) {
				Map<String, Object> ar =  new HashMap<String, Object>();
				ar.put("admin_id", adminId);
				ar.put("role_id", roleId);
				adminDao.saveAdminRole(ar);
			}
		}		
		//获取session中存储的page信息
		Integer page = (Integer) session.getAttribute("aPage");
		return "redirect:/admin/list/"+page;
	}
}
