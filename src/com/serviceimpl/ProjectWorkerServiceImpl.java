package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.ProjectWorkerDao;
import com.entity.project.ProjectWorker;
import com.service.ProjectWorkerService;

@Service("projectWorkerService")
public class ProjectWorkerServiceImpl implements ProjectWorkerService{
    private ProjectWorkerDao projectWorkerDao;

    public List<ProjectWorker> getByProjectId(int projectId) {
        return projectWorkerDao.findByName("project.id", projectId);
    }
    
    public ProjectWorkerDao getProjectWorkerDao() {
        return projectWorkerDao;
    }
    @Resource(name="projectWorkerDao")
    public void setProjectWorkerDao(ProjectWorkerDao projectWorkerDao) {
        this.projectWorkerDao = projectWorkerDao;
    }
}
