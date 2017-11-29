package com.serviceimpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.CityDao;
import com.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
        private CityDao cityDao;

        public CityDao getCityDao() {
            return cityDao;
        }
        @Resource(name="cityDao")
        public void setCityDao(CityDao cityDao) {
            this.cityDao = cityDao;
        }
}
