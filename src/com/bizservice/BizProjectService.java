package com.bizservice;

import com.base.action.datatables.DataTables;

/**
 * <p>ClassName: BizProjectService</p>
 * <p>@Description: 项目业务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-30下午7:33:18</p>
 */
public interface BizProjectService {
    /**
     * <p>@Description: 查看项目成员列表</p>
     * <p>@param @param dtJson
     * <p>@param @param projectId</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-30下午7:36:21</p>
     */
    void getUnderUserDataTablePage(DataTables dtJson, int projectId);
   
}
