package net.xiangyawen.netctoss.controller.fee;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeAddController {
	
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	
	
	@RequestMapping(value="/toadd",method=RequestMethod.GET)
	public String toAdd(){
		return "fee/fee_add";
	}
	
	
	//将表单参数封装成cost对象传入,jsp表单组件中name属性要与Cost中属性一致
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Cost cost,HttpSession session){  
		//调用dao.saveCost方法
		dao.saveCost(cost);
		//获取session中存储的page信息
		Integer page = (Integer) session.getAttribute("feePage");
		return "redirect:/fee/list/"+page;
	}

}
