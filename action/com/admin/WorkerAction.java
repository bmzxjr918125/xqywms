package com.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizProjectService;
import com.bizservice.BizWorkerService;
import com.entity.common.Entry;
import com.entity.enumobj.EntryType;
import com.util.RequestUtils;
import com.util.pojo.Answer;
/**
 * <p>ClassName: WorkerAction</p>
 * <p>@Description: 人员管理</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-12-1上午10:54:20</p>
 */
@Controller("workerAction")
@Scope("prototype")
public class WorkerAction extends BaseAction {
    private static final long serialVersionUID = -467663480988412472L;
    private BizWorkerService bizWorkerService;
    private BizProjectService bizProjectService;
    private List<Entry> departList = new ArrayList<Entry>();
    private List<Entry> jobList = new ArrayList<Entry>();
    
    private JSONArray departJa ;
    private JSONArray jobJa ;
    
    public String Show(){
        departList  =  bizCommonService.getEntryListByType(EntryType.DEPART);
        jobList = bizCommonService.getEntryListByType(EntryType.JOB);
        
        departJa = JSONArray.fromObject(departList);
        jobJa = JSONArray.fromObject(jobList);
        
        return super.SHOW;
    }
    
    public String AjaxShow(){
        String nickName = RequestUtils.getStrParamter("nickName");
        String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
        int status = RequestUtils.getIntParamter("status");
        
        bizWorkerService.getDataTablePage(super.getDtJson(),nickName,phoneNumber,status);
       
        return super.AJAXSHOW;
    }
   
    /**
     * <p>@Description: 冻结或启用账户</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-1下午3:13:56</p>
     */
    public String ChangeStatus(){
        int workerId = RequestUtils.getIntParamter("workerId");
        int flag = RequestUtils.getIntParamter("flag");
        
        bizWorkerService.changeStatus(workerId,flag);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"操作成功");
        return super.ANSWER;
    }
    /**
     * <p>@Description: 重置用户密码为默认手机后六位</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-12-1下午3:14:21</p>
     */
    public String UpdateDefaultPwd(){
        int workerId = RequestUtils.getIntParamter("workerId");
        
        bizWorkerService.updateDefaultPwd(workerId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"密码重置成功");
        return super.ANSWER;
    }
   
    public String AddSave(){
        String nickName = RequestUtils.getStrParamter("nickName");
        String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
        String department = RequestUtils.getStrParamter("department");
        String job = RequestUtils.getStrParamter("job");
        
        bizWorkerService.register(nickName, phoneNumber, department, job);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加人员信息成功，初始密码为手机后六位。");
        return super.ANSWER;
    }
    public String UpdateSave(){
        int workerId = RequestUtils.getIntParamter("workerId");
        String nickName = RequestUtils.getStrParamter("nickName");
        String department = RequestUtils.getStrParamter("department");
        String job = RequestUtils.getStrParamter("job");
        
        bizWorkerService.updateSave(workerId, nickName ,department, job);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改人员信息成功。");
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

    public List<Entry> getDepartList() {
        return departList;
    }

    public void setDepartList(List<Entry> departList) {
        this.departList = departList;
    }

    public List<Entry> getJobList() {
        return jobList;
    }

    public void setJobList(List<Entry> jobList) {
        this.jobList = jobList;
    }

    public JSONArray getDepartJa() {
        return departJa;
    }

    public void setDepartJa(JSONArray departJa) {
        this.departJa = departJa;
    }

    public JSONArray getJobJa() {
        return jobJa;
    }

    public void setJobJa(JSONArray jobJa) {
        this.jobJa = jobJa;
    }

   
}
