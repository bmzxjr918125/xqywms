package com.serviceimpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AreaDao;
import com.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService{
    private AreaDao areaDao;

    
    public AreaDao getAreaDao() {
        return areaDao;
    }
    @Resource(name="areaDao")
    public void setAreaDao(AreaDao areaDao) {
        this.areaDao = areaDao;
    }
}
