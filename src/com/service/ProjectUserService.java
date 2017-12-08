package com.service;

import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.project.ProjectUser;


public interface ProjectUserService {

    void getUnderUserDataTablePage(DataTables dtJson, int projectId);

    void delete(ProjectUser projectUser);

    void update(ProjectUser projectUser);

    List<ProjectUser> getByProjectId(int projectId);

  
}
