package com.service;

import java.io.File;
import java.util.List;

import com.entity.common.Image;
import com.entity.enumobj.ImageType;



public interface ImageService {
    /**
     * <p>@Description: 多图片上传保存</p>
     * <p>@param @param imageType
     * <p>@param @param imagehostplural 上传远端地址
     * <p>@param @param fileList 文件列表
     * <p>@param @param obj  上传对应对象 member/product/....</p>    
     * <p>@param @param account  账户</p>    
     * <p>@return Image 商品首张图片</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-1下午4:00:04</p>
     */
	List<Image> uploadImages(ImageType imageType,
			List<File> fileList, Object obj, String account);
	/**
	 * <p>@Description: 单图片上传</p>
	 * <p>@param @param imageType
	 * <p>@param @param file
	 * <p>@param @param obj
	 * <p>@param @param account
	 * <p>@param @param boolean isSave 是否直接储存 image （是否save）
	 * <p>@param @return</p>   
	 * <p>@return Image</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-8-1下午5:43:44</p>
	 */
	Image uploadImage(ImageType imageType,
			File file, Object obj, String account,boolean isSave);
	/**
	 * <p>@Description: 获取图片集合</p>
	 * <p>@param @param string
	 * <p>@param @param productId
	 * <p>@param @param productscan
	 * <p>@param @return</p>   
	 * <p>@return Set<Image></p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-8-3下午1:31:59</p>
	 */
	List<Image> getImageList(String string, long productId, ImageType productscan);
    /**
     * <p>@Description: 删除图片</p>
     * <p>@param @param images</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-15下午8:02:26</p>
     */
	public void deleteImages(List<Image> images);
	/**
	 * <p>@Description: 删除图片</p>
	 * <p>@param @param imageIds</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-8-15下午8:02:26</p>
	 */
	public void deleteImages(String[] imageIds);
	void save(Image image);
	

}
