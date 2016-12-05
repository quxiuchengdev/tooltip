package com.qxcwl.tooltip.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author 曲修成
 * @ClassName DateUtils
 * @Description
 * @Date 2016-11-04 13:15:00
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	//一天的毫秒数 86400000 = 24*60*60*1000;
	private static final int millisPerDay = 86400000;
	//一小时的毫秒数 3600000 = 24*60*60*1000;
	private static final int millisPerHour = 3600000;

	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 *
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算时间差 (时间单位,开始时间,结束时间)
	 * 调用方法 howLong("h","2007-08-09 10:22:26","2007-08-09 20:21:30") ///9小时56分 返回9小时
	 */
	public static long howLong(String unit, Date time1, Date time2) {
		try {
			// 时间单位(如：不足1天(24小时) 则返回0)，开始时间，结束时间
			Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(time1));
			Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(time2));
			long ltime = date1.getTime() - date2.getTime() < 0 ? date2.getTime() - date1.getTime() : date1.getTime() - date2.getTime();
			if (unit.equals("s")) {
				return ltime / 1000;// 返回秒
			} else if (unit.equals("m")) {
				return ltime / 60000;// 返回分钟
			} else if (unit.equals("h")) {
				return ltime / millisPerHour;// 返回小时
			} else if (unit.equals("d")) {
				return ltime / millisPerDay;// 返回天数
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 计算系统日期与目标日期的时间差, 检查传入时间是否早于系统时间， 不符合则返回""
	 *
	 * @param tar 与系统时间对比的目标日期
	 * @return
	 */
	public static String getIntervalString(Date tar) {
		return getIntervalString(new Date(), tar, true);
	}

	/**
	 * 计算系统日期与目标日期的相隔天数
	 *
	 * @param tar 与系统时间对比的目标日期
	 * @return 相隔天数, 参数无效返回-1
	 */
	public static int getIntervalDay(Date tar) {
		int ret = -1;
		Calendar calNow = Calendar.getInstance();
		if (null != tar && tar.before(calNow.getTime())) {// 参数有效

			// 获得指定时间的Calendar
			Calendar calTar = Calendar.getInstance();
			calTar.setTime(tar);

			long millisNow = calNow.getTimeInMillis();
			long millisTar = tar.getTime();

			// 指定时间小于系统时间才处理， 否则返回空字符串
			if (millisTar < millisNow) {
				// 86400000 = 24*60*60*1000;
				ret = (int) ((millisNow - millisTar) / (millisPerDay));
			}
		}
		return ret;
	}

	/**
	 * @param tar     与系统时间对比的目标日期(字符串格式)
	 * @param formats 解析日期的格式
	 * @return
	 */
	public static String getIntervalString(String tar, String[] formats) {
		Date dTar = null;
		try {
			dTar = DateUtils.parseDate(tar, formats);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return getIntervalString(dTar);
	}

	/**
	 * 获得指定年月的天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	private static int getDaysOfMonth(int year, int month) {
		int day = 0;
		switch (month) {
			// 大月
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			// 小月
			case 4:
			case 6:
			case 9:
			case 11:
				day = 30;
				break;
			case 2:
				if ((0 == year % 400) || (0 == year % 4 && 0 != year % 100)) {// 闰年
					day = 29;
				} else {
					day = 28;
				}
				break;
		}

		// System.out.println("--nian:" + year + "--月:" + month + "--天数:" +
		// day);
		return day;
	}

	/**
	 * 计算两个日期的差， 返回如"1年2月14天11.5小时"的字符串， 异常情况返回""
	 *
	 * @param after
	 * @param before
	 * @return
	 */
	private static String getIntervalString(Date after, Date before) {
		StringBuffer ret = new StringBuffer();

		Calendar calAfter = Calendar.getInstance();
		calAfter.setTime(after);

		// 获得指定时间的Calendar
		Calendar calBefore = Calendar.getInstance();
		calBefore.setTime(before);

		float hour = 0F;
		int day = 0;
		int month = 0;
		int year = 0;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(1);// 保留1位小数

		// 计算小时
		hour = ((calAfter.getTimeInMillis() - calBefore.getTimeInMillis()) % millisPerDay) / (float) millisPerHour;

		// 计算天
		day = calAfter.get(Calendar.DAY_OF_MONTH) - calBefore.get(Calendar.DAY_OF_MONTH);
		if (day < 0) {
			day = day + getDaysOfMonth(calAfter.get(Calendar.YEAR), calAfter.get(Calendar.MONTH));
			month--;
		}

		// 计算月
		month = month + calAfter.get(Calendar.MONTH) - calBefore.get(Calendar.MONTH);
		if (month < 0) {
			month = month + 12;
			year--;
		}

		// 计算年
		year = year + calAfter.get(Calendar.YEAR) - calBefore.get(Calendar.YEAR);

		if (year > 0) {
			ret.append(year).append("年");
		}
		if (month > 0) {
			ret.append(month).append("月");
		}
		if (day > 0) {
			ret.append(day).append("天");
		}
		if (hour > 0F) {
			ret.append(nf.format(hour)).append("小时");
		}
		return ret.toString();
	}

	/**
	 * 计算两个日期的差， 返回如"1年2月14天"的字符串， 异常情况返回""
	 *
	 * @param after
	 * @param before
	 * @param check  是否检查参数 after.before(before), 检查不符合返回""
	 */
	public static String getIntervalString(Date after, Date before, boolean check) {
		if (null != after && null != before) {// 参数有效
			if (after.before(before)) {// 期望tar1日期更大
				if (check) {// 强制检查则返""
					return "";
				} else {// 调换
					Date tmpDate = after;
					after = before;
					before = tmpDate;
				}
			}

			return getIntervalString(after, before);
		} else {
			return "";
		}
	}

	/**
	 * 获取过去的分钟
	 *
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 解析timestamp时间戳
	 *
	 * @param time
	 * @param pattern
	 * @return
	 * @Title parseTimestamp
	 * @Description
	 * @author 唐建飞
	 * @date 2016年2月26日 下午5:54:26
	 */
	public static String parseTimestamp(Long time, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date(time);
		return sdf.format(date);
	}

	public static boolean isValidDate(String str, String format) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			sf.setLenient(false);
			sf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 当月第一天
	 *
	 * @param date
	 * @return
	 * @Title getMonthFirstDay
	 * @Description
	 * @author 曲修成
	 * @date 2016年9月23日 上午10:16:13
	 */
	public static String getMonthFirstDay(Date date) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(date);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}

	/**
	 * 当月最后一天
	 *
	 * @return
	 * @Title getMonthLastDay
	 * @Description
	 * @author 曲修成
	 * @date 2016年9月23日 上午10:16:24
	 */
	public static String getMonthLastDay(Date date) {
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return str.toString();
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar cal = toCalendar(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		Date first = cal.getTime();
		return first;
	}

	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar cal = toCalendar(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date last = cal.getTime();

		return last;
	}

	/**
	 * 获得某个月里共有几天
	 *
	 * @param time
	 * @return
	 */
	public static Integer countDay(Date time) {
		Date date = time;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取日期之间的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR)
				- d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 获取工作日
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getWorkingDay(Calendar d1, Calendar d2) {
		int result = -1;
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		// int betweendays = getDaysBetween(d1, d2);
		// int charge_date = 0;
		int charge_start_date = 0;// 开始日期的日期偏移量
		int charge_end_date = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
			charge_end_date = etmp - 1;
		}
		// }
		result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7)
				* 5 + charge_start_date - charge_end_date;
		// System.out.println("charge_start_date>" + charge_start_date);
		// System.out.println("charge_end_date>" + charge_end_date);
		// System.out.println("between day is-->" + betweendays);
		return result;
	}

	/**
	 * 获取中文日期
	 * @param date
	 * @return
	 */
	public String getChineseWeek(Calendar date) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		// System.out.println(dayNames[dayOfWeek - 1]);
		return dayNames[dayOfWeek - 1];
	}

	/**
	 * 获得日期的下一个星期一的日期
	 * @param date
	 * @return
	 */
	public Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 获取休息日
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getHolidays(Calendar d1, Calendar d2) {
		return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
	}

}
