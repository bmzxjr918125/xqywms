package com.daoimpl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.NewDao;
import com.entity.common.New;

@Repository("newDao")
public class NewDaoImpl extends BaseDaoImpl<New> implements NewDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from New ");
       
        super.findByPage(hql.toString(), dtJson);
    }

    @SuppressWarnings("unchecked")
    public List<New> getNewList(int currentNum, int perPageNum) {
        StringBuffer hql = new StringBuffer(" from New order by createDate desc ");
        return this.getHibernateTemplate()
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(hql.toString())
                .setFirstResult(currentNum)
                .setMaxResults(perPageNum)
                .list();
    }
   
}
