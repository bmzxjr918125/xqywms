package com.daoimpl;
import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectWorkerDao;
import com.entity.project.ProjectWorker;

@Repository("projectWorkerDao")
public class ProjectWorkerDaoImpl extends BaseDaoImpl<ProjectWorker> implements ProjectWorkerDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Project ");
       
        super.findByPage(hql.toString(), dtJson);
    }

   
}
