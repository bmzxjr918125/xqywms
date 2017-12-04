package com.entity.enumobj;

import com.entity.enumobj.base.IntegerValuedEnum;
import com.exception.BizException;

/**
 * <p>ClassName: EntryType</p>
 * <p>@Description: 词条类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-30上午10:52:31</p>
 */
public enum EntryType implements IntegerValuedEnum{
	
    CHECK("巡检词条",1),
    REPAIR("维修词条",2),
    DEPART("部门词条",3),
    JOB("职位词条",4),
    DEVICETYPE("设备类型词条",5);
    // 描述
    private String description;
    //状态标号
    private int index;
    //用于数据库映射int
    public int getCode() {
        return index;
    }
    // 构造方法
    private EntryType(String description, int index) {
        this.description = description;
        this.index = index;
    }
    // 获取状态描述
    public static String getDesc(int index) {
        for (EntryType c : EntryType.values()) {
            if (c.getIndex() == index) {
                return c.description;
            }
        }
        throw new BizException("当前词条类型不可用");
    }
    //对应枚举
    public static EntryType getByIndex(int index) {
        
        for (EntryType c : EntryType.values()) {
            
            if (c.getIndex() == index) {
                
                return c;
            }
        }
        throw new BizException("当前词条类型不可用");
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
