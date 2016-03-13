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
		//�޸Ĺ���Ա��Ϣ
		adminDao.update(admin);
		//ɾ���ù���Ա��Ӧ���м������
		System.out.println(admin.getAdmin_id());
		adminDao.deleteAdminRole(admin.getAdmin_id());
		//����ù���Ա��Ӧ���м������
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
		//��ȡsession�д洢��page��Ϣ
		Integer page = (Integer) session.getAttribute("aPage");
		return "redirect:/admin/list/"+page;
	}
}
