package com.util;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.config.GeneralConfig;

/**
 * <p>ClassName: ImageUtils</p>
 * <p>@Description: 图片文件上传地址配置以及图片一些处理公共方法类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:01:39</p>
 */
public class ImageUtils {
	
	protected static final Logger log = Logger.getLogger(ImageUtils.class);

	public static final String basePath = System.getProperty("catalina.home")
			+ "/webapps/";
	

	/**
     * <p>@Description: 转换图片地址为缩略图地址</p>
     * <p>@param @param path
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-3下午3:15:24</p>
     */
    public static String exImageSmall(String path){
        if(!StringUtils.isEmpty(path)){
            String ext = path.substring(
                    path.lastIndexOf("."),
                    path.length());
            String str=path.replace(ext, "");
            return str+ "_s" + ext;
            
        }else{
            return "";
        }
    }
    
    public static String exAbsolutelyUrl(String relativePath){
        String path=GeneralConfig.IMAGE_BASE_PATH+relativePath;
        return path;
    }

	

}
