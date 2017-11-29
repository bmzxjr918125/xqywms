package com.admin;

import javax.annotation.Resource;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizUserService;
/**
 * <p>ClassName: UserlAction</p>
 * <p>@Description: 用户</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午12:38:03</p>
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

    private static final long serialVersionUID = 1596884049551146064L;
    
    private BizUserService bizUserService;
    
   
    public BizUserService getBizUserService() {
        return bizUserService;
    }
    @Resource(name = "bizUserService")
    public void setBizUserService(BizUserService bizUserService) {
        this.bizUserService = bizUserService;
    }
}
