package egovframework.com.utl.fda.ucc.service;

import java.util.HashMap;

/**
 * 길이, 부피, 넓이, 무게 환산 데이터를 생성하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *
 * </pre>
 */
public class EgovUnitCalcUtil {

	//길이
	HashMap<String, Double> g_hmVt = new HashMap<String, Double>();

	//부피
	HashMap<String, Double> g_hmVl = new HashMap<String, Double>();

	//넓이
	HashMap<String, Double> g_hmAr = new HashMap<String, Double>();

	//무게
	HashMap<String, Double> g_hmWt = new HashMap<String, Double>();

	/**
	 * 생성자를 통하여 길이, 부피, 넓이, 무게 환산 데이터를 생성한다.
	 */
	public EgovUnitCalcUtil(){
		//길이
		g_hmVt.put("vt0", (double)0);
		g_hmVt.put("vt1", (double)1);
		g_hmVt.put("vt2", 0.01);
		g_hmVt.put("vt3", (1/2.54));
		g_hmVt.put("vt4", (1/30.48));
		g_hmVt.put("vt5", (1/91.44));
		g_hmVt.put("vt6", (1/160934.4));
		g_hmVt.put("vt7", 0.033);
		g_hmVt.put("vt8", (0.033/6));
		g_hmVt.put("vt9", (0.033/360));
		g_hmVt.put("vt10", (0.033/1296));


		//부피
		g_hmVl.put("vl0", (double)0);
		g_hmVl.put("vl1", (1/0.18039));
		g_hmVl.put("vl2", (1/1.8039));
		g_hmVl.put("vl3", (1/18.039));
		g_hmVl.put("vl4", 10000.0);
		g_hmVl.put("vl5", 0.001);
		g_hmVl.put("vl6", (double)1);
		g_hmVl.put("vl7", (1000/16.387064));
		g_hmVl.put("vl8", (1000/Math.pow(2.54*12,3)));
		g_hmVl.put("vl9", (1000/Math.pow(2.54*36,3)));
		g_hmVl.put("vl10", (1000/(Math.pow(2.54,3)*231)));

		//넓이
		g_hmAr.put("ar0", (double)0);
		g_hmAr.put("ar1", (double)(1089/100));
		g_hmAr.put("ar2", (double)(1089/3600));
		g_hmAr.put("ar3", (double)(1089/1080000));
		g_hmAr.put("ar4", (double)(1089/10800000));
		g_hmAr.put("ar5", (double)1);
		g_hmAr.put("ar6", 0.01);
		g_hmAr.put("ar7", (1/Math.pow(2.54*12/100,2)));
		g_hmAr.put("ar8", (1/Math.pow(2.54*36/100,2)));
		g_hmAr.put("ar9", (1/(Math.pow(2.54*36/100,2)*4840)));
		g_hmAr.put("ar10", 0.0001);

		//무게
		g_hmWt.put("wt0", (double)0);
		g_hmWt.put("wt1", (double)1);
		g_hmWt.put("wt2", (double)1000);
		g_hmWt.put("wt3", 0.001);
		g_hmWt.put("wt4", 0.000001);
		g_hmWt.put("wt5", (1/0.06479891));
		g_hmWt.put("wt6", (16/453.59237));
		g_hmWt.put("wt7", (1/453.59237));
		g_hmWt.put("wt8", (1/3.75));
		g_hmWt.put("wt9", (1/37.5));
		g_hmWt.put("wt10", (double)(1/600));
		g_hmWt.put("wt11", (double)(1/3750));
	}

	/**
	 * 길이단위를 환산하여 리턴한다.
	 * @param nLength -길이
	 * @param sLengthUnit-길이 단위
	 * @param sLengthUnitAs-길이 환산 단위
	 * @return double -환산된 길이
	 */
	public double convertLengthCalcUnit(double nLength, String sLengthUnit, String sLengthUnitAs){

		double nSelAr = g_hmVt.get(sLengthUnit);
		double nSelArAs = g_hmVt.get(sLengthUnitAs);

		return (nSelArAs / nSelAr) * nLength;
	}

	/**
	 * 부피단위를 환산하여 리턴한다.
	 * @param nVolume -부피
	 * @param sVolumeUnit-부피 단위
	 * @param sVolumeUnitAs-부피 환산 단위
	 * @return double -환산된 부피
	 */
	public double convertVolumeCalcUnit(double nVolume, String sVolumeUnit, String sVolumeUnitAs){

		double nSelVl = g_hmVl.get(sVolumeUnit);
		double nSelVlAs = g_hmVl.get(sVolumeUnitAs);

		return (nSelVl / nSelVlAs) * nVolume;
	}

	/**
	 * 무게단위를 환산하여 리턴한다.
	 * @param nWeight -무게
	 * @param sWeightUnit -무게 단위
	 * @param sWeightUnitAs -무게 환산 단위
	 * @return double -환산된 무게
	 */
	public double convertWeightCalcUnit(double nWeight, String sWeightUnit, String sWeightUnitAs){

		double nSelWt = g_hmAr.get(sWeightUnit);
		double nSelWtAs = g_hmAr.get(sWeightUnitAs);

		return (nSelWt / nSelWtAs) * nWeight;
	}

	/**
	 * 넓이단위를 환산하여 리턴한다.
	 * @param nWidth -넓이
	 * @param sWidthUnit-넓이 단위
	 * @param sWidthUnitAs-넓이 환산 단위
	 * @return double -환산된 넓이
	 */
	public double convertWidthCalcUnit(double nWidth, String sWidthUnit, String sWidthUnitAs){

		double nSelAr = g_hmWt.get(sWidthUnit);
		double nSelArAs = g_hmWt.get(sWidthUnitAs);

		return (nSelAr / nSelArAs) * nWidth;
	}
}
