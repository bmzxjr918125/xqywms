package com.dao;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.project.ProjectDeviceCheckDetails;


public interface ProjectDeviceCheckDetailsDao extends BaseDao<ProjectDeviceCheckDetails>{

    void getDataTablePage(DataTables dtJson, int projectDeviceId);

}
