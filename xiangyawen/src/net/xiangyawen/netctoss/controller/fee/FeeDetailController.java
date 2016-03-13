package net.xiangyawen.netctoss.controller.fee;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.vo.Page;

@Controller
@RequestMapping("/fee")
public class FeeDetailController {
	
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}


	@RequestMapping(value="/content/{p}",method=RequestMethod.GET)
	public String execute(
			@PathVariable("p") int p,
			Model model){
		Cost cost = dao.findById(p);
		model.addAttribute("cost", cost);
		return "fee/fee_detail";
	}
	
	
	@RequestMapping(value="/return",method=RequestMethod.GET)
	@ResponseBody
	public Page getPage(HttpSession session){
 		Integer page = (Integer) session.getAttribute("feePage");
 		Page p=new Page();
 		p.setPage(page);
		return p;
	}
	
	@RequestMapping(value="/getChecked/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Cost getCost(@PathVariable("id") Integer id){
		Cost c = dao.findById(id);
		return c;
	}
	
}
