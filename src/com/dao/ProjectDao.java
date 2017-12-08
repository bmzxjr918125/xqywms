package com.dao;


import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.project.Project;


public interface ProjectDao extends BaseDao<Project>{

    void getDataTablePage(DataTables dtJson);

}
