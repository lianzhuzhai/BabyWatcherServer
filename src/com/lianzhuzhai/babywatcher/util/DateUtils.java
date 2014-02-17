package com.lianzhuzhai.babywatcher.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	/**
	 * �������java������ڸ�ʽ
	 */
	public static String format = "yyyy-MM-dd HH:mm:ss";
	public static String format1 = "yyyy-MM-dd";

	/**
	 * �������oracle������ڸ�ʽ
	 */
	public static String format2 = "yyyy-MM-dd HH24:mi:ss";
	public static String format3 = "yyyy-MM-dd HH24:mi:ss.S";
	public static String format4 = "yyyyMMdd";
	public static String format5 = "yyyy��MM��dd��HHʱmm��ss��";

	private final static String[] chineseWeekNames = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };

	/**
	 * ���ݸ��������ڴ� ת��������
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
	 * ���ݸ������ꡢ�¡��� ת��������
	 */
	public static Date parseDate(int y, int m, int d) {
		// Calendar���·ݴ�0~11,0��1�·ݣ�1�Ƕ��·ݣ���������
		Calendar ca = Calendar.getInstance();
		ca.set(y, m - 1, d, 0, 0);
		return ca.getTime();
	}

	/**
	 * ��ָ�����ڸ�ʽ��Ϊָ����ʽ�ַ�������ʽ�����Լ����壬Ҳ���Բ��� format �� format1
	 * 
	 * �����ʽ�����⣬���ؿ��ַ���
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
	 * ���ظ�ʽ�������ڴ�
	 */
	public static String formatDate(Date date) {
		return formatDate(date, format1);
	}

	/**
	 * ���ظ�ʽ��������ʱ�䴮
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, format);
	}

	/**
	 * �õ���������� û��ʱ��
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
	 * �õ�����,�������ڼ�1����ʱ��
	 * 
	 * @return
	 */
	public static java.util.Date getTomorrow() {
		return addDay(new java.util.Date(), 1);
	}

	/**
	 * �õ�����,�������ڼ�1����ʱ��
	 * 
	 * @return
	 */
	public static java.util.Date getYesterday() {
		return addDay(new java.util.Date(), -1);
	}

	/**
	 * �õ�ָ�����������µĵ�һ��
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getMinDay(Date date) {
		return setDay(date, 1);
	}

	/**
	 * ȡĳ�µ����һ��
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
	 * ����ĳ��ĳ���ж�����
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
	 * �õ����ڵ����
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
	 * �õ����ڵ��·�
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
	 * �õ����ڵ���(����)
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
	 * �õ��꣬�£��յ�����
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
	 * ���� ����
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

	// ת��Ϊjava.sql.Date
	public static java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	// ת��Ϊjava.util.Date
	public static java.util.Date getDate(java.sql.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * �Ƚ�����ʱ��Ĵ�С
	 * 
	 * @return 0 : ��� 1:���� -1��С��
	 * 
	 *         ע�⣺���ﴫ����·�����ʵ�·ݣ�����Ҫ��1
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
	 * �Ƚϵ�ǰ���ڸ�ָ�����ڵĴ�С
	 * 
	 * @param y2
	 *            ��
	 * @param m2
	 *            ��
	 * @param d2
	 *            ��
	 * @return
	 */
	public static int CompareTime(int y2, int m2, int d2) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return CompareTime(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH) + 1, ca.get(Calendar.DATE), y2, m2, d2);
	}

	/**
	 * �Ƚϵ�ǰ���ڸ�ָ�����ڵĴ�С
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
	 * �Ƚ�����ָ�����ڵĴ�С
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
	 * �ж�ĳ��ʱ���Ƿ�����һ��ʱ����� (y2��ʱ�����y1 ��ʱ��)
	 * 
	 * @param y
	 * @param m
	 * @param d
	 * @param y1
	 *            ��ʼ���� ��
	 * @param m1
	 *            ��ʼ���� ��
	 * @param d1
	 *            ��ʼ���� ��
	 * @param y2
	 *            �������� ��
	 * @param m2
	 *            �������� ��
	 * @param d2
	 *            �������� ��
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
	 * ȡ������������õ������� �� 2007-04-24 �� 2007-11-13 ��� 6 ����
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
		// ����� X 12 + �����

		number = (endYear - startYear) * 12;
		number += (endMonth - startMonth);

		// ���������< 0 ˵������һ�£��¼�1
		if (endDate - startDate < 0)
			number--;
		return number;
	}

	/**
	 * ��������ʱ��֮����������죨����n*24Сʱ����Ϊn-1�죩
	 * 
	 * ע�⣡���������� start�ǽ���ʱ��
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int durnDays(Date start, Date end) {
		return (int) ((end.getTime() - start.getTime()) / (86400 * 1000));
	}

	/**
	 * �����ַ����õ�����
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
	 * �����ַ����õ�ָ����ʽ������
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String date, String pattern) {
		return parseDate(date, pattern);
	}

	/**
	 * �����ڵ���ݼӼ� ֻ������ û��ʱ��
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
	 * �����ڵ��·ݼӼ� ֻ������ û��ʱ��
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
	 * �����ڵ������Ӽ� ֻ������ û��ʱ��
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
	 * �����ڵ��·ݼӼ�
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
	 * �����ڵ������Ӽ�
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
	 * �����ض����� ��������ʱ��
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
	 * �����ض�����(����)
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
	 * ����ַ��������Ƿ�Ϸ�
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
	 * �����ʱ�䴦������ǰ�����µ�����,���ڿͻ���ϢͶ�ţ�xiongrui@sogou-inc.com,20110429
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
	 * ��timestampת����date
	 * 
	 * @param tt
	 *            : Timestamp����
	 * @return
	 */
	public static Date timestampToDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	/**
	 * ����������ڵļ���
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
	 * ��ȡָ�����ȵĵ�һ��
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
	 * ��ȡ�¶ȵĵ�һ��
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
	 * ���ؼ�������
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
	 * ���Сʱʱ��
	 * 
	 * @param date
	 *            �������������
	 * @param hourInterval
	 *            ��Сʱ�Ĳ��
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
