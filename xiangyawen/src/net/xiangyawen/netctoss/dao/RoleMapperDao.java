package net.xiangyawen.netctoss.dao;

import java.util.List;
import java.util.Map;

import net.xiangyawen.netctoss.entity.Module;
import net.xiangyawen.netctoss.entity.Role;
import net.xiangyawen.netctoss.util.MyBatisDao;
import net.xiangyawen.netctoss.vo.Page;

@MyBatisDao
public interface RoleMapperDao {

	public List<Module> findModuleByRoleId(int id);
	
	/**
	 * ��ҳ��ѯ��ɫ���ڲ�ѯ��ÿһ����ɫ��
	 * Ҫ�Զ���ѯ������Ӧ��ģ�飬������Щ
	 * ģ��װ�䵽��ɫ�����С�
	 */
	public List<Role> findByPage(Page page);
	
	public int findRows();
	
	public List<Module> findAllModule();
	
	/**
	 * ������ɫ
	 */
	public void saveRole(Role role);
	
	/**
	 * ������ɫģ���м������Map�д���2��ֵ��
	 * һ���ǽ�ɫID��һ����ģ��ID��
	 * key			value
	 * role_id		5
	 * module_id	7
	 */
	public void saveRoleModule(Map<String, Object> rm);
	
	public Role findByName(String name);
	
	/**
	 * ����ID��ѯ��ɫ��ͬʱ�Զ���ѯ�ý�ɫ
	 * ��Ӧ��ȫ��ģ�顣
	 */
	public Role findById(int id);
	
	public void update(Role role);
	
	public void deleteRoel(int roleId);
	
	public void deleteRoleModule(int roleId);
}
