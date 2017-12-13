package com.service;
import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.device.Device;


public interface DeviceService {

    void getDataTablePage(DataTables dtJson);
    
    Device getByDeviceNo(String deviceNo);

    void addSave(Device device);

    Device getById(int deviceId);

    void update(Device device);

    void delete(Device device);

    List<String> getDeviceNameAndIdList(Boolean pDeviceIsNull, int status);

	
}
