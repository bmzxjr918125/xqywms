package com.daoimpl;



import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.ProjectDao;
import com.entity.project.Project;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Project as d ");
        hql.append(" where d.id != 0 and ");
        hql.append("(  d.projectName like ? or ");
        hql.append(" d.projectNo like ? or ");
        hql.append(" d.user.nickName like ? or ");
        hql.append(" d.user.phoneNumber like ? or ");
        hql.append(" d.description like ? or ");
        hql.append(" CONCAT(d.address.province,d.address.city,d.address.area,d.address.address)  like ?  ");
        hql.append(" )  ");
       
        
        //排序
        super.findByPage(hql.toString(), dtJson,
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%");
    }

   
}
