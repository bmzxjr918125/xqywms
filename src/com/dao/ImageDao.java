package com.dao;


import java.util.List;

import com.base.dao.BaseDao;
import com.entity.common.Image;
import com.entity.enumobj.ImageType;


public interface ImageDao extends BaseDao<Image>{
    /**
     * <p>@Description: 获取图片集合</p>
     * <p>@param @param name
     * <p>@param @param productId
     * <p>@param @param productscan
     * <p>@param @return</p>   
     * <p>@return List<Image></p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-3下午1:37:25</p>
     */
	List<Image> getImageList(String name, int productId, ImageType productscan);
	/**
	 * <p>@Description: 获取图片集合</p>
	 * <p>@param @param name
	 * <p>@param @param productId
	 * <p>@param @param productscan
	 * <p>@param @return</p>   
	 * <p>@return List<Image></p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-8-3下午1:37:25</p>
	 */
	List<Image> getImageList(String name, long productId, ImageType productscan);

}
