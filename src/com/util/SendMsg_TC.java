package com.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import com.exception.BizException;
import com.qcloud.sms.SmsSingleSender;
import com.qcloud.sms.SmsSingleSenderResult;

/**
 * <p>ClassName: SendMsg_TC</p>
 * <p>@Description: 腾讯短信发送</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-25上午11:08:01</p>
 */
public class SendMsg_TC {
	private static Logger logger = Logger.getLogger(SendMsg_TC.class);
	private static final int appid = 1400039391;
    private static final String appkey = "a831a610ebb6c45d96e1b9026dffd91f";
    public  enum TempEmun{
        REGISTER_CODE("用户注册短信验证码",40314),
        FORGET_CODE("用户修改密码短信验证码",40315);
        public String tempName;
        public int tempId;
        private TempEmun(String tempName, int tempId) {
            this.tempName = tempName;
            this.tempId = tempId;
        }

        public String getTempName() {
            return tempName;
        }

        public void setTempName(String tempName) {
            this.tempName = tempName;
        }

        public int getTempId() {
            return tempId;
        }

        public void setTempId(int tempId) {
            this.tempId = tempId;
        }
    }
    /**
     * <p>@Description: TODO</p>
     * <p>@param @param phoneNumber
     * <p>@param @param tempEmun
     * <p>@param @param paramsList
     * <p>@param @return</p>   
     * <p>@return int //0表示成功(计费依据)，非0表示失败</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-8-25下午12:30:24</p>
     */
    public static int sendMsg(String phoneNumber,TempEmun tempEmun,ArrayList<String> paramsList) throws Exception{
        int resultCode=0;
        
            //请根据实际 appid 和 appkey 进行开发，以下只作为演示 sdk 使用
            //appid,appkey,templId申请方式可参考接入指南 https://www.qcloud.com/document/product/382/3785#5-.E7.9F.AD.E4.BF.A1.E5.86.85.E5.AE.B9.E9.85.8D.E7.BD.AE
            //初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult singleSenderResult;
           
          
            singleSenderResult = singleSender.sendWithParam("86", phoneNumber, tempEmun.getTempId(), paramsList, "", "", "");
          
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
           logger.info("发送短信"+sdf.format(new Date())+":"+singleSenderResult);
           
           resultCode = singleSenderResult.result;
            if(resultCode != 0){
                if(resultCode == 1016){
                    throw new BizException("手机号格式错误");
                }else{
                    throw new BizException("短信发送失败 code:"+resultCode);
                }
            }
           
        
        return resultCode;
    }
    
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("158000");
        list.add("123456");
        try {
            sendMsg("15802861658",TempEmun.REGISTER_CODE,list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}