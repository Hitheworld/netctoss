package net.xiangyawen.netctoss.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.vo.Page;

@Controller
@RequestMapping("/test")
public class JsonController {

	@RequestMapping("/test1")
	@ResponseBody
	public boolean f1(){
		return true;
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public Map<String, Object> f2(){
		Map map = new HashMap<String, Object>();
		map.put("id", 1001);
		map.put("name", "test");
		return map;
	}
	
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/test3")
	@ResponseBody
	public Cost f3(){
		Cost cost = dao.findById(3);
		return cost;
	}
	
	
	@RequestMapping("/test4")
	@ResponseBody
	public List<Cost> f4(){
		Page page = new Page();
		List<Cost> list = dao.findPage(page);
		return list;
	}
	
}
