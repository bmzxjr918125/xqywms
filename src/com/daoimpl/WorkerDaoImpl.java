package com.daoimpl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.WorkerDao;
import com.entity.enumobj.Status;
import com.entity.user.Worker;

@Repository("workerDao")
public class WorkerDaoImpl extends BaseDaoImpl<Worker> implements WorkerDao{
    /**
     * 通过手机号获取对应worker
     */
	public Worker getByPhone(String phoneNum) {
		StringBuffer hql=new StringBuffer(" from Worker where phoneNumber = ?");
		return (Worker) this.getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(hql.toString())
				.setString(0,phoneNum)
				.uniqueResult();
	}
    /**
     * 分页获取人员列表数据
     */
    public void getDataTablePage(DataTables dtJson, String nickName,
            String phoneNumber, int status) {
        StringBuffer hql = new StringBuffer(" from Worker where id != 0");
        
        if( nickName != null && !"".equals(nickName.trim())){
            hql.append(" and nickName like '%" + nickName + "%'");
        }
        if( phoneNumber != null && !"".equals(phoneNumber.trim())){
            hql.append(" and phoneNumber like '%" + phoneNumber + "%'");
        }
        if(status != 0){
            hql.append(" and status = "+status);
        }
        
        super.findByPage(hql.toString(), dtJson);
    }
    @SuppressWarnings("unchecked")
    public List<Worker> getCanAddToProjectWorker(List<Integer> falseWorkerIds) {
        StringBuffer hql = new StringBuffer(" from Worker where id != 0");
        
        hql.append(" and status = "+Status.WORKER_ABLE.getIndex());
        hql.append(" and id not in(:ids)");
        
        if(falseWorkerIds == null || falseWorkerIds.size()==0){
            falseWorkerIds.add(0);
        }
        
        return this.getHibernateTemplate()
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(hql.toString())
                .setParameterList("ids", falseWorkerIds)
                .list();
    }
   

}
