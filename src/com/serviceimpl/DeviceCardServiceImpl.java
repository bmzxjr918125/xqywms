package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.action.datatables.DataTables;
import com.dao.DeviceCardDao;
import com.entity.device.DeviceCard;
import com.service.DeviceCardService;

@Service("deviceCardService")
public class DeviceCardServiceImpl implements DeviceCardService{
    private DeviceCardDao deviceCardDao;

    public void getDataTablePage(DataTables dtJson) {
        
        deviceCardDao.getDataTablePage(dtJson);
    }

    public void addSave(DeviceCard deviceCard) {
        deviceCardDao.save(deviceCard);
    }
    public DeviceCard getById(int deviceCardId) {
       
        return deviceCardDao.get(deviceCardId);
    }

    public void updateSave(DeviceCard deviceCard) {
        deviceCardDao.update(deviceCard);
    }

    public void delete(DeviceCard deviceCard) {
        deviceCardDao.delete(deviceCard);
    }

    public List<String> getCardNameAndIdList() {
        return deviceCardDao.getCardNameAndIdList();
    }
    
    
    public DeviceCardDao getDeviceCardDao() {
        return deviceCardDao;
    }
    @Resource(name="deviceCardDao")
    public void setDeviceCardDao(DeviceCardDao deviceCardDao) {
        this.deviceCardDao = deviceCardDao;
    }
}
