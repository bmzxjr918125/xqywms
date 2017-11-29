package com.entity.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>ClassName: New</p>
 * <p>@Description: 新闻</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-23下午2:28:28</p>
 */
@Entity
@Table(name = "new")
public class New implements Serializable {
    private static final long serialVersionUID = 695089334239351099L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	/**
	 * 速览图
	 */
    private String imagePath;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 简述
	 */
	private String description;
	/**
	 * 类容
	 */
	private String content;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 推送类型 0 全部 1客户 2人员
	 */
	private byte type;
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public byte getType() {
        return type;
    }
    public void setType(byte type) {
        this.type = type;
    }
    
}
