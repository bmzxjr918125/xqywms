package com.entity.vo;

import java.io.Serializable;
/**
 * <p>ClassName: DeviceVo</p>
 * <p>@Description: 设备vo</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-28下午1:58:24</p>
 */
public class DeviceInfoVo implements Serializable {
    private static final long serialVersionUID = 720406412997766877L;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 供应商
     */
    private String supplier;
    /**
     * 设备描述
     */
     private String description;
     /**
      * 设备型号
      */
     private String modelNum;
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getModelNum() {
        return modelNum;
    }
    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }
    
    private DeviceInfoVo() {
        super();
    }
    private DeviceInfoVo(String deviceName, String supplier, String description,
            String modelNum) {
        super();
        this.deviceName = deviceName;
        this.supplier = supplier;
        this.description = description;
        this.modelNum = modelNum;
    }
     
}
