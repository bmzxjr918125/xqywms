package com.entity.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.entity.admin.Admin;
import com.entity.enumobj.LogHandleType;
import com.entity.enumobj.Status;
import com.entity.user.User;
import com.entity.user.Worker;
import com.exception.BizException;
/**
 * <p>ClassName: RepairOrderDetails</p>
 * <p>@Description: 维修工单详细</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-28下午3:00:37</p>
 */
@Entity
@Table(name = "repair_order_details")
public class RepairOrderDetails implements Serializable {
    private static final long serialVersionUID = 4404423623931821615L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 对应工单
	 */
    @ManyToOne
	@JoinColumn(name = "repairtOrderId",nullable = false)
    private RepairOrder repairOrder;
    /**
     * 起始状态
     */
    @Column(nullable = true)
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
    parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Status")})
    private Status startStatus;
    /**
     * 结束状态
     */
    @Column(nullable = false)
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
    parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Status")})
    private Status endStatus;
    /**
     * 创建时间
     */
    @Column(nullable = false , columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createDate;
	/**
	 * 操作描述
	 */
	private String description;
	
	/**
     * 操作原因
     * 问题描述
     * 工作人员拒绝接单原因/工单审核不通过原因/工单系统取消重新派发原因/工单系统取消重新派发原因/转派原因
     * 修复描述
     */
    private String reasonDesc;
	/**
	 * 操作人
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminId",nullable = true,insertable = false ,updatable = false)
	private Admin admin;
	/**
     * 操作人
     */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = true,insertable = false ,updatable = false)
	private User user;
	/**
     * 操作人
     */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workerId",nullable = true,insertable = false ,updatable = false)
	private Worker worker;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public RepairOrder getRepairOrder() {
        return repairOrder;
    }
    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }
    public Status getStartStatus() {
        return startStatus;
    }
    public void setStartStatus(Status startStatus) {
        this.startStatus = startStatus;
    }
    public Status getEndStatus() {
        return endStatus;
    }
    public void setEndStatus(Status endStatus) {
        this.endStatus = endStatus;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public String getReasonDesc() {
        return reasonDesc;
    }
    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }
    
    private RepairOrderDetails(RepairOrder repairOrder,
            Status endStatus, Date createDate) {
        super();
        this.repairOrder = repairOrder;
        this.endStatus = endStatus;
        this.createDate = createDate;
    }
    /**
     * <p>@Description: 创建维修工单详细信息</p>
     * <p>@param @param repairOrder
     * <p>@param @param startStatus
     * <p>@param @param endStatus
     * <p>@param @param obj
     * <p>@param @param reasonDesc
     * <p>@param @return</p>   
     * <p>@return RepairOrderDetails</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-28下午4:39:11</p>
     */
    public static RepairOrderDetails create(RepairOrder repairOrder,Status startStatus,Status endStatus,Object obj,String reasonDesc){
        if(repairOrder == null){
            throw new BizException("未找到当前工单信息");
        }
        if(!Status.isContains(endStatus.getIndex(),1,2)){
            throw new BizException("工单状态不在系统给定状态中");
        }
        
        RepairOrderDetails repairOrderDetails = new RepairOrderDetails(repairOrder,endStatus,new Date());
        repairOrderDetails.setStartStatus(startStatus);
        repairOrderDetails.setEndStatus(endStatus);
        repairOrderDetails.setDescription(endStatus.getRepairDesc(LogHandleType.SYSTEM));
        repairOrderDetails.setReasonDesc(reasonDesc);
        if(endStatus == Status.REPAIR_WAIT_ASK){
            
            if(obj instanceof User){
                repairOrderDetails.setUser((User)obj);
            }else{
                repairOrderDetails.setAdmin((Admin)obj);
            }
            
            //创建工单 及 详细
            
            
            
        }else if(endStatus == Status.REPAIR_ACCEPT || endStatus == Status.REPAIR_WAIT_CHECK
                || endStatus == Status.REPAIR_SEND_FAIL){
            repairOrderDetails.setWorker((Worker)obj);
        }else{
            repairOrderDetails.setAdmin((Admin)obj);
        }
        return repairOrderDetails;
    }
}
