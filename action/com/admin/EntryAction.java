package com.admin;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizCommonService;
import com.util.RequestUtils;
import com.util.pojo.Answer;

/**
 * <p>ClassName: EntryAction</p>
 * <p>@Description: 词条管理Action</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-30上午10:41:09</p>
 */
@Controller("entryAction")
@Scope("prototype")
public class EntryAction extends BaseAction {
    private static final long serialVersionUID = 8891287689839697628L;
    private BizCommonService bizCommonService;
    private int flag;
    
    public String Show(){
        
        return super.SHOW;
    }
    public String AjaxShow(){
        //1巡检 2维修 3部门 4职位 0全部
        flag = RequestUtils.getIntParamter("flag");
        bizCommonService.getEntryDataTablePage(super.getDtJson(),flag);
        return super.AJAXSHOW;
    }
   
    public String AddSave(){
        
       String name = RequestUtils.getStrParamter("name");
        
        flag = RequestUtils.getIntParamter("flag");
       
       bizCommonService.addSaveEntry(name,flag);
       super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"添加成功");
        return super.ANSWER;
    }
   
    public String UpdateSave(){
        int id = RequestUtils.getIntParamter("id");
        
        String name = RequestUtils.getStrParamter("name");
        bizCommonService.updateSaveEntry(id,name);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改成功");
        return super.ANSWER;
    }
    
    public String Delete(){
        String ids = RequestUtils.getStrParamter("ids");
       
        bizCommonService.deleteEntryByIds(ids);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功");
        return super.ANSWER;
    }
    
    
    public BizCommonService getBizCommonService() {
        return bizCommonService;
    }
    @Resource(name="bizCommonService")
    public void setBizCommonService(BizCommonService bizCommonService) {
        this.bizCommonService = bizCommonService;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    
}
