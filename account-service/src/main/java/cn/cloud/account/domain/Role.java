package cn.cloud.account.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="role_name")
	private String roleName;

	@Column(name="role_sign")
	private String roleSign;

	@Column(name="remark")
	private String remark;

	@Column(name="user_id_create")
	private Long userIdCreate;

	// 创建时间
	@Column(name="time_create")
	private Date timeCreate;

	// 修改时间
	@Column(name="time_update")
	private Date timeUpdate;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleSign() {
		return roleSign;
	}

	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Long userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
