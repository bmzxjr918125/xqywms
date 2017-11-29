package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: RepairType</p>
 * <p>@Description: 设备报修类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-28下午2:25:01</p>
 */
public enum RepairType implements IntegerValuedEnum{
	
    REPAIR_BY_USER("客户报修",1),
    REPAIR_BY_SYS("系统报修",2);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
        return index;
    }
    // 构造方法
    private RepairType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
        for (RepairType c : RepairType.values()) {
            if (c.getIndex() == index) {
                return c.description;
            }
        }
        throw new BizException("当前报修类型不可用");
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
