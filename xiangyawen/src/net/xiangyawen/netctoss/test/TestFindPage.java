package net.xiangyawen.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.vo.Page;

public class TestFindPage {

	public static void main(String[] args) {
		String conf = "net/xiangyawen/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		CostMapperDao dao = ac.getBean("costMapperDao",CostMapperDao.class);
		//创建page对象
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(2);
		
		List<Cost> list = dao.findPage(page);
		for(Cost cost : list){
			System.out.println(cost.getCost_id()+""+cost.getName()+""+cost.getCreatime());
		}

	}

}
