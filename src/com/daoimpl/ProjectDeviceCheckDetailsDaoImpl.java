package com.daoimpl;
import org.springframework.stereotype.Repository;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectDeviceCheckDetailsDao;
import com.entity.project.ProjectDeviceCheckDetails;

@Repository("projectDeviceCheckDetailsDao")
public class ProjectDeviceCheckDetailsDaoImpl extends BaseDaoImpl<ProjectDeviceCheckDetails> implements ProjectDeviceCheckDetailsDao{


    public void getDataTablePage(DataTables dtJson, int projectDeviceId) {
        StringBuffer hql = new StringBuffer(" from ProjectDeviceCheckDetails as d ");
        hql.append(" where d.id != 0 and ");
        hql.append("(  d.projectDevice.project.projectName like ? or ");
        hql.append(" d.projectDevice.device.deviceInfo.deviceName like ? or ");
        hql.append(" d.projectWorker.worker.nickName like ? or ");
        hql.append(" d.projectWorker.worker.phoneNumber like ? or ");
        hql.append(" d.description like ?  ");
        hql.append(" )  ");
       
        
        //排序
        super.findByPage(hql.toString(), dtJson,
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%");
    }

   
}
