package com.service;
import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.device.DeviceCard;


public interface DeviceCardService {

    void getDataTablePage(DataTables dtJson);

    void addSave(DeviceCard deviceCard);

    DeviceCard getById(int deviceCardId);

    void updateSave(DeviceCard deviceCard);

    void delete(DeviceCard deviceCard);

    List<String> getCardNameAndIdList();


	
}
