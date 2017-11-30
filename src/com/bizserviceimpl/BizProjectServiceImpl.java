package com.bizserviceimpl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizProjectService;
import com.service.ProjectUserService;


@Service("bizProjectService")
public class BizProjectServiceImpl implements BizProjectService{
    private ProjectUserService projectUserService;
   
   /**
    * 查看下属成员列表
    */
    public void getUnderUserDataTablePage(DataTables dtJson, int projectId) {
       
        projectUserService.getUnderUserDataTablePage(dtJson,projectId);
        
        
        
    }

    public ProjectUserService getProjectUserService() {
        return projectUserService;
    }
    @Resource(name="projectUserService")
    public void setProjectUserService(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }
    
  
}
