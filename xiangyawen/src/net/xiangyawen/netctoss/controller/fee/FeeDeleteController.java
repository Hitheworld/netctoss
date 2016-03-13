package net.xiangyawen.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.xiangyawen.netctoss.dao.CostMapperDao;

@Controller
@RequestMapping("/fee")
public class FeeDeleteController {

	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE) 
	@ResponseBody
	public boolean execute(@PathVariable("id") Integer id){
		if(id != null){
			//����mapperDao��delete
			dao.deleteCost(id);
		}
		return true; //��������ajax������ֱ�ӷ��ص�ҳ��
	}
}
