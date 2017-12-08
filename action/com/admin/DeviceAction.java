package com.admin;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizDeviceService;
import com.bizservice.BizProjectService;
import com.bizservice.BizWorkerService;
import com.entity.admin.Admin;
import com.entity.device.DeviceCard;
import com.entity.enumobj.EntryType;
import com.util.RequestUtils;
import com.util.pojo.Answer;
/**
 * <p>ClassName: DeviceAction</p>
 * <p>@Description: 设备管理</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-12-4下午2:35:19</p>
 */
@Controller("deviceAction")
@Scope("prototype")
public class DeviceAction extends BaseAction {
    private static final long serialVersionUID = -4094444893257295747L;
    private BizWorkerService bizWorkerService;
    private BizProjectService bizProjectService;
    
    private BizDeviceService bizDeviceService;
    
    private JSONArray entryTypeJa;
    
    public String CardShow(){
        entryTypeJa = JSONArray.fromObject(bizCommonService.getEntryListByType(EntryType.DEVICETYPE));
        
        return "CardShow";
    }
    
    public String CardAjaxShow(){
        
        bizDeviceService.getCardDataTablePage(super.getDtJson());
       
        return super.AJAXSHOW;
    }
   
    public String CardAddSave(){
        String deviceName = RequestUtils.getStrParamter("deviceName");
        String deviceType = RequestUtils.getStrParamter("deviceType");
        String supplier = RequestUtils.getStrParamter("supplier");
        String modeNum = RequestUtils.getStrParamter("modeNum");
        String input = RequestUtils.getStrParamter("input");
        String output = RequestUtils.getStrParamter("output");
        String cryogenType = RequestUtils.getStrParamter("cryogenType");
        String charge = RequestUtils.getStrParamter("charge");
        String description = RequestUtils.getStrParamter("description");
        
        bizDeviceService.addSaveDeviceCar(deviceName,deviceType,supplier,modeNum,input,output,
                cryogenType,charge,description);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加设备名片信息成功。");
        return super.ANSWER;
    }
    public String CardUpdateSave(){
        int deviceCardId = RequestUtils.getIntParamter("deviceCardId");
        String deviceName = RequestUtils.getStrParamter("deviceName");
        String deviceType = RequestUtils.getStrParamter("deviceType");
        String supplier = RequestUtils.getStrParamter("supplier");
        String modeNum = RequestUtils.getStrParamter("modeNum");
        String input = RequestUtils.getStrParamter("input");
        String output = RequestUtils.getStrParamter("output");
        String cryogenType = RequestUtils.getStrParamter("cryogenType");
        String charge = RequestUtils.getStrParamter("charge");
        String description = RequestUtils.getStrParamter("description");
        
        bizDeviceService.updateSaveDeviceCar(deviceCardId,deviceName,deviceType,supplier,modeNum,input,output,
                cryogenType,charge,description);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改设备名片信息成功。");
        return super.ANSWER;
        
    }
    
    public String CardDelete(){
        String ids = RequestUtils.getStrParamter("ids");
        
        bizDeviceService.deletesCard(ids);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"删除成功。");
        
        return super.ANSWER;
    }
    
    public String Show(){
        entryTypeJa = JSONArray.fromObject(bizCommonService.getEntryListByType(EntryType.DEVICETYPE));
        
        return super.SHOW;
    }
    
    public String AjaxShow(){
        bizDeviceService.getDataTablePage(super.getDtJson());
        return super.AJAXSHOW;
    }

    public String GetCardNameAndIdList(){
       JSONArray ja = bizDeviceService.getCardNameAndIdList();
       super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功",ja);
       
       return super.ANSWER;
    }
    
    public String GetCardById(){
        int cardId = RequestUtils.getIntParamter("id");
        DeviceCard deviceCard = bizDeviceService.getById(cardId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功",JSONObject.fromObject(deviceCard));
        return super.ANSWER;
    }
    
    public String AddSave(){
        
        int deviceCardId = RequestUtils.getIntParamter("deviceCardId");
        String deviceNo = RequestUtils.getStrParamter("deviceNo");
        String productionDate = RequestUtils.getStrParamter("productionDate");
        String buyDate = RequestUtils.getStrParamter("buyDate");
        String otherDesc = RequestUtils.getStrParamter("otherDesc");
        Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
        
        bizDeviceService.addSaveDevice(deviceCardId,deviceNo,productionDate,buyDate,otherDesc,admin);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加设备信息成功");
        return super.ANSWER;
    }
    
    public String UpdateSave(){
        int flag = RequestUtils.getIntParamter("flag");
        int deviceId = RequestUtils.getIntParamter("deviceId");
        int deviceCardId = RequestUtils.getIntParamter("deviceCardId");
        String deviceNo = RequestUtils.getStrParamter("deviceNo");
        String productionDate = RequestUtils.getStrParamter("productionDate");
        String buyDate = RequestUtils.getStrParamter("buyDate");
        String otherDesc = RequestUtils.getStrParamter("otherDesc");
        Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
        
        bizDeviceService.updateSaveDevice(deviceCardId,deviceNo,productionDate,buyDate,otherDesc,admin,flag,deviceId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,(flag == 1 ? "修改" : "复制添加")+ "设备信息成功");
        return super.ANSWER;
    }
    
    public String Delete(){
        String ids = RequestUtils.getStrParamter("ids");
        
        bizDeviceService.deletes(ids);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"删除成功。");
        
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
    public BizDeviceService getBizDeviceService() {
        return bizDeviceService;
    }
    @Resource(name="bizDeviceService")
    public void setBizDeviceService(BizDeviceService bizDeviceService) {
        this.bizDeviceService = bizDeviceService;
    }

    public JSONArray getEntryTypeJa() {
        return entryTypeJa;
    }

    public void setEntryTypeJa(JSONArray entryTypeJa) {
        this.entryTypeJa = entryTypeJa;
    }
}
