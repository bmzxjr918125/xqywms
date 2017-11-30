package com.admin;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizProjectService;
import com.bizservice.BizUserService;
import com.util.RequestUtils;
/**
 * <p>ClassName: UserAction</p>
 * <p>@Description: 用户</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午12:38:03</p>
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

    private static final long serialVersionUID = 1596884049551146064L;
    
    private BizUserService bizUserService;
    private BizProjectService bizProjectService;
    
    public String Show(){
        
        return super.SHOW;
    }
    
    public String AjaxShow(){
        String nickName = RequestUtils.getStrParamter("nickName");
        String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
        
        bizUserService.getDataTablePage(super.getDtJson(),nickName,phoneNumber);
        
        
        return super.AJAXSHOW;
    }
    
    /**
     * <p>@Description: 查看下属成员</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-30下午7:17:37</p>
     */
    public String UnderAjaxShow(){
        int projectId = RequestUtils.getIntParamter("projectId");
        bizProjectService.getUnderUserDataTablePage(super.getDtJson(),projectId);
        
        return super.AJAXSHOW;
    }
   
    public BizUserService getBizUserService() {
        return bizUserService;
    }
    @Resource(name = "bizUserService")
    public void setBizUserService(BizUserService bizUserService) {
        this.bizUserService = bizUserService;
    }

    public BizProjectService getBizProjectService() {
        return bizProjectService;
    }
    @Resource(name="bizProjectService")
    public void setBizProjectService(BizProjectService bizProjectService) {
        this.bizProjectService = bizProjectService;
    }
}
