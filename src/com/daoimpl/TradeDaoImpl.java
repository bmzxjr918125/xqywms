package com.daoimpl;


import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.TradeDao;
import com.entity.trade.Trade;

@Repository("tradeDao")
public class TradeDaoImpl extends BaseDaoImpl<Trade> implements TradeDao{

	public double countDayAmount(String feildName) {
		StringBuffer hql=new StringBuffer(" select sum("+feildName+") from Trade where date_format(createDate,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')");
		Object obj=this.getHibernateTemplate()
		.getSessionFactory()
		.getCurrentSession()
		.createQuery(hql.toString())
		.uniqueResult();
		
		return obj != null ? Double.parseDouble(obj.toString()) : 0.0;
	}

	public void getTradeByPage(DataTables dt, int adminId) {
		StringBuffer hql = new StringBuffer();

		hql.append("from Trade where admin is not null and admin.id="+adminId);


		super.findByPage(hql.toString(), dt);
	}

	public double countDayByAdminId(String name, int adminId) {
		StringBuffer hql=new StringBuffer(" select sum("+name+") from Trade where date_format(createDate,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') and admin is not null and admin.id="+adminId);
		Object obj=this.getHibernateTemplate()
		.getSessionFactory()
		.getCurrentSession()
		.createQuery(hql.toString())
		.uniqueResult();
		
		return obj != null ? Double.parseDouble(obj.toString()) : 0.0;
	}

	public double countMonthByAdminId(String name, int adminId) {
		StringBuffer hql=new StringBuffer(" select sum("+name+") from Trade where date_format(createDate,'%Y-%m') = date_format(now(),'%Y-%m') and admin is not null and admin.id="+adminId);
		Object obj=this.getHibernateTemplate()
		.getSessionFactory()
		.getCurrentSession()
		.createQuery(hql.toString())
		.uniqueResult();
		
		return obj != null ? Double.parseDouble(obj.toString()) : 0.0;
	}

	public double countAllByAdminId(String name, int adminId) {
		StringBuffer hql=new StringBuffer(" select sum("+name+") from Trade where  admin is not null and admin.id="+adminId);
		Object obj=this.getHibernateTemplate()
		.getSessionFactory()
		.getCurrentSession()
		.createQuery(hql.toString())
		.uniqueResult();
		
		return obj != null ? Double.parseDouble(obj.toString()) : 0.0;
	}

}
