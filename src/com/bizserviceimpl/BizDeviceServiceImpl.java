package com.bizserviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizDeviceService;
import com.entity.admin.Admin;
import com.entity.device.Device;
import com.entity.device.DeviceCard;
import com.entity.vo.DeviceInfoVo;
import com.exception.BizException;
import com.service.DeviceCardService;
import com.service.DeviceService;


@Service("bizDeviceService")
public class BizDeviceServiceImpl implements BizDeviceService{
   private DeviceCardService deviceCardService;
   private DeviceService deviceService;
    
   
   public void getDataTablePage(DataTables dtJson) {
       deviceService.getDataTablePage(dtJson);
   }

   public void getCardDataTablePage(DataTables dtJson) {
       deviceCardService.getDataTablePage(dtJson);
   }

   
   /**
    * 添加设备名片信息
    */
   public void addSaveDeviceCar(String deviceName, String deviceType,
           String supplier, String modelNum, String input, String output,
           String cryogenType, String charge, String description) {
       if(deviceName == null || "".equals(deviceName.trim())){
           throw new BizException("请输入设备名称");
       }
       if(deviceType == null || "".equals(deviceType.trim())){
           throw new BizException("请选择设备类型");
       }
       if(supplier == null || "".equals(supplier.trim())){
           throw new BizException("请输入设备生产厂家");
       }
       if(modelNum == null || "".equals(modelNum.trim())){
           throw new BizException("请输入设备型号");
       }
       
       DeviceInfoVo deviceInfoVo = new DeviceInfoVo(deviceName, supplier, modelNum, input, output, cryogenType, charge, description, deviceType);
       
       DeviceCard deviceCard = new DeviceCard();
       deviceCard.create(deviceInfoVo);
       
       deviceCardService.addSave(deviceCard);
       
   }
      /**
       * 编辑设备名片信息
       */
   public void updateSaveDeviceCar(int deviceCardId, String deviceName,
           String deviceType, String supplier, String modelNum, String input,
           String output, String cryogenType, String charge, String description) {
       DeviceCard deviceCard = deviceCardService.getById(deviceCardId);
       
       if(deviceCard == null){
           throw new BizException("未找到对应设备名片信息");
       }
       
       if(deviceName == null || "".equals(deviceName.trim())){
           throw new BizException("请输入设备名称");
       }
       if(deviceType == null || "".equals(deviceType.trim())){
           throw new BizException("请选择设备类型");
       }
       if(supplier == null || "".equals(supplier.trim())){
           throw new BizException("请输入设备生产厂家");
       }
       if(modelNum == null || "".equals(modelNum.trim())){
           throw new BizException("请输入设备型号");
       }
       
       DeviceInfoVo deviceInfoVo = new DeviceInfoVo(deviceName, supplier, modelNum, input, output, cryogenType, charge, description, deviceType);
       
       deviceCard.setDeviceInfo(deviceInfoVo);
       
       deviceCardService.updateSave(deviceCard);
       
   }
   public void deletesCard(String ids) {
       if(ids == null || "".equals(ids.trim())){
           throw new BizException("请选择删除操作项。");
       }
       String id[] = ids.trim().split(",");
       
       for(int i = 0;i<id.length;i++){
          DeviceCard deviceCard = deviceCardService.getById(Integer.parseInt(id[i]));
          deviceCardService.delete(deviceCard);
       }
       
       
   }
   
   

   public JSONArray getCardNameAndIdList() {
       List<String> list = deviceCardService.getCardNameAndIdList();
       return JSONArray.fromObject(list);
   }
   

   public DeviceCard getById(int cardId) {
       DeviceCard deviceCard = deviceCardService.getById(cardId);
       if(deviceCard == null){
           throw new BizException("未找到对应设备名片信息");
       }
       return deviceCard;
   }

    /**
     * 添加设备信息
     */
   public void addSaveDevice(int deviceCardId, String deviceNo,
           String productionDate, String buyDate,String otherDesc,Admin admin) {
       DeviceCard deviceCard = deviceCardService.getById(deviceCardId);
       if(deviceCard == null){
           throw new BizException("未找到对应设备名片信息");
       }
       if(deviceNo == null || "".equals(deviceNo.trim())){
           throw new BizException("请输入设备唯一编号");
       }
       if(productionDate == null || "".equals(productionDate.trim())){
           throw new BizException("请输入设备出厂日期");
       }
       if(buyDate == null || "".equals(buyDate.trim())){
           throw new BizException("请输入设备购买日期");
       }
       deviceNo = deviceNo.trim();
       //验证编码唯一
       Device dev = deviceService.getByDeviceNo(deviceNo);
       
       if(dev !=null){
           throw new BizException("设备编码'"+deviceNo.trim()+"'已存在请更换。");
       }
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
       
       DeviceInfoVo deviceInfo = deviceCard.getDeviceInfo();
       
       Device device = new Device();
       Date production = null;
       Date buy = null ;
    try {
        production = sdf.parse(productionDate);
        buy = sdf.parse(buyDate);
    } catch (ParseException e) {
        throw new BizException("时间转化异常");
    }
      
        device.create(deviceInfo, deviceNo, production,buy,admin,otherDesc);
       
       deviceService.addSave(device);
   }
   
    /**
     * flag 1修改设备信息  2 复制添加设备信息
     */
   public void updateSaveDevice(int deviceCardId, String deviceNo,
           String productionDate, String buyDate, String otherDesc,
           Admin admin, int flag, int deviceId) {
       Device device = deviceService.getById(deviceId);
       if(device == null){
           throw new BizException("未找到对应设备信息");
       }
       DeviceCard deviceCard = null;
       if(deviceCardId != 0){
            deviceCard = deviceCardService.getById(deviceCardId);
           if(deviceCard == null){
               throw new BizException("未找到对应设备名片信息");
           }
       }
       
       if(flag !=1 && (deviceNo == null || "".equals(deviceNo.trim()))){
           throw new BizException("请输入设备唯一编号");
       }
       if(productionDate == null || "".equals(productionDate.trim())){
           throw new BizException("请输入设备出厂日期");
       }
       if(buyDate == null || "".equals(buyDate.trim())){
           throw new BizException("请输入设备购买日期");
       }
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date production = null;
       Date buy = null ;
        try {
            production = sdf.parse(productionDate);
            buy = sdf.parse(buyDate);
        } catch (ParseException e) {
            throw new BizException("时间转化异常");
        }
       
       if(flag != 1){//复制添加
           deviceNo = deviceNo.trim();
           //验证编码唯一
           Device dev = deviceService.getByDeviceNo(deviceNo);
           
           if(dev !=null){
               throw new BizException("设备编码'"+deviceNo.trim()+"'已存在请更换。");
           }
           
           DeviceInfoVo deviceInfo = device.getDeviceInfo();
           device = new Device();
            device.create(deviceInfo, deviceNo, production,buy,admin,otherDesc);
           
           deviceService.addSave(device);
       }else{//编辑信息
           if(deviceCard != null){
               device.setDeviceInfo(deviceCard.getDeviceInfo());
           }
           device.setUpdateDate(new Date());
           device.setOtherDesc(otherDesc);
           device.setBuyDate(buy);
           device.setProductionDate(production);
           
           deviceService.update(device);
       }
   }
    /**
     * 删除设备信息
     */
   public void deletes(String ids) {
       if(ids == null || "".equals(ids.trim())){
           throw new BizException("请选择删除操作项。");
       }
       String id[] = ids.trim().split(",");
       List<Device> devices = new ArrayList<Device>();
       for(int i = 0;i<id.length;i++){
          Device device = deviceService.getById(Integer.parseInt(id[i]));
          if(device != null){
              if(device.getProjectDevice() != null){
                  throw new BizException("设备'"+device.getDeviceInfo().getDeviceName()+"'已经在项目'"
              +device.getProjectDevice().getProject().getProjectName()+"'中，不能进行删除操作。");
              }
              devices.add(device);
          }
          
          for(int j=0;j<devices.size();j++){
              deviceService.delete(devices.get(j));
          }
       }
       
       
   }
   
    public DeviceCardService getDeviceCardService() {
        return deviceCardService;
    }
    @Resource(name="deviceCardService")
    public void setDeviceCardService(DeviceCardService deviceCardService) {
        this.deviceCardService = deviceCardService;
    }
    public DeviceService getDeviceService() {
        return deviceService;
    }
    @Resource(name="deviceService")
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

}
