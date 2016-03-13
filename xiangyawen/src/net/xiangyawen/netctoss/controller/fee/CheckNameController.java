package net.xiangyawen.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class CheckNameController {
	
	@Resource
	private CostMapperDao dao;
	//setDao，set方法可以省略
//	public void setDao(CostMapperDao dao) {
//		this.dao = dao;
//	}


	//(value="name",required=false)  为了防止不传参数的情况
	@RequestMapping("/check/{name}")
	@ResponseBody   //返回json
	public boolean check(@PathVariable("name") String name){
		Cost cost = dao.findByName(name);
		if(cost == null){
			return true;////没记录，表明资费名可用
		}else{
			return false;//有记录，表明资费名不可用
		}
		
	}
}
