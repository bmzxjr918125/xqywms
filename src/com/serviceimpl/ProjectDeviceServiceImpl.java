package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.DeviceDao;
import com.dao.ProjectDeviceDao;
import com.entity.device.Device;
import com.entity.project.Project;
import com.entity.project.ProjectDevice;
import com.service.ProjectDeviceService;

@Service("projectDeviceService")
public class ProjectDeviceServiceImpl implements ProjectDeviceService{
    private ProjectDeviceDao projectDeviceDao;
    private DeviceDao deviceDao;
    
    public List<ProjectDevice> getByProjectId(int projectId) {
        
        return projectDeviceDao.findByName("project.id", projectId);
    }
    
    public void getDataTablePage(DataTables dtJson, int projectId) {
        projectDeviceDao.getDataTablePage(dtJson,projectId);
    }
    public void create(Project project, Device device, JSONArray entryJa,
            String installWorker, String position) {
       ProjectDevice projectDevice = new ProjectDevice();
       projectDevice.create(project,device,entryJa,installWorker,position);
       projectDeviceDao.save(projectDevice);
       
       device.setProjectDevice(projectDevice);
       deviceDao.update(device);
       
    }
    public ProjectDevice getById(int projectDeviceId) {
        return projectDeviceDao.get(projectDeviceId);
    }

    public void update(ProjectDevice projectDevice, Device device,
            JSONArray ja, String installWorker, String position) {
       Device oldDevice = projectDevice.getDevice();
       oldDevice.setProjectDevice(null);
       device.setProjectDevice(projectDevice);
      
       projectDevice.setJSONArrayEntry(ja);
       projectDevice.setInstallWorker(installWorker);
       projectDevice.setPosition(position);
       projectDevice.setDevice(device);
       
       projectDeviceDao.update(projectDevice);
       
       deviceDao.update(oldDevice);
       deviceDao.update(device);
        
    }
    public void delete(ProjectDevice projectDevice) {
        projectDeviceDao.delete(projectDevice);
    }
    
    public ProjectDeviceDao getProjectDeviceDao() {
        return projectDeviceDao;
    }
    @Resource(name="projectDeviceDao")
    public void setProjectDeviceDao(ProjectDeviceDao projectDeviceDao) {
        this.projectDeviceDao = projectDeviceDao;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }
    @Resource(name="deviceDao")
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
}
