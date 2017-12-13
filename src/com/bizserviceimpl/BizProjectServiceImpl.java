package com.bizserviceimpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizProjectService;
import com.entity.common.Area;
import com.entity.common.City;
import com.entity.common.Province;
import com.entity.device.Device;
import com.entity.enumobj.Boolean;
import com.entity.enumobj.CheckType;
import com.entity.enumobj.ProjectType;
import com.entity.enumobj.Status;
import com.entity.enumobj.WorkerType;
import com.entity.order.RepairOrder;
import com.entity.project.Project;
import com.entity.project.ProjectDevice;
import com.entity.project.ProjectDeviceCheckDetails;
import com.entity.project.ProjectUser;
import com.entity.project.ProjectWorker;
import com.entity.user.User;
import com.entity.user.Worker;
import com.entity.vo.AddressVo;
import com.exception.BizException;
import com.service.AreaService;
import com.service.CityService;
import com.service.DeviceService;
import com.service.EntryService;
import com.service.ProjectDeviceCheckDetailsService;
import com.service.ProjectDeviceService;
import com.service.ProjectEnergyService;
import com.service.ProjectService;
import com.service.ProjectUserService;
import com.service.ProjectWorkerService;
import com.service.ProvinceService;
import com.service.RepairOrderService;
import com.service.UserService;
import com.service.WorkerService;


@Service("bizProjectService")
public class BizProjectServiceImpl implements BizProjectService{
    private ProjectUserService projectUserService;
    private ProjectService projectService;
    private ProjectDeviceService projectDeviceService;
    private ProjectDeviceCheckDetailsService projectDeviceCheckDetailsService;
    private ProjectEnergyService projectEnergyService;
    private ProjectWorkerService projectWorkerService;
    private ProvinceService provinceService;
    private CityService cityService;
    private AreaService areaService;
    private UserService userService;
    private DeviceService deviceService;
    private EntryService entryService;
    private RepairOrderService repairOrderService;
    private WorkerService workerService;
    
    
   /**
    * 查看下属成员列表
    */
    public void getUnderUserDataTablePage(DataTables dtJson, int projectId) {
       
        projectUserService.getUnderUserDataTablePage(dtJson,projectId);
    }
    public void getDataTablePage(DataTables dtJson) {
        projectService.getDataTablePage(dtJson);
    }
    public void getProjectDeviceDataTablePage(DataTables dtJson, int projectId) {
        projectDeviceService.getDataTablePage(dtJson,projectId);
    }
    public void addSave(int projectType, int isChargeRepair, int checkType,
            int provinceId, int cityId, int areaId, String projectName,
            String repairDateStart, String repairDateEnd,
            String userPhoneNumber, String address, String description) throws Exception{
       ProjectType pType = ProjectType.getByIndex(projectType);
       Boolean pIsChargeRepair = Boolean.getByIndex(isChargeRepair);
       CheckType pCheckType = CheckType.getByIndex(checkType);
       if(projectName == null || "".equals(projectName.trim())){
           throw new BizException("请输入项目名称");
       }
       if(projectName == null || "".equals(projectName.trim())){
           throw new BizException("请输入项目名称");
       }
       if(repairDateStart == null || "".equals(repairDateStart.trim())){
           throw new BizException("请选择项目维保开始日期");
       }
       if(repairDateEnd == null || "".equals(repairDateEnd.trim())){
           throw new BizException("请选择项目维保结束日期");
       }
       if(userPhoneNumber == null || "".equals(userPhoneNumber.trim()) || userPhoneNumber.length() != 11){
           throw new BizException("请输入正确的甲方11位联系号码");
       }
       if(address == null || "".equals(address.trim())){
           throw new BizException("请输入项目详细地址信息");
       }
       if(description == null || "".equals(description.trim())){
           throw new BizException("请输入项目概况描述信息");
       }
       Province province = provinceService.getById(provinceId);
       City city = cityService.getById(cityId);
       Area area = areaService.getById(areaId);
       if(province== null){
           throw new BizException("请选择项目所在省");
       }
       if(city== null){
           throw new BizException("请选择项目所在市");
       }
       if(area== null){
           throw new BizException("请选择项目所在区");
       }
       
       AddressVo addressVo = new AddressVo(province.getProvince(), city.getCity(), area.getArea(), address);
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
       Date sDate = null;
       Date eDate = null;
        eDate = sdf.parse(repairDateEnd);
        sDate = sdf.parse(repairDateStart);
   
       
       
       Project project = new Project();
       project.create(pType,pIsChargeRepair,pCheckType,
               projectName,addressVo,sDate,eDate,userPhoneNumber,description);
       
      User user = userService.getByPhone(userPhoneNumber);
       if(user != null){
           throw new BizException("甲方负责人手机号已被使用，请更换。");
       }
       projectService.create(project,userPhoneNumber,Boolean.YES);
    }
    public void updateSave(int projectId, int projectType, int isChargeRepair,
            int checkType, int provinceId, int cityId, int areaId,
            String projectName, String repairDateStart, String repairDateEnd,
            String address, String description) throws Exception{
        ProjectType pType = ProjectType.getByIndex(projectType);
        Boolean pIsChargeRepair = Boolean.getByIndex(isChargeRepair);
        CheckType pCheckType = CheckType.getByIndex(checkType);
        if(projectName == null || "".equals(projectName.trim())){
            throw new BizException("请输入项目名称");
        }
        if(projectName == null || "".equals(projectName.trim())){
            throw new BizException("请输入项目名称");
        }
        if(repairDateStart == null || "".equals(repairDateStart.trim())){
            throw new BizException("请选择项目维保开始日期");
        }
        if(repairDateEnd == null || "".equals(repairDateEnd.trim())){
            throw new BizException("请选择项目维保结束日期");
        }
        if(address == null || "".equals(address.trim())){
            throw new BizException("请输入项目详细地址信息");
        }
        if(description == null || "".equals(description.trim())){
            throw new BizException("请输入项目概况描述信息");
        }
        
        Project project = projectService.getById(projectId);
        
        if(project == null){
            throw new BizException("未找到该项目对应信息。");
        }
        AddressVo addressVo = project.getAddress();
        Province province = provinceService.getById(provinceId);
        City city = cityService.getById(cityId);
        Area area = areaService.getById(areaId);
       
        if(area != null){
            addressVo.setArea(area.getArea());
            if(city != null){
                addressVo.setCity(city.getCity());
                if(province != null){
                    addressVo.setProvince(province.getProvince());
                }
            }
        }
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date sDate = null;
        Date eDate = null;
         eDate = sdf.parse(repairDateEnd);
         sDate = sdf.parse(repairDateStart);
    
        
        addressVo.setAddress(address);
        project.setAddress(addressVo);
        project.setProjectName(projectName);
        project.setProjectType(pType);
        project.setCheckType(pCheckType);
        project.setIsChargeRepair(pIsChargeRepair);
        project.setRepairDateStart(sDate);
        project.setRepairDateEnd(eDate);
        project.setDescription(description);
        
        projectService.update(project);
    }
    public void deletes(String ids) {
        String id[] = ids.split(",");
        String projectNo="";
        List<Project> pList = new ArrayList<Project>();
        for(int i=0;i<id.length;i++){
            Project project = projectService.getById(Integer.parseInt(id[i]));
            projectNo = project.getProjectNo();
            //检查是否已经添加设备
           List<ProjectDevice> pDeviceList = projectDeviceService.getByProjectId(project.getId());
           if(pDeviceList != null && pDeviceList.size()>0){
               throw new BizException("项目'"+projectNo+"'已发生设备加入操作，不能进行删除操作。");
           }
           //检查是否已经添加下属User
           List<ProjectUser> pUserList = projectUserService.getByProjectId(project.getId());
           if(pUserList != null && pUserList.size()>1){
               throw new BizException("项目'"+projectNo+"'已发生负责人下属成员添加操作，不能进行删除操作。");
           }
           //检查是否已加入工作人员
           List<ProjectWorker> pWorkerList = projectWorkerService.getByProjectId(project.getId());
           if(pWorkerList != null && pWorkerList.size()>0){
               throw new BizException("项目'"+projectNo+"'已发生工作人员添加操作，不能进行删除操作。");
           }
            pList.add(project);
        }
            for(int i=0;i<pList.size();i++){
                Project project = pList.get(i);
                ProjectUser projectUser = project.getUser().getProjectUser(); 
                User user = project.getUser();
                projectUser.setUser(null);
                projectUser.setProject(null);
                projectUserService.update(projectUser);
                projectService.delete(project); 
                userService.delete(user);
                projectUserService.delete(projectUser);
             }
       
    }
    
    public Project getById(int projectId) {
        return projectService.getById(projectId);
    }
    public void addSaveDevice(int projectId, int deviceId, String entryIds,
            String installWorker, String position) {
        Project project = projectService.getById(projectId);
        if(project == null){
            throw new BizException("未找到该项目对应信息");
        }
        Device device = deviceService.getById(deviceId);
        if(device == null){
            throw new BizException("未找到对应设备信息");
        }
        if(device.getProjectDevice() != null){
            throw new BizException("当前设备已加入过项目，请更换");
        }
        if(project.getCheckType() != CheckType.NONE && (entryIds == null || entryIds.trim().equals(""))){
            throw new BizException("当前项目需巡检，请添加对应巡检词条信息");
        }
        if(position == null || position.trim().equals("")){
            throw new BizException("请输入设备所在位置");
        }
        
        JSONArray ja = entryService.getJAByIds(entryIds);
        
        projectDeviceService.create(project,device,ja,installWorker,position);
        
    }
    public void updateSaveDevice(int projectDeviceId, int deviceId,
            String entryIds, String installWorker, String position) {
        ProjectDevice projectDevice = projectDeviceService.getById(projectDeviceId);
        if(projectDevice == null){
            throw new BizException("未找到该项目设备对应信息");
        }
        Device device = deviceService.getById(deviceId);
        if(device == null){
            throw new BizException("未找到对应设备信息");
        }
        if(device.getId() != projectDevice.getDevice().getId() && device.getProjectDevice() != null){
            throw new BizException("当前设备已加入过其它项目，请更换");
        }
        if(projectDevice.getProject().getCheckType() != CheckType.NONE && (entryIds == null || entryIds.trim().equals(""))){
            throw new BizException("当前项目需巡检，请添加对应巡检词条信息");
        }
        if(position == null || position.trim().equals("")){
            throw new BizException("请输入设备所在位置");
        }
        String oldEntryJaStr = projectDevice.getCheckEntryJa();
        JSONArray ja = entryService.updateJAByIds(entryIds,oldEntryJaStr);
        
        projectDeviceService.update(projectDevice,device,ja,installWorker,position);
        
    }
    public void deletesDevice(String ids) {
        String id[] = ids.split(",");
        String deviceNo="";
        List<ProjectDevice> pList = new ArrayList<ProjectDevice>();
        for(int i=0;i<id.length;i++){
            ProjectDevice projectDevice = projectDeviceService.getById(Integer.parseInt(id[i]));
            deviceNo = projectDevice.getDevice().getDeviceNo();
            //检查是否已经有巡检记录
           List<ProjectDeviceCheckDetails> pDeviceCheckList = projectDeviceCheckDetailsService.getByProjectDeviceId(projectDevice.getId());
           if(pDeviceCheckList != null && pDeviceCheckList.size()>0){
               throw new BizException("设备'"+deviceNo+"'已发生设备巡检操作，不能进行移除操作。");
           }
           //检查是否已经有维修记录
           List<RepairOrder> repairOrderList = repairOrderService.getByProjectDeviceId(projectDevice.getId());
           if(repairOrderList != null && repairOrderList.size()>1){
               throw new BizException("项目'"+deviceNo+"'已发生设备维修操作，不能进行移除操作。");
           }
          
            pList.add(projectDevice);
        }
            for(int i=0;i<pList.size();i++){
                Device device = pList.get(i).getDevice();
                device.setProjectDevice(null);
                
                deviceService.update(device);
                
                projectDeviceService.delete(pList.get(i));
                
             }
       
    }
    
    
    public void getCheckDetailsDataTablePage(DataTables dtJson,
            int projectDeviceId) {
        projectDeviceCheckDetailsService.getDataTablePage(dtJson,projectDeviceId);
    }
    
    
    public void getWorkerDataTablePage(DataTables dtJson, int projectId,
            int workerType) {
        projectWorkerService.getDataTablePage(dtJson,projectId,workerType);
    }
    public JSONObject getCanAddToProjectWorker(int projectId, int workerType) {
       List<ProjectWorker>  pWorkerList = projectWorkerService.getByProjectIdAndWorkerType(projectId,workerType);
       List<Integer> falseWorkerIds = new ArrayList<Integer>();
       for(ProjectWorker pw : pWorkerList){
           falseWorkerIds.add(pw.getWorker().getId());
        }
       List<Worker> workerList = workerService.getCanAddToProjectWorker(falseWorkerIds);
       
       if(workerList == null || workerList.size() == 0){
           throw new BizException("当前无可用人员信息，请先在人员管理中添加人员信息");
       }
       
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略 
       jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//设置循环策略为忽略    解决json最头疼的问题 死循环
       //jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
       jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer","worker"}); 
       
      List<ProjectDevice> deviceList = projectDeviceService.getByProjectId(projectId);
      if(deviceList == null || deviceList.size() == 0){
          throw new BizException("当前无可用项目设备信息，请先在项目设备管理中添加设备信息");
      }
      
      JSONObject jo = new JSONObject();
      jo.put("workerList",JSONArray.fromObject(workerList,jsonConfig) );
      jo.put("deviceList",JSONArray.fromObject(deviceList,jsonConfig) );
      
      return jo;
    }
    
    public void addSaveWorker(int projectId, int workerType, int workerId,int projectDeviceId) {
        Project project = projectService.getById(projectId);
        if(project == null){
            throw new BizException("未找到对应项目信息");
        }
        
        ProjectDevice projectDevice = projectDeviceService.getById(projectDeviceId);
        if(projectDevice == null || projectDevice.getProject().getId() != projectId){
            throw new BizException("未找到该项目下对应设备信息");
        }
        Worker worker = workerService.getById(workerId);
        if(worker == null){
            throw new BizException("未找到该人员信息");
        }
        if(worker.getStatus() != Status.WORKER_ABLE){
            throw new BizException("该人员当前状态为非可用状态");
        }
       ProjectWorker worke1 = projectWorkerService.getByProjectDeviceIdAndWorkerId(workerId,projectDeviceId,workerType);
        if(worke1 != null){
            throw new BizException("该人员已添加在该设备组中");
        }
       WorkerType workerTypeEnum = WorkerType.getByIndex(workerType);
        
       ProjectWorker projectWorker = new ProjectWorker();
       projectWorker.create(projectDevice,worker,workerTypeEnum);
       
       projectWorkerService.addSave(projectWorker);
        
    }
    
    public void deleteWorker(int projectWorkerId) {
        ProjectWorker projectWorker = projectWorkerService.getById(projectWorkerId);
        if(projectWorker == null){
            throw new BizException("未找到当前该组员信息");
        }
        //巡检记录
        List<ProjectDeviceCheckDetails> checkDetailsList = projectDeviceCheckDetailsService.getByProjectWorkerId(projectWorkerId);
        
        if(checkDetailsList != null && checkDetailsList.size()>0){
            throw new BizException("该人员在该项目已有巡检记录信息，不能移除");
        }
        
        projectWorkerService.delete(projectWorker);
        
    }
    
    
    public ProjectUserService getProjectUserService() {
        return projectUserService;
    }
    @Resource(name="projectUserService")
    public void setProjectUserService(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }
    public ProjectService getProjectService() {
        return projectService;
    }
    @Resource(name="projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    public ProjectDeviceService getProjectDeviceService() {
        return projectDeviceService;
    }
    @Resource(name="projectDeviceService")
    public void setProjectDeviceService(ProjectDeviceService projectDeviceService) {
        this.projectDeviceService = projectDeviceService;
    }
    public ProjectDeviceCheckDetailsService getProjectDeviceCheckDetailsService() {
        return projectDeviceCheckDetailsService;
    }
    @Resource(name="projectDeviceCheckDetailsService")
    public void setProjectDeviceCheckDetailsService(
            ProjectDeviceCheckDetailsService projectDeviceCheckDetailsService) {
        this.projectDeviceCheckDetailsService = projectDeviceCheckDetailsService;
    }
    public ProjectEnergyService getProjectEnergyService() {
        return projectEnergyService;
    }
    @Resource(name="projectEnergyService")
    public void setProjectEnergyService(ProjectEnergyService projectEnergyService) {
        this.projectEnergyService = projectEnergyService;
    }
    public ProjectWorkerService getProjectWorkerService() {
        return projectWorkerService;
    }
    @Resource(name="projectWorkerService")
    public void setProjectWorkerService(ProjectWorkerService projectWorkerService) {
        this.projectWorkerService = projectWorkerService;
    }
    public ProvinceService getProvinceService() {
        return provinceService;
    }
    @Resource(name="provinceService")
    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }
    public CityService getCityService() {
        return cityService;
    }
    @Resource(name="cityService")
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
    public AreaService getAreaService() {
        return areaService;
    }
    @Resource(name="areaService")
    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }
    public UserService getUserService() {
        return userService;
    }
    @Resource(name="userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public DeviceService getDeviceService() {
        return deviceService;
    }
    @Resource(name="deviceService")
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    public EntryService getEntryService() {
        return entryService;
    }
    @Resource(name="entryService")
    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }
    public RepairOrderService getRepairOrderService() {
        return repairOrderService;
    }
    @Resource(name="repairOrderService")
    public void setRepairOrderService(RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;
    }
    public WorkerService getWorkerService() {
        return workerService;
    }
    @Resource(name="workerService")
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
