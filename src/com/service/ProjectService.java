package com.service;

import com.base.action.datatables.DataTables;
import com.entity.enumobj.Boolean;
import com.entity.project.Project;




public interface ProjectService {

    Project getById(int projectId);

    void getDataTablePage(DataTables dtJson);

    void create(Project project, String userPhoneNumber,Boolean isMain);

    void update(Project project);

    void delete(Project project);

   
    
	
}
