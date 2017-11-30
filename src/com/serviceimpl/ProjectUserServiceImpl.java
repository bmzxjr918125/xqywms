package com.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.ProjectUserDao;
import com.service.ProjectUserService;

@Service("projectUserService")
public class ProjectUserServiceImpl implements ProjectUserService{
    private ProjectUserDao projectUserDao;

    
    public void getUnderUserDataTablePage(DataTables dtJson, int projectId) {
        projectUserDao.getUnderUserDataTablePage(dtJson,projectId);
    }
    
    public ProjectUserDao getProjectUserDao() {
        return projectUserDao;
    }
    @Resource(name="projectUserDao")
    public void setProjectUserDao(ProjectUserDao projectUserDao) {
        this.projectUserDao = projectUserDao;
    }
}
