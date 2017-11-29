package com.bizserviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizAdminService;
import com.entity.admin.Admin;
import com.exception.BizException;
import com.service.AdminService;
import com.service.TradeService;
import com.service.UserService;
import com.util.MathUtil;
import com.util.Md5Utils;

@Service("bizAdminService")
public class BizAdminServiceImpl implements BizAdminService{
    private AdminService adminService;
    private TradeService tradeService;
    private UserService userService;    
   /**
    * 系统管理员登录
    */
	public void adminLogin(HttpServletRequest request,String username, String password) {
		
		
		
		Admin admin = adminService.getAdminByName("username", username);
		if (null == admin || "".equals(admin) ) {
			throw new BizException("没有此用户信息,请重新输入");
		}
		
		if (!admin.getUsername().equals(username) || !admin.getPassword().equals(password)) {
			throw new BizException("用户名或密码错误");
		}
		
		Date lastTime = admin.getThisLoginDate();
		Date newdate = new Date();
			
		if (request.getSession().getAttribute("adminInfo") != null) {
			
			request.getSession().removeAttribute("adminInfo");
		}
		
		admin.setLastLoginDate(lastTime);
		admin.setThisLoginDate(newdate);
		adminService.saveOrUpdate(admin);
		request.getSession().setAttribute("adminInfo", admin);
	}
	
	
	
	/**
	 * 添加店员信息
	 */
	public void addSaveEmpl(String phoneNum, String roleName) {
		if(phoneNum == null || phoneNum.trim().equals("")){
			throw new BizException("请填写手机号");
		}
		if(roleName == null || roleName.trim().equals("")){
			throw new BizException("请填写店员名称");
		}
		
		if(phoneNum.length() != 11){
			throw new BizException("请正确填写11位手机号");
		}
		
		if(getOnly(phoneNum) != null ? true : false){
             throw new BizException("该手机号码已被使用");			
		}
		
		Admin admin=new Admin();
		admin.setCreateDate(new Date());
		admin.setUsername(phoneNum);
		admin.setRole_name(roleName);
		admin.setPassword(MathUtil.createDefaultPwd(phoneNum));
		admin.setType((byte)1);
		admin.setLastLoginDate(new Date());
		admin.setThisLoginDate(new Date());
		adminService.saveOrUpdate(admin);
	}


    /**
     * 修改店员信息
     */
	public void updateSaveEmpl(String roleName,
			String password, int adminId) {
	     Admin admin=adminService.getById(adminId);
		 if(admin == null){
			 throw new BizException("未找到该店员信息");
		 }
		if(roleName == null || roleName.trim().equals("")){
			throw new BizException("请填写店员名称");
		}
		if(password != null && !password.trim().equals("") && password.trim().length()<6){
			throw new BizException("密码请至少填写六位");
		}
		if(password != null ){
			
			admin.setPassword(Md5Utils.md5(Md5Utils.md5(password)));
		}
		admin.setRole_name(roleName);
		admin.setUpdateDate(new Date());
		
		adminService.saveOrUpdate(admin);
	}


    /**
     * 计算admin首页所需各个数据
     */
	public JSONObject countAmount() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject jo=new JSONObject();
		int allNumber = userService.countUserNumber(null);
		int dayNumber = userService.countUserNumber(sdf.format(new Date()));
		
		jo.put("allNumber",allNumber);
		jo.put("dayNumber",dayNumber);
		return jo;
	}
	
	
	public Admin getById(int id) {
		return adminService.getById(id);
	}
	public Admin getByName(String fieldName, String username) {
		return adminService.getAdminByName(fieldName, username);
	}
	public void saveOrUpdate(Admin l_Admin) {
		adminService.saveOrUpdate(l_Admin);
	}
	
	public void getByPage(DataTables dtJson) {
		adminService.getByPage(dtJson);
	}
    public Object getOnly(String username) {
		
		return adminService.getOnly(username);
	}
	
    public boolean saveEdit(Admin admin) {
		
		return adminService.saveEdit(admin);
	}
	



	public void getTradeByPage(DataTables dtJson, int adminId) {
		tradeService.getTradeByPage(dtJson,adminId);
	}
    

	public JSONObject countAmountByAdminId(int adminId) {
		JSONObject jo=new JSONObject();
		
		double dAmount=tradeService.countDayByAdminId("amount",adminId);
		double dPayAmount=tradeService.countDayByAdminId("payAmount",adminId);
		double dUseIntegral=tradeService.countDayByAdminId("useIntegral",adminId);
		
		double mAmount=tradeService.countMonthByAdminId("amount",adminId);
		double mPayAmount=tradeService.countMonthByAdminId("payAmount",adminId);
		double mUseIntegral=tradeService.countMonthByAdminId("useIntegral",adminId);
		
		double aAmount=tradeService.countAllByAdminId("amount",adminId);
		double aPayAmount=tradeService.countAllByAdminId("payAmount",adminId);
		double aUseIntegral=tradeService.countAllByAdminId("useIntegral",adminId);
		
		jo.put("dAmount", MathUtil.showPriceByFD(dAmount, 0, 1, 1));
		jo.put("dPayAmount",MathUtil.showPriceByFD(dPayAmount, 0, 1, 1) );
		jo.put("dUseIntegral",MathUtil.showPriceByFD(dUseIntegral, 1, 1, 1) );

		jo.put("mAmount", MathUtil.showPriceByFD(mAmount, 0, 1, 1));
		jo.put("mPayAmount",MathUtil.showPriceByFD(mPayAmount, 0, 1, 1) );
		jo.put("mUseIntegral",MathUtil.showPriceByFD(mUseIntegral, 1, 1, 1) );
		
		jo.put("aAmount",MathUtil.showPriceByFD(aAmount, 0, 1, 1) );
		jo.put("aPayAmount", MathUtil.showPriceByFD(aPayAmount, 0, 1, 1));
		jo.put("aUseIntegral", MathUtil.showPriceByFD(aUseIntegral, 1, 1, 1));
		
		return jo;
	}
    
    
	public AdminService getAdminService() {
		return adminService;
	}
	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}



	public TradeService getTradeService() {
		return tradeService;
	}
    @Resource(name="tradeService")
	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}
    public UserService getUserService() {
        return userService;
    }
    @Resource(name="userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
