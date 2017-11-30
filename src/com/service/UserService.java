package com.service;


import com.base.action.datatables.DataTables;
import com.entity.project.ProjectUser;
import com.entity.user.User;



public interface UserService {
    /**
     * <p>@Description: 通过手机号码获取对应user</p>
     * <p>@param @param phoneNum
     * <p>@param @return</p>   
     * <p>@return User</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-3-16下午4:38:20</p>
     */
	User getByPhone(String phoneNum);


	void update(User user);

    User getById(Integer userId);

    String getNextCode();

    void create(String phoneNumber, String pwd, ProjectUser projectUser);




    int countUserNumber(String format);


    void getDataTablePage(DataTables dtJson, String nikeName, String phoneNumber);

	
}
