package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;

import sun.net.www.protocol.http.HttpURLConnection;


import com.config.GeneralConfig;
import com.entity.common.Image;
import com.exception.BizException;
/**
 * <p>ClassName: HttpRequestUtils</p>
 * <p>@Description: HTTP请求公共方法类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:02:34</p>
 */
public class HttpRequestUtils {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	 String urlNameString ="";
        	if(StringUtils.isNotEmpty(param)){
        		  urlNameString = url + "?" + param;
        	}else{
        		  urlNameString = url;
        	}
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            /* Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
           // System.out.println("发送GET请求出现异常！" + e);
        	e.printStackTrace();
            throw new BizException("发送GET请求出现异常！");
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new BizException("发送GET请求出现异常！");
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            
            
            e.printStackTrace();
            throw new BizException("发送GET请求出现异常！");
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                throw new BizException("发送GET请求出现异常！");
            }
        }
        return result;
    }    
    
    
    /**
     * <p>@Description: 上传图片或文件  请求</p>
     * <p>@param @param urlStr 请求地址
     * <p>@param @param textMap 参数
     * <p>@param @param fileMap 文件
     * <p>@param @param fileName 上传对应名称
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-1上午11:06:07</p>
     */
    @SuppressWarnings("rawtypes")
	public static String formUpload(String urlStr, Map<String, String> textMap,  
            List<File> fileList,String fileName) {  
    	String postUrl=urlStr; 
    	if(!urlStr.endsWith("\\?")){
        	 urlStr=urlStr+"?";
         }
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------tamaotamaotamao"; //boundary就是request头和上传文件内容的分隔符  
        try { 
        	 StringBuffer strBuf = new StringBuffer();  
        	//生成验证参数
        	 if (textMap != null) {
        		
                 Iterator iter = textMap.entrySet().iterator();  
                 while (iter.hasNext()) {  
                     Map.Entry entry = (Map.Entry) iter.next();  
                     String inputName = (String) entry.getKey();  
                     String inputValue = (String) entry.getValue();  
                     if (inputValue == null) {  
                         continue;  
                     }  
                     if(textMap.get("account") != null){
            			 urlStr=urlStr+inputName+"="+inputValue+"&";  
            		 }else{
            			 throw new BizException("未找到对应用户信息，上传失败");
            		 }
                     strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
                             "\r\n");  
                     strBuf.append("Content-Disposition: form-data; name=\""  
                             + inputName + "\"\r\n\r\n");  
                     strBuf.append(inputValue);  
                 }  
             } 
        	 long mili=System.currentTimeMillis();
        	 postUrl=postUrl+"?sign="+Sign.md5(Sign.Encrypt(urlStr)+mili+GeneralConfig.SERVER_TOKEN)+"&timestamp="+mili;
            URL url = new URL(postUrl);  
            System.out.println("图片上传地址："+urlStr);
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn  
                    .setRequestProperty("User-Agent",  
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",  
                    "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text  
            if (textMap != null) {  
                out.write(strBuf.toString().getBytes());  
            }  
  
            // file  
            if (fileList != null && fileList.size()>0) {  
            	
            	for(File file:fileList){
            		if (file == null) {  
                        continue;  
                    }  
                    String filename = file.getName();  
                    String contentType = new MimetypesFileTypeMap()  
                            .getContentType(file);  
                   
                    if (filename.toLowerCase().endsWith(".jpg")) {  
                        contentType = "image/jpg";  
                    }  
                    if (filename.toLowerCase().endsWith(".png")) {  
                    	contentType = "image/png";  
                    }  
                    if (filename.toLowerCase().endsWith(".jpeg")) {  
                    	contentType = "image/jpeg";  
                    }  
                    if (filename.toLowerCase().endsWith(".tmp")) {  
                    	contentType = "image/png";  
                    }  
                    
                    
                    if (contentType == null || contentType.equals("")) {  
                        contentType = "application/octet-stream";  
                    }  
  
                    StringBuffer strBuf1 = new StringBuffer();  
                    strBuf1.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBuf1.append("Content-Disposition: form-data; name=\""  
                            + fileName + "\"; filename=\"" + filename  
                            + "\"\r\n");  
                    strBuf1.append("Content-Type:" + contentType + "\r\n\r\n");  
  
                    out.write(strBuf1.toString().getBytes());  
  
                    DataInputStream in = new DataInputStream(  
                            new FileInputStream(file));  
                    int bytes = 0;  
                    byte[] bufferOut = new byte[1024];  
                    while ((bytes = in.read(bufferOut)) != -1) {  
                        out.write(bufferOut, 0, bytes);  
                    }  
                    in.close();  
            	}
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
            }
            // 读取返回数据  
            StringBuffer strBuf2= new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(),"utf-8"));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf2.append(line).append("\n");  
            }  
            res = strBuf2.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
        	e.printStackTrace();
            throw new BizException("发送图片上传请求出错。");
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        return res;  
    }  
    /**
     * <p>@Description: 删除指定图片</p>
     * <p>@param @param urlStr
     * <p>@param @param images
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-8-15下午7:52:21</p>
     */
    public static void deleteImages(String urlStr,Image m){
    	//http://192.168.0.188:8080/filems/filemsApiDeleteFile?path=xx_file/userlogo/10000/8.png
    		sendGet(urlStr, "path="+m.getImageUrl());
    	
    }
    /**
     * <p>@Description: 删除指定文件</p>
     * <p>@param @param urlStr
     * <p>@param @param path 相对地址</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-10-10下午3:28:13</p>
     */
    public static void deleteImages(String urlStr,String path){
    	//http://192.168.0.188:8080/filems/filemsApiDeleteFile?path=xx_file/userlogo/10000/8.png
    	sendGet(urlStr, "path="+path);
    	
    }
    
    
    /**
     * 测试通知接口
     */
    public static void main(String[] args) {
    	 //发送 GET 请求
        //String s=HttpRequestUtil.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
        //System.out.println(s);
        
        //发送 POST 请求
       // String sr=HttpRequestUtil.sendPost("http://192.168.2.101:9090/plugins/sendnotice/sendservlet", "msg={\"message\":\"说的阿萨德\",\"flag\":\"1\"}&from=10001&to=admin");
    	String resultJson=HttpRequestUtils.sendGet("http://restapi.amap.com/v3/geocode/geo?","address=成都香瑞湖花园&output=json&key=467d44a290411f7bcc8b4ea7dce5a73c");
	 	
    	System.out.println(resultJson);
	}
    
    
    //============================以下请求经过返回异常堆栈信息==========================
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGetE(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
             String urlNameString ="";
            if(StringUtils.isNotEmpty(param)){
                  urlNameString = url + "?" + param;
            }else{
                  urlNameString = url;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            /* Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
           // System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();
            try {
                buf.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            result=expMessage;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                e2.printStackTrace(new java.io.PrintWriter(buf, true));
                String expMessage = buf.toString();
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result=expMessage;
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostE(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
           // System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();
            try {
                buf.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            result=expMessage;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                // System.out.println("发送GET请求出现异常！" + e);
                ex.printStackTrace();
                ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                ex.printStackTrace(new java.io.PrintWriter(buf, true));
                String expMessage = buf.toString();
                try {
                    buf.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                result=expMessage;
            }
        }
        return result;
    }  
    
    
}