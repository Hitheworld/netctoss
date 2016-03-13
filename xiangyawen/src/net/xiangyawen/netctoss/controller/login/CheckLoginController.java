package net.xiangyawen.netctoss.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xiangyawen.netctoss.dao.AdminInfoMapperDao;
import net.xiangyawen.netctoss.entity.AdminInfo;


@Controller
@RequestMapping("/login")
public class CheckLoginController {
	
	@Resource
	private AdminInfoMapperDao dao;
	
	
	//����������װ��cost������,jsp�������name����Ҫ��Cost������һ��
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> login(
			@RequestHeader("code") String code,
			@RequestHeader("name") String name,
			@RequestHeader("password") String password,
			Model model,HttpSession session){ 
		Map<String, Object> map = new HashMap<String, Object>();
		AdminInfo info = dao.findByAdminCode(name);
		map.put("login", false);//����ʶ��¼ʧ��
		//model.addAttribute("admin", admin);
		//��sessinȡ����֤��
		String scode = (String) session.getAttribute("scode");
		if(code==null ||!code.equalsIgnoreCase(scode)){
			//��֤�����
			map.put("code_error", "��֤�����");
			return map;
		}
		if(info==null || !password.equals(info.getPassword())){
			map.put("error", "�û�����������������ԣ�");
		}else{
			//session.setAttribute("user", info.getName());
			session.setAttribute("user", info.getAdmin_code());
			session.setMaxInactiveInterval(30*60);//��ʱʱ��
				map.put("login", true);
		}
		return map;
	}

}
