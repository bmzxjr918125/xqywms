package com.config;

/**
 * <p>ClassName: GeneralConfig</p>
 * <p>@Description: 常用参数配置</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-1下午4:42:20</p>
 */
public class GeneralConfig {
	//在windows上使用"\\"，在linux上使用"/"
	//public static final String DIR_LOC = "\\";
    public static final String basePath = System.getProperty("catalina.home")
            + "/webapps/";
	public static final String DIR_LOC = "/";
	
	//图片服务器 中图片绝对路径 访问 根地址
    //外网生产
public static final String IMAGE_BASE_PATH ="http://119.29.143.200:80/";
    //外网测试
//public static final String IMAGE_BASE_PATH ="http://114.55.53.48:8080/";
    //本地
//public static final String IMAGE_BASE_PATH ="http://192.168.0.100:8080/";
    
    // 备份SQL文件存放文件夹
    public static final String SQLDataPath = basePath + "cxjmsSQLData";
    
  //系统固定token 处理未登录时请求使用 双32位大写md5：tamao2016
    public static final String SERVER_TOKEN="60DB9DE82709E3796470E40502955724";
    
    //单图片上传服务器地址
    //外网生产
public static final String imageHostSingle="http://119.29.143.200:80/filems/filemsApiUploadImage";
    //外网测试
//public static final String imageHostSingle="http://114.55.53.48:8080/filems/filemsApiUploadImage";
    //本地
//public static final String imageHostSingle="http://192.168.0.100:8080/filems/filemsApiUploadImage";
    
    //单文件上传服务器地址
    //外网生产
public static final String fileHostSingle="http://119.29.143.200:80/filems/filemsApiUploadFile";
    //外网测试
//public static final String fileHostSingle="http://114.55.53.48:8080/filems/filemsApiUploadFile";
    //本地
//public static final String fileHostSingle="http://192.168.0.100:8080/filems/filemsApiUploadFile";
    
    //多图片上传服务器地址
    //外网生产
public static final String imageHostPlural="http://119.29.143.200:80/filems/filemsApiUploadImageList";
    //外网测试
//public static final String imageHostPlural="http://114.55.53.48:8080/filems/filemsApiUploadImageList";
    //本地
//public static final String imageHostPlural="http://192.168.0.100:8080/filems/filemsApiUploadImageList";
    
    //服务器图片转存服务器地址
    //外网生产
public static final String imageHostCopy="http://119.29.143.200:80/filems/filemsApiCopyFile";
    //外网测试
//public static final String imageHostCopy="http://114.55.53.48:8080/filems/filemsApiCopyFile";
    //本地
//public static final String imageHostCopy="http://192.168.0.100:8080/filems/filemsApiCopyFile";
    
    //图片删除服务器地址
    //外网生产
public static final String imageHostDelete="http://119.29.143.200:80/filems/filemsApiDeleteFile";
    //外网测试    
//public static final String imageHostDelete="http://114.55.53.48:8080/filems/filemsApiDeleteFile";
    //本地
//public static final String imageHostDelete="http://192.168.0.103:8080/filems/filemsApiDeleteFile";
    
	
	/**
	 * 图片上传大小 1MB
	 */
	public static final int IMAGE_SIZE=1048576;
	/**
	 * 图片上传大小 2MB
	 */
	public static final int IMAGE_SIZE_2=2097152;
	/**
	 * 图片保存大小限制 100kb
	 */
	public static final int JPG_IMAGE_SIZE=102400;
	/**
	 * HTml 中信息列表显示条数
	 */
	public static final int HTML_SHOWNUMBER = 10;
	

	

	

	






	

	

	
}
