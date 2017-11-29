package com.daoimpl;


import org.springframework.stereotype.Repository;

import com.base.dao.BaseDaoImpl;
import com.dao.ProvinceDao;
import com.entity.common.Province;

@Repository("provinceDao")
public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements ProvinceDao{
   
}
