package net.xiangyawen.netctoss.controller.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.xiangyawen.netctoss.dao.AccountMapperDao;
import net.xiangyawen.netctoss.entity.Account;
import net.xiangyawen.netctoss.vo.AccountPage;

@Controller
@RequestMapping("/account")
public class AccountListController {
	@Resource
	private AccountMapperDao dao;
	
	//����"/account/list/*/*/*/-1/1"
	@RequestMapping(
		value="/list/{idcard_no}/{real_name}/{login_name}/{status}/{page}",
		method=RequestMethod.GET)
	public String execute(
		@PathVariable("idcard_no") String idcard_no,
		@PathVariable("real_name") String real_name,
		@PathVariable("login_name") String login_name,
		@PathVariable("status") String status,
		@PathVariable("page") Integer p,
		Model model){
		System.out.println("idcard_no="+idcard_no);
		System.out.println("real_name="+real_name);
		System.out.println("login_name="+login_name);
		System.out.println("status="+status);
		System.out.println("page="+p);
		//������ѯ��������
		AccountPage page = new AccountPage();
		page.setIdcard_no("*");
		page.setReal_name("*");
		page.setLogin_name("*");
		page.setStatus("-1");
		page.setPage(p);
		//����AccountMapperDao��ѯ
		List<Account> list = 
			dao.findByPage(page);
		model.addAttribute("accounts", list);
		//������ҳ��
		int totalRows = dao.findRows(page);
		int totalPage = 1;//Ĭ��Ϊ1
		if(totalRows%page.getPageSize() == 0){
			//������ʱ,��10/5=2����
			totalPage = 
				totalRows/page.getPageSize();
		}else{//��������ʱ,��7/5=1+1����
			totalPage = 
				totalRows/page.getPageSize()+1;
		}//����ҳ������page����
		page.setTotalPage(totalPage);
		//������*�����
		page.setIdcard_no(convert(page.getIdcard_no()));
		page.setReal_name(convert(page.getReal_name()));
		page.setLogin_name(convert(page.getLogin_name()));
		model.addAttribute("page",page);
		return "account/account_list";//����account_list.jsp
	}
	
	public String convert(String s){
		if("*".equals(s)){
			return "";
		}else{
			return s;
		}
	}
	
	
}
