package com.util;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * <p>ClassName: SendMsg_webchinese</p>
 * <p>@Description: 阿里大鱼短信发送</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:09:23</p>
 */
public class SendMsg_Ali {
	private static Logger logger = Logger.getLogger(SendMsg_Ali.class);
	/**
	 * <p>Description : 阿里大鱼发送短信</p>
	 * <p>Author : BianMingZhou</p>
	 * <p>Date : 2016/5/23 17:58</p>
	 *
	 * @param content 短信内容JSONObject[对应短信模板的键值对]
	 * @param phone 发送短信手机号
	 * @param msgCode 短信模板ID
	 * @return String -4号码不正确  >0发送短信数量 -1短信发送异常
     * @throws Exception
     */
	public static String sendMsg(JSONObject content, String phone, String msgCode) throws Exception{

		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest","23706721","68eb8d7d20485ab1c4f928225db87411");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//req.setExtend("");//公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
		req.setSmsType("normal");
		req.setRecNum(phone);
		if(content != null && !StringUtils.isEmpty(content.toString())){
			req.setSmsFreeSignName("四方城");
			req.setSmsParamString(content.toString());
			req.setSmsTemplateCode(msgCode);

			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);

			BizResult result = rsp.getResult();
			String bodystr = rsp.getBody();

			String responseStr = new String(bodystr.getBytes("gbk"));

			if(result != null){
				if(result.getSuccess()){//成功
					return "1";
				}else{
					logger.error(phone+"发送类型:"+msgCode+"短信时，短信发送失败，短信返回："+responseStr);
					return "-1";
				}
			}else{
				//http://open.taobao.com/apitools/errorCodeSearch?spm=0.0.0.0.fNPfev   异常查询
				//短信发送异常
				JSONObject jo=JSONObject.fromObject(responseStr);
				JSONObject json=jo.getJSONObject("error_response");
				if(json.getInt("code")==15&&json.getString("sub_code").equalsIgnoreCase("isv.MOBILE_NUMBER_ILLEGAL")){
					logger.error(phone+"发送类型:"+msgCode+"短信时，手机号"+phone+"格式不正确");
					return "-4";
				}
				//错误原因：触发业务流控限制
				//解决方案：短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
				logger.error(phone+"发送类型:"+msgCode+"短信时，短信发送异常，短信返回："+responseStr);
				return "-1";
			}
		}else{
			logger.error(phone+"发送类型:"+msgCode+"短信时，短信发送类容为null");
			return "-1";
		}
	}

/*	public static String sendMsg(String content, String phoneNumber)
			throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "Cdquanquaner"),
				new NameValuePair("Key", "86b8b7ff4a9492589685"),
				new NameValuePair("smsMob", phoneNumber),
				new NameValuePair("smsText", content) };
		post.setRequestBody(data);


		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:"+statusCode);
		for(Header h : headers)
		{
		System.out.println(h.toString());
		}
		18281863522

		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		logger.info(result);
		//System.out.println(result); //打印返回消息状态

		post.releaseConnection();
		return result;
	}
*/

	public static void main(String[] args) {
		try {
			//String result = SendMsg_webchinese.sendMsg("123456","18328669168",1);
			JSONObject content = new JSONObject();
			content.put("code","半阙残月");
			content.put("product","1230000");
			String result = SendMsg_Ali.sendMsg(content,"18328669168","SMS_1950122");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}