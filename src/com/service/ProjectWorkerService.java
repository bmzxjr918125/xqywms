package com.service;

import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.project.ProjectWorker;


public interface ProjectWorkerService {

    List<ProjectWorker> getByProjectId(int projectId);

    void getDataTablePage(DataTables dtJson, int projectId, int workerType);

    List<ProjectWorker> getByProjectIdAndWorkerType(int projectId,
            int workerType);

    ProjectWorker getByProjectDeviceIdAndWorkerId(int workerId,
            int projectDeviceId, int workerType);

    void addSave(ProjectWorker projectWorker);

    ProjectWorker getById(int projectWorkerId);

    void delete(ProjectWorker projectWorker);

    
	
}
