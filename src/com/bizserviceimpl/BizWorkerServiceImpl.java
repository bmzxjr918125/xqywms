package com.bizserviceimpl;


import java.io.File;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.base.action.datatables.DataTables;
import com.bizservice.BizWorkerService;
import com.entity.user.Worker;
import com.exception.BizException;
import com.service.ImageService;
import com.service.WorkerService;
import com.util.ImageUtils;
import com.util.Md5Utils;


@Service("bizWorkerService")
public class BizWorkerServiceImpl implements BizWorkerService{
    private WorkerService workerService;
    private ImageService imageService;
    /**
     * 分页获取人员列表数据
     */
    public void getDataTablePage(DataTables dtJson, String nickName,
            String phoneNumber, int status) {
       
        workerService.getDataTablePage(dtJson, nickName, phoneNumber,status);
    }
    
   
  
    
    public JSONObject login(HttpServletRequest request,String phoneNumber, String pwd) {
        
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new BizException("请填写登录手机号码");
        }
        if(pwd == null || "".equals(pwd.trim())){
            throw new BizException("请填写登录密码");
        }
        
        Worker worker = workerService.getByPhone(phoneNumber);
        if(worker == null){
            throw new BizException("未找到该用户，请先注册!");
        }
        
        if(!worker.getPwd().equals(Md5Utils.md5ForPwd(pwd))){
            
            throw new BizException("登录密码错误");
        }
        worker.login();
        workerService.update(worker);
        
      if(request.getSession().getAttribute("workerInfo") != null) {
            
            request.getSession().removeAttribute("workerInfo");
        }
        request.getSession().setAttribute("workerInfo", worker);
        
        JSONObject jo = new JSONObject();
        jo.put("workerId", worker.getId());
        return jo;
    }
    
    
    public JSONObject getWorkerInfo(Integer userId) {
        JSONObject jo = new JSONObject();
        
       Worker worker = workerService.getById(userId);
      if(worker == null){
          throw new BizException("未找到该用户信息");
      }
      
      jo.put("id",worker.getId());
      jo.put("img",ImageUtils.exAbsolutelyUrl(ImageUtils.exImageSmall(worker.getHeaderImg().getImageUrl())));
      jo.put("nickName", worker.getNickName());
      jo.put("phoneNumber", worker.getPhoneNumber());
     
      jo.put("department", worker.getDepartment());
      jo.put("job", worker.getJob());
      jo.put("statusDesc", worker.getStatus().getDescription());
      jo.put("status", worker.getStatus().getIndex());

      return jo;
    }
    
    public void register(String phoneNumber, String pwd,String department,String job) {
       if(phoneNumber == null || "".equals(phoneNumber.trim())){
           throw new BizException("请输入正确的手机号");
       }
       if(pwd == null || "".equals(pwd.trim())){
           throw new BizException("请输入登录密码");
       }
       if(department == null || "".equals(department.trim())){
           throw new BizException("请输入人员部门");
       }
       if(job == null || "".equals(job.trim())){
           throw new BizException("请输入人员职位");
       }
       
       //判断手机号
       Worker worker = workerService.getByPhone(phoneNumber);
       if(worker != null){
           throw new BizException("该手机号已被注册");
       }
       workerService.create(phoneNumber,pwd,department,job);
    }
  
    
    public JSONObject updateHeaderImg(int workerId, File file) {
        
        if(file == null){
            throw new BizException("请选择修改上传头像图片");
        }
        Worker worker = workerService.getById(workerId);
        
        if(worker == null){
            throw new BizException("未找到该用户信息");
        }
        
        workerService.updateHeaderImg(worker,file);
        
        JSONObject jo = new JSONObject();
        
        jo.put("img", ImageUtils.exAbsolutelyUrl(ImageUtils.exImageSmall(worker.getHeaderImg().getImageUrl())));
        
        return jo;
    }
    /**
     * 修改个人信息
     */
    public void updateUser(Worker worker, String nickName,String department,String job){
        if(worker == null){
            throw new BizException("未找到该用户信息");
        }
        
        if(nickName == null || "".equals(nickName.trim())){
            throw new BizException("请填写用户昵称");
        }
        if(department == null || "".equals(department.trim())){
            throw new BizException("请输入人员部门");
        }
        if(job == null || "".equals(job.trim())){
            throw new BizException("请输入人员职位");
        }
    
        worker = workerService.getById(worker.getId());
        worker.setUpdateDate(new Date());
        worker.setNickName(nickName);
        worker.setDepartment(department);
        worker.setJob(job);
        workerService.update(worker);
    }
    
    public void isRegister(String phoneNumber) {
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new BizException("请输入正确的手机号");
        }
        Worker worker = workerService.getByPhone(phoneNumber);
        if(worker == null){
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
        
        Worker worker = workerService.getByPhone(phoneNumber);
       
       if(worker == null){
           throw new BizException("未找到该手机号对应用户信息");
       }
       
       worker.setUpdateDate(new Date());
       worker.setPwd(Md5Utils.md5ForPwd(newPwd));
       
       workerService.update(worker);
        
    }
  
   
    public ImageService getImageService() {
        return imageService;
    }
    @Resource(name="imageService")
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public WorkerService getWorkerService() {
        return workerService;
    }
    @Resource(name="workerService")
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
