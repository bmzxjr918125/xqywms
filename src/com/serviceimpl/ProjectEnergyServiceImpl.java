package com.serviceimpl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ProjectEnergyDao;
import com.service.ProjectEnergyService;

@Service("projectEnergyService")
public class ProjectEnergyServiceImpl implements ProjectEnergyService{
    private ProjectEnergyDao projectEnergyDao;

    public ProjectEnergyDao getProjectEnergyDao() {
        return projectEnergyDao;
    }
    @Resource(name="projectEnergyDao")
    public void setProjectEnergyDao(ProjectEnergyDao projectEnergyDao) {
        this.projectEnergyDao = projectEnergyDao;
    }
}
