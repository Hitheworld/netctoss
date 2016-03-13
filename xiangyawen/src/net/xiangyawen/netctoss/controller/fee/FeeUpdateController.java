package net.xiangyawen.netctoss.controller.fee;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeUpdateController {
	
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="/{id}/toedit",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Integer id,Model model){
		//利用id查询cost
		Cost cost = dao.findById(id);
		model.addAttribute("cost", cost);
		return "fee/fee_modi";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String updateCost(Cost cost,HttpSession session){
		//获取更新记录，调用dao更新
		dao.updateCost(cost);
		//获取session中存储的page信息
		Integer page = (Integer) session.getAttribute("feePage");
		return "redirect:/fee/list/"+page;
	}

}
