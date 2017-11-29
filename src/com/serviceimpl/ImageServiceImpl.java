package com.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.config.GeneralConfig;
import com.dao.ImageDao;
import com.entity.common.Image;
import com.entity.common.New;
import com.entity.enumobj.ImageType;
import com.entity.enumobj.LogoDefaultEnum;
import com.entity.order.RepairOrderDetails;
import com.entity.user.Worker;
import com.exception.BizException;
import com.service.ImageService;
import com.util.HttpRequestUtils;
import com.util.pojo.Answer;

@Service("imageService")
public class ImageServiceImpl implements ImageService{
    private ImageDao imageDao;

    public List<Image> uploadImages(ImageType imageType,
    		List<File> fileList, Object obj,String account) {
    	//上传图片
    	Map<String, String> textMap=new HashMap<String, String>();
    	textMap.put("fileType", imageType.getIndex()+"");
    	textMap.put("account", account);
    	String res= HttpRequestUtils.formUpload(GeneralConfig.imageHostPlural, textMap, fileList, "imageList");
    	if(res != null && !"".equals(res.trim())){
        	JSONObject jo=JSONObject.fromObject(res);
        	if(Answer.SUCCESS.equals(jo.getString("response"))){
        		String paths=jo.getString("path");
        		if(paths.endsWith(",")){
        			paths=paths.substring(0,paths.lastIndexOf(","));
        		}
        		String[] path=paths.split(",");
        		
        		List<Image> imageList=new ArrayList<Image>();
        		for(String p:path){
        			Image image=new Image();
        			image.setCreateDate(new Date());
        			image.setDescription(imageType.getDescription());
        			image.setImageType(imageType);
        			image.setImageUrl(p);
        			if(obj instanceof Worker){
        				image.setWorker((Worker)obj);
        			}else if(obj instanceof New){
        				image.setNews((New)obj);
        			}else if(obj instanceof RepairOrderDetails){
        		        image.setRepairOrderDetails((RepairOrderDetails)obj);
        		    }
        			
        			imageDao.save(image);
        			imageList.add(image);
        		}
        		return imageList;
    			}else{
	        		throw new BizException(jo.getString("msg"));
	        	}
    		}else{
    	throw new BizException("上传异常，未返回数据");
    }
    }
    
	public Image uploadImage(ImageType imageType, File file, Object obj,
			String account, boolean isSave) {

		// 上传图片
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("fileType", imageType.getIndex() + "");
		textMap.put("account", account);
		List<File> fileList = new ArrayList<File>();
		fileList.add(file);
		String res = HttpRequestUtils.formUpload(GeneralConfig.imageHostSingle, textMap, fileList, "image");
		if (res != null && !"".equals(res.trim())) {
			JSONObject jo = JSONObject.fromObject(res);
			if (Answer.SUCCESS.equals(jo.getString("response"))) {
				String paths = jo.getString("path");
				if (paths.endsWith(",")) {
					paths = paths.substring(0, paths.lastIndexOf(","));
				}
				String[] path = paths.split(",");
				int i = 0;
				Image returnImage = null;
				for (String p : path) {

					Image image = new Image();
					image.setCreateDate(new Date());
					image.setDescription(imageType.getDescription());
					image.setImageType(imageType);
					image.setImageUrl(p);
					if(obj instanceof Worker){
                        image.setWorker((Worker)obj);
                    }else if(obj instanceof New){
                        image.setNews((New)obj);
                    }else if(obj instanceof RepairOrderDetails){
                        image.setRepairOrderDetails((RepairOrderDetails)obj);
                    }
					if (i == 0) {
						returnImage = image;
					}
					if (isSave) {
						imageDao.save(image);
					}
				}
				return returnImage;
			} else {
				throw new BizException(jo.getString("msg"));
			}
		} else {
			throw new BizException("上传异常，未返回数据");
		}
	}
    /**
     * <p>@Description: 删除对应地址图片</p>
     * <p>@param @param images</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-15下午7:54:26</p>
     */
    public void deleteImages(List<Image> images){
    	for(Image m:images){
    		//不删除默认图
    		if(!m.getImageUrl().equals(LogoDefaultEnum.WORKERLOGO.getPath()))
    		  {
    			
    			HttpRequestUtils.deleteImages(GeneralConfig.imageHostDelete,m );
        		
    		  }
    		
    		imageDao.deleteById(m.getId());
    	}
    }
    /**
     * <p>@Description: 删除对应地址图片</p>
     * <p>@param @param images</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-15下午7:54:26</p>
     */
    public void deleteImages(String[] imageIds) {
    	for(String mId:imageIds){
    		if(mId != null && !"".equals(mId.trim())){
    			Image m =imageDao.get(Long.parseLong(mId));
    			//不删除默认图片
        			
	        		if( !m.getImageUrl().equals(LogoDefaultEnum.WORKERLOGO.getPath())){
	        			
	                    HttpRequestUtils.deleteImages(GeneralConfig.imageHostDelete,m );
	        		}
    		    
        		imageDao.deleteById(Long.parseLong(mId));
    		}
    	}
    }
    
    /**
     * 获取图片集合
     */
    public List<Image> getImageList(String name, long productId,
    		ImageType productscan) {
    	return imageDao.getImageList(name,productId,productscan);
    }
    
    public void save(Image image) {
    	imageDao.save(image);
    }
    
	
    
    
	public ImageDao getImageDao() {
		return imageDao;
	}
   @Resource(name="imageDao")
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}


}
