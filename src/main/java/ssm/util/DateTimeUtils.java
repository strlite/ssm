package ssm.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @author 王建洪
 *
 */
public class DateTimeUtils {

	/**
	 * 时间格式
	 */
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Title: Date
	 * @Description: 格式化当前时间
	 * @return String 返回类型
	 */
	public static String date() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return df.format(LocalDateTime.now());
	}

	/**
	 * 
	 * @Title: Date
	 * @Description: 格式化当前时间
	 * @param pattern
	 *            时间格式
	 * @return String 返回时间格式
	 */
	public static String date(String pattern) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
		return df.format(LocalDateTime.now());
	}

	/**
	 * 将日期时间转换为时间戳
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Long dateToLong(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static String longToDate(Long time) {
		Date date = new Date();
		date.setTime(time);
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return sdf.format(date);
	}

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
	}

	/**
	 * 
	 * @Title: Date
	 * @Description: 格式化当前时间
	 * @param pattern
	 *            时间格式
	 * @param timeStap
	 *            时间戳
	 * @return String 返回时间格式
	 */
	public static String date(String pattern, String timeStap) {
		if (timeStap.equals("")) {
			timeStap = String.valueOf(System.currentTimeMillis());
		}
		if (timeStap.length() != 13 && timeStap.length() != 10) {
			DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
			return df.format(LocalDateTime.now());
		} else if (timeStap.length() == 10) {
			timeStap = String.valueOf(Long.parseLong(timeStap) * 1000L);
		}
		if (pattern.equals("")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(Long.parseLong(timeStap)));
	}

	/**
	 * 
	 * @Title: getAgeFromBirthTime
	 * @Description: 根据出生日期计算年龄
	 * @param birthTimeString
	 *            如 "1994-11-14"
	 * @return
	 * @return int 返回类型
	 */
	public static int getAgeFromBirthTime(String birthTimeString) {
		// 先截取到字符串中的年、月、日
		String strs[] = birthTimeString.trim().split("-");
		int selectYear = Integer.parseInt(strs[0]);
		int selectMonth = Integer.parseInt(strs[1]);
		int selectDay = Integer.parseInt(strs[2]);
		// 得到当前时间的年、月、日
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayNow = cal.get(Calendar.DATE);

		// 用当前年月日减去生日年月日
		int yearMinus = yearNow - selectYear;
		int monthMinus = monthNow - selectMonth;
		int dayMinus = dayNow - selectDay;

		int age = yearMinus;// 先大致赋值
		if (yearMinus < 0) {// 选了未来的年份
			age = 0;
		} else if (yearMinus == 0) {// 同年的，要么为1，要么为0
			if (monthMinus < 0) {// 选了未来的月份
				age = 0;
			} else if (monthMinus == 0) {// 同月份的
				if (dayMinus < 0) {// 选了未来的日期
					age = 0;
				} else if (dayMinus >= 0) {
					age = 1;
				}
			} else if (monthMinus > 0) {
				age = 1;
			}
		} else if (yearMinus > 0) {
			if (monthMinus < 0) {// 当前月>生日月
			} else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
				if (dayMinus < 0) {
				} else if (dayMinus >= 0) {
					age = age + 1;
				}
			} else if (monthMinus > 0) {
				age = age + 1;
			}
		}
		return age;
	}

	/**
	 * 
	 * @Title: getAgeFromBirthTime
	 * @Description: 根据时间戳计算年龄
	 * @param birthTimeLong
	 *            时间戳
	 * @return
	 * @return int 返回类型
	 */
	public static int getAgeFromBirthTime(long birthTimeLong) {
		Date date = new Date(birthTimeLong * 1000l);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String birthTimeString = format.format(date);
		return getAgeFromBirthTime(birthTimeString);
	}

}
