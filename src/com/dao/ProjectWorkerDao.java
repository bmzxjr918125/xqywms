package com.dao;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.project.ProjectWorker;


public interface ProjectWorkerDao extends BaseDao<ProjectWorker>{

    void getDataTablePage(DataTables dtJson, int projectId, int workerType);

}
