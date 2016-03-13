package net.xiangyawen.netctoss.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
	
	private Integer role_id;
	private String name;
	
	/**
	 * �������ԣ����ڷ�װ��ǰ��ɫ��Ӧ��һ��ģ�顣
	 * ����ģ��������MyBatis����ӳ���Զ���ѯ������
	 * ���Զ�װ�������
	 */
	private List<Module> modules;
	
	/**
	 * ģ��ID�����ڷ�װ����ʱ�������һ��ģ��ID��
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
