package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: CheckType</p>
 * <p>@Description: 巡检周期类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:34:48</p>
 */
public enum CheckType implements IntegerValuedEnum{
	
    NONE("无",1),
    DAY("日",2),
    WEEK("周",3),
    MONTH("月",4),
    QUARTER("季度",5);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
        return index;
    }
    // 构造方法
    private CheckType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
        for (CheckType c : CheckType.values()) {
            if (c.getIndex() == index) {
                return c.description;
            }
        }
        throw new BizException("当前周期类型不可用");
    }
    public static CheckType getByIndex(int index) {
        
        for (CheckType c : CheckType.values()) {
            
            if (c.getIndex() == index) {
                
                return c;
            }
        }
        throw new BizException("当前巡检周期类型不可用");
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
