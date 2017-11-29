package com.dao;


import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.admin.Admin;


public interface AdminDao extends BaseDao<Admin>{

	/**
	 * 根据指定字段以及字段的值获取Admin
	 * @param param
	 * @param value
	 * @return
	 */
	public Admin getAdminByName(String param,String value);

	/**
	 * <p>@Description: 根据用户名查询单个用户的信息</p>
	 * <p>@param @param username
	 * <p>@param @return</p>   
	 * <p>@return Admin</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15上午9:58:52</p>
	 */
	Admin findByOnly(String username);

	/**
	 * <p>@Description: 后台管理员列表</p>
	 * <p>@param @param dt</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2017-3-15上午9:59:00</p>
	 */
	void getByPage(DataTables dt);

	
}
