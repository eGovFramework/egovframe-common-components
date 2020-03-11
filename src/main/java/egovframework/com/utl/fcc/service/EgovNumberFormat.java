package egovframework.com.utl.fcc.service;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 숫자, 통화, 퍼센트에 대한 형식 변환을 수행하는 클래스
 */
public class EgovNumberFormat {

	private static final int MAX_FRACTION_DIGIT = 3;
	private static final boolean GROUPING_USED = true; 
	
	/**
	 * 기본 Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param number 숫자
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Number number) {
		return formatNumber(number, GROUPING_USED, MAX_FRACTION_DIGIT);
	}
	
	/**
	 * Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Locale locale, Number number) {
		return formatNumber(locale, number, GROUPING_USED, MAX_FRACTION_DIGIT);
	}
	
	/**
	 * 기본 Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Number number, boolean groupingUsed) {
		return formatNumber(number, groupingUsed, MAX_FRACTION_DIGIT);
	}

	/**
	 * Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Locale locale, Number number, boolean groupingUsed) {
		return formatNumber(locale, number, groupingUsed, MAX_FRACTION_DIGIT);
	}

	/**
	 * 기본 Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param number 숫자
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Number number, int maxFactionDigits) {
		return formatNumber(number, GROUPING_USED, maxFactionDigits);
	}
	
	/**
	 * Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Locale locale, Number number, int maxFactionDigits) {
		return formatNumber(locale, number, GROUPING_USED, maxFactionDigits);
	}

	/**
	 * 기본 Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Number number, boolean groupingUsed, int maxFactionDigits) {
		NumberFormat numberberFormat = NumberFormat.getNumberInstance();
		numberberFormat.setGroupingUsed(groupingUsed);		
		numberberFormat.setMaximumFractionDigits(maxFactionDigits);
		return numberberFormat.format(number);
	}
	
	/**
	 * Locale에 해당하는 형식으로 숫자를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 숫자 문자열
	 */
	public static String formatNumber(Locale locale, Number number, boolean groupingUsed, int maxFactionDigits) {
		NumberFormat numberberFormat = NumberFormat.getNumberInstance(locale);
		numberberFormat.setGroupingUsed(groupingUsed);		
		numberberFormat.setMaximumFractionDigits(maxFactionDigits);
		return numberberFormat.format(number);
	}
	
	/**
	 * 기본 Locale에 해당하는 형식으로 통화를 변환한다.
	 * 
	 * @param number 숫자
	 * @return 통화 문자열
	 */
	public static String formatCurrency(Number number) {
		return formatCurrency(number, GROUPING_USED);
	}
	
	/**
	 * Locale에 해당하는 형식으로 통화를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @return 통화 문자열
	 */
	public static String formatCurrency(Locale locale, Number number) {
		return formatCurrency(locale, number, GROUPING_USED);
	}
	
	/**
	 * 기본 Locale에 해당하는 형식으로 통화를 변환한다.
	 * 
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 통화 문자열
	 */
	public static String formatCurrency(Number number, boolean groupingUsed) {
		NumberFormat numberberFormat = NumberFormat.getCurrencyInstance();
		numberberFormat.setGroupingUsed(groupingUsed);		
		return numberberFormat.format(number);
	}

	/**
	 * Locale에 해당하는 형식으로 통화를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 통화 문자열
	 */
	public static String formatCurrency(Locale locale, Number number, boolean groupingUsed) {
		NumberFormat numberberFormat = NumberFormat.getCurrencyInstance(locale);
		numberberFormat.setGroupingUsed(groupingUsed);		
		return numberberFormat.format(number);
	}	

	/**
	 * 기본 Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param number 숫자
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Number number) {
		return formatPercent(number, GROUPING_USED, MAX_FRACTION_DIGIT);
	}
	
	/**
	 * Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Locale locale, Number number) {
		return formatPercent(locale, number, GROUPING_USED, MAX_FRACTION_DIGIT);
	}
	
	/**
	 * 기본 Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Number number, boolean groupingUsed) {
		return formatPercent(number, groupingUsed, MAX_FRACTION_DIGIT);
	}

	/**
	 * Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Locale locale, Number number, boolean groupingUsed) {
		return formatPercent(locale, number, groupingUsed, MAX_FRACTION_DIGIT);
	}

	/**
	 * 기본 Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param number 숫자
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Number number, int maxFactionDigits) {
		return formatPercent(number, GROUPING_USED, maxFactionDigits);
	}
	
	/**
	 * Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Locale locale, Number number, int maxFactionDigits) {
		return formatPercent(locale, number, GROUPING_USED, maxFactionDigits);
	}

	/**
	 * 기본 Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Number number, boolean groupingUsed, int maxFactionDigits) {
		NumberFormat numberberFormat = NumberFormat.getPercentInstance();
		numberberFormat.setGroupingUsed(groupingUsed);		
		numberberFormat.setMaximumFractionDigits(maxFactionDigits);
		return numberberFormat.format(number);
	}
	
	/**
	 * Locale에 해당하는 형식으로 퍼센트를 변환한다.
	 * 
	 * @param locale 로케일
	 * @param number 숫자
	 * @param groupingUsed 그룹 분리기호 포함 여부
	 * @param maxFactionDigits 변환된 문자열에서 출력할 소수점 이하 최대 자리수
	 * @return 퍼센트 문자열
	 */
	public static String formatPercent(Locale locale, Number number, boolean groupingUsed, int maxFactionDigits) {
		NumberFormat numberberFormat = NumberFormat.getPercentInstance(locale);
		numberberFormat.setGroupingUsed(groupingUsed);		
		numberberFormat.setMaximumFractionDigits(maxFactionDigits);
		return numberberFormat.format(number);
	}

}