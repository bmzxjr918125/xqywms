package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: ImageType</p>
 * <p>@Description: 图片类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-22下午3:27:43</p>
 */
public enum ImageType implements IntegerValuedEnum{
	
    XQ_HEADERIMAGE("人员头像",1),   
    XQ_NEW_IMG("新闻速览图",2);   
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
		
		return index;
	}
    // 构造方法
    private ImageType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
    	
        for (ImageType c : ImageType.values()) {
        	
            if (c.getIndex() == index) {
            	
                return c.description;
            }
        }
        throw new BizException("当前图片类型不可用");
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
