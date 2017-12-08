package com.serviceimpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ProvinceDao;
import com.entity.common.Province;
import com.service.ProvinceService;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{
		private ProvinceDao provinceDao;

		 public List<Province> getProvinceList() {
	            return provinceDao.findAll();
	     }

	        public Province getById(int provinceId) {
	            return provinceDao.get(provinceId);
	        }
		
        public ProvinceDao getProvinceDao() {
            return provinceDao;
        }
        @Resource(name="provinceDao")
        public void setProvinceDao(ProvinceDao provinceDao) {
            this.provinceDao = provinceDao;
        }
}
