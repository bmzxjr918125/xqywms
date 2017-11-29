package com.apiaction.base;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import com.base.action.BaseAction;
import com.bizservice.BizCommonService;
import com.bizservice.BizUserService;
import com.bizservice.BizWorkerService;

/**
 * <p>ClassName: ClientBaseApiAction</p>
 * <p>@Description: API接口基类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-22下午3:26:30</p>
 */
public class ClientBaseApiAction extends BaseAction {
    private static final long serialVersionUID = 4591556179183444504L;
   
    protected BizUserService bizUserService;
    protected BizCommonService bizCommonService;
    protected BizWorkerService bizWorkerService;
    protected File image;
    protected String imageFileName;
    protected String imageContentType;
    
    //多图片
    protected List<File> imageList;
    protected List<String> imageListFileName;
    protected List<String> imageListContentType;
    
    //多图片
    protected List<File> ylfile;
    protected List<String> ylfileFileName;
    protected List<String> ylfileContentType;
    
    
    //多图片
    protected List<File> twfile;
    protected List<String> twfileFileName;
    protected List<String> twfileContentType;
    public BizUserService getBizUserService() {
        return bizUserService;
    }
    @Resource(name="bizUserService")
    public void setBizUserService(BizUserService bizUserService) {
        this.bizUserService = bizUserService;
    }
    public File getImage() {
        return image;
    }
    public void setImage(File image) {
        this.image = image;
    }
    public String getImageFileName() {
        return imageFileName;
    }
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    public String getImageContentType() {
        return imageContentType;
    }
    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
    public List<File> getImageList() {
        return imageList;
    }
    public void setImageList(List<File> imageList) {
        this.imageList = imageList;
    }
    public List<String> getImageListFileName() {
        return imageListFileName;
    }
    public void setImageListFileName(List<String> imageListFileName) {
        this.imageListFileName = imageListFileName;
    }
    public List<String> getImageListContentType() {
        return imageListContentType;
    }
    public void setImageListContentType(List<String> imageListContentType) {
        this.imageListContentType = imageListContentType;
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
    public List<File> getTwfile() {
        return twfile;
    }
    public void setTwfile(List<File> twfile) {
        this.twfile = twfile;
    }
    public List<String> getTwfileFileName() {
        return twfileFileName;
    }
    public void setTwfileFileName(List<String> twfileFileName) {
        this.twfileFileName = twfileFileName;
    }
    public List<String> getTwfileContentType() {
        return twfileContentType;
    }
    public void setTwfileContentType(List<String> twfileContentType) {
        this.twfileContentType = twfileContentType;
    }
    public BizCommonService getBizCommonService() {
        return bizCommonService;
    }
    @Resource(name="bizCommonService")
    public void setBizCommonService(BizCommonService bizCommonService) {
        this.bizCommonService = bizCommonService;
    }
    public BizWorkerService getBizWorkerService() {
        return bizWorkerService;
    }
    @Resource(name="bizWorkerService")
    public void setBizWorkerService(BizWorkerService bizWorkerService) {
        this.bizWorkerService = bizWorkerService;
    }
}
