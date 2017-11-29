package com.daoimpl;


import java.util.List;

import org.springframework.stereotype.Repository;
import com.base.dao.BaseDaoImpl;
import com.dao.ImageDao;
import com.entity.common.Image;
import com.entity.enumobj.ImageType;

@Repository("imageDao")
public class ImageDaoImpl extends BaseDaoImpl<Image> implements ImageDao{
   /**
    * 获取图片集合
    */
	@SuppressWarnings("unchecked")
	public List<Image> getImageList(String name, int id,
			ImageType productscan) {
		String hql="  from  Image where "+name+" = ? and imageType= ? order by createDate desc";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setCacheable(true)
				.setInteger(0, id)
				.setInteger(1, productscan.getIndex())
				.list();
	}
	/**
	 * 获取图片集合
	 */
	@SuppressWarnings("unchecked")
	public List<Image> getImageList(String name, long id,
			ImageType productscan) {
		String hql="  from  Image where "+name+" = ? and imageType= ? order by createDate asc";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setCacheable(true)
				.setLong(0, id)
				.setInteger(1, productscan.getIndex())
				.list();
	}

}
