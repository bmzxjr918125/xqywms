package com.entity.project;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * <p>ClassName: ProjectDeviceCheckDetails</p>
 * <p>@Description: 项目设备巡检记录</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-29下午5:29:49</p>
 */
@Entity
@Table(name = "project_device_check_details")
public class ProjectDeviceCheckDetails implements Serializable {
    private static final long serialVersionUID = 8265983913614324459L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 对应项目设备
	 */
	@ManyToOne
	@JoinColumn(name="projectDeviceId",nullable=false)
	private ProjectDevice projectDevice;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 巡检人员
	 */
	@ManyToOne
    @JoinColumn(name="projectWorkerId",nullable=false)
	private ProjectWorker projectWorker;
	/**
     * 巡检结果 jsonArray字符串
      [{"id":1,"name":"是否运行正常","value":false,"desc":"123"},
       {"id":2,"name":"是否制冷正常","value":true,"desc":"346"},
       {"id":3,"name":"是否运行正常","value":false,"desc":"789"}]
     * LongText
     */
    @Column(length = 16777216)
	private String checkJa;
    /**
     * 巡检备注
     */
    private String description;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ProjectDevice getProjectDevice() {
        return projectDevice;
    }
    public void setProjectDevice(ProjectDevice projectDevice) {
        this.projectDevice = projectDevice;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public ProjectWorker getProjectWorker() {
        return projectWorker;
    }
    public void setProjectWorker(ProjectWorker projectWorker) {
        this.projectWorker = projectWorker;
    }
    public String getCheckJa() {
        return checkJa;
    }
    public void setCheckJa(String checkJa) {
        this.checkJa = checkJa;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
