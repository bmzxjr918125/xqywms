package com.admin;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizProjectService;
import com.bizservice.BizWorkerService;
import com.util.RequestUtils;
import com.util.pojo.Answer;
/**
 * <p>ClassName: ProjectAction</p>
 * <p>@Description: 项目管理</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-12-5下午6:03:07</p>
 */
@Controller("projectAction")
@Scope("prototype")
public class ProjectAction extends BaseAction {
    private static final long serialVersionUID = 967352557365820255L;
    private BizWorkerService bizWorkerService;
    private BizProjectService bizProjectService;
    private int projectId;
    
    public String Show(){
       
        
        return super.SHOW;
    }
    
    public String AjaxShow(){
        
        bizProjectService.getDataTablePage(super.getDtJson());
       
        return super.AJAXSHOW;
    }
    
    
   
    public String AddSave() throws Exception{
        int projectType = RequestUtils.getIntParamter("projectType");
        int isChargeRepair = RequestUtils.getIntParamter("isChargeRepair");
        int checkType = RequestUtils.getIntParamter("checkType");
        int provinceId = RequestUtils.getIntParamter("provinceId");
        int cityId = RequestUtils.getIntParamter("cityId");
        int areaId = RequestUtils.getIntParamter("areaId");
        String projectName = RequestUtils.getStrParamter("projectName");
        String repairDateStart = RequestUtils.getStrParamter("repairDateStart");
        String repairDateEnd = RequestUtils.getStrParamter("repairDateEnd");
        String userPhoneNumber = RequestUtils.getStrParamter("userPhoneNumber");
        String address = RequestUtils.getStrParamter("address");
        String description = RequestUtils.getStrParamter("description");
        
        bizProjectService.addSave(projectType,isChargeRepair,checkType,provinceId,cityId,areaId
                ,projectName,repairDateStart,repairDateEnd,userPhoneNumber,address,description);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加项目信息成功，请在项目列表中继续完成项目设备以及团队添加。");
        return super.ANSWER;
    }
    public String UpdateSave() throws Exception{
        int projectId = RequestUtils.getIntParamter("projectId");
        int projectType = RequestUtils.getIntParamter("projectType");
        int isChargeRepair = RequestUtils.getIntParamter("isChargeRepair");
        int checkType = RequestUtils.getIntParamter("checkType");
        int provinceId = RequestUtils.getIntParamter("provinceId");
        int cityId = RequestUtils.getIntParamter("cityId");
        int areaId = RequestUtils.getIntParamter("areaId");
        String projectName = RequestUtils.getStrParamter("projectName");
        String repairDateStart = RequestUtils.getStrParamter("repairDateStart");
        String repairDateEnd = RequestUtils.getStrParamter("repairDateEnd");
        String address = RequestUtils.getStrParamter("address");
        String description = RequestUtils.getStrParamter("description");
        
        bizProjectService.updateSave(projectId,projectType,isChargeRepair,checkType,provinceId,cityId,areaId
                ,projectName,repairDateStart,repairDateEnd,address,description);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改项目信息成功。");
        return super.ANSWER;
    }

    public String Delete(){
        String ids = RequestUtils.getStrParamter("ids");
        bizProjectService.deletes(ids);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"删除项目信息成功。");
        return super.ANSWER;
    }
    
    
    public BizProjectService getBizProjectService() {
        return bizProjectService;
    }
    @Resource(name="bizProjectService")
    public void setBizProjectService(BizProjectService bizProjectService) {
        this.bizProjectService = bizProjectService;
    }

    public BizWorkerService getBizWorkerService() {
        return bizWorkerService;
    }
    @Resource(name="bizWorkerService")
    public void setBizWorkerService(BizWorkerService bizWorkerService) {
        this.bizWorkerService = bizWorkerService;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
