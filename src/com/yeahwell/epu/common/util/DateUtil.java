package com.yeahwell.epu.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeahwell.epu.common.constants.Constants;

/**
 * 
 * @author yeahwell
 */
public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 年月日pattern
	 */
	public static final String PATTERN_YYYY_MM_DD_1 = "yyyy-MM-dd";
	public static final String PATTERN_YYYY_MM_DD_2 = "yyyy.MM.dd";
	public static final String PATTERN_YYYY_MM_DD_3 = "yyyyMMdd";

	public static final String PATTERN_HH_MM_1 = "HH:mm";

	/**
	 * 年月日时分秒pattern
	 */
	public static final String PATTERN_FULL_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_FULL_2 = "yyyyMMddHHmmss";
	public static final String PATTERN_FULL_3 = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_FULL_4 = "yyyy.MM.dd HH.mm.ss";

	private DateUtil() {

	}

	public static class DateRange {
		public Date to;
		public Date from;
	}

	public static DateRange calculateOffset(final String code) {
		final Calendar calendar = GregorianCalendar.getInstance();
		final DateRange range = new DateRange();
		range.to = calendar.getTime();
		int offsetType;
		if (code.endsWith("m")) {
			offsetType = Calendar.MONTH;
		} else if (code.endsWith("d")) {
			offsetType = Calendar.DATE;
		} else if (code.endsWith("y")) {
			offsetType = Calendar.YEAR;
		} else {
			return null;
		}
		final String amountStr = code.substring(0, code.length() - 1);
		int amount = 0;
		try {
			amount = Integer.valueOf(amountStr);
		} catch (final Exception e) {
			logger.info("offset code is malformed");
			return null;
		}
		if (code.endsWith("d")) {
			range.from = format2Date(new Date());
		} else {
			calendar.add(offsetType, -amount);
			range.from = calendar.getTime();
		}
		return range;
	}

	/**
	 * @功能 在原日期的基础上添加或者减少几个月
	 * @param srcDate
	 *            原日期
	 * @param num
	 *            offset,为正数则增加num个月,为负数则减少num个月
	 * @return
	 */
	public static Date addMonth(final Date srcDate, final int num) {
		final Calendar startDT = Calendar.getInstance();
		startDT.setTime(srcDate);
		startDT.add(Calendar.MONTH, num);
		return startDT.getTime();
	}

	/**
	 * @功能 在原日期的基础上添加或者减少几天
	 * @param srcDate
	 *            原日期
	 * @param num
	 *            offset,为正数则增加num天,为负数则减少num天
	 * @return
	 */
	public static Date addDay(final Date srcDate, final int num) {
		final Calendar startDT = Calendar.getInstance();
		startDT.setTime(srcDate);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	public static long dateDiff(final Date date1, final Date date2) {
		long n1 = date1.getTime();
		long n2 = date2.getTime();
		long diff = Math.abs(n1 - n2);
		diff /= 3600 * 1000 * 24;
		return diff;
	}

	public static Date str2Date(final String pattern, final String strDate) {
		final DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date resultDate = null;
		try {
			resultDate = dateFormat.parse(strDate);
		} catch (ParseException e) {
			logger.info("日期格式转换错误" + e.getMessage());
		}
		return resultDate;
	}

	/**
	 * 将字符串转换成日期
	 */
	public static Date str2Date(final String strDate) {
		return str2Date(PATTERN_FULL_1);
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 得到当前日期的时间戳
	 * 
	 * @return
	 */
	public static Long getCurrentTimestamp() {
		Date date = new Date();
		Long result = date.getTime() / 1000;
		return result;
	}

	public static Date format2Date(final Date date) {
		final DateFormat dateFormat = new SimpleDateFormat(PATTERN_YYYY_MM_DD_3);
		final String shortDate = dateFormat.format(date);
		final String resultDate = shortDate.substring(0, 4) + "-"
				+ shortDate.substring(4, 6) + "-" + shortDate.substring(6, 8)
				+ " " + Constants.START_HHMMSS;
		return str2Date(resultDate);
	}

	/**
	 * 格式化日期为标准形式
	 */
	public static String formatDate(Date srcDate, final String pattern) {
		if(null == srcDate){
			srcDate = getCurrentTime();
		}
		final DateFormat dateFormate = new SimpleDateFormat(pattern);
		return dateFormate.format(srcDate);
	}

	/**
	 * 格式化日期为yyyy-MM-dd形式
	 */
	public static String formatDate1(final Date srcDate) {
		return formatDate(srcDate, PATTERN_YYYY_MM_DD_1);
	}

	/**
	 * 格式化日期为yyyy.MM.dd形式
	 * 
	 * @param srcDate
	 * @return
	 */
	public static String formatDate2(final Date srcDate) {
		return formatDate(srcDate, PATTERN_YYYY_MM_DD_2);
	}

	/**
	 * 格式化日期为yyyyMMdd形式
	 * 
	 * @param srcDate
	 * @return
	 */
	public static String formatDate3(final Date srcDate) {
		return formatDate(srcDate, PATTERN_YYYY_MM_DD_3);
	}

	public static String formatDateTime1(final Date srcDate) {
		return formatDate(srcDate, PATTERN_FULL_1);
	}

	public static String formatDateTime2(final Date srcDate) {
		return formatDate(srcDate, PATTERN_FULL_2);
	}

	public static String formatDateTime3(final Date srcDate) {
		return formatDate(srcDate, PATTERN_FULL_3);
	}

	public static String formatDateTime4(final Date srcDate) {
		return formatDate(srcDate, PATTERN_FULL_4);
	}

	public static String formatTime(final Date srcDate) {
		return formatDate(srcDate, PATTERN_HH_MM_1);
	}

	/**
	 * 把一个10位数字的Unix时间戳转换成格式化的日期格式
	 * 
	 * @param timestamp
	 *            被转换的10为长整数Unix时间戳
	 * @return java.util.Date 格式化过的日期
	 */
	public static Date timestamp2Date(long timestamp) {
		return new Date(timestamp * 1000);
	}

	/**
	 * 把一个10位数字的Unix时间戳转换成格式化的日期格式字符串
	 * 
	 * @param pattern
	 *            格式化格式如yyyy-MM-dd HH:mm:ss等
	 * @param timestamp
	 *            被转换的10为长整数Unix时间戳
	 * @return String 格式化过的日期
	 */
	public static String timestamp2FormatDate(String pattern, long timestamp) {
		if ((pattern == null) || pattern.isEmpty()) {
			pattern = PATTERN_FULL_1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String resultTime = ""; // 返回结果
		Date resultDate = new Date();
		resultDate.setTime(timestamp * 1000); // 毫秒数乘以1000
		resultTime = sdf.format(resultDate);
		return resultTime;
	}

	/**
	 * 把指定日期时间转换成Unix时间戳
	 * 
	 * @param pattern
	 *            根据目标时间来决定格式化方式,一定要保持一致.如2013-01-31，则patter为yyyy-MM-dd
	 * @param srcTime
	 *            目标时间 如2013-01-31
	 * @return 转换后的时间戳
	 */
	public static long date2Timestamp(Date srcDate) {
		long resultTime = srcDate.getTime() / 1000;
		return resultTime;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentTimestamp());
		System.out.println(DateUtil.timestamp2FormatDate("yyyy-MM-dd HH:mm:ss",
				1359609038));
		System.out.println(DateUtil.date2Timestamp(new Date()));
		System.out.println(DateUtil.formatDate(null, PATTERN_FULL_1));
	}
}