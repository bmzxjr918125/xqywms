package com.entity.device;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.entity.vo.DeviceInfoVo;

/**
 * <p>ClassName: Device</p>
 * <p>@Description: 设备表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:50:49</p>
 */
@Entity
@Table(name = "device_card")
public class DeviceCard implements Serializable {
    private static final long serialVersionUID = -1385918312443854493L;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DeviceInfoVo getDeviceInfo() {
        return deviceInfo;
    }
    public void setDeviceInfo(DeviceInfoVo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
    
}
