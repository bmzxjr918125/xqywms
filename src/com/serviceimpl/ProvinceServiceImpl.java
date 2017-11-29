package com.serviceimpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ProvinceDao;
import com.service.ProvinceService;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{
		private ProvinceDao provinceDao;

		
        public ProvinceDao getProvinceDao() {
            return provinceDao;
        }
        @Resource(name="provinceDao")
        public void setProvinceDao(ProvinceDao provinceDao) {
            this.provinceDao = provinceDao;
        }
}
