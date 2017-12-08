package com.serviceimpl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.ProjectDao;
import com.entity.enumobj.Boolean;
import com.entity.project.Project;
import com.entity.project.ProjectUser;
import com.entity.user.User;
import com.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
    private ProjectDao projectDao;

    public Project getById(int projectId) {
        return projectDao.get(projectId);
    }
    public void getDataTablePage(DataTables dtJson) {
        projectDao.getDataTablePage(dtJson);
    }
    public void create(Project project, String userPhoneNumber,Boolean isMain) {
        
        ProjectUser projectUser = new ProjectUser();
        projectUser.setProject(project);
        projectUser.setIsMain(isMain);
        projectUser.setCreateDate(new Date());
        
        User user = new User();
        user.create(userPhoneNumber,projectUser);
        
        projectUser.setUser(user);
        
        project.setUser(user);
        
        projectDao.save(project);
        
        //TODO发送短信
        
        
    }
    public void update(Project project) {
       
        projectDao.update(project);
    }
    public void delete(Project project) {
        projectDao.delete(project);
    }
    
    public ProjectDao getProjectDao() {
        return projectDao;
    }
    @Resource(name="projectDao")
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
}
