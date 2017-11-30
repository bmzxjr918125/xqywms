package com.dao;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.project.ProjectUser;


public interface ProjectUserDao extends BaseDao<ProjectUser>{
    /**
     * <p>@Description:查看项目成员列表</p>
     * <p>@param @param dtJson
     * <p>@param @param projectId</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-30下午7:37:43</p>
     */
    void getUnderUserDataTablePage(DataTables dtJson, int projectId);

}
