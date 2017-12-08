package com.bizserviceimpl;


import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizUserService;
import com.entity.user.User;
import com.entity.vo.AddressVo;
import com.exception.BizException;
import com.service.ImageService;
import com.service.ProjectService;
import com.service.ProjectUserService;
import com.service.UserService;
import com.util.Md5Utils;


@Service("bizUserService")
public class BizUserServiceImpl implements BizUserService{
    private UserService userService;
    private ImageService imageService;
    private ProjectService projectService;
    private ProjectUserService projectUserService;
   
    
  
    
    public JSONObject login(HttpServletRequest request,String phoneNumber, String pwd) {
        
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new BizException("请填写登录用户手机号码");
        }
        if(pwd == null || "".equals(pwd.trim())){
            throw new BizException("请填写登录用户密码");
        }
        
        User user = userService.getByPhone(phoneNumber);
        if(user == null){
            throw new BizException("未找到该用户，请先注册!");
        }
        
        if(!user.getPwd().equals(Md5Utils.md5ForPwd(pwd))){
            
            throw new BizException("登录密码错误");
        }
        user.login();
        userService.update(user);
        
      if(request.getSession().getAttribute("userInfo") != null) {
            
            request.getSession().removeAttribute("userInfo");
        }
        request.getSession().setAttribute("userInfo", user);
        
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getId());
        return jo;
    }
    
    
    public JSONObject getUserInfo(Integer userId) {
        JSONObject jo = new JSONObject();
        
      User user = userService.getById(userId);
      if(user == null){
          throw new BizException("未找到该用户信息");
      }
      
      jo.put("id",user.getId());
      jo.put("nickName", user.getNickName());
     
      jo.put("profession", user.getProfession());
      JSONObject joo = new JSONObject();
      
      joo.put("col0",user.getAddress() != null ? user.getAddress().getProvince() : "");
      joo.put("col1",user.getAddress() != null ? user.getAddress().getCity() : "");
      joo.put("col2",user.getAddress() != null ? user.getAddress().getArea() : "");
      jo.put("addressVo", joo);
      
      jo.put("address",user.getAddress() != null ? user.getAddress().getAddress() : "");
      
      return jo;
    }
   
    /**
     * 修改个人信息
     */
    public void updateUser(User user, String nickName, int sex,
            String profession, String province, String city, String area,
            String address) {
        if(user == null){
            throw new BizException("未找到该用户信息");
        }
        
        if(nickName == null || "".equals(nickName.trim())){
            throw new BizException("请填写用户昵称");
        }
        boolean flag = false;
        if(((province != null && !"".equals(province.trim()))
                &&(city != null && !"".equals(city.trim()))
                        &&(area != null && !"".equals(area.trim())))
                        || ((province == null || "".equals(province.trim()))
                             && (city == null || "".equals(city.trim()))
                             && (area == null || "".equals(area.trim())))){
            flag = true;
        }
        
        if(!flag){
            throw new BizException("请完善地址 省 市 区 信息");
        }
        user = userService.getById(user.getId());
        user.setUpdateDate(new Date());
        user.setNickName(nickName);
        user.setProfession(profession);
        
        AddressVo addressVo = new AddressVo(province, city, area, address);
        
        user.setAddress(addressVo);
        
        userService.update(user);
    }
    
    public void isRegister(String phoneNumber) {
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new BizException("请输入正确的手机号");
        }
        User user = userService.getByPhone(phoneNumber);
        if(user == null){
            throw new BizException("未找到该手机号对应注册信息");
        }
    }
    
    public void updatePwd(String newPwd, String newPwdAgin, String phoneNumber) {
        if(newPwd == null || "".equals(newPwd.trim())){
            throw new BizException("请输入新密码");
        }
        if(newPwdAgin == null || "".equals(newPwdAgin.trim())){
            throw new BizException("请输入再次输入新密码");
        }
        if(!newPwd.equals(newPwdAgin)){
            throw new BizException("请确认两次输入密码一致");
        }
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new BizException("请输入正确的手机号");
        }
        
       User user = userService.getByPhone(phoneNumber);
       
       if(user == null){
           throw new BizException("未找到该手机号对应用户信息");
       }
       
       user.setUpdateDate(new Date());
       user.setPwd(Md5Utils.md5ForPwd(newPwd));
       
       userService.update(user);
        
    }
   
    public void getDataTablePage(DataTables dtJson, String nikeName, String phoneNumber) {
       userService.getDataTablePage(dtJson,nikeName,phoneNumber);
    }
   
  
    public UserService getUserService() {
        return userService;
    }
    @Resource(name = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
   
    public ImageService getImageService() {
        return imageService;
    }
    @Resource(name="imageService")
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
    public ProjectService getProjectService() {
        return projectService;
    }
    @Resource(name="projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    public ProjectUserService getProjectUserService() {
        return projectUserService;
    }
    @Resource(name="projectUserService")
    public void setProjectUserService(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }
}
