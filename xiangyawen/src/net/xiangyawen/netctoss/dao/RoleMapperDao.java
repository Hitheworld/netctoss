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
	 * 分页查询角色，在查询到每一个角色后，
	 * 要自动查询出它对应的模块，并将这些
	 * 模块装配到角色对象中。
	 */
	public List<Role> findByPage(Page page);
	
	public int findRows();
	
	public List<Module> findAllModule();
	
	/**
	 * 新增角色
	 */
	public void saveRole(Role role);
	
	/**
	 * 新增角色模块中间表，其中Map中存了2个值，
	 * 一个是角色ID，一个是模块ID。
	 * key			value
	 * role_id		5
	 * module_id	7
	 */
	public void saveRoleModule(Map<String, Object> rm);
	
	public Role findByName(String name);
	
	/**
	 * 根据ID查询角色，同时自动查询该角色
	 * 对应的全部模块。
	 */
	public Role findById(int id);
	
	public void update(Role role);
	
	public void deleteRoel(int roleId);
	
	public void deleteRoleModule(int roleId);
}
