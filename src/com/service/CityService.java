package com.service;

import java.util.List;

import com.entity.common.City;


public interface CityService {

    List<City> getCityList(int provinceId);

    City getById(int cityId);
    
	
}
