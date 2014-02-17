package com.lianzhuzhai.babywatcher.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	/**
	 * 这个是在java里的日期格式
	 */
	public static String format = "yyyy-MM-dd HH:mm:ss";
	public static String format1 = "yyyy-MM-dd";

	/**
	 * 这个是在oracle里的日期格式
	 */
	public static String format2 = "yyyy-MM-dd HH24:mi:ss";
	public static String format3 = "yyyy-MM-dd HH24:mi:ss.S";
	public static String format4 = "yyyyMMdd";
	public static String format5 = "yyyy年MM月dd日HH时mm分ss秒";

	private final static String[] chineseWeekNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 根据给定的日期串 转化成日期
	 */
	public static Date parseDate(String date, String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date ret = formatter.parse(date);
			return ret;
		} catch (Exception ex) {
			return new Date();
		}
	}

	/**
	 * 根据给定的年、月、日 转化成日期
	 */
	public static Date parseDate(int y, int m, int d) {
		// Calendar的月份从0~11,0是1月份，1是二月份，依次类推
		Calendar ca = Calendar.getInstance();
		ca.set(y, m - 1, d, 0, 0);
		return ca.getTime();
	}

	/**
	 * 将指定日期格式化为指定格式字符串，格式可以自己定义，也可以采用 format 和 format1
	 * 
	 * 如果格式有问题，返回空字符串
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.US);
			String ret = formatter.format(date);
			return ret;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 返回格式化的日期串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, format1);
	}

	/**
	 * 返回格式化的日期时间串
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, format);
	}

	/**
	 * 得到今天的日期 没有时间
	 * 
	 * @return
	 */
	public static java.util.Date getToday() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}

	/**
	 * 得到明天,今天日期加1，含时间
	 * 
	 * @return
	 */
	public static java.util.Date getTomorrow() {
		return addDay(new java.util.Date(), 1);
	}

	/**
	 * 得到昨天,今天日期减1，含时间
	 * 
	 * @return
	 */
	public static java.util.Date getYesterday() {
		return addDay(new java.util.Date(), -1);
	}

	/**
	 * 得到指定日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getMinDay(Date date) {
		return setDay(date, 1);
	}

	/**
	 * 取某月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getMaxDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return setDay(date, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 返回某年某月有多少天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到日期的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(java.util.Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(java.util.Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到日期的天(月中)
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(java.util.Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到年，月，日的数组
	 * 
	 * @param date
	 * @return
	 */
	public static int[] getYMD(java.util.Date date) {
		int[] ymd = new int[3];
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		ymd[0] = calendar.get(Calendar.YEAR);
		ymd[1] = calendar.get(Calendar.MONTH) + 1;
		ymd[2] = calendar.get(Calendar.DAY_OF_MONTH);
		return ymd;
	}

	/**
	 * 返回 星期
	 * 
	 * @param number
	 * @return
	 */
	public static String getChineseWeek(int number) {
		if (number < 0 || number > 6) {
			return null;
		}
		return chineseWeekNames[number];
	}

	// 转化为java.sql.Date
	public static java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	// 转化为java.util.Date
	public static java.util.Date getDate(java.sql.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @return 0 : 相等 1:大于 -1：小于
	 * 
	 *         注意：这里传入的月份是真实月份，不需要减1
	 */
	public static int CompareTime(int y1, int m1, int d1, int y2, int m2, int d2) {
		int longtime1, longtime2;
		Calendar ca = Calendar.getInstance();
		ca.set(y1, m1 - 1, d1);
		longtime1 = ca.get(Calendar.DAY_OF_YEAR);
		ca.set(y2, m2 - 1, d2);
		longtime2 = ca.get(Calendar.DAY_OF_YEAR);
		if (y1 > y2)
			return 1;
		if (y1 < y2)
			return -1;
		else {
			if (longtime1 > longtime2)
				return 1;
			else if (longtime1 < longtime2)
				return -1;
			else
				return 0;
		}
	}

	/**
	 * 比较当前日期跟指定日期的大小
	 * 
	 * @param y2
	 *            年
	 * @param m2
	 *            月
	 * @param d2
	 *            日
	 * @return
	 */
	public static int CompareTime(int y2, int m2, int d2) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return CompareTime(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH) + 1, ca.get(Calendar.DATE), y2, m2, d2);
	}

	/**
	 * 比较当前日期跟指定日期的大小
	 * 
	 * @param date
	 * @return
	 */
	public static int CompareTime(Date date) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(new Date());
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(date);
		return CompareTime(ca1.get(Calendar.YEAR), ca1.get(Calendar.MONTH) + 1, ca1.get(Calendar.DATE),
				ca2.get(Calendar.YEAR), ca2.get(Calendar.MONTH) + 1, ca2.get(Calendar.DATE));
	}

	/**
	 * 比较两个指定日期的大小
	 * 
	 * @param date1
	 *            date2
	 * @return
	 */
	public static int CompareTime(Date date1, Date date2) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(date1);
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(date2);
		return CompareTime(ca1.get(Calendar.YEAR), ca1.get(Calendar.MONTH) + 1, ca1.get(Calendar.DATE),
				ca2.get(Calendar.YEAR), ca2.get(Calendar.MONTH) + 1, ca2.get(Calendar.DATE));
	}

	/**
	 * 判断某个时间是否属于一个时间段内 (y2的时间大于y1 的时间)
	 * 
	 * @param y
	 * @param m
	 * @param d
	 * @param y1
	 *            开始日期 年
	 * @param m1
	 *            开始日期 月
	 * @param d1
	 *            开始日期 日
	 * @param y2
	 *            结束日期 年
	 * @param m2
	 *            结束日期 月
	 * @param d2
	 *            结束日期 日
	 * @return
	 */
	public static boolean hasInDate(int y, int m, int d, int y1, int m1, int d1, int y2, int m2, int d2) {
		int longtime1, longtime2, longtime;
		Calendar ca = Calendar.getInstance();
		ca.set(y, m, d);
		longtime = ca.get(Calendar.DAY_OF_YEAR);
		ca.set(y1, m1, d1);
		longtime1 = ca.get(Calendar.DAY_OF_YEAR);
		ca.set(y2, m2, d2);
		longtime2 = ca.get(Calendar.DAY_OF_YEAR);
		if ((y > y1) && (y < y2))
			return true;
		else if ((y == y2) && (longtime <= longtime2))
			return true;
		else if ((y == y1) && (longtime >= longtime1))
			return true;
		else
			return false;
	}

	/**
	 * 取两个日期相减得到的月数 如 2007-04-24 和 2007-11-13 相差 6 个月
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int durnMonths(Date start, Date end) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		int startDate = calendar.get(Calendar.DATE);
		int startMonth = calendar.get(Calendar.MONTH);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.setTime(end);
		int endDate = calendar.get(Calendar.DATE);
		int endMonth = calendar.get(Calendar.MONTH);
		int endYear = calendar.get(Calendar.YEAR);
		int number = 1;
		// 年相减 X 12 + 月相减

		number = (endYear - startYear) * 12;
		number += (endMonth - startMonth);

		// 日期相减，< 0 说明不够一月，月减1
		if (endDate - startDate < 0)
			number--;
		return number;
	}

	/**
	 * 计算两个时间之间的天数差异（不足n*24小时的视为n-1天）
	 * 
	 * 注意！！！！！！ start是结束时间
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int durnDays(Date start, Date end) {
		return (int) ((end.getTime() - start.getTime()) / (86400 * 1000));
	}

	/**
	 * 根据字符串得到日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (date.length() > 10)
			return parseDate(date, format);
		else
			return parseDate(date, format1);
	}

	/**
	 * 根据字符串得到指定格式的日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String date, String pattern) {
		return parseDate(date, pattern);
	}

	/**
	 * 将日期的年份加减 只有日期 没有时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYear(java.util.Date date, int amount) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 将日期的月份加减 只有日期 没有时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonth(java.util.Date date, int amount) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 将日期的天数加减 只有日期 没有时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(java.util.Date date, int amount) {
		GregorianCalendar calendar = new GregorianCalendar();
		if (date == null)
			date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * 将日期的月份加减
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonthWithTime(java.util.Date date, int amount) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 将日期的天数加减
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDayWithTime(java.util.Date date, int amount) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 设置特定的月 包含具体时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date setMonth(java.util.Date date, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, month - 1);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 设置特定的天(月中)
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date setDay(java.util.Date date, int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 检查字符串日期是否合法
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean checkDate(String dateStr) {
		try {
			String[] dateStrs = dateStr.split("-");
			if (null == dateStrs || dateStrs.length != 3) {
				return false;
			}
			int year = Integer.parseInt(dateStrs[0]);
			int month = Integer.parseInt(dateStrs[1]);
			int day = Integer.parseInt(dateStrs[2]);

			if (month < 1 || month > 12) {
				return false;
			}
			int maxday = getDaysOfMonth(year, month);
			if (day < 1 || day > maxday) {
				return false;
			}

			if (year < 1000 || year > 9999)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getFullDateWeekTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 特殊的时间处理，计算前两个月的日期,用于客户信息投放，xiongrui@sogou-inc.com,20110429
	 * eg:2011-5-1->2011-3-1 eg:2011-4-14->2011-2-14 eg:2011-4-30->2011-3-1
	 * eg:2011-4-29->2011-3-1 eg:2011-4-28->2011-2-28
	 */
	public static String beforeTwoMonth(Date date) {

		int yearInt = DateUtils.getYear(date);
		int monthInt = DateUtils.getMonth(date);
		int dateInt = DateUtils.getDay(date);

		if (monthInt <= 2) {
			yearInt = yearInt - 1;
			monthInt += 10;
		} else {
			monthInt -= 2;
		}

		String monthStr = monthInt < 10 ? "0" + monthInt : "" + monthInt;
		String dateStr = dateInt < 10 ? "0" + dateInt : "" + dateInt;
		String beforeDate = "" + yearInt + "-" + monthStr + "-" + dateStr;

		if (DateUtils.checkDate(beforeDate)) {
			return beforeDate;
		} else {
			monthStr = (monthInt + 1) < 10 ? "0" + (monthInt + 1) : "" + (monthInt + 1);
			return "" + yearInt + "-" + monthStr + "-01";
		}
	}

	/**
	 * 将timestamp转换成date
	 * 
	 * @param tt
	 *            : Timestamp类型
	 * @return
	 */
	public static Date timestampToDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	/**
	 * 获得日期所在的季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarter(java.util.Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	/**
	 * 获取指定季度的第一天
	 * 
	 * @param quarter
	 * @return 1-1 2-4 3-7 4-10
	 */
	public static Date getFirstDayByQuarter(int year, int quarter) {
		int m = 3 * (quarter - 1) + 1;
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(year, m - 1, 1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.MILLISECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获取月度的第一天
	 * 
	 * @param year
	 * @param monthget
	 * @return
	 */
	public static Date getFirstDayByMonth(int year, int month) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(year, month - 1, 1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.MILLISECOND, 0);
		return lastDate.getTime();
	}

	public static int getDiffDays(Date d1, Date d2) {
		return (int) ((d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));
	}

	public static void main(String[] args) {

		Date d = new Date();
		String s = DateUtils.formatDate(d, format4);

		System.out.println(s);
	}

	/**
	 * 返回季度天数
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static int getDaysOfQuarter(int year, int quarter) {
		return DateUtils.getDiffDays(DateUtils.getFirstDayByQuarter(year, quarter + 1),
				DateUtils.getFirstDayByQuarter(year, quarter));
	}

	/**
	 * 获得小时时间
	 * 
	 * @param date
	 *            ：基于这个日期
	 * @param hourInterval
	 *            ：小时的差距
	 * @return
	 */
	public static Date getHourDate(Date date, int hourInterval) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.HOUR_OF_DAY, hourInterval);
		cal.set(java.util.Calendar.MINUTE, 0);
		cal.set(java.util.Calendar.SECOND, 0);
		return cal.getTime();
	}
}
