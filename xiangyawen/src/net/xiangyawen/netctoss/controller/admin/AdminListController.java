package net.xiangyawen.netctoss.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;
import net.xiangyawen.netctoss.entity.Module;
import net.xiangyawen.netctoss.vo.AdminPage;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
@Transactional(readOnly=true)
public class AdminListController {
	
	@Resource
	private AdminInfoMapperDao adminDao;
	
	@Resource
	private RoleMapperDao roleDao;

	
	@RequestMapping(value="/list/{p}",method=RequestMethod.GET)
	public String find(@PathVariable("p") int p, Model model,HttpSession session) {
		AdminPage page = new AdminPage();
		page.setPage(p);
		//查询出全部的模块，用于初始化下拉选
		List<Module> modules = roleDao.findAllModule();
		model.addAttribute("modules", modules);
		//查询当前页的管理员
		List<AdminInfo> list = adminDao.findByPage(page);
		model.addAttribute("admins", list);
		//查询总行数，用来计算总页数
		/*int rows = adminDao.findRows();
		page.setRows(rows);*/
		//计算总页数
		int totalRows = adminDao.findRows();
		int totalPage = 1;
		if(totalRows%page.getPageSize() == 0){
			//  7/7    14/7
			totalPage = totalRows/page.getPageSize();
		}else{
			//  7/51+1
			totalPage = totalRows/page.getPageSize()+1;
		}
		page.setTotalPage(totalPage);
		model.addAttribute("page", page);
		model.addAttribute("adminPage", page);
		session.setAttribute("aPage", p);
		return "admin/admin_list";
	}
	
	/*用户体验*/
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String toExecute(){
		return "redirect:/admin/list/1";
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public String Index(){
		return "redirect:/admin/list/1";
	}
	
	
}
