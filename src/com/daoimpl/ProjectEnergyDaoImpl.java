package com.daoimpl;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectEnergyDao;
import com.entity.project.ProjectEnergy;

@Repository("projectEnergyDao")
public class ProjectEnergyDaoImpl extends BaseDaoImpl<ProjectEnergy> implements ProjectEnergyDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Project ");
       
        super.findByPage(hql.toString(), dtJson);
    }

   
}
