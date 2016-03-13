package net.xiangyawen.netctoss.controller.fee;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.vo.Page;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fee")
public class FeeListController {
	
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	//��Ӧ /fee/list/1  ����
	@RequestMapping(value="/list/{p}",method=RequestMethod.GET)
	public String execute(
			@PathVariable("p") int p,
			Model model,HttpSession session){
		Page page = new Page();
		page.setPage(p);
		List<Cost> list = dao.findPage(page);
		//������ҳ��
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
		//������ŵ�model����ҳ�����
		model.addAttribute("page", page);
		session.setAttribute("feePage", p);
		model.addAttribute("costs", list);
		return "fee/fee_list";
		
	}
	
	/*�û�����*/
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toExecute(){
		return "redirect:/fee/list/1";
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public String Index(){
		return "redirect:/fee/list/1";
	}
	
	
	
	@Test
	public static String getDate(){
		String time = new SimpleDateFormat("yy/MM/dd").format(15-01-02).toString();
		System.out.println(time);
		return time;
		
	}
}
