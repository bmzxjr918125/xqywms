package com.dao;
import java.util.List;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.device.DeviceCard;


public interface DeviceCardDao extends BaseDao<DeviceCard>{

    void getDataTablePage(DataTables dtJson);

    List<String> getCardNameAndIdList();
}
