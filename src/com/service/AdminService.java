package com.service;

import com.base.action.datatables.DataTables;
import com.entity.admin.Admin;


public interface AdminService {

	/**
	 * 根据指定字段以及指定字段的值获取Admin
	 * @param param
	 * @param value
	 * @return
	 */
	public Admin getAdminByName(String param,String value);

	public Admin getById(int id);

	public void saveOrUpdate(Admin l_Admin);

	/**
	 * <p>@Description: 后台管理员列表</p>
	 * <p>@param @param dt</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15上午10:00:25</p>
	 */
	void getByPage(DataTables dt);

	/**
	 * <p>@Description: 根据用户名查询单个用户信息</p>
	 * <p>@param @param username
	 * <p>@param @return</p>   
	 * <p>@return Admin</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15上午10:00:32</p>
	 */
	Admin getOnly(String username);

	/**
	 * <p>@Description: 保存编辑信息</p>
	 * <p>@param @param admin
	 * <p>@param @return</p>   
	 * <p>@return boolean</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15上午10:00:41</p>
	 */
	boolean saveEdit(Admin admin);

	
}
