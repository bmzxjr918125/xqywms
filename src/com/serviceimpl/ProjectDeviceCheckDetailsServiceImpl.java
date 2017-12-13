package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.ProjectDeviceCheckDetailsDao;
import com.entity.project.ProjectDeviceCheckDetails;
import com.service.ProjectDeviceCheckDetailsService;

@Service("projectDeviceCheckDetailsService")
public class ProjectDeviceCheckDetailsServiceImpl implements ProjectDeviceCheckDetailsService{
    private ProjectDeviceCheckDetailsDao projectDeviceCheckDetailsDao;
    
    public List<ProjectDeviceCheckDetails> getByProjectDeviceId(Integer projectDeviceId) {
       return projectDeviceCheckDetailsDao.findByName("projectDevice.id", projectDeviceId);
    }
    public void getDataTablePage(DataTables dtJson, int projectDeviceId) {
        projectDeviceCheckDetailsDao.getDataTablePage(dtJson,projectDeviceId);
    }
    public List<ProjectDeviceCheckDetails> getByProjectWorkerId(
            int projectWorkerId) {
        return projectDeviceCheckDetailsDao.findByName("projectWorker.id", projectWorkerId);
    }
    
    
    public ProjectDeviceCheckDetailsDao getProjectDeviceCheckDetailsDao() {
        return projectDeviceCheckDetailsDao;
    }
    @Resource(name="projectDeviceCheckDetailsDao")
    public void setProjectDeviceCheckDetailsDao(
            ProjectDeviceCheckDetailsDao projectDeviceCheckDetailsDao) {
        this.projectDeviceCheckDetailsDao = projectDeviceCheckDetailsDao;
    }
}
