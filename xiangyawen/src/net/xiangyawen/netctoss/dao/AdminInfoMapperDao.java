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
	 * 管理员员登陆
	 */
	public AdminInfo findByAdminCode(String name);
	/**
	 * 根据账号查询管理员
	 */
	//public AdminInfo findByCode(String adminCode);
	
	/**
	 * 分页查询管理员信息，同时自动查询出
	 * 每一个管理员对应的角色，并自动装配
	 * 到管理员对象中。
	 */
	public List<AdminInfo> findByPage(AdminPage page);
	
	public int findRows();
	
	/**
	 * 查询出所有的角色
	 */
	public List<Role> findAllRole();
	
	public AdminInfo findByAdminId(int id);
	
	public List<Role> findRoleByAdminId(int id);
	
	/**
	 * 添加管理员
	 */
	public void saveAdmin(AdminInfo admin);
	
	/**
	 * 新增管理员角色中间表，其中Map中存了2个值，
	 * 一个是管理员ID，一个是角色ID。
	 */
	public void saveAdminRole(Map<String, Object> ar);
	
	
	/**
	 * 修改管理员
	 */
	public void update(AdminInfo admin);
	
	/**
	 * 重置密码，其中Map中包含2个值，一个是要重置
	 * 的管理员ID集合，另一个是默认密码。
	 * key				value
	 * ids				List<Integer>
	 * defaultPassword	String
	 */
	public void updatePassword(Map<String, Object> param);

	public void deleteAdminRole(int adminId);
	
	/**
	 * 删除管理员信息
	 */
	public void deleteAdmin(int adminId);
	
	/**
	 * 查询某管理员有权限的模块
	 */
	public List<Module> findModuleByAdminId(int adminId);
	
}
