package com.bizservice;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.entity.project.ProjectUser;
import com.entity.user.User;



/**
 * <p>ClassName: BizUserService</p>
 * <p>@Description: 用户业务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午5:22:02</p>
 */
public interface BizUserService {
    /**
     * <p>@Description: 用户登录</p>
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
     * <p>@Description: 获取用户信息</p>
     * <p>@param @param userId
     * <p>@param @return</p>   
     * <p>@return JSONObject</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-22下午5:50:21</p>
     */
    JSONObject getUserInfo(Integer userId);
    
    void register(String phoneNumber, String pwd,ProjectUser projectUser);
    void updateUser(User user, String nickName, int sex, String profession,
            String province, String city, String area, String address);
    void isRegister(String phoneNumber);
    void updatePwd(String newPwd, String newPwdAgin, String phoneNumber);
  
   
  
  
    
    
	

}
