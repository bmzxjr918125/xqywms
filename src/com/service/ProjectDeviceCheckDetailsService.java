package com.service;

import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.project.ProjectDeviceCheckDetails;


public interface ProjectDeviceCheckDetailsService {

    List<ProjectDeviceCheckDetails> getByProjectDeviceId(Integer id);

    void getDataTablePage(DataTables dtJson, int projectDeviceId);

    List<ProjectDeviceCheckDetails> getByProjectWorkerId(int projectWorkerId);

    
	
}
