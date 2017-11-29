package com.entity.device;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.entity.admin.Admin;
import com.entity.enumobj.Status;
import com.entity.project.ProjectDevice;
import com.entity.vo.DeviceInfoVo;

/**
 * <p>ClassName: Device</p>
 * <p>@Description: 设备表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:50:49</p>
 */
@Entity
@Table(name = "device")
public class Device implements Serializable {
    private static final long serialVersionUID = 5442531097535632567L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
   
    
    /**
     * 设备通用信息
     */
    @Embedded
    /*@AttributeOverrides({
        @AttributeOverride(name = "province", column = @Column( name = "province")),
        @AttributeOverride(name = "city", column = @Column( name = "city")),
        @AttributeOverride(name = "area", column = @Column( name = "area")),
        @AttributeOverride(name = "address", column = @Column( name = "address"))
    })*/
    private DeviceInfoVo deviceInfo;
    
    /**
     * 设备状态
     */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Status")})
    private Status status;
    /**
     * 设备编号
     */
    @Column(unique = true, nullable = false, length = 30)
    private String deviceNo;
    
    /**
     * 购买日期
     */
    private Date buyDate;
    /**
     * 对应项目设备 
     */
    @OneToOne
    @JoinColumn(nullable = true,name = "projectDeviceId")
    private ProjectDevice projectDevice;    
    /**
     * 添加管理员
     */
    private Admin admin;
    /**
     * 创建日期
     */
    @Column(nullable = false , columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;
    /**
     * 最近一次修改时间
     */
    @Column(nullable = false , columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date updateDate;
    
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
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public ProjectDevice getProjectDevice() {
        return projectDevice;
    }

    public void setProjectDevice(ProjectDevice projectDevice) {
        this.projectDevice = projectDevice;
    }

    public DeviceInfoVo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoVo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
    
}
