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
	
	//请求"/account/list/*/*/*/-1/1"
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
		//构建查询参数对象
		AccountPage page = new AccountPage();
		page.setIdcard_no("*");
		page.setReal_name("*");
		page.setLogin_name("*");
		page.setStatus("-1");
		page.setPage(p);
		//调用AccountMapperDao查询
		List<Account> list = 
			dao.findByPage(page);
		model.addAttribute("accounts", list);
		//计算总页数
		int totalRows = dao.findRows(page);
		int totalPage = 1;//默认为1
		if(totalRows%page.getPageSize() == 0){
			//能整除时,按10/5=2计算
			totalPage = 
				totalRows/page.getPageSize();
		}else{//不能整除时,按7/5=1+1计算
			totalPage = 
				totalRows/page.getPageSize()+1;
		}//将总页数放入page对象
		page.setTotalPage(totalPage);
		//将条件*号清空
		page.setIdcard_no(convert(page.getIdcard_no()));
		page.setReal_name(convert(page.getReal_name()));
		page.setLogin_name(convert(page.getLogin_name()));
		model.addAttribute("page",page);
		return "account/account_list";//进入account_list.jsp
	}
	
	public String convert(String s){
		if("*".equals(s)){
			return "";
		}else{
			return s;
		}
	}
	
	
}
