package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.action.datatables.DataTables;
import com.dao.DeviceDao;
import com.entity.device.Device;
import com.service.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{
    private DeviceDao deviceDao;

    public void getDataTablePage(DataTables dtJson) {
        
        deviceDao.getDataTablePage(dtJson);
    }

    public Device getByDeviceNo(String deviceNo) {
        List<Device> list = deviceDao.findByName("deviceNo", deviceNo);
        
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
    public void addSave(Device device) {
        deviceDao.save(device);
    }

    public Device getById(int deviceId) {
        return deviceDao.get(deviceId);
    }

    public void update(Device device) {
        deviceDao.update(device);
    }
    public void delete(Device device) {
        deviceDao.delete(device);
    }
    public List<String> getDeviceNameAndIdList(Boolean pDeviceIsNull, int status) {
        return deviceDao.getDeviceNameAndIdList(pDeviceIsNull,status);
    }
    
    
    
    public DeviceDao getDeviceDao() {
        return deviceDao;
    }
    @Resource(name="deviceDao")
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
}
