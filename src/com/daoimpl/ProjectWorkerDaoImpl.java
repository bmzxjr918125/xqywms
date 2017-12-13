package com.daoimpl;
import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectWorkerDao;
import com.entity.enumobj.WorkerType;
import com.entity.project.ProjectWorker;

@Repository("projectWorkerDao")
public class ProjectWorkerDaoImpl extends BaseDaoImpl<ProjectWorker> implements ProjectWorkerDao{

    public void getDataTablePage(DataTables dtJson,int projectId,int workerType) {
        StringBuffer hql = new StringBuffer(" from ProjectWorker where id!=0 ");
        if(projectId != 0){
            hql.append(" and projectDevice.project.id = "+projectId);
        }
        if( WorkerType.isContain(workerType)){
            hql.append(" and workerType = "+workerType);
        }
        super.findByPage(hql.toString(), dtJson);
    }

   
}
