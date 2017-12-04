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
     * 设备类型
     */
    private String deviceType;
    
    /**
     * 供应商 设备厂家
     */
    private String supplier;
    /**
     * 设备型号
     */
    private String modelNum;
    /**
     * 制冷量/输入功率
     */
    private String input;
    /**
     * 制热量/输出功率
     */
    private String output;
    /**
     * 制冷剂类型
     */
    private String cryogenType;
    /**
     * 制冷剂充注量
     */
    private String charge;
    /**
     * 设备描述
     */
     private String description;
    
     
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
    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    public String getCryogenType() {
        return cryogenType;
    }
    public void setCryogenType(String cryogenType) {
        this.cryogenType = cryogenType;
    }
    public String getCharge() {
        return charge;
    }
    public void setCharge(String charge) {
        this.charge = charge;
    }
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    
    public DeviceInfoVo() {
        super();
    }
    public DeviceInfoVo(String deviceName, String supplier, String modelNum,
            String input, String output, String cryogenType, String charge,
            String description,String deviceType) {
        super();
        this.deviceName = deviceName;
        this.supplier = supplier;
        this.modelNum = modelNum;
        this.input = input;
        this.output = output;
        this.cryogenType = cryogenType;
        this.charge = charge;
        this.description = description;
        this.deviceType = deviceType;
    }
    
}
