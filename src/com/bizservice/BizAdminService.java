package com.bizservice;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.base.action.datatables.DataTables;
import com.entity.admin.Admin;


/**
 * <p>ClassName: BizAdminLoginService</p>
 * <p>@Description: Admin后台操作业务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-20上午9:44:02</p>
 */
public interface BizAdminService {
    /**
     * <p>@Description: Admin登录</p>
     * <p>@param @param request
     * <p>@param @param username
     * <p>@param @param password
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-20上午10:14:09</p>
     */
	void adminLogin(HttpServletRequest request, String username, String password);
    /**
     * <p>@Description: 通过ID获取对象信息</p>
     * <p>@param @param id
     * <p>@param @return</p>   
     * <p>@return L_Admin</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-20上午10:14:20</p>
     */
	Admin getById(int id);
	/**
	 * <p>@Description: 根据名称获取</p>
	 * <p>@param @param fieldName
	 * <p>@param @param username
	 * <p>@param @return</p>   
	 * <p>@return L_Admin</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午10:25:06</p>
	 */
	Admin getByName(String fieldName, String username);
	void saveOrUpdate(Admin l_Admin);
	/**
	 * <p>@Description: 分页获取管理员列表数据</p>
	 * <p>@param @param dtJson</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午10:27:02</p>
	 */
	void getByPage(DataTables dtJson);
	/**
	 * <p>@Description: 根据用户名查询单个用户信息</p>
	 * <p>@param @param username
	 * <p>@param @return</p>   
	 * <p>@return Object</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午10:28:30</p>
	 */
	Object getOnly(String username);
	/**
	 * <p>@Description: 保存admin用户修改信息</p>
	 * <p>@param @param admin
	 * <p>@param @return</p>   
	 * <p>@return boolean</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午10:29:44</p>
	 */
	boolean saveEdit(Admin admin);
	/**
	 * <p>@Description: 添加店员信息</p>
	 * <p>@param @param phoneNum
	 * <p>@param @param roleName</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15下午4:24:14</p>
	 */
	void addSaveEmpl(String phoneNum, String roleName);
	/**
	 * <p>@Description: 修改店员信</p>
	 * <p>@param @param roleName
	 * <p>@param @param password
	 * <p>@param @param adminId</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15下午5:03:46</p>
	 */
	void updateSaveEmpl( String roleName, String password,
			int adminId);
	/**
	 * <p>@Description: 计算admin首页所需各个数据</p>
	 * <p>@param @return</p>   
	 * <p>@return JSONObject</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-17下午4:07:05</p>
	 */
	JSONObject countAmount();
	void getTradeByPage(DataTables dtJson, int adminId);
	JSONObject countAmountByAdminId(int adminId);
	
	

}
