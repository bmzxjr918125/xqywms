package com.entity.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.entity.project.ProjectEnergy;

/**
 * <p>ClassName: EnergyType</p>
 * <p>@Description:能源系统监控类型</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-29上午11:24:21</p>
 */
@Entity
@Table(name = "energy_type")
public class EnergyType implements Serializable {
    private static final long serialVersionUID = -4914318928962566374L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	/**
	 * 名称
	 */
    private String name;
    /**
     * 
     */
    //控制权交给ProjectEnergy
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = ProjectEnergy.class,mappedBy = "energyTypes")
    private List<ProjectEnergy> projectEnergies = new ArrayList<ProjectEnergy>();
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ProjectEnergy> getProjectEnergies() {
        return projectEnergies;
    }
    public void setProjectEnergies(List<ProjectEnergy> projectEnergies) {
        this.projectEnergies = projectEnergies;
    }
    
    
}
