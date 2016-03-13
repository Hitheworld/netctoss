package net.xiangyawen.netctoss.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class AdminResetPasswordController {
	
	public static final String DEFAULT_PASSWORD = "123456";
	
	@Resource
	private AdminInfoMapperDao adminDao;
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePassword(String ids) {
			Map<String, Object> result = new HashMap<String, Object>();
			if(ids == null || ids.length() == 0) {
				result.put("message", "参数不能为空.");
				return result;
			}
			
			List<Integer> idList = buildIdList(ids);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", idList);
			map.put("defaultPassword", DEFAULT_PASSWORD);
			
			adminDao.updatePassword(map);
			
			result.put("message", "密码重置成功.");
			return result;
	}

	/**
	 * Alt+Shift+M
	 */
	private List<Integer> buildIdList(String ids) {
		List<Integer> idList = 
			new ArrayList<Integer>();
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			idList.add(Integer.valueOf(id));
		}
		return idList;
	}
}
	
	

