package com.admin;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.action.BaseAction;
import com.bizservice.BizCommonService;
import com.entity.common.Image;
import com.entity.common.New;
import com.util.ImageUtils;
import com.util.RequestUtils;

/**
 * <p>ClassName: UserlAction</p>
 * <p>@Description: 用户</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-16下午12:38:03</p>
 */
@Controller("newAction")
@Scope("prototype")
public class NewAction extends BaseAction {
    private static final long serialVersionUID = 2672950514484572497L;
    
    private BizCommonService bizCommonService;
    //多文件
    private List<File> ylfile;
    private List<String> ylfileFileName;
    private List<String> ylfileContentType;
    private String imgath;
    
    public String Show(){
        
        return super.SHOW;
    }
    public String AjaxShow(){
        
        bizCommonService.getNewDataTablePage(super.getDtJson());
        
        return super.AJAXSHOW;
    }
    public String Add(){
        
        
        return "Add";
    }
    
    public String AddSave(){
        
       String title = RequestUtils.getStrParamter("title");
        
       String content = RequestUtils.getStrParamter("content");
       String description = RequestUtils.getStrParamter("description");
       
       bizCommonService.addSaveNew(title,content,description,ylfile);
       
        return "AddSave";
    }
    private New news;
    private long imageId;
    public String Update(){
        int id = RequestUtils.getIntParamter("id");
        
        news = bizCommonService.getNewObjById(id);
        
        imgath = ImageUtils.exAbsolutelyUrl(ImageUtils.exImageSmall(news.getImagePath()));
        List<Image> iList = bizCommonService.getImageByNameAndVal("news.id",news.getId());
        if(iList != null && iList.size() > 0){
            imageId =iList.get(0).getId();
        }
        
        return "Update";
    }
    public String UpdateSave(){
        int newId = RequestUtils.getIntParamter("id");
        String title = RequestUtils.getStrParamter("title");
        String description = RequestUtils.getStrParamter("description");
        String content = RequestUtils.getStrParamter("content");
        String deleteylIds=RequestUtils.getStrParamter("deleteylIds");
        bizCommonService.updateSaveNew(newId,title,description,content,deleteylIds,ylfile);
        
        return "AddSave";
    }
    
    public BizCommonService getBizCommonService() {
        return bizCommonService;
    }
    @Resource(name="bizCommonService")
    public void setBizCommonService(BizCommonService bizCommonService) {
        this.bizCommonService = bizCommonService;
    }
    public List<File> getYlfile() {
        return ylfile;
    }
    public void setYlfile(List<File> ylfile) {
        this.ylfile = ylfile;
    }
    public List<String> getYlfileFileName() {
        return ylfileFileName;
    }
    public void setYlfileFileName(List<String> ylfileFileName) {
        this.ylfileFileName = ylfileFileName;
    }
    public List<String> getYlfileContentType() {
        return ylfileContentType;
    }
    public void setYlfileContentType(List<String> ylfileContentType) {
        this.ylfileContentType = ylfileContentType;
    }
    public New getNews() {
        return news;
    }
    public void setNews(New news) {
        this.news = news;
    }
    public String getImgath() {
        return imgath;
    }
    public void setImgath(String imgath) {
        this.imgath = imgath;
    }
    public long getImageId() {
        return imageId;
    }
    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
    
    
}
