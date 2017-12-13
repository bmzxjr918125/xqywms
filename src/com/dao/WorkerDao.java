package com.dao;


import java.util.List;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.user.Worker;


public interface WorkerDao extends BaseDao<Worker>{
    /**
     * <p>@Description: 通过手机号获取对应worker</p>
     * <p>@param @param phoneNum
     * <p>@param @return</p>   
     * <p>@return User</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-3-16下午4:39:11</p>
     */
	Worker getByPhone(String phoneNum);
    /**
     * <p>@Description: 分页获取人员列表数据</p>
     * <p>@param @param dtJson
     * <p>@param @param nickName
     * <p>@param @param phoneNumber
     * <p>@param @param status</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-17下午2:20:22</p>
     */
    void getDataTablePage(DataTables dtJson, String nickName,
            String phoneNumber, int status);
    List<Worker> getCanAddToProjectWorker(List<Integer> falseWorkerIds);
  

}
