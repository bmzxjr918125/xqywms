package com.service;

import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.project.ProjectDevice;





public interface ProjectDeviceService {

    void getDataTablePage(DataTables dtJson, int projectId);

    List<ProjectDevice> getByProjectId(int projectId);

    
	
}
