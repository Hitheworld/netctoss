package net.xiangyawen.netctoss.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminInfo implements Serializable {
	
	private Integer admin_id;
	@NotEmpty(message="�û�������Ϊ��")
	private String admin_code;
	@Size(min=6,max=10,message="����λ��������{min}-{max}֮��")
	private String password;
	private String name;
	private String telphone;
	private String email;
	private Timestamp enrolldate;
	
	/**
	 * �������ԣ����ڷ�װ��ǰ����Ա��Ӧ��һ���ɫ��
	 * �����ɫ������MyBatis����ӳ���Զ���ѯ������
	 * ���Զ�װ�䵽��������С�
	 */
	private List<Role> roles;
	
	/**
	 * ��ɫID�����ڷ�װ����ʱ�������һ���ɫID��
	 */
	private List<Integer> roleIds;
	
	/**
	 * ��չ���ԣ����ڽ��������ύ��֤��Ϣ
	 */
	private String code;

	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Timestamp enrolldate) {
		this.enrolldate = enrolldate;
	}
	


}
