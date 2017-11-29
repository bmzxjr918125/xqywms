package com.entity.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entity.common.EnergyType;
/**
 * <p>ClassName: ProjectEnergy</p>
 * <p>@Description: 项目对应能源系统</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-29上午11:17:47</p>
 */
@Entity
@Table(name = "project_energy")
public class ProjectEnergy implements Serializable {
    private static final long serialVersionUID = -742544486387378162L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 对应项目
	 */
	@ManyToOne
	@JoinColumn(name="projectId",nullable=false)
	private Project project;
	/**
	 * 能源系统名称
	 */
	private String energyName;
	/**
	 * 地址描述
	 */
	private String address;
	/**
	 * 涉及设备
	 */
	private String deviceNames;
	/**
	 * 能源监测项
	 */
	@ManyToMany(fetch = FetchType.LAZY,targetEntity = EnergyType.class)
    @JoinTable(name = "project_and_energy",
    joinColumns=@JoinColumn(name = "projectEnergyId", referencedColumnName = "id", nullable = false, updatable = false),
    inverseJoinColumns=@JoinColumn(name = "energyTypeId", referencedColumnName = "id", nullable = false, updatable = false))
	private List<EnergyType> energyTypes = new ArrayList<EnergyType>();
	
	/**
	 * 创建日期
	 */
    @Column(nullable = false , columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public String getEnergyName() {
        return energyName;
    }
    public void setEnergyName(String energyName) {
        this.energyName = energyName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDeviceNames() {
        return deviceNames;
    }
    public void setDeviceNames(String deviceNames) {
        this.deviceNames = deviceNames;
    }
    public List<EnergyType> getEnergyTypes() {
        return energyTypes;
    }
    public void setEnergyTypes(List<EnergyType> energyTypes) {
        this.energyTypes = energyTypes;
    }
   
}
