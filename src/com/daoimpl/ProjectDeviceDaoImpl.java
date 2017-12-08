package com.daoimpl;

import org.springframework.stereotype.Repository;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectDeviceDao;
import com.entity.project.ProjectDevice;

@Repository("projectDeviceDao")
public class ProjectDeviceDaoImpl extends BaseDaoImpl<ProjectDevice> implements ProjectDeviceDao{

    public void getDataTablePage(DataTables dtJson,int projectId) {
        StringBuffer hql = new StringBuffer(" from ProjectDevice ");
        hql.append(" where project.id = "+projectId);
        super.findByPage(hql.toString(), dtJson);
    }
   
}
