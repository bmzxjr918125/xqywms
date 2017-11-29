package com.daoimpl;


import org.springframework.stereotype.Repository;

import com.base.dao.BaseDaoImpl;
import com.dao.AreaDao;
import com.entity.common.Area;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao{
   
}
