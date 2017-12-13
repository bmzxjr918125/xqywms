package com.admin;


import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizProjectService;
import com.bizservice.BizWorkerService;
import com.entity.enumobj.EntryType;
import com.entity.project.Project;
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
    private Project project;
    private JSONArray entryTypeJa;
    
    //=========================项目=================
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
    
    
    
    //=========================设备=================
    
    public String DeviceShow(){
        projectId = RequestUtils.getIntParamter("projectId");
        project= bizProjectService.getById(projectId);
        entryTypeJa = JSONArray.fromObject(bizCommonService.getEntryListByType(EntryType.CHECK));
        return "DeviceShow";
    }
    public String DeviceAjaxShow(){
        projectId = RequestUtils.getIntParamter("projectId");
        bizProjectService.getProjectDeviceDataTablePage(super.getDtJson(),projectId);
        return super.AJAXSHOW;
    }
    public String DeviceAddSave(){
        projectId = RequestUtils.getIntParamter("projectId");
        int deviceId = RequestUtils.getIntParamter("deviceId");
        String entryIds = RequestUtils.getStrParamter("entryIds");
        String installWorker = RequestUtils.getStrParamter("installWorker");
        String position = RequestUtils.getStrParamter("position");
        
        bizProjectService.addSaveDevice(projectId,deviceId,entryIds,installWorker,position);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"设备加入成功。");
        
        return super.ANSWER;
    }
    public String DeviceUpdateSave(){
        int projectDeviceId = RequestUtils.getIntParamter("projectDeviceId");
        int deviceId = RequestUtils.getIntParamter("deviceId");
        String entryIds = RequestUtils.getStrParamter("entryIds");
        String installWorker = RequestUtils.getStrParamter("installWorker");
        String position = RequestUtils.getStrParamter("position");
        
        bizProjectService.updateSaveDevice(projectDeviceId,deviceId,entryIds,installWorker,position);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"设备编辑成功。");
        
        return super.ANSWER;
    }
    public String DeviceDelete(){
        String ids = RequestUtils.getStrParamter("ids");
        bizProjectService.deletesDevice(ids);
        super.answer  = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"移除设备成功。");
        return super.ANSWER;
    }
    
    
    //=========================巡检=================
    private int projectDeviceId;
    public String DeviceCheckShow(){
        projectDeviceId = RequestUtils.getIntParamter("projectDeviceId");
        return "DeviceCheckShow";
    }
    
    public String CheckDetailsAjaxShow(){
        int projectDeviceId = RequestUtils.getIntParamter("projectDeivceId");
        bizProjectService.getCheckDetailsDataTablePage(super.getDtJson(),projectDeviceId);
        
        return super.AJAXSHOW;
    }
    
    //==========================团队================
    public String WorkerShow(){
        
        projectId = RequestUtils.getIntParamter("projectId");
        return "WorkerShow";
    }
    public String WorkerAjaxShow(){
        projectId = RequestUtils.getIntParamter("projectId");
        int workerType = RequestUtils.getIntParamter("workerType");
        bizProjectService.getWorkerDataTablePage(super.getDtJson(),projectId,workerType);
        
        return super.AJAXSHOW;
    }
    public String GetCanAddToProjectWorker(){
        int workerType = RequestUtils.getIntParamter("workerType");
        int projectId = RequestUtils.getIntParamter("projectId");
        JSONObject jo = bizProjectService.getCanAddToProjectWorker(projectId,workerType);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功。",jo);
        return super.ANSWER;
    }
    
    public String WorkerAddSave(){
        int workerType = RequestUtils.getIntParamter("workerType");
        int projectId = RequestUtils.getIntParamter("projectId");
        int workerId = RequestUtils.getIntParamter("workerId");
        int projectDeviceId = RequestUtils.getIntParamter("projectDeviceId");
        bizProjectService.addSaveWorker(projectId,workerType,workerId,projectDeviceId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加成功。");
        return super.ANSWER;
    }
    public String WorkerDelete(){
        int projectWorkerId = RequestUtils.getIntParamter("projectWorkerId");
        bizProjectService.deleteWorker(projectWorkerId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"移除成功。");
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public JSONArray getEntryTypeJa() {
        return entryTypeJa;
    }

    public void setEntryTypeJa(JSONArray entryTypeJa) {
        this.entryTypeJa = entryTypeJa;
    }

    public int getProjectDeviceId() {
        return projectDeviceId;
    }

    public void setProjectDeviceId(int projectDeviceId) {
        this.projectDeviceId = projectDeviceId;
    }
}
