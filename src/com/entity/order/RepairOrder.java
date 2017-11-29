package com.entity.order;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.entity.admin.Admin;
import com.entity.enumobj.RepairType;
import com.entity.enumobj.Status;
import com.entity.project.ProjectDevice;
import com.entity.user.User;
import com.entity.user.Worker;
import com.exception.BizException;
/**
 * <p>ClassName: RepairOrder</p>
 * <p>@Description: 维修工单表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-28下午2:12:46</p>
 */
@Entity
@Table(name = "repair_order")
public class RepairOrder implements Serializable {
    private static final long serialVersionUID = -4634189614876176854L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 工单号
	 */
	@Column(unique=true,nullable=false,length=30)
	private String orderNo;
	/**
	 * 订单状态
	 */
	@Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Status")})
	private Status status;
	/**
	 * 工单金额
	 */
	@Column(nullable=false,columnDefinition="double(8,1) default  '0.0'")
	private double amount;
	/**
	 * 保修设备
	 */
	@ManyToOne
	@JoinColumn(name="projectDeviceId",nullable = false)
	private ProjectDevice projectDevice;
	/**
	 * 保修类型 
	 */
	@Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.RepairType")})
	private RepairType repairType;
	/**
	 * 对应报修用户 系统报修时为NULL
	 */
	@ManyToOne
	@JoinColumn(name="userId",nullable=true)
	private User user;
	/**
	 * 创建时间
	 */
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createDate;
	@Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updateDate;
	/**
	 * 报修描述
	 */
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public ProjectDevice getProjectDevice() {
        return projectDevice;
    }
    public void setProjectDevice(ProjectDevice projectDevice) {
        this.projectDevice = projectDevice;
    }
    public RepairType getRepairType() {
        return repairType;
    }
    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
	
	/**
	 * <p>Description:创建工单构造方法 </p>
	 * <p>@param projectDevice
	 * <p>@param repairType
	 * <p>@param user</p>
	 */
	private RepairOrder(ProjectDevice projectDevice, RepairType repairType,User user,String description) {
        super();
        
        this.projectDevice = projectDevice;
        this.repairType = repairType;
        this.user = user;
        this.description = description;
       
        String no="";
        String phoneNumber = "";
        if(repairType == RepairType.REPAIR_BY_USER){
            phoneNumber = user.getPhoneNumber();
        }else{
            phoneNumber = "123456789";
        }
        if(phoneNumber != null && phoneNumber.length() > 6){
            Random random = new Random();
            no=random.nextInt(Integer.parseInt(phoneNumber.substring(phoneNumber.length()-7,phoneNumber.length()-1)))+"";
        }
        String orderNumber = "100"+no+System.currentTimeMillis();
        this.orderNo = orderNumber;
        
        this.status = Status.REPAIR_WAIT_ASK;
        this.amount = 0.0;
        this.createDate = new Date();
        this.updateDate = new Date();
    }
	
	/**
	 * <p>@Description: 创建维修工单</p>
	 * <p>@param @param projectDevice
	 * <p>@param @param repairType
	 * <p>@param @param user
	 * <p>@param @param description
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午4:59:44</p>
	 */
	public RepairOrderDetails create(ProjectDevice projectDevice, RepairType repairType,User user,String description){
	    RepairOrder repairOrder = new RepairOrder(projectDevice, repairType, user,description);
	    return RepairOrderDetails.create(repairOrder, null, repairOrder.getStatus(), user, description);
	}
	/**
	 * <p>@Description: 工单派发</p>
	 * <p>@param @param admin
	 * <p>@param @param reasonDesc 派发原因
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:10:22</p>
	 */
	public RepairOrderDetails send(Admin admin,String reasonDesc){
       if( this.status != Status.REPAIR_WAIT_ASK &&
               this.status != Status.REPAIR_SEND_FAIL){
           throw new BizException("工单当前操作状态不正确");
       }
	   Status startStatus = this.status; 
       this.updateDate = new Date();
       this.status = Status.REPAIR_SEND;
       return RepairOrderDetails.create(this, startStatus, this.getStatus(), admin, reasonDesc);
    }
	/**
	 * <p>@Description: 人员受理工单</p>
	 * <p>@param @param worker
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:14:01</p>
	 */
	public RepairOrderDetails accept(Worker worker){
	    if( this.status != Status.REPAIR_SEND){
	           throw new BizException("工单当前操作状态不正确");
	       }
	    Status startStatus = this.status; 
	    this.updateDate = new Date();
	    this.status = Status.REPAIR_ACCEPT;
	    return RepairOrderDetails.create(this, startStatus, this.getStatus(), worker, "");
	}
	/**
	 * <p>@Description: 工作人员取消受理工单</p>
	 * <p>@param @param worker
	 * <p>@param @param reasonDesc
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:16:49</p>
	 */
	public RepairOrderDetails sendFailForWorker(Worker worker,String reasonDesc){
	    if( this.status != Status.REPAIR_SEND){
            throw new BizException("工单当前操作状态不正确");
        }
     Status startStatus = this.status; 
     this.updateDate = new Date();
     this.status = Status.REPAIR_SEND_FAIL;
     return RepairOrderDetails.create(this, startStatus, this.getStatus(), worker,reasonDesc);
	}
	/**
	 * <p>@Description: 系统取消受理工单</p>
	 * <p>@param @param worker
	 * <p>@param @param reasonDesc
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:17:04</p>
	 */
	public RepairOrderDetails sendFailForSys(Admin admin,String reasonDesc){
        if( this.status != Status.REPAIR_SEND || 
                this.status != Status.REPAIR_ACCEPT ||
                this.status != Status.REPAIR_CHECK_FAIL){
            throw new BizException("工单当前操作状态不正确");
        }
     Status startStatus = this.status; 
     this.updateDate = new Date();
     this.status = Status.REPAIR_FAIL;
     return RepairOrderDetails.create(this, startStatus, this.getStatus(), admin,reasonDesc);
    }
	/**
	 * <p>@Description: 工作人员完成维修提交审核</p>
	 * <p>@param @param worker
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:21:41</p>
	 */
	public RepairOrderDetails check(Worker worker,String reasonDesc,double amount){
        if( this.status != Status.REPAIR_ACCEPT){
            throw new BizException("工单当前操作状态不正确");
        }
     Status startStatus = this.status; 
     this.updateDate = new Date();
     this.amount = amount;
     this.status = Status.REPAIR_WAIT_CHECK;
     return RepairOrderDetails.create(this, startStatus, this.getStatus(), worker,reasonDesc);
    }
	/**
	 * <p>@Description: 审核工单完成</p>
	 * <p>@param @param worker
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:25:07</p>
	 */
	public RepairOrderDetails finish(Admin admin){
        if( this.status != Status.REPAIR_WAIT_CHECK){
            throw new BizException("工单当前操作状态不正确");
        }
     Status startStatus = this.status; 
     this.updateDate = new Date();
     
     this.status = Status.REPAIR_FINISH;
     return RepairOrderDetails.create(this, startStatus, this.getStatus(), admin,"");
    }
	/**
	 * <p>@Description:工单完成审核不通过</p>
	 * <p>@param @param admin
	 * <p>@param @param reasonDesc
	 * <p>@param @return</p>   
	 * <p>@return RepairOrderDetails</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-11-28下午5:27:04</p>
	 */
	public RepairOrderDetails checkFail(Admin admin,String reasonDesc){
        if( this.status != Status.REPAIR_WAIT_CHECK){
            throw new BizException("工单当前操作状态不正确");
        }
     Status startStatus = this.status; 
     this.updateDate = new Date();
     this.status = Status.REPAIR_CHECK_FAIL;
     return RepairOrderDetails.create(this, startStatus, this.getStatus(), admin,reasonDesc);
    }
    
	
}
