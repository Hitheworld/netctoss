package net.xiangyawen.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.xiangyawen.netctoss.dao.CostMapperDao;
import net.xiangyawen.netctoss.dao.RoleMapperDao;
import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.entity.Role;





public class TestRoleDao {

	public static void main(String[] args) {
		String conf = "net/xiangyawen/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		//MyBatisDao�ӿ�������ĸСд����ȡDaoʵ��
		/*RoleMapperDao dao = ac.getBean("roleMapperDao",RoleMapperDao.class);
		List<Role> list = dao.findAll();
		for(Role r : list){
			System.out.println("=================");
			System.out.println(r.getRole_id()+""+r.getName() + ""+ r.getPrisName());
			
			
			
			//��ȡ��ǰRole��Ȩ��ID
//			String pris = "";
//			for(RolePrivilege rp: r.getPris()){
//				pris += rp.getPrivilege_id();
//			}
//			System.out.println("����Ȩ��IDΪ:"+pris);
			
			
			
		}
		*/
	}

}
