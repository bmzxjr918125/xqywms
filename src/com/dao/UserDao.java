package com.dao;


import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.user.User;


public interface UserDao extends BaseDao<User>{
    /**
     * <p>@Description: 通过手机号获取对应user</p>
     * <p>@param @param phoneNum
     * <p>@param @return</p>   
     * <p>@return User</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-3-16下午4:39:11</p>
     */
	User getByPhone(String phoneNum);
  
    String getNextCode();
    int countUserNumber(String formatDate);

    void getDataTablePage(DataTables dtJson, String nikeName, String phoneNumber);

}
