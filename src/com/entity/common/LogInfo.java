package com.entity.common;

import java.io.Serializable;
import java.util.Date;

import com.entity.enumobj.LogHandleType;
import com.entity.enumobj.LogType;
/**
 * <p>ClassName: LogoInfo</p>
 * <p>@Description: 各种状态改变日志记录表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-22上午11:38:39</p>
 */
public class LogInfo implements Serializable {
	
    private static final long serialVersionUID = -830513392802386283L;
    
    private long id;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 *  描述
	 */
	private String description;
	/**
	 * 初始状态
	 */
	private int fromStatus;
	/**
	 * 改变后状态
	 */
	private int toStatus;
	/**
	 * 操作类型 系统 用户 平台
	 */
	private LogHandleType handleType;
   
	/**
	 * 系统 自动操作时为0
	 * 用户 操作会员用户memberId
	 * 平台 操作admin管理员id
	 */
	private long handleId;
	/**
	 * 操作对象类型
	 */
	private LogType logType;
	/**
	 * 对应对象Id
	 */
	private long objId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(int fromStatus) {
		this.fromStatus = fromStatus;
	}

	public int getToStatus() {
		return toStatus;
	}

	public void setToStatus(int toStatus) {
		this.toStatus = toStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public LogHandleType getHandleType() {
		return handleType;
	}

	public void setHandleType(LogHandleType handleType) {
		this.handleType = handleType;
	}
	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public long getHandleId() {
		return handleId;
	}

	public void setHandleId(long handleId) {
		this.handleId = handleId;
	}

	public long getObjId() {
		return objId;
	}

	public void setObjId(long objId) {
		this.objId = objId;
	}

	

	
	
}
