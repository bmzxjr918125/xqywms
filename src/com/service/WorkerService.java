package com.service;

import java.io.File;
import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.user.Worker;



public interface WorkerService {
    /**
     * <p>@Description: 通过手机号码获取对应user</p>
     * <p>@param @param phoneNum
     * <p>@param @return</p>   
     * <p>@return User</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-3-16下午4:38:20</p>
     */
	Worker getByPhone(String phoneNum);


	void update(Worker worker);
    /**
     * <p>@Description: 分页获取人员列表数据</p>
     * <p>@param @param dtJson
     * <p>@param @param nickName
     * <p>@param @param phoneNumber
     * <p>@param @param status</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-17下午2:18:44</p>
     */
    void getDataTablePage(DataTables dtJson, String nickName,
            String phoneNumber, int status);

    Worker getById(Integer workerId);


    void create(String phoneNumber, String pwd,String department,String job);


    void updateHeaderImg(Worker worker, File file);


    List<Worker> getCanAddToProjectWorker(List<Integer> falseWorkerIds);


	
}
