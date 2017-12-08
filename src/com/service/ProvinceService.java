package com.service;

import java.util.List;

import com.entity.common.Province;


public interface ProvinceService {

    List<Province> getProvinceList();

    Province getById(int provinceId);
    
	
}
