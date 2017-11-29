package com.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.el.Functions;

/**
 * <p>ClassName: DateUtils</p>
 * <p>@Description: 时间计算的相关方法</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午4:51:27</p>
 */
public class DateUtils {
	/**
	 * 获取时间差 天 小时 分
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDifference(Date startTime, Date endTime)
			throws Exception {

		Date begin = startTime;
		Date end = endTime;
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between % 60 % 60;
		String result = "" + day + "天" + hour + "小时" + minute + "分" + second
				+ "秒";

		return result;
	}
	/**
	 * 获取时间差 小时 分
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDifferenceHour(Date startTime, Date endTime)
			throws Exception {

		Date begin = startTime;
		Date end = endTime;
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		//long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between % 60 % 60;
		String result =hour + "小时" + minute + "分" + second
				+ "秒";

		return result;
	}


	/**
	 * 获取时间差
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimePoor(Date startTime,Date endTime){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(startTime);
		long estimatetime = calendar1.getTimeInMillis();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(endTime);
		long nowtime = calendar2.getTimeInMillis();
		long time = estimatetime - nowtime;
		long days = time / (1000 * 60 * 60 * 24);
		long hours = (time - days * (1000 * 60 * 60 * 24))/(1000 * 60 * 60);
		long minutes = (time - days * (1000 * 60 * 60 * 24) - hours*(1000 * 60 * 60))/(1000 * 60);
		String result = Math.abs(days) + "天" + Math.abs(hours) + "小时" + Math.abs(minutes) + "分钟";
		return result;
	}


	/**
	 * 获取时间差 集合
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Map<String, String> getTimeDifferenceList(Date startTime,
			Date endTime) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
      if(startTime==null||endTime==null){
    	  map.put("day","0");
    	  map.put("hour","0");
    	  map.put("minute","1");
      }else{
    	  Date begin = startTime;
  		Date end = endTime;
  		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

  		long day = between / (24 * 3600);
  		long hour = between % (24 * 3600) / 3600;
  		long minute = between % 3600 / 60;
  		// long second=between%60/60;

  		map.put("day", day + "");
  		map.put("hour", hour + "");
  		map.put("minute", minute + "");
      }

		return map;
	}

	/**
	 * 获取时间差 秒数
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long getTimeDiffSeconds(Date startTime, Date endTime)
			throws Exception {

		Date begin = startTime;
		Date end = endTime;
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		return between;
	}

	/**
	 * 时间格式化
	 *
	 * @param time
	 *            需格式的时间 秒数 （秒）
	 * @return
	 */
	public static String formatMS(long time) throws Exception {

		long day = time / (24 * 3600);
		long hour = time % (24 * 3600) / 3600;
		long minute = time % 3600 / 60;
		long second = time % 60 / 60;
		String result = "";
		if (day == 0) {
			if (hour == 0) {
				if (minute == 0) {
					result = "" + second + "秒";
				} else {
					result = "" + minute + "分" + second + "秒";
				}
			} else {
				result = "" + hour + "小时" + minute + "分" + second + "秒";
			}
		} else {
			result = "" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
		}

		return result;
	}



	/**
	 * 时间格式化
	 *
	 * @param time
	 *            需格式的时间 秒数 （秒）
	 * @return
	 */
	public static String formatMSEn(long time) throws Exception {

		long day = time / (24 * 3600);
		long hour = time % (24 * 3600) / 3600;
		long minute = time % 3600 / 60;
		String result = "";
		if (day < 0) {
			if (hour <0) {
				if (minute < 0) {
					result = "0h 0m";
				} else {
					result = "0h" + minute + "m";
				}
			} else {
				result = "" + hour + "h" + minute + "m";
			}
		} else {
			result = "" + day + "d" + hour + "h" + minute + "m" ;
		}

		return result;
	}


	/**
	 * 获取对应星期
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getWeek(Date date) {
		String[] weekDays = DateFormatSymbols.getInstance(Locale.CHINA)
				.getWeekdays();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		return weekDays[week];

	}

	/** 计算年龄 */
	public static String getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("日期错误!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}

		return age + "";
	}

	/**
	 * 毫秒时间转换为xx分钟前的格式
	 *
	 * @param text
	 * @return
	 */
	public static String timeToStr(Long date) throws Exception {
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
     * 得到指定时间月第一天的日期
     * @Methods Name getFirstDayOfMonth
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date)  throws ParseException {
        Calendar cDay = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
      String str=  sdf.format(date)+" 00:00:00";
      sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cDay.setTime( sdf.parse(str));
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        return cDay.getTime();
    }

    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     */
    public static int compareDate(String date1,String date2,int stype){
        int n = 0;

       // String[] u = {"天","月","年"};
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";

        date2 = date2==null?getCurrentDate():date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        //List list = new ArrayList();
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }

        n = n-1;

        if(stype==2){
            n = (int)n/365;
        }

       // System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);
        return n;
    }


    /**
     * 得到当前日期
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

	/**
	 * <p>Description : 将日期字符串转为Date类型</p>
	 * <p>Author : XQL</p>
	 * <p>Date : 2016/4/28 17:37</p>
	 *
	 * @param dateStr 日期字符串
	 * @param format 格式
     * @return
     */
	public static Date formatDate(String dateStr,String format){
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * <p>Descritpion : 将Date转为字符串 </p>
	 * <p>Author : XQL </p>
	 * <p>Date : 2015/12/17 0017 下午 9:10</p>
	 *
	 * @param date 日期
	 * @param format 格式
	 */
	public static String formatDate(Date date,String format){
		if(date == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * <p>Description : 将日期转为字符串</p>
	 * <p>Author : XQL</p>
	 * <p>Date : 2016/1/9 14:07</p>
	 *
	 * @param date 要格式化的日期
	 * @return
	 *
	 * 格式化日期规则：<br />
	 * 刚刚[1分钟内]
	 * xx分钟前
	 * xx小时前
	 * 昨天 00:00
	 * 前天 00:00
	 * xx-xx xx:xx
	 * xxxx-xx-xx xx:xx
	 *
	 */
	public static String formatDate(Date date){
		Calendar current = Calendar.getInstance();
		current.setTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//年份相同
		if(current.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
			//月份相同
			if(current.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)){
				//日期相同
				if(current.get(Calendar.DATE) == calendar.get(Calendar.DATE)){
					//小时相同
					if(current.get(Calendar.HOUR) == calendar.get(Calendar.HOUR)){
						//分钟相同
						if(current.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)){
							return "刚刚";
						}else{
							int difMinute = current.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE);
							return difMinute > 0 ? difMinute + "分钟前" : (difMinute * -1) + "分钟后";
						}
					}else{
						int difHour = current.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY);
						return difHour > 0 ? difHour + "小时前" : (difHour * -1)+ "小时后";
					}
				}else{
					int difDate = current.get(Calendar.DATE) - calendar.get(Calendar.DATE);
					if(difDate == 1){
						//return "昨天 " + formatDate(date,"HH:mm");
						return "1天前";
					}else if(difDate == 2){
						//return "前天 " + formatDate(date,"HH:mm");
						return "2天前";
					}else{
						//return formatDate(date,"MM-dd HH:mm");
						return "3天前";
					}
				}
			}else{
				//return formatDate(date,"MM-dd HH:mm");
				return "3天前";
			}
		}else{
			//return formatDate(date,"yyyy-MM-dd HH:mm");
			return "3天前";
		}
	}

	/**
	 * 上一个月的第一天
	 * @return
	 */
	public static String getLastMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		// 设置为1号,当前日期既为本月第一天
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = format.format(cal.getTime());
		return firstDay;
	}
	
	/**
	 * 上月最后一天
	 * @return
	 */
	public static String getLastMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cale = Calendar.getInstance();
		//设置为1号,当前日期既为本月第一天 
		cale.set(Calendar.DAY_OF_MONTH,0);
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}
	
    public static void main(String[] args) throws ParseException {
    	//System.out.println(getLastMonthLastDay());
    	//System.out.println("-----1------firstDay:"+firstDay);
	}
	/*Calendar calendar = new GregorianCalendar();
    // 1. 当前登陆日期的周一至周五的日期 比如 现在是2012-06-11 周一是2012-06-11 周五就是 2012-06-15。
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        System.out.println("登录日期的周一：" + print(calendar.getTime()));
        // 2.星期五，第六天s
        calendar.set(Calendar.DAY_OF_WEEK, 6);
        System.out.println("登录日期的周五：" + print(calendar.getTime()));
        // 3.当前月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("当前月的第一天：" + print(calendar.getTime()));
        // 4.当前月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        System.out.println("当前月的最后一天：" + print(calendar.getTime()));

        // 季度初
        calendar.setTime(new Date());
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("当季度的第一天：" + print(calendar.getTime()));
        // 季度末
        calendar.setTime(new Date());
        month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        System.out.println("当前时间的季度末：" + print(calendar.getTime()));
    }  */


    /**
     * 获取当天起始时间
     * @return
     */
    public static String getStartTime(){  
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(todayStart.getTime().getTime());  
    }
    
    /**
     * 获取当天结束时间
     * @return
     */
    public static String getEndTime(){  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(todayEnd.getTime().getTime());
    }  
    
	public static Long dateDiff(String startTime, String endTime,  String format, String str) {   
        SimpleDateFormat sd = new SimpleDateFormat(format);    
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff;    
        long day = 0;    
        long hour = 0;    
        long min = 0;    
        // 获得两个时间的毫秒时间差异    
        try {    
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();    
            day = diff / nd;// 计算差多少天    
            hour = diff % nd / nh + day * 24;// 计算差多少小时    
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟    
            if (str.equalsIgnoreCase("h")) {    
                return hour;    
            } else {    
                return min;    
            }    
   
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        if (str.equalsIgnoreCase("h")) {    
            return hour;    
        } else {    
            return min;    
        }    
}  

	public static String getDifference(Date createDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse(sdf.format(getTimeOf24()));
		long l = date.getTime() -(new Date()).getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return   (hour<10?("0"+hour):hour)+ ":" + (min<10?("0"+min):min) + ":" + (s<10?("0"+s):s);
	}
	private static Date getTimeOf24() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime();
    }
	
	public static String change(int second){  
		int h = 0;
		int d = 0;
		int s = 0;
		if (second != 0) {
			int temp = second % 3600;
			if (second > 3600) {
				h = second / 3600;
				if (temp != 0) {
					if (temp > 60) {
						d = temp / 60;
						if (temp % 60 != 0) {
							s = temp % 60;
						}
					} else {
						s = temp;
					}
				}
			} else {
				d = second / 60;
				if (second % 60 != 0) {
					s = second % 60;
				}
			}
		}

		return  (h<10?("0"+h):h)+ ":" + (d<10?("0"+d):d) + ":" + (s<10?("0"+s):s);
	}
	
	public static double  mrandom(int max,int min) {
		Random random = new Random();
		double s = random.nextInt(max)% (max - min + 1) + min;
		return Double.parseDouble(Functions.priceEx(s));

	}

}
