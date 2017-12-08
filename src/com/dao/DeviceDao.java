package com.dao;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.device.Device;


public interface DeviceDao extends BaseDao<Device>{
    void getDataTablePage(DataTables dtJson);
}
