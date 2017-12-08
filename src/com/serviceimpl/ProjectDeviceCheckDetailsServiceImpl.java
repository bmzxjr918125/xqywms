package com.serviceimpl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ProjectDeviceCheckDetailsDao;
import com.service.ProjectDeviceCheckDetailsService;

@Service("projectDeviceCheckDetailsService")
public class ProjectDeviceCheckDetailsServiceImpl implements ProjectDeviceCheckDetailsService{
   private ProjectDeviceCheckDetailsDao projectDeviceCheckDetailsDao;

    public ProjectDeviceCheckDetailsDao getProjectDeviceCheckDetailsDao() {
        return projectDeviceCheckDetailsDao;
    }
    @Resource(name="projectDeviceCheckDetailsDao")
    public void setProjectDeviceCheckDetailsDao(
            ProjectDeviceCheckDetailsDao projectDeviceCheckDetailsDao) {
        this.projectDeviceCheckDetailsDao = projectDeviceCheckDetailsDao;
    }
}
