package com.daoimpl;


import org.springframework.stereotype.Repository;

import com.base.dao.BaseDaoImpl;
import com.dao.CityDao;
import com.entity.common.City;

@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao{
   
}
