package cn.cloud.admin.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RoleMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="role_id")
	private Long  roleId;

	@Column(name="menu_id")
	private Long menuId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu{" +
				"id=" + id +
				", roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}
