package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: ProjectType</p>
 * <p>@Description:项目类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:23:48</p>
 */
public enum ProjectType implements IntegerValuedEnum{
	
    PERSONAL("个人",1),
    PROJECT("工程",2);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
        return index;
    }
    // 构造方法
    private ProjectType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
        for (ProjectType c : ProjectType.values()) {
            if (c.getIndex() == index) {
                return c.description;
            }
        }
        throw new BizException("当前项目类型不可用");
    }
  public static ProjectType getByIndex(int index) {
        
        for (ProjectType c : ProjectType.values()) {
            
            if (c.getIndex() == index) {
                
                return c;
            }
        }
        throw new BizException("当前项目类型不可用");
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
