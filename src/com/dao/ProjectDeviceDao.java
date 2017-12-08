package com.dao;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.project.ProjectDevice;


public interface ProjectDeviceDao extends BaseDao<ProjectDevice>{

    void getDataTablePage(DataTables dtJson, int projectId);

}
