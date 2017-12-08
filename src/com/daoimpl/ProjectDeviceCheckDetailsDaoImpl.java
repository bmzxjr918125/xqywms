package com.daoimpl;
import org.springframework.stereotype.Repository;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectDeviceCheckDetailsDao;
import com.entity.project.ProjectDeviceCheckDetails;

@Repository("projectDeviceCheckDetailsDao")
public class ProjectDeviceCheckDetailsDaoImpl extends BaseDaoImpl<ProjectDeviceCheckDetails> implements ProjectDeviceCheckDetailsDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Project ");
       
        super.findByPage(hql.toString(), dtJson);
    }

   
}
