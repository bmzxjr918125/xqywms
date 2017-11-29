package com.el;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.util.DateUtils;

/**
 * <p>ClassName: Functions</p>
 * <p>@Description: 自定义EL函数</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-20上午9:38:13</p>
 */
public class Functions {
	/**
	 * <p>@Description: json中的时间转换为xx分钟前的格式</p>
	 * <p>@param @param date
	 * <p>@param @return
	 * <p>@param @throws Exception</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:38:32</p>
	 */
	public static String timeEx(Long date) throws Exception {
		// return new StringBuffer( text ).reverse().toString();
		Map<String, String> timeMap = DateUtils.getTimeDifferenceList(new Date(
				date), new Date());
		String time = "";
		if (Integer.parseInt(timeMap.get("day")) > 0) {
			time = timeMap.get("day") + "天前";
		} else if (Integer.parseInt(timeMap.get("hour")) > 0) {
			time = timeMap.get("hour") + "小时前";
		} else if (Integer.parseInt(timeMap.get("minute")) > 0) {
			time = timeMap.get("minute") + "分钟前";
		} else {
			time = "1分钟前";
		}
		return time;
	}

	/**
	 * <p>@Description: json中的时间转换为yyyy年mm月dd日</p>
	 * <p>@param @param date
	 * <p>@param @return
	 * <p>@param @throws Exception</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:04</p>
	 */
	public static String timeToChineseEx(Long date) throws Exception {
		// return new StringBuffer( text ).reverse().toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		Date dd=new Date(date);
		return sdf.format(dd);
	}

	/**
	 * <p>@Description: long型转为yyyy-MM-dd HH:mm</p>
	 * <p>@param @param date
	 * <p>@param @return
	 * <p>@param @throws Exception</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:15</p>
	 */
	public static String longEx(Long date) throws Exception {
		Date date_ = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date_);
	}

	/**
	 * <p>@Description: json中的时间转换为“今天 10:00 昨天 01:00 或2015-11-03 12:45”的格式</p>
	 * <p>@param @param date
	 * <p>@param @return
	 * <p>@param @throws Exception</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:23</p>
	 */
	public static String dateEx(Long date) throws Exception {
		// return new StringBuffer( text ).reverse().toString();
		Date formatDate = new Date(date);

		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = sdf.format(nowDate);
		nowStr = nowStr + " 23:59";
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		nowDate = sdf.parse(nowStr);

		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(nowDate);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		Date dBefore2 = new Date();
		calendar.setTime(dBefore);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore2 = calendar.getTime(); // 得到前一天的时间

		if (formatDate.after(dBefore) && formatDate.before(nowDate)) {
			// 今天
			sdf = new SimpleDateFormat("HH:mm");
			return "今天 " + sdf.format(formatDate);
		} else if (formatDate.after(dBefore2) && formatDate.before(dBefore)) {
			// 昨天
			sdf = new SimpleDateFormat("HH:mm");
			return "昨天 " + sdf.format(formatDate);
		} else {
			// 显示时间
			return sdf.format(formatDate);
		}
	}

	/**
	 * <p>@Description: 价格转换</p>
	 * <p>@param @param price
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:33</p>
	 */
	public static String getPrice(Double price) {
		DecimalFormat df = new DecimalFormat("######0.00");
		String price_str = df.format(price);
		return price_str;
	}

	/**
	 * <p>@Description: 显示价格</p>
	 * <p>@param @param price
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:39</p>
	 */
	public static String priceEx(Double price){
		NumberFormat currenct = NumberFormat.getCurrencyInstance();
		return currenct.format(price).substring(1);
	}

	/**
	 * <p>@Description: Double 转为整形</p>
	 * <p>@param @param score
	 * <p>@param @return</p>   
	 * <p>@return Integer</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:39:48</p>
	 */
	public static Integer scoreEx(Double score){
		return score.intValue();
	}
    /**
     * <p>@Description: 取绝对值</p>
     * <p>@param @param value
     * <p>@param @return</p>   
     * <p>@return Integer</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-20上午9:40:16</p>
     */
	public static Integer absEx(Integer value){
		return Math.abs(value);
	}

	/**
	 * <p>@Description: 将秒数转为时间格式</p>
	 * <p>@param @param seconds
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-20上午9:40:28</p>
	 */
	public static String formatTime(Integer seconds){
		if(seconds == null) return "";
		int hour = seconds / 3600;
		int minuts = seconds % 3600 / 60;
		//int second = seconds % 60;
		return (hour < 10 ? "0" + hour : hour+"") + ":" + (minuts < 10 ? "0" + minuts : minuts) ;
	}

}
