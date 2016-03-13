package net.xiangyawen.netctoss.dao;

import java.util.List;
import java.util.Map;

import net.xiangyawen.netctoss.entity.AdminInfo;
import net.xiangyawen.netctoss.entity.Module;
import net.xiangyawen.netctoss.entity.Role;
import net.xiangyawen.netctoss.util.MyBatisDao;
import net.xiangyawen.netctoss.vo.AdminPage;

@MyBatisDao
public interface AdminInfoMapperDao {
	
	/**
	 * ����ԱԱ��½
	 */
	public AdminInfo findByAdminCode(String name);
	/**
	 * �����˺Ų�ѯ����Ա
	 */
	//public AdminInfo findByCode(String adminCode);
	
	/**
	 * ��ҳ��ѯ����Ա��Ϣ��ͬʱ�Զ���ѯ��
	 * ÿһ������Ա��Ӧ�Ľ�ɫ�����Զ�װ��
	 * ������Ա�����С�
	 */
	public List<AdminInfo> findByPage(AdminPage page);
	
	public int findRows();
	
	/**
	 * ��ѯ�����еĽ�ɫ
	 */
	public List<Role> findAllRole();
	
	public AdminInfo findByAdminId(int id);
	
	public List<Role> findRoleByAdminId(int id);
	
	/**
	 * ��ӹ���Ա
	 */
	public void saveAdmin(AdminInfo admin);
	
	/**
	 * ��������Ա��ɫ�м������Map�д���2��ֵ��
	 * һ���ǹ���ԱID��һ���ǽ�ɫID��
	 */
	public void saveAdminRole(Map<String, Object> ar);
	
	
	/**
	 * �޸Ĺ���Ա
	 */
	public void update(AdminInfo admin);
	
	/**
	 * �������룬����Map�а���2��ֵ��һ����Ҫ����
	 * �Ĺ���ԱID���ϣ���һ����Ĭ�����롣
	 * key				value
	 * ids				List<Integer>
	 * defaultPassword	String
	 */
	public void updatePassword(Map<String, Object> param);

	public void deleteAdminRole(int adminId);
	
	/**
	 * ɾ������Ա��Ϣ
	 */
	public void deleteAdmin(int adminId);
	
	/**
	 * ��ѯĳ����Ա��Ȩ�޵�ģ��
	 */
	public List<Module> findModuleByAdminId(int adminId);
	
}
