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

    void getDataTablePage(DataTables dtJson);

    void getProjectDeviceDataTablePage(DataTables dtJson, int projectId);
    /**
     * <p>@Description:项目基本信息添加</p>
     * <p>@param @param projectType
     * <p>@param @param isChargeRepair
     * <p>@param @param checkType
     * <p>@param @param provinceId
     * <p>@param @param cityId
     * <p>@param @param areaId
     * <p>@param @param projectName
     * <p>@param @param repairDateStart
     * <p>@param @param repairDateEnd
     * <p>@param @param userPhoneNumber
     * <p>@param @param address
     * <p>@param @param description</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-8下午2:05:54</p>
     */
    void addSave(int projectType, int isChargeRepair, int checkType,
            int provinceId, int cityId, int areaId, String projectName,
            String repairDateStart, String repairDateEnd,
            String userPhoneNumber, String address, String description) throws Exception;
    /**
     * <p>@Description: 项目基本信息修改</p>
     * <p>@param @param projectId
     * <p>@param @param projectType
     * <p>@param @param isChargeRepair
     * <p>@param @param checkType
     * <p>@param @param provinceId
     * <p>@param @param cityId
     * <p>@param @param areaId
     * <p>@param @param projectName
     * <p>@param @param repairDateStart
     * <p>@param @param repairDateEnd
     * <p>@param @param address
     * <p>@param @param description</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-8下午4:48:16</p>
     */
    void updateSave(int projectId, int projectType, int isChargeRepair,
            int checkType, int provinceId, int cityId, int areaId,
            String projectName, String repairDateStart, String repairDateEnd,
            String address, String description) throws Exception;

    void deletes(String ids);
   
}
