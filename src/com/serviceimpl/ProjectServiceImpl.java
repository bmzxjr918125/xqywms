package com.serviceimpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ProjectDao;
import com.entity.project.Project;
import com.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
    private ProjectDao projectDao;

    public Project getById(int projectId) {
        return projectDao.get(projectId);
    }
    
    
    
    public ProjectDao getProjectDao() {
        return projectDao;
    }
    @Resource(name="projectDao")
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
}
