package net.xiangyawen.netctoss.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
	
	private Integer role_id;
	private String name;
	
	/**
	 * 关联属性，用于封装当前角色对应的一组模块。
	 * 这组模块数据由MyBatis关联映射自动查询出来，
	 * 并自动装配进来。
	 */
	private List<Module> modules;
	
	/**
	 * 模块ID，用于封装保存时表单传入的一组模块ID。
	 */
	private List<Integer> moduleIds;
	
	public List<Integer> getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
