package net.xiangyawen.netctoss.controller.role;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.Role;
import net.xiangyawen.netctoss.vo.Page;

@Controller
@RequestMapping("/role")
public class RoleListController {

	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/list/{p}",method=RequestMethod.GET)
	public String execute(@PathVariable("p") int p,Model model,HttpSession session){
		Page page = new Page();
		page.setPage(p);
		List<Role> list = dao.findByPage(page);
		//¼ÆËã×ÜÒ³Êý
		int totalRows = dao.findRows();
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
		model.addAttribute("roles",list);
		session.setAttribute("rolePage", p);
		return "role/role_list";
	}
}
