package net.xiangyawen.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.xiangyawen.netctoss.dao.AccountMapperDao;
import net.xiangyawen.netctoss.entity.Account;
import net.xiangyawen.netctoss.vo.AccountPage;

public class TestAccountDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String conf = 
			"net/xiangyawen/netctoss/config/applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		AccountMapperDao dao = ac.getBean(
			"accountMapperDao",AccountMapperDao.class);
		AccountPage page = new AccountPage();
		page.setPage(1);
		page.setStatus("-1");
		page.setLogin_name("*");
		page.setReal_name("*");
		page.setIdcard_no("*");
		List<Account> list = dao.findByPage(page);
		for(Account a : list){
			System.out.println(
				a.getAccount_id()+" "+a.getLogin_name());
		}
	}

}
