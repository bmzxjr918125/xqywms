package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: Boolean</p>
 * <p>@Description: 封装 boolean枚举</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午12:07:17</p>
 */
public enum Boolean implements IntegerValuedEnum{
	
    YES("是",1,true),
    NO("否",0,false);   
    // 描述
    private String description;
    //状态标号
    private int index;
    private boolean value;
    //用于数据库映射int
    public int getCode() {
		
		return index;
	}
    // 获取状态描述
    public static String getDesc(int index) {
        for (Boolean c : Boolean.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c.description;
            }
        }
        throw new BizException("当前判断状态不可用");
    }
	private Boolean(String description, int index, boolean value) {
        this.description = description;
        this.index = index;
        this.value = value;
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

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
