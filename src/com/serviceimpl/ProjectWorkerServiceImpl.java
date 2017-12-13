package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.ProjectWorkerDao;
import com.entity.project.ProjectWorker;
import com.service.ProjectWorkerService;

@Service("projectWorkerService")
public class ProjectWorkerServiceImpl implements ProjectWorkerService{
    private ProjectWorkerDao projectWorkerDao;
    
    public void getDataTablePage(DataTables dtJson, int projectId,
            int workerType) {
        projectWorkerDao.getDataTablePage(dtJson,projectId,workerType);
    }
    
    public List<ProjectWorker> getByProjectId(int projectId) {
        return projectWorkerDao.findByName("project.id", projectId);
    }
    

    public List<ProjectWorker> getByProjectIdAndWorkerType(int projectId,
            int workerType) {
        return projectWorkerDao.findByIntAndInt("projectDevice.project.id", projectId, "workerType", workerType);
    }
    
    public ProjectWorker getByProjectDeviceIdAndWorkerId(int workerId,
            int projectDeviceId, int workerType) {
        List<ProjectWorker> list = projectWorkerDao.findByIntAndInt("projectDevice.project.id", projectDeviceId, "workerType", workerType);
        if(list != null && list.size()>0){
            for(int i=0;i<list.size();i++){
                if(list.get(i).getWorkerType().getIndex() == workerType){
                    return list.get(i);
                }
            }
        }
        return null;
    }
    public void addSave(ProjectWorker projectWorker) {
        projectWorkerDao.save(projectWorker);
    }

    public ProjectWorker getById(int projectWorkerId) {
        return projectWorkerDao.get(projectWorkerId);
    }

    public void delete(ProjectWorker projectWorker) {
        projectWorkerDao.delete(projectWorker);
    }
    
    
    
    
    public ProjectWorkerDao getProjectWorkerDao() {
        return projectWorkerDao;
    }
    @Resource(name="projectWorkerDao")
    public void setProjectWorkerDao(ProjectWorkerDao projectWorkerDao) {
        this.projectWorkerDao = projectWorkerDao;
    }
}
