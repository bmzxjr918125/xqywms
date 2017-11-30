package com.entity.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
/**
 * <p>ClassName: CountVo</p>
 * <p>@Description: 各种次数统计</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-30下午5:46:05</p>
 */
public class CountVo implements Serializable {
    private static final long serialVersionUID = 8803891470000278093L;
    /**
     * 所在项目数
     */
    @Column(nullable = false , columnDefinition = "INT default 0")
    private int projectNum;
    /**
     * 维修次数
     */
    @Column(nullable = false , columnDefinition = "INT default 0")
    private int repairNum;
    /**
     * 巡检次数
     */
    @Column(nullable = false , columnDefinition = "INT default 0")
    private int checkNum;
    /**
     * 最近巡检日期
     */
    private Date lastCheckDate;
    /**
     * 最近维修日期
     */
    @Column(nullable = true)
    private Date lastRepairDate;
    
    
    public int getProjectNum() {
        return projectNum;
    }
    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }
    public int getRepairNum() {
        return repairNum;
    }
    public void setRepairNum(int repairNum) {
        this.repairNum = repairNum;
    }
    public int getCheckNum() {
        return checkNum;
    }
    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }
    public Date getLastCheckDate() {
        return lastCheckDate;
    }
    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }
    public Date getLastRepairDate() {
        return lastRepairDate;
    }
    public void setLastRepairDate(Date lastRepairDate) {
        this.lastRepairDate = lastRepairDate;
    }
}
