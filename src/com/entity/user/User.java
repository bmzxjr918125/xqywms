package com.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.entity.project.ProjectUser;
import com.entity.vo.AddressVo;
import com.util.Md5Utils;


/**
 * <p>ClassName: User</p>
 * <p>@Description: 用户表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-15上午10:48:15</p>
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 8297203550528190415L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 用户手机号码
	 */
	@Column(unique=true,nullable=false,length=11)
	private String phoneNumber;
	/**
	 * 登录密码
	 */
	private String pwd;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 职业
	 */
	private String profession;
	
	/**
	 * 本次登录时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date thisLoginDate;
	/**
	 * 上次登录时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date lastLoginDate;
    /**
     *创建日期    	
     */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createDate;
	/**
	 * 个人信息 更新时间 登录时不更改此时间 
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updateDate;
	/**
	 * 对应项目
	 */
    @OneToOne
    @JoinColumn(nullable = false , name = "projectUserId")
	private ProjectUser projectUser;
    /**
     * 提交维修次数
     */
    @Column(nullable = false , columnDefinition = "INT default 0")
    private int commitCheckNum;
	/**
	 * 用户地址
	 */
	@Embedded
	/*@AttributeOverrides({
	    @AttributeOverride(name = "province", column = @Column( name = "province")),
	    @AttributeOverride(name = "city", column = @Column( name = "city")),
	    @AttributeOverride(name = "area", column = @Column( name = "area")),
	    @AttributeOverride(name = "address", column = @Column( name = "address"))
	})*/
	private AddressVo address;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
  
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
   
    public Date getThisLoginDate() {
        return thisLoginDate;
    }
    public void setThisLoginDate(Date thisLoginDate) {
        this.thisLoginDate = thisLoginDate;
    }
    public Date getLastLoginDate() {
        return lastLoginDate;
    }
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
  
    public AddressVo getAddress() {
        return address;
    }
    public void setAddress(AddressVo address) {
        this.address = address;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public ProjectUser getProjectUser() {
        return projectUser;
    }
    public void setProjectUser(ProjectUser projectUser) {
        this.projectUser = projectUser;
    }
    public int getCommitCheckNum() {
        return commitCheckNum;
    }
    public void setCommitCheckNum(int commitCheckNum) {
        this.commitCheckNum = commitCheckNum;
    }
    
    /**
     * <p>@Description: 用户登录</p>
     * <p>@param </p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-28下午2:21:39</p>
     */
    public void login(){
        this.lastLoginDate = this.thisLoginDate;
        this.thisLoginDate = new Date();
    }
    
    /**
     * <p>@Description: 创建用户</p>
     * <p>@param @param phoneNumber2
     * <p>@param @param pwd2
     * <p>@param @param myCode2
     * <p>@param @param projectUser</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-28下午2:21:31</p>
     */
    public void create(String phoneNumber2, String pwd2,ProjectUser projectUser) {
        
        this.createDate = new Date();
        this.lastLoginDate = new Date();
        this.nickName = phoneNumber2;
        this.pwd = Md5Utils.md5ForPwd(pwd2);
        this.phoneNumber =phoneNumber2;
        this.thisLoginDate = new Date();
        this.updateDate = new Date();
        this.projectUser = projectUser;
    }
}
