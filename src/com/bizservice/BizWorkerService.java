package com.bizservice;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.base.action.datatables.DataTables;
import com.entity.user.Worker;



/**
 * <p>ClassName: BizWorkerService</p>
 * <p>@Description: 人员业务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27上午11:28:43</p>
 */
public interface BizWorkerService {
    /**
     * <p>@Description: 分页获取人员列表数据</p>
     * <p>@param @param dtJson
     * <p>@param @param nickName
     * <p>@param @param phoneNumber
     * <p>@param @param status</p>  
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-17上午11:12:19</p>
     
     */
    void getDataTablePage(DataTables dtJson, String nickName,
            String phoneNumber, int status);
  
    /**
     * <p>@Description: 人员登录</p>
     * <p>@param @param phoneNumber
     * <p>@param @param pwd
     * <p>@param @return</p>   
     * <p>@return JSONObject</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-22下午4:17:44</p>
     * @param request 
     */
    JSONObject login(HttpServletRequest request, String phoneNumber, String pwd);
    /**
     * <p>@Description: 获取人员信息</p>
     * <p>@param @param workerId
     * <p>@param @return</p>   
     * <p>@return JSONObject</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-22下午5:50:21</p>
     */
    JSONObject getWorkerInfo(Integer workerId);
    
    void register(String phoneNumber, String pwd,String department,String job);
    /**
     * <p>@Description: 修改人员头像</p>
     * <p>@param @param workerId
     * <p>@param @param file
     * <p>@param @return</p>   
     * <p>@return JSONObject</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-24下午3:08:37</p>
     */
    JSONObject updateHeaderImg(int workerId, File file);
    void updateUser(Worker worker, String nickName, String department,
            String job);
    void isRegister(String phoneNumber);
    void updatePwd(String newPwd, String newPwdAgin, String phoneNumber);
  
   
  
  
    
    
	

}
