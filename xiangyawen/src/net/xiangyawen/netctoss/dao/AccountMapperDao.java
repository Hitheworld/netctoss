package net.xiangyawen.netctoss.dao;

import java.util.List;

import net.xiangyawen.netctoss.entity.Account;
import net.xiangyawen.netctoss.util.MyBatisDao;
import net.xiangyawen.netctoss.vo.AccountPage;



@MyBatisDao
public interface AccountMapperDao {

	public List<Account> findByPage(AccountPage page);
	
	public int findRows();
	
	public int findRows(AccountPage page);
	
	public void stop(int id);
	
	public Account findById(int id);
	
	public Account findByIdcardNo(String idcardNo);
	
}
