package com.entity.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.entity.enumobj.ImageType;
import com.entity.order.RepairOrderDetails;
import com.entity.user.Worker;

/**
 * <p>ClassName: Image</p>
 * <p>@Description: 系统图片资源路径表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16上午11:22:39</p>
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {
    
    private static final long serialVersionUID = 4607660208724045272L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	/**
     *图片类型
	 */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.ImageType")})
	private ImageType imageType;
	
	/**
	 * 图片相对地址
	 */
	private String imageUrl;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 图片描述
	 */
	private String description;
	/**
	 * 所属人员
	 */
	@OneToOne
	@JoinColumn(name = "workerId")
	private Worker worker;
	/**
	 * 所属新闻
	 */
	@ManyToOne
    @JoinColumn(name = "newId")
	private New news;
	/**
	 * 所属维修工单详细
	 */
	@ManyToOne
	@JoinColumn(name = "repairOrderDetailsId")
	private RepairOrderDetails repairOrderDetails;
	
	public Image(){
		super();
	}
	/**
	 * <p>Description:新建头像 </p>
	 * <p>@param imageUrl
	 * <p>@param createDate
	 * <p>@param description
	 * <p>@param user</p>
	 */
	public Image(String imageUrl, Date createDate, String description,Worker worker) {
		super();
		this.imageType = ImageType.XQ_HEADERIMAGE;
		this.imageUrl = imageUrl;
		this.createDate = createDate;
		this.description = description;
		this.worker = worker;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ImageType getImageType() {
		return imageType;
	}
	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}
    public New getNews() {
        return news;
    }
    public void setNews(New news) {
        this.news = news;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public RepairOrderDetails getRepairOrderDetails() {
        return repairOrderDetails;
    }
    public void setRepairOrderDetails(RepairOrderDetails repairOrderDetails) {
        this.repairOrderDetails = repairOrderDetails;
    }
    
}
