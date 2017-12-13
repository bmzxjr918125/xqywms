package com.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.base.action.datatables.DataTables;
import com.entity.device.Device;
import com.entity.project.Project;
import com.entity.project.ProjectDevice;





public interface ProjectDeviceService {

    void getDataTablePage(DataTables dtJson, int projectId);

    List<ProjectDevice> getByProjectId(int projectId);

    void create(Project project, Device device, JSONArray entryJa,
            String installWorker, String position);

    ProjectDevice getById(int projectDeviceId);

    void update(ProjectDevice projectDevice, Device device, JSONArray ja,
            String installWorker, String position);

    void delete(ProjectDevice projectDevice);

    
	
}
