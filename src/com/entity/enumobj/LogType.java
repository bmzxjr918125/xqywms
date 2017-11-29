package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: LogType</p>
 * <p>@Description: 日志记录表 操作对象类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-30下午5:03:36</p>
 */
public enum LogType implements IntegerValuedEnum{
    ADMIN("admin日志",1),MEMBER("member日志",2),ORDER("order日志",3),PRODUCT("product日志",4)
    ,RECEIPTADDRESS("receiptAddress日志",5),SHOP("shop日志",6),EMPLOYEE("employee日志",7),
    ECSHOP("ecShop日志",8),OPERATOR("operator日志",9),TAGGINGSHOP("taggingShop日志",10);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
		
		return index;
	}
    // 构造方法
    private LogType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
    	
        for (LogType c : LogType.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c.description;
            }
        }
        throw new BizException("当前店铺状态不可用");
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
