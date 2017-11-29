package com.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


import com.entity.common.Image;
import com.entity.enumobj.LogoDefaultEnum;
import com.entity.enumobj.Status;
import com.util.Md5Utils;

/**
 * <p>ClassName: Worker</p>
 * <p>@Description: 工作人员表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27上午10:54:15</p>
 */
@Entity
@Table(name = "worker")
public class Worker implements Serializable {
    private static final long serialVersionUID = 8227429802043307257L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    
    /**
     * 人员状态
     */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Status")})
    private Status status;
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
	 * 人员头像
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	private Image headerImg;
	/**
	 * 人员昵称
	 */
	private String nickName;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职位
	 */
	private String job;
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
   
    public Image getHeaderImg() {
        return headerImg;
    }
    public void setHeaderImg(Image headerImg) {
        this.headerImg = headerImg;
    }
  
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    /**
     * <p>@Description: 人员登录</p>
     * <p>@param </p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-27上午11:26:24</p>
     */
    public void login(){
        this.lastLoginDate = this.thisLoginDate;
        this.thisLoginDate = new Date();
    }
    
    /**
     * <p>@Description: 创建用户</p>
     * <p>@param @param phoneNumber2
     * <p>@param @param pwd2</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-24上午10:54:32</p>
     * @param myCode2 
     */
    public void create(String phoneNumber2, String pwd2, String department,String job) {
        this.createDate = new Date();
        this.lastLoginDate = new Date();
        this.nickName = phoneNumber2;
        this.pwd = Md5Utils.md5ForPwd(pwd2);
        this.phoneNumber =phoneNumber2;
        this.thisLoginDate = new Date();
        this.updateDate = new Date();
        // 设置默认头像
        this.headerImg = new Image(LogoDefaultEnum.WORKERLOGO.getPath(),
                new Date(), "默认头像", this);
        this.department = department;
        this.job = job;
        this.status = Status.WORKER_ABLE;
        
    }
}
