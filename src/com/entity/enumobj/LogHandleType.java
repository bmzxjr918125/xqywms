package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: WithdrawStatus</p>
 * <p>@Description: 日志表记录操作类型枚举类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-19下午6:10:03</p>
 */
public enum LogHandleType implements IntegerValuedEnum{
    SYSTEM("系统",1),USER("用户",2),WORKER("人员",3),ADMIN("管理员",4);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
		
		return index;
	}
    // 构造方法
    private LogHandleType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
    	
        for (LogHandleType c : LogHandleType.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c.description;
            }
        }
        throw new BizException("当前日志记录类型状态不可用");
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
