package com.bizservice;

import net.sf.json.JSONArray;

import com.base.action.datatables.DataTables;
import com.entity.admin.Admin;
import com.entity.device.DeviceCard;

/**
 * <p>ClassName: BizDeviceService</p>
 * <p>@Description: 设备业务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-12-4下午2:37:09</p>
 */
public interface BizDeviceService {

    void getDataTablePage(DataTables dtJson);
    /**
     * <p>@Description:添加设备名片信息</p>
     * <p>@param @param deviceName
     * <p>@param @param deviceType
     * <p>@param @param supplier
     * <p>@param @param modeNum
     * <p>@param @param input
     * <p>@param @param output
     * <p>@param @param cryogenType
     * <p>@param @param charge
     * <p>@param @param description</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-4下午4:11:49</p>
     */
    void addSaveDeviceCar(String deviceName, String deviceType,
            String supplier, String modeNum, String input, String output,
            String cryogenType, String charge, String description);
    /**
     * <p>@Description: 编辑设备名片信息</p>
     * <p>@param @param deviceCardId
     * <p>@param @param deviceName
     * <p>@param @param deviceType
     * <p>@param @param supplier
     * <p>@param @param modeNum
     * <p>@param @param input
     * <p>@param @param output
     * <p>@param @param cryogenType
     * <p>@param @param charge
     * <p>@param @param description</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-4下午4:29:36</p>
     */
    void updateSaveDeviceCar(int deviceCardId, String deviceName,
            String deviceType, String supplier, String modeNum, String input,
            String output, String cryogenType, String charge, String description);
    void deletesCard(String ids);
    void getCardDataTablePage(DataTables dtJson);
    /**
     * <p>@Description: 获取名片名称 和 id 列表</p>
     * <p>@param @return</p>   
     * <p>@return JSONArray</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-5下午12:58:29</p>
     */
    JSONArray getCardNameAndIdList();
    DeviceCard getById(int cardId);
    /**
     * <p>@Description: 添加设备信息</p>
     * <p>@param @param deviceCardId
     * <p>@param @param deviceNo
     * <p>@param @param productionDate
     * <p>@param @param buyDate</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-5下午2:44:59</p>
     */
    void addSaveDevice(int deviceCardId, String deviceNo,
            String productionDate, String buyDate,String otherDesc,Admin admin);
    /**
     * <p>@Description: 修改或复制添加设备信息</p>
     * <p>@param @param deviceCardId
     * <p>@param @param deviceNo
     * <p>@param @param productionDate
     * <p>@param @param buyDate
     * <p>@param @param otherDesc
     * <p>@param @param admin
     * <p>@param @param flag 1修改设备信息  2 复制添加设备信息
     * <p>@param @param deviceId</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-5下午5:17:48</p>
     */
    void updateSaveDevice(int deviceCardId, String deviceNo,
            String productionDate, String buyDate, String otherDesc,
            Admin admin, int flag, int deviceId);
    /**
     * <p>@Description: 删除设备信息</p>
     * <p>@param @param ids</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-5下午5:34:04</p>
     */
    void deletes(String ids);

 
   

}
