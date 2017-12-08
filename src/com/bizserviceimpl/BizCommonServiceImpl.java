package com.bizserviceimpl;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.bizservice.BizCommonService;
import com.config.GeneralConfig;
import com.entity.common.Area;
import com.entity.common.City;
import com.entity.common.Entry;
import com.entity.common.Image;
import com.entity.common.New;
import com.entity.common.Province;
import com.entity.enumobj.EntryType;
import com.entity.enumobj.ImageType;
import com.exception.BizException;
import com.service.AreaService;
import com.service.CityService;
import com.service.EntryService;
import com.service.ImageService;
import com.service.NewService;
import com.service.ProvinceService;
import com.util.ImageUtils;



@Service("bizCommonService")
public class BizCommonServiceImpl implements BizCommonService{
    private ProvinceService provinceService;
    private CityService cityService;
    private AreaService areaService;
    private NewService newService;
    private ImageService imageService;
    private EntryService entryService;
    
    
    
    public void getNewDataTablePage(DataTables dtJson) {
       
        newService.getDataTablePage(dtJson);
    }


    public void addSaveNew(String title, String content, String description, List<File> ylfile) {
        if(title == null || "".equals(title.trim())){
            throw new BizException("请填写信息公告标题");
        }
        if(content == null || "".equals(content.trim())){
            throw new BizException("请填写信息公告内容");
        }
        if(description == null || "".equals(description.trim())){
            throw new BizException("请填写信息公告简述");
        }
        
        New new_= new New(); 
        new_.setCreateDate(new Date());
        new_.setTitle(title);
        new_.setContent(content.replaceAll("/cxjms/ueditor/",GeneralConfig.IMAGE_BASE_PATH+"cxjms/ueditor/"));
        new_.setDescription(description);
        
        newService.save(new_);
        
        if(ylfile!=null&&ylfile.size()>0){
            // 预览图片上传
            List<Image> imageList = imageService.uploadImages(ImageType.XQ_NEW_IMG,
                    ylfile, new_, "admin");
            Image firstImage=imageList.get(0);
           
            new_.setImagePath(firstImage.getImageUrl());
            
            newService.update(new_);
        }else{
            throw new BizException("添加信息失败，请至少选择一张信息预览图片");
        }
    }

    public void updateSaveNew(int newId, String title, String description,
            String content, String deleteylIds, List<File> ylfile) {
           New news = newService.getById(newId);
        
        if(news == null){
            throw new BizException("未找到该信息");
        }
        if(title == null || "".equals(title.trim())){
            throw new BizException("请填写信息公告标题");
        }
        if(content == null || "".equals(content.trim())){
            throw new BizException("请填写信息公告内容");
        }
        if(description == null || "".equals(description.trim())){
            throw new BizException("请填写信息公告简述");
        }
        String[] deleteylId=deleteylIds.split(",");
        
        List<Image> imageScanList=imageService.getImageList("news.id",newId,ImageType.XQ_NEW_IMG);
        
        boolean isYlDeleteAll=false;
        
        if( (imageScanList == null || ( (deleteylId.length- imageScanList.size())>=0  && !StringUtils.isEmpty(deleteylIds)))){
            isYlDeleteAll=true;
        }
       
        
        if(isYlDeleteAll && ylfile == null){
            throw new BizException("请至少上传一张商品预览图片");
        }
       
        if(ylfile != null){
            //预览图片上传
            imageService.uploadImages(ImageType.XQ_NEW_IMG,ylfile,news,"admin");
        }
        
        imageService.deleteImages(deleteylId);
        
        imageScanList=imageService.getImageList("news.id",newId,ImageType.XQ_NEW_IMG);
        
        if(imageScanList != null && imageScanList.size() > 0){
            news.setImagePath(imageScanList.get(0).getImageUrl());
        }
        
        news.setContent((content.replaceAll(GeneralConfig.IMAGE_BASE_PATH+"cxjms/ueditor/", "/cxjms/ueditor/"))
                .replaceAll("/cxjms/ueditor/",GeneralConfig.IMAGE_BASE_PATH+"cxjms/ueditor/"));
        news.setTitle(title);
        news.setDescription(description);
        
       newService.update(news);
        
    }


    public JSONObject getNewList(int currentNum, int perPageNum) {
        JSONObject jo = new JSONObject();
        if(currentNum == 0){//第一次加载计算总条数
           int count = newService.count();
           jo.put("total", count);
        }
        
        List<New> newList = newService.getNewList(currentNum,perPageNum);
       
        JSONArray ja = new JSONArray();
        for(New e:newList){
            JSONObject ejo=new JSONObject();
            ejo.put("id", e.getId());
            ejo.put("img",ImageUtils.exAbsolutelyUrl(ImageUtils.exImageSmall(e.getImagePath())));
            ejo.put("title",e.getTitle());
            ejo.put("description",e.getDescription());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ejo.put("createDate", sdf.format(e.getCreateDate()));
            ja.add(ejo);
        }
        
        jo.put("newList",ja );
        
        return jo;
    }

    public JSONObject getNewById(int newId) {
        JSONObject jo = new JSONObject();
        New news = newService.getById(newId);
        
        if(news == null){
            throw new BizException("未找到该信息");
        }
        jo.put("id", newId);
        jo.put("title", news.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jo.put("createDate",sdf.format( news.getCreateDate()));
        
        jo.put("content", news.getContent());
        
        return jo;
    }

    public New getNewObjById(int id) {
        New news = newService.getById(id);
        
        if(news == null){
            throw new BizException("未找到该信息");
        }
        return news;
    }

    public List<Image> getImageByNameAndVal(String name, int value) {
        
        return imageService.getImageList(name, value, ImageType.XQ_NEW_IMG);
    }

    public void getEntryDataTablePage(DataTables dtJson,int flag) {
       
        entryService.getEntryDataTablePage(dtJson,flag);
    }

    public void addSaveEntry(String name, int flag) {
        if(name == null || name.trim().equals("")){
            throw new BizException("请填写词条名称");
        }
        
        Entry entry = new Entry();
        
        entry.setValue(name);
        entry.setEntryType(EntryType.getByIndex(flag));
        entry.setCreateDate(new Date());
        entryService.save(entry);
    }


    public void updateSaveEntry(int id, String name) {
        if(name == null || name.trim().equals("")){
            throw new BizException("请填写词条名称");
        }
        Entry entry = entryService.getById(id);
        
        if(entry == null){
            throw new BizException("未找到词条对应信息");
        }
        
        entry.setValue(name);
        
        entryService.update(entry);
        
    }


    public void deleteEntryByIds(String ids) {
        if(ids == null || ids.trim().equals("")){
            throw new BizException("请选择要删除得词条");
        }
        String id[] = ids.split(",");
        for(int i=0;i<id.length;i++){
            Entry entry = entryService.getById(Integer.parseInt(id[i]));
            if(entry == null){
                throw new BizException("未找到词条对应信息");
            }
            entryService.delete(entry);
        }
    }

    public List<Entry> getEntryListByType(EntryType type) {
       
        return  entryService.getEntryListByType(type);
    }
    


    public JSONArray getProvinceList() {
        List<Province> provinceList = provinceService.getProvinceList();
        return JSONArray.fromObject(provinceList);
    }


    public JSONArray getCityList(int provinceId) {
        List<City> cityList = cityService.getCityList(provinceId);
        return JSONArray.fromObject(cityList);
    }


    public JSONArray getAreaList(int cityId) {
        List<Area> areaList = areaService.getAreaList(cityId);
        return JSONArray.fromObject(areaList);
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
    public NewService getNewService() {
        return newService;
    }
    @Resource(name="newService")
    public void setNewService(NewService newService) {
        this.newService = newService;
    }
    public ImageService getImageService() {
        return imageService;
    }
    @Resource(name="imageService")
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
    public EntryService getEntryService() {
        return entryService;
    }
    @Resource(name="entryService")
    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }
}
