package com.daoimpl;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectUserDao;
import com.entity.project.ProjectUser;

@Repository("projectUserDao")
public class ProjectUserDaoImpl extends BaseDaoImpl<ProjectUser> implements ProjectUserDao{

  

    public void getUnderUserDataTablePage(DataTables dtJson, int projectId) {
        StringBuffer hql = new StringBuffer(" from ProjectUser ");
        
        super.findByPage(hql.toString(), dtJson);
    }

   
}
