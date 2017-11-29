package com.daoimpl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.AdminDao;
import com.entity.admin.Admin;


import java.util.List;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@SuppressWarnings("unchecked")
	public Admin getAdminByName(String param,String value){
		String hql = "from Admin where username=?";
		List<Admin> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
			//	.setString(0, param)
				.setString(0, value)
				.list();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
    /**
     * 根据用户名查询单个用户的信息
     */
	@SuppressWarnings("unchecked")
	public Admin findByOnly(String username){
		String hql = "from Admin where username=?";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0,username);
		List<Admin> list = query.list();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 后台管理员列表
	 */
	public void getByPage(DataTables dt){
		StringBuffer hql = new StringBuffer();

		hql.append("from Admin where type=1 ");

		//筛选条件
		hql.append("and ( username like ? ");
		hql.append("or role_name like ? )");

		//排序
		//hql.append("order by "+dt.getColumns().get(dt.getOrder().get(0).getColumn()).getData()+" "+dt.getOrder().get(0).getDir());

		super.findByPage(hql.toString(), dt,
				"%"+dt.getSearch().getValue()+"%",
				"%"+dt.getSearch().getValue()+"%");
	}
	
}
