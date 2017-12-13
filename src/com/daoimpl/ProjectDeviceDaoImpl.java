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
        
        hql.append(" and ( device.deviceInfo.deviceName like ? or ");
        hql.append(" device.deviceInfo.deviceType like ? or ");
        hql.append(" device.deviceInfo.modelNum like ? or ");
        //hql.append(" ( IFNULL(d.projectDevice.project.projectName,'') like ? ) or ");
        hql.append(" position like ?  or ");
        hql.append(" device.deviceNo like ? )  ");
       
        
        //排序
        super.findByPage(hql.toString(), dtJson,
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                //"%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%");
    }
   
}
