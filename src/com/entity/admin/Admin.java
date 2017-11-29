package com.entity.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * <p>ClassName: Admin</p>
 * <p>@Description: 后台管理员账户信息表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-3-15上午9:54:26</p>
 */
@Entity
@Table(name = "admin")
public class Admin implements Serializable {
	private static final long serialVersionUID = 7582736239180382279L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 管理员账户
	 */
	@JoinColumn(unique=true)
	private String username;
	/**
	 * 管理员密码
	 */
	private String password;
	/**
	 * 帐号创建时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createDate;
	/**
	 * 帐号最近修改时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updateDate;
	/**
	 * 上次登陆时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date lastLoginDate;
	/**
	 * 本次登陆时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date thisLoginDate;
	/**
	 * 帐号类型0后台管理员超级账号1店员账号
	 */
	private byte type;
	/**
	 * 店员名称
	 */
	private String role_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getThisLoginDate() {
		return thisLoginDate;
	}

	public void setThisLoginDate(Date thisLoginDate) {
		this.thisLoginDate = thisLoginDate;
	}
}
