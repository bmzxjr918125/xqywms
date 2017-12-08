package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.ProjectDeviceDao;
import com.entity.project.ProjectDevice;
import com.service.ProjectDeviceService;

@Service("projectDeviceService")
public class ProjectDeviceServiceImpl implements ProjectDeviceService{
    private ProjectDeviceDao projectDeviceDao;
    
    public List<ProjectDevice> getByProjectId(int projectId) {
        
        return projectDeviceDao.findByName("project.id", projectId);
    }
    
    public void getDataTablePage(DataTables dtJson, int projectId) {
        projectDeviceDao.getDataTablePage(dtJson,projectId);
    }
    public ProjectDeviceDao getProjectDeviceDao() {
        return projectDeviceDao;
    }
    @Resource(name="projectDeviceDao")
    public void setProjectDeviceDao(ProjectDeviceDao projectDeviceDao) {
        this.projectDeviceDao = projectDeviceDao;
    }
    
}
