package com.entity.enumobj;

import java.util.List;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;
/**
 * <p>ClassName: Status</p>
 * <p>@Description: 各种信息 状态枚举</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午2:04:02</p>
 */
public enum Status implements IntegerValuedEnum{
	//1开头  报修工单 正常流程  状态    描述分为 后台/维修人员/客户/描述
	REPAIR_WAIT_ASK("派单/../待处理/用户或系统提交维修申请",11),REPAIR_SEND("待响应/待受理/待处理/系统派单操作",12),
	REPAIR_ACCEPT("维修中/维修中/维修中/工作人员接受工单",13),REPAIR_WAIT_CHECK("审核/待审核/维修中/工作人员完成订单提交后台审核",14),
	REPAIR_FINISH("已结束/已完成/已完成/后台审核通过订单完成",15),
	//2开头 报修工单 异常流程  状态    描述分为 后台/维修人员/客户/描述
	REPAIR_SEND_FAIL("重新派单/../待处理/工作人员拒绝接受派单，返回重新派单状态",21),REPAIR_CHECK_FAIL("维修中/维修中/维修中/订单完成审核不通过，返回维修状态",22),
	REPAIR_FAIL("重新派单/../待处理/系统直接取消工单，返回重新派单状态",23),
    
	//3开头 人员状态
	WORKER_ABLE("可用",31),WORKER_DISABLE("冻结",32),
	//4开头 用户状态
	USER_ABLE("可用",41),USER_DISABLE("冻结",42),
	//5开头 设备状态
	DEVICE_NORMAL("正常",51),DEVICE_REPAIR_ASK("报修中",52),DEVICE_REPAIRING("维修中",53);
	
	// 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
		
		return index;
	}
    // 构造方法
    private Status(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
    	
        for (Status c : Status.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c.description;
            }
        }
        throw new BizException("当前状态不可用");
    }
    
    public static Status getByIndex(int index) {
    	
        for (Status c : Status.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c;
            }
        }
        throw new BizException("当前状态不可用");
    }
    
    /**
     * <p>@Description: 判断状态是否包含在订单状态</p>
     * <p>@param @param status
     * <p>@param @param typeFlag 状态首数字
     * <p>@param @return</p>   
     * <p>@return boolean</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-4下午12:49:12</p>
     */
    public static boolean isContains(int orderStatus,int... typeFlag){
    	boolean flag=false;
    	 for (Status c : Status.values()) {
    	     for(int i=0;i<typeFlag.length;i++){
    	         if((c.getIndex()+"").substring(0,1).equals(typeFlag[i]+"")){
    	             if (c.getIndex() == orderStatus) {
    	                 flag=true;
    	                 break;
    	             }
                 }
             }
         }
    	 return flag;
    }
    /**
     * <p>@Description: 判断状态是否包含在订单状态</p>
     * <p>@param @param orderStatus
     * <p>@param @param typeFlag 状态首数字
     * <p>@param @return</p>   
     * <p>@return boolean</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-4下午12:49:12</p>
     */
    public static boolean isContains(List<Integer> orderStatusList,int... typeFlag){
    	boolean flag=false;
    	 for (Status c : Status.values()) {
    	     for(Integer status:orderStatusList){
    	         for(int i=0;i<typeFlag.length;i++){
                     if((c.getIndex()+"").substring(0,1).equals(typeFlag[i]+"")){
                         if (c.getIndex() == status) {
                             flag=true;
                             break;
                         }
                     }
                 }
                 
             }
            
         }
    	return flag;
    }
    /**
     * <p>@Description: 获取工单对应状态 对应描述</p>
     * <p>@param @param status
     * <p>@param @param type
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-28下午4:33:45</p>
     */
    public String getRepairDesc(LogHandleType type){
            if(type == LogHandleType.ADMIN){
                return  this.getDescription().split("/")[0];
            }else if(type == LogHandleType.WORKER){
                return  this.getDescription().split("/")[1];
            }else if(type == LogHandleType.USER){
                return  this.getDescription().split("/")[2];
            }else{
                return  this.getDescription().split("/")[3];
            }
    }
    
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
