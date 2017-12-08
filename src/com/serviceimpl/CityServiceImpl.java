package com.serviceimpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.CityDao;
import com.entity.common.City;
import com.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
        private CityDao cityDao;
        
        public List<City> getCityList(int provinceId) {
            
            return cityDao.findByName("province.id", provinceId);
        }
        public City getById(int cityId) {
            return cityDao.get(cityId);
        }
        
        public CityDao getCityDao() {
            return cityDao;
        }
        @Resource(name="cityDao")
        public void setCityDao(CityDao cityDao) {
            this.cityDao = cityDao;
        }
       
        
}
