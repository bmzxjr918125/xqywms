package com.admin;

import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizAdminService;
import com.entity.admin.Admin;
import com.exception.BizException;
import com.util.Md5Utils;
import com.util.RequestUtils;
import com.util.pojo.Answer;

/**
 * <p>ClassName: AdminAction</p>
 * <p>@Description: 后台管理员相关操作</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-19下午4:08:43</p>
 */
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction {
	private static final long serialVersionUID = 8513303269448930258L;
	private BizAdminService bizAdminService;
	private Admin admin;
	private int adminId;
	private JSONObject json;
    
	/**
	 * <p>@Description: 跳转至登陆页面</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:09:02</p>
	 */
	public String Login() {
		    
		return "Login";
	}

	/**
	 * <p>@Description: 登陆</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:09:14</p>
	 */
	public String Loginin() {
		
		String username = RequestUtils.getStrParamter("username");
		String password = RequestUtils.getStrParamter("password");
		bizAdminService.adminLogin(request, username, password);
		
		answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"登录成功，跳转中...");
		
		return super.ANSWER;
	}

	/**
	 * <p>@Description:  登出</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:09:23</p>
	 */
	public String Logout() {
		request.getSession().removeAttribute("adminInfo");
		request.getSession().invalidate();// 手动使session失效
		return "Logout";
	}

	/**
	 * <p>@Description: 首页显示</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:09:33</p>
	 */
	public String MenuShow() {
		
	
		
		
		JSONObject jo=bizAdminService.countAmount();
		
		
		request.getSession().setAttribute("numberJo",jo);
		
		return "MenuShow";
	}


	public String Update() {
		int id = RequestUtils.getIntParamter("id");
		admin = bizAdminService.getById(id);
		return "Update";
	}

	/**
	 * <p>@Description: 账号修改跳转</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:09:50</p>
	 */
	public String UpdateSave() {
		String oldPass = RequestUtils.getStrParamter("oldPass");
		String newPass = RequestUtils.getStrParamter("newPass");
		String username = RequestUtils.getStrParamter("admin.username");
		int id = RequestUtils.getIntParamter("adminId");
		admin = bizAdminService.getById(id);
		if(admin == null ){
			throw new BizException("未找到该用户信息");
		}
		
		Admin admin1 = bizAdminService.getByName("username", username);
		if (admin1 != null && admin1.getId() != id) {
			// 用户名已存在
			
			throw new BizException("用户名已存在");
		} 
		if (!admin.getPassword().equals(
					Md5Utils.md5(Md5Utils.md5(oldPass)))) {
				// 原密码输入不正确
				throw new BizException("原密码输入不正确");
		} 
				
		        admin.setPassword(Md5Utils.md5(Md5Utils.md5(newPass)));

				bizAdminService.saveOrUpdate(admin);
				request.getSession().removeAttribute("adminInfo");
				request.getSession().setAttribute("adminInfo", admin);
				
				answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"修改信息成功");
			return super.ANSWER;
	}

	/**
	 * <p>@Description: 跳转到后台管理员列表</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:10:02</p>
	 */
	public String Show() {
		
		return "Show";
	}

	/**
	 * <p>@Description: 后台管理员列表</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-19下午4:10:14</p>
	 */
	public String AjaxShow() {
		bizAdminService.getByPage(super.getDtJson());
		return "AjaxShow";
	}

	/**
     * <p>@Description: 跳转到添加管理员页面</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-19下午4:10:22</p>
     */
    public String Add() {

        return "Add";
    }

    /**
     * <p>@Description: 校验用户名是否重复</p>
     * <p>@param </p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-19下午4:10:30</p>
     */
    public void CheckUnique() {
        PrintWriter out;
        try {
            out = super.response.getWriter();
            out.write(bizAdminService.getOnly(admin.getUsername()) != null ? "false"
                    : "true");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>@Description: 保存新增管理员</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-19下午4:10:38</p>
     */
    public String AddSave() {

        String eachPass = RequestUtils.getStrParamter("eachPass");
        admin.setCreateDate(new Date());

        if (eachPass.equals(admin.getPassword())) {
            admin.setPassword(Md5Utils.md5(Md5Utils.md5(admin.getPassword())));
            admin.setThisLoginDate(new Date());
            admin.setLastLoginDate(new Date());
            admin.setUpdateDate(new Date());
            admin.setCreateDate(new Date());
            admin.setType((byte)1);
            bizAdminService.saveOrUpdate(admin);
            return "AddSave";
        } else {
           
            return "Add";
        }
    }

    /**
     * <p>
     * Description : 跳转到编辑管理员页面
     * </p>
     * <p>
     * Author : XQL
     * </p>
     * <p>
     * Date : 2016/3/24 10:50
     * </p>
     */
    public String Edit() {

        admin = bizAdminService.getById(admin.getId());

       
        return "Edit";
    }

    /**
     * <p>@Description: 保存编辑管理员</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-19下午4:10:48</p>
     */
    public String EditSave() {

         bizAdminService.saveEdit(admin);

            return "EditSave";
        }
	

	public BizAdminService getBizAdminService() {
		return bizAdminService;
	}
    @Resource(name="bizAdminService")
	public void setBizAdminService(BizAdminService bizAdminService) {
		this.bizAdminService = bizAdminService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}
}
