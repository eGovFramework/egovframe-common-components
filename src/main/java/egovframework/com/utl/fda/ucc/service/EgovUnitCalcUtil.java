package egovframework.com.utl.fda.ucc.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 길이, 부피, 넓이, 무게 환산 데이터를 생성하는 Service Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *   2025.09.03  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(변수명에 밑줄 사용)
 *   2025.09.03  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(불필요한 괄호사용)
 *
 *      </pre>
 */
public class EgovUnitCalcUtil {

	// 길이
	private final Map<String, Double> hmVt = new HashMap<>();

	// 부피
	private final Map<String, Double> hmVl = new HashMap<>();

	// 넓이
	private final Map<String, Double> hmAr = new HashMap<>();

	// 무게
	private final Map<String, Double> hmWt = new HashMap<>();

	/**
	 * 생성자를 통하여 길이, 부피, 넓이, 무게 환산 데이터를 생성한다.
	 */
	public EgovUnitCalcUtil() {
		// 길이
		hmVt.put("vt0", (double) 0);
		hmVt.put("vt1", (double) 1);
		hmVt.put("vt2", 0.01);
		hmVt.put("vt3", 1 / 2.54);
		hmVt.put("vt4", 1 / 30.48);
		hmVt.put("vt5", 1 / 91.44);
		hmVt.put("vt6", 1 / 160934.4);
		hmVt.put("vt7", 0.033);
		hmVt.put("vt8", 0.033 / 6);
		hmVt.put("vt9", 0.033 / 360);
		hmVt.put("vt10", 0.033 / 1296);

		// 부피
		hmVl.put("vl0", (double) 0);
		hmVl.put("vl1", 1 / 0.18039);
		hmVl.put("vl2", 1 / 1.8039);
		hmVl.put("vl3", 1 / 18.039);
		hmVl.put("vl4", 10000.0);
		hmVl.put("vl5", 0.001);
		hmVl.put("vl6", (double) 1);
		hmVl.put("vl7", 1000 / 16.387064);
		hmVl.put("vl8", 1000 / Math.pow(2.54 * 12, 3));
		hmVl.put("vl9", 1000 / Math.pow(2.54 * 36, 3));
		hmVl.put("vl10", 1000 / (Math.pow(2.54, 3) * 231));

		// 넓이
		hmAr.put("ar0", (double) 0);
		hmAr.put("ar1", 1089d / 100d);
		hmAr.put("ar2", 1089d / 3600d);
		hmAr.put("ar3", 1089d / 1080000d);
		hmAr.put("ar4", 1089d / 10800000d);
		hmAr.put("ar5", (double) 1);
		hmAr.put("ar6", 0.01);
		hmAr.put("ar7", 1 / Math.pow(2.54 * 12 / 100, 2));
		hmAr.put("ar8", 1 / Math.pow(2.54 * 36 / 100, 2));
		hmAr.put("ar9", 1 / (Math.pow(2.54 * 36 / 100, 2) * 4840));
		hmAr.put("ar10", 0.0001);

		// 무게
		hmWt.put("wt0", (double) 0);
		hmWt.put("wt1", (double) 1);
		hmWt.put("wt2", (double) 1000);
		hmWt.put("wt3", 0.001);
		hmWt.put("wt4", 0.000001);
		hmWt.put("wt5", 1 / 0.06479891);
		hmWt.put("wt6", 16 / 453.59237);
		hmWt.put("wt7", 1 / 453.59237);
		hmWt.put("wt8", 1 / 3.75);
		hmWt.put("wt9", 1 / 37.5);
		hmWt.put("wt10", 1 / 600d);
		hmWt.put("wt11", 1 / 3750d);
	}

	/**
	 * 길이단위를 환산하여 리턴한다.
	 * 
	 * @param nLength          -길이
	 * @param sLengthUnit-길이   단위
	 * @param sLengthUnitAs-길이 환산 단위
	 * @return double -환산된 길이
	 */
	public double convertLengthCalcUnit(double nLength, String sLengthUnit, String sLengthUnitAs) {

		double nSelAr = hmVt.get(sLengthUnit);
		double nSelArAs = hmVt.get(sLengthUnitAs);

		return nSelArAs / nSelAr * nLength;
	}

	/**
	 * 부피단위를 환산하여 리턴한다.
	 * 
	 * @param nVolume          -부피
	 * @param sVolumeUnit-부피   단위
	 * @param sVolumeUnitAs-부피 환산 단위
	 * @return double -환산된 부피
	 */
	public double convertVolumeCalcUnit(double nVolume, String sVolumeUnit, String sVolumeUnitAs) {

		double nSelVl = hmVl.get(sVolumeUnit);
		double nSelVlAs = hmVl.get(sVolumeUnitAs);

		return nSelVl / nSelVlAs * nVolume;
	}

	/**
	 * 무게단위를 환산하여 리턴한다.
	 * 
	 * @param nWeight       -무게
	 * @param sWeightUnit   -무게 단위
	 * @param sWeightUnitAs -무게 환산 단위
	 * @return double -환산된 무게
	 */
	public double convertWeightCalcUnit(double nWeight, String sWeightUnit, String sWeightUnitAs) {

		double nSelWt = hmAr.get(sWeightUnit);
		double nSelWtAs = hmAr.get(sWeightUnitAs);

		return nSelWt / nSelWtAs * nWeight;
	}

	/**
	 * 넓이단위를 환산하여 리턴한다.
	 * 
	 * @param nWidth          -넓이
	 * @param sWidthUnit-넓이   단위
	 * @param sWidthUnitAs-넓이 환산 단위
	 * @return double -환산된 넓이
	 */
	public double convertWidthCalcUnit(double nWidth, String sWidthUnit, String sWidthUnitAs) {

		double nSelAr = hmAr.get(sWidthUnit);
		double nSelArAs = hmAr.get(sWidthUnitAs);

		return nSelAr / nSelArAs * nWidth;
	}
}
