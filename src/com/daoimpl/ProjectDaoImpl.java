package com.daoimpl;



import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectDao;
import com.entity.project.Project;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Project ");
       
        super.findByPage(hql.toString(), dtJson);
    }

   
}
