package com.common;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizCommonService;
import com.util.RequestUtils;
import com.util.pojo.Answer;

/**
 * <p>ClassName: CommonAction</p>
 * <p>@Description:公共action</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-12-7下午8:03:03</p>
 */
@Controller("commonAction")
@Scope("prototype")
public class CommonAction extends BaseAction {
    private static final long serialVersionUID = -5015548968944622531L;
    private BizCommonService bizCommonService;
    
    public String GetProvinceList(){
        JSONArray ja = bizCommonService.getProvinceList();
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功。",ja);
        return super.ANSWER;
    }
    public String GetCityList(){
        int provinceId = RequestUtils.getIntParamter("provinceId");
        JSONArray ja = bizCommonService.getCityList(provinceId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功。",ja);
        return super.ANSWER;
    }
    public String GetAreaList(){
        int cityId = RequestUtils.getIntParamter("cityId");
        JSONArray ja = bizCommonService.getAreaList(cityId);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功。",ja);
        return super.ANSWER;
    }
    
    
    public BizCommonService getBizCommonService() {
        return bizCommonService;
    }
    @Resource(name="bizCommonService")
    public void setBizCommonService(BizCommonService bizCommonService) {
        this.bizCommonService = bizCommonService;
    }
}
