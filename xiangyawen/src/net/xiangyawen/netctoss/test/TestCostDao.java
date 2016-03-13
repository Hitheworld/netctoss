package net.xiangyawen.netctoss.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.entity.Cost;




public class TestCostDao {

	public static void main(String[] args) {
		String conf = "net/xiangyawen/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		//MyBatisDao�ӿ�������ĸСд����ȡDaoʵ��
		CostMapperDao dao = ac.getBean("costMapperDao",CostMapperDao.class);
		List<Cost> list = dao.findAll();
		for(Cost cost : list){
			System.out.println(cost.getCost_id()+""+cost.getName()+""+cost.getCreatime());
		}
		
	}

}
