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
	
	
	//����������װ��cost������,jsp�������name����Ҫ��Cost������һ��
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Cost cost,HttpSession session){  
		//����dao.saveCost����
		dao.saveCost(cost);
		//��ȡsession�д洢��page��Ϣ
		Integer page = (Integer) session.getAttribute("feePage");
		return "redirect:/fee/list/"+page;
	}

}
