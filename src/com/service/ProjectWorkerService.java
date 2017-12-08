package com.service;

import java.util.List;

import com.entity.project.ProjectWorker;


public interface ProjectWorkerService {

    List<ProjectWorker> getByProjectId(int projectId);

    
	
}
