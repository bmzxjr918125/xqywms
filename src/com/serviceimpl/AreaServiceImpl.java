package com.serviceimpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AreaDao;
import com.entity.common.Area;
import com.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService{
    private AreaDao areaDao;
    
    public List<Area> getAreaList(int cityId) {
        return areaDao.findByName("city.id", cityId);
    }
    public Area getById(int areaId) {
       
        return areaDao.get(areaId);
    }
    public AreaDao getAreaDao() {
        return areaDao;
    }
    @Resource(name="areaDao")
    public void setAreaDao(AreaDao areaDao) {
        this.areaDao = areaDao;
    }
}
