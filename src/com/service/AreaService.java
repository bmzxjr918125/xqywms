package com.service;

import java.util.List;

import com.entity.common.Area;


public interface AreaService {

    List<Area> getAreaList(int cityId);

    Area getById(int areaId);
    
	
}
