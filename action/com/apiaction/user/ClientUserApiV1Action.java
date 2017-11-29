package com.apiaction.user;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.apiaction.base.ClientBaseApiAction;
import com.entity.project.ProjectUser;
import com.entity.user.User;
import com.exception.BizException;
import com.util.RequestUtils;
import com.util.SendMsg_TC;
import com.util.SendMsg_TC.TempEmun;
import com.util.pojo.Answer;
/**
 * <p>ClassName: ClientUserApiV1Action</p>
 * <p>@Description: 客户端 用户 接口 v1版本</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-22下午3:29:28</p>
 */
@Controller("clientUserApiV1Action")
@Scope("prototype")
public class ClientUserApiV1Action extends ClientBaseApiAction{
    private static final long serialVersionUID = 1900173877908743200L;
   // private Logger logger = Logger.getLogger(ClientUserApiV1Action.class);
	
	public String Login(){
		String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
	    String pwd = RequestUtils.getStrParamter("pwd");
	    JSONObject json = super.bizUserService.login(super.request,phoneNumber,pwd);

	    super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"登录成功",json);
		
		return super.ANSWER;
	}
	
	
	public String GetUserInfo(){
	    User user = (User) super.request.getSession().getAttribute("userInfo");
	    
	    JSONObject jo =  bizUserService.getUserInfo(user.getId());
	    super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功",jo);
	    return super.ANSWER;
	}
	
	
	public String GetNewList(){
	    int currentNum=RequestUtils.getIntParamter("currentNum");
        //分页显示条数
        int perPageNum=RequestUtils.getIntParamter("perPageNum");
	    JSONObject jo =  bizCommonService.getNewList(currentNum,perPageNum);
        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功",jo);
        return super.ANSWER;
	}
	  public String GetNewInfo(){
	      int newId=RequestUtils.getIntParamter("newId");
	        JSONObject jo =  bizCommonService.getNewById(newId);
	        super.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"请求成功",jo);
	        return super.ANSWER;
	    }
	public String GetRegisterCode() throws Exception{
	    String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
	    // 发送验证码
        int code = (int) ((Math.random() * (999999 - 100000) + 100000));
        //System.out.println(code);
        
        ArrayList<String> params = new ArrayList<String>();
        params.add(code+"");
        
        SendMsg_TC.sendMsg(phoneNumber,TempEmun.REGISTER_CODE,params);
        //String smsResult="1";
            //存入 session
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(100);//100秒过期
            session.setAttribute("phoneCode", code+"");
            
            HttpSession session1 = request.getSession();
            session1.setMaxInactiveInterval(100);//100秒过期
            session1.setAttribute("phoneNum", phoneNumber);
            
            this.answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"验证码发送成功");
       
	    return super.ANSWER;
	}
	
	public String GetForgetCode()  throws Exception{
        String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
        
        //验证手机号是否注册
        bizUserService.isRegister(phoneNumber);
        
        //发送验证码
        int code = (int) ((Math.random() * (999999 - 100000) + 100000));
        System.out.println(code);
        ArrayList<String> params = new ArrayList<String>();
        params.add(phoneNumber);
        params.add(code+"");
        SendMsg_TC.sendMsg(phoneNumber,TempEmun.FORGET_CODE,params);
            //存入 session
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(100);//100秒过期
            session.setAttribute("phoneCode", code+"");
            
            HttpSession session1 = request.getSession();
            session1.setMaxInactiveInterval(100);//100秒过期
            session1.setAttribute("phoneNum", phoneNumber);
            
            this.answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"验证码发送成功");
        
        return super.ANSWER;
    }
	
	
	public String Register(){
	    String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
	    String phoneCode = RequestUtils.getStrParamter("phoneCode");
	    String pwd = RequestUtils.getStrParamter("pwd");
	    
	    ProjectUser projectUser = null;
	    
	    String sessionPhoneCode = (String) request.getSession().getAttribute("phoneCode");
	    String sessionphoneNum = (String) request.getSession().getAttribute("phoneNum");
	    
	    if(sessionPhoneCode == null){
	        throw new BizException("验证码失效，请重新获取");
	    }
	    if(phoneCode == null || "".equals(phoneCode.trim())){
	        throw new BizException("请输入短信验证码");
	    }
	    if(!sessionPhoneCode.equals(phoneCode)){
	        throw new BizException("短信验证码不正确");
	    }
	    if(!sessionphoneNum.equals(phoneNumber)){
	        throw new BizException("短息验证与手机号码不符");
	    }
	    
	    bizUserService.register(phoneNumber,pwd,projectUser);
	    
	    this.answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"注册用户成功");
	    return super.ANSWER;
	}
	public String ValiForGetCode(){
	    String phoneNumber = RequestUtils.getStrParamter("phoneNumber");
	    String phoneCode = RequestUtils.getStrParamter("phoneCode");
	    
	    String sessionPhoneCode = (String) request.getSession().getAttribute("phoneCode");
	    String sessionphoneNum = (String) request.getSession().getAttribute("phoneNum");
	    
	    if(sessionPhoneCode == null){
	        throw new BizException("验证码失效，请重新获取");
	    }
	    if(phoneCode == null || "".equals(phoneCode.trim())){
	        throw new BizException("请输入短信验证码");
	    }
	    if(!sessionPhoneCode.equals(phoneCode)){
	        throw new BizException("短信验证码不正确");
	    }
	    if(!sessionphoneNum.equals(phoneNumber)){
	        throw new BizException("短息验证与手机号码不符");
	    }
	    
	    
        HttpSession session1 = request.getSession();
        session1.setMaxInactiveInterval(600);//600秒过期
        session1.setAttribute("phoneNum", phoneNumber);
	    
	    this.answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"验证成功");
	    return super.ANSWER;
	}
	
	public String UpdatePwd(){
	    String newPwd = RequestUtils.getStrParamter("newPwd");
        String newPwdAgin = RequestUtils.getStrParamter("newPwdAgin");
	    
        String sessionphoneNum = (String) request.getSession().getAttribute("phoneNum");
        
        if(sessionphoneNum == null){
            throw new BizException("重置密码账号验证已过期，请重新验证");
        }
        
        bizUserService.updatePwd(newPwd,newPwdAgin,sessionphoneNum);
        
        this.answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改密码成功");
        
	    return super.ANSWER;
	}
	
	
	public String UpdateUserInfo(){
	    User user = (User) super.request.getSession().getAttribute("userInfo");
	    String nickName = RequestUtils.getStrParamter("nickName");
	    int sex = RequestUtils.getIntParamter("sex");
	    String profession = RequestUtils.getStrParamter("profession");
	    String province = RequestUtils.getStrParamter("province");
	    String city = RequestUtils.getStrParamter("city");
	    String area = RequestUtils.getStrParamter("area");
	    String address = RequestUtils.getStrParamter("address");
	    
	    bizUserService.updateUser(user,nickName,sex,profession,province,city,area,address);
	    
	    this.answer = new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改个人信息成功");
	    
	    return super.ANSWER;
	}
	
	
	
	
	

	
	
	
	
}
