package com.entity.enumobj;


/**
 * <p>ClassName: LogoDefaultEnum</p>
 * <p>@Description: 系统各种默认logo枚举类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-13下午2:20:57</p>
 */
public enum LogoDefaultEnum{
	
    WORKERLOGO("人员默认头像","xqywms_file/defaultMemberLogo.jpg");
    
	// 描述
	private String description;
	// 状态标号
	private String path;

	// 构造方法
	private LogoDefaultEnum(String description, String path) {
		this.description = description;
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
