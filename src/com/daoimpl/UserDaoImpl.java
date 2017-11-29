package com.daoimpl;


import org.springframework.stereotype.Repository;

import com.base.dao.BaseDaoImpl;
import com.dao.UserDao;
import com.entity.user.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    /**
     * 通过手机号获取对应user
     */
	public User getByPhone(String phoneNum) {
		StringBuffer hql=new StringBuffer(" from User where phoneNumber = ?");
		return (User) this.getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(hql.toString())
				.setString(0,phoneNum)
				.uniqueResult();
	}
  
    public String getNextCode() {
        String hql=" select max(myCode+1) from User ";
       Object obj = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setCacheable(true).uniqueResult();
        return obj != null ? obj.toString() : "10000";
    }
    public int countUserNumber(String formatDate) {
        StringBuffer hql =new  StringBuffer(" select count(*) from User where id!=0");
        if(formatDate !=null && !formatDate.trim().equals("")){
            hql.append(" and date_format(createDate,'%Y-%m-%d') = '"+formatDate+"'");
        }
        return ((Long)this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession()
                .createQuery(hql.toString())
                .uniqueResult()).intValue();
    }

}
