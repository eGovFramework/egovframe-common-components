package egovframework.com.uss.ion.mtg.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 회의실관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class MtgPlaceManageVO extends MtgPlaceManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 회의실관리 목록
	 */
	List<MtgPlaceManageVO> mtgPlaceManageList;

	/**
	*  예약ID
	*/
	private String resveId;

	/**
	*  회의제목
	*/
	private String mtgSj;

	/**
	*  예약자ID
	*/
	private String resveManId;

	/**
	*  예약일자
	*/
	private String resveDe;

	/**
	*  예약시작시간
	*/
	private String resveBeginTm;

	/**
	*  예약종료시간
	*/
	private String resveEndTm;

	/**
	*  참석인원
	*/
	private int atndncNmpr;

	/**
	*  회의내용
	*/
	private String mtgCn;

	/**
	*  list 번호
	*/
	private int rowCount;

	/**
	*  출력 변수	1
	*/
	private String mtgPlaceTemp1;

	/**
	*  출력 변수	2
	*/
	private String mtgPlaceTemp2;

	/**
	*  출력 변수	3
	*/
	private String mtgPlaceTemp3;

	/**
	*  출력 변수	4
	*/
	private String mtgPlaceTemp4;

	/**
	*  출력 변수	5
	*/
	private String mtgPlaceTemp5;

	/**
	*  ID 변수
	*/
	private String usidTemp;

	/**
	*  시간출력 변수
	*/
	private String resveTemp0800;
	private String resveTemp0830;
	private String resveTemp0900;
	private String resveTemp0930;
	private String resveTemp1000;
	private String resveTemp1030;
	private String resveTemp1100;
	private String resveTemp1130;
	private String resveTemp1200;
	private String resveTemp1230;
	private String resveTemp1300;
	private String resveTemp1330;
	private String resveTemp1400;
	private String resveTemp1430;
	private String resveTemp1500;
	private String resveTemp1530;
	private String resveTemp1600;
	private String resveTemp1630;
	private String resveTemp1700;
	private String resveTemp1730;
	private String resveTemp1800;
	private String resveTemp1830;
	private String resveTemp1900;
	private String resveTemp1930;
	private String resveTemp2000;
	private String resveTemp2030;
	private String resveTemp2100;

	/**
	*  resveDeView 변수
	*/
	private String resveDeView;



	/**
	 * @return the resveDeView
	 */
	public String getResveDeView() {
		return resveDeView;
	}
	/**
	 * @param resveDeView the resveDeView to set
	 */
	public void setResveDeView(String resveDeView) {
		this.resveDeView = resveDeView;
	}
	/**
	 * @return the resveId
	 */
	public String getResveId() {
		return resveId;
	}
	/**
	 * @param resveId the resveId to set
	 */
	public void setResveId(String resveId) {
		this.resveId = resveId;
	}

	/**
	 * @return the mtgSj
	 */
	public String getMtgSj() {
		return mtgSj;
	}
	/**
	 * @param mtgSj the mtgSj to set
	 */
	public void setMtgSj(String mtgSj) {
		this.mtgSj = mtgSj;
	}
	/**
	 * @return the resveManId
	 */
	public String getResveManId() {
		return resveManId;
	}
	/**
	 * @param resveManId the resveManId to set
	 */
	public void setResveManId(String resveManId) {
		this.resveManId = resveManId;
	}
	/**
	 * @return the resveBeginTm
	 */
	public String getResveBeginTm() {
		return resveBeginTm;
	}
	/**
	 * @param resveBeginTm the resveBeginTm to set
	 */
	public void setResveBeginTm(String resveBeginTm) {
		this.resveBeginTm = resveBeginTm;
	}
	/**
	 * @return the resveEndTm
	 */
	public String getResveEndTm() {
		return resveEndTm;
	}
	/**
	 * @param resveEndTm the resveEndTm to set
	 */
	public void setResveEndTm(String resveEndTm) {
		this.resveEndTm = resveEndTm;
	}


	/**
	 * @return the mtgPlaceManageList
	 */
	public List<MtgPlaceManageVO> getMtgPlaceManageList() {
		return mtgPlaceManageList;
	}
	/**
	 * @param eventManage the mtgPlaceManage to set
	 */
	public void setMtgPlaceManageList(List<MtgPlaceManageVO> mtgPlaceManageList) {
		this.mtgPlaceManageList = mtgPlaceManageList;
	}
	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}
	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * @return the mtgPlaceTemp1
	 */
	public String getMtgPlaceTemp1() {
		return mtgPlaceTemp1;
	}
	/**
	 * @param mtgPlaceTemp1 the mtgPlaceTemp1 to set
	 */
	public void setMtgPlaceTemp1(String mtgPlaceTemp1) {
		this.mtgPlaceTemp1 = mtgPlaceTemp1;
	}
	/**
	 * @return the mtgPlaceTemp2
	 */
	public String getMtgPlaceTemp2() {
		return mtgPlaceTemp2;
	}
	/**
	 * @param mtgPlaceTemp2 the mtgPlaceTemp2 to set
	 */
	public void setMtgPlaceTemp2(String mtgPlaceTemp2) {
		this.mtgPlaceTemp2 = mtgPlaceTemp2;
	}
	/**
	 * @return the mtgPlaceTemp3
	 */
	public String getMtgPlaceTemp3() {
		return mtgPlaceTemp3;
	}
	/**
	 * @param mtgPlaceTemp3 the mtgPlaceTemp3 to set
	 */
	public void setMtgPlaceTemp3(String mtgPlaceTemp3) {
		this.mtgPlaceTemp3 = mtgPlaceTemp3;
	}
	/**
	 * @return the mtgPlaceTemp4
	 */
	public String getMtgPlaceTemp4() {
		return mtgPlaceTemp4;
	}
	/**
	 * @param mtgPlaceTemp4 the mtgPlaceTemp4 to set
	 */
	public void setMtgPlaceTemp4(String mtgPlaceTemp4) {
		this.mtgPlaceTemp4 = mtgPlaceTemp4;
	}
	/**
	 * @return the mtgPlaceTemp5
	 */
	public String getMtgPlaceTemp5() {
		return mtgPlaceTemp5;
	}
	/**
	 * @param mtgPlaceTemp5 the mtgPlaceTemp5 to set
	 */
	public void setMtgPlaceTemp5(String mtgPlaceTemp5) {
		this.mtgPlaceTemp5 = mtgPlaceTemp5;
	}
	/**
	 * @return the resveDe
	 */
	public String getResveDe() {
		return resveDe;
	}
	/**
	 * @param resveDe the resveDe to set
	 */
	public void setResveDe(String resveDe) {
		this.resveDe = resveDe;
	}
	/**
	 * @return the atndncNmpr
	 */
	public int getAtndncNmpr() {
		return atndncNmpr;
	}
	/**
	 * @param atndncNmpr the atndncNmpr to set
	 */
	public void setAtndncNmpr(int atndncNmpr) {
		this.atndncNmpr = atndncNmpr;
	}
	/**
	 * @return the mtgCn
	 */
	public String getMtgCn() {
		return mtgCn;
	}
	/**
	 * @param mtgCn the mtgCn to set
	 */
	public void setMtgCn(String mtgCn) {
		this.mtgCn = mtgCn;
	}
	/**
	 * @return the resveTemp0800
	 */
	public String getResveTemp0800() {
		return resveTemp0800;
	}
	/**
	 * @param resveTemp0800 the resveTemp0800 to set
	 */
	public void setResveTemp0800(String resveTemp0800) {
		this.resveTemp0800 = resveTemp0800;
	}
	/**
	 * @return the resveTemp0830
	 */
	public String getResveTemp0830() {
		return resveTemp0830;
	}
	/**
	 * @param resveTemp0830 the resveTemp0830 to set
	 */
	public void setResveTemp0830(String resveTemp0830) {
		this.resveTemp0830 = resveTemp0830;
	}
	/**
	 * @return the resveTemp0900
	 */
	public String getResveTemp0900() {
		return resveTemp0900;
	}
	/**
	 * @param resveTemp0900 the resveTemp0900 to set
	 */
	public void setResveTemp0900(String resveTemp0900) {
		this.resveTemp0900 = resveTemp0900;
	}
	/**
	 * @return the resveTemp0930
	 */
	public String getResveTemp0930() {
		return resveTemp0930;
	}
	/**
	 * @param resveTemp0930 the resveTemp0930 to set
	 */
	public void setResveTemp0930(String resveTemp0930) {
		this.resveTemp0930 = resveTemp0930;
	}
	/**
	 * @return the resveTemp1000
	 */
	public String getResveTemp1000() {
		return resveTemp1000;
	}
	/**
	 * @param resveTemp1000 the resveTemp1000 to set
	 */
	public void setResveTemp1000(String resveTemp1000) {
		this.resveTemp1000 = resveTemp1000;
	}
	/**
	 * @return the resveTemp1030
	 */
	public String getResveTemp1030() {
		return resveTemp1030;
	}
	/**
	 * @param resveTemp1030 the resveTemp1030 to set
	 */
	public void setResveTemp1030(String resveTemp1030) {
		this.resveTemp1030 = resveTemp1030;
	}
	/**
	 * @return the resveTemp1100
	 */
	public String getResveTemp1100() {
		return resveTemp1100;
	}
	/**
	 * @param resveTemp1100 the resveTemp1100 to set
	 */
	public void setResveTemp1100(String resveTemp1100) {
		this.resveTemp1100 = resveTemp1100;
	}
	/**
	 * @return the resveTemp1130
	 */
	public String getResveTemp1130() {
		return resveTemp1130;
	}
	/**
	 * @param resveTemp1130 the resveTemp1130 to set
	 */
	public void setResveTemp1130(String resveTemp1130) {
		this.resveTemp1130 = resveTemp1130;
	}
	/**
	 * @return the resveTemp1200
	 */
	public String getResveTemp1200() {
		return resveTemp1200;
	}
	/**
	 * @param resveTemp1200 the resveTemp1200 to set
	 */
	public void setResveTemp1200(String resveTemp1200) {
		this.resveTemp1200 = resveTemp1200;
	}
	/**
	 * @return the resveTemp1230
	 */
	public String getResveTemp1230() {
		return resveTemp1230;
	}
	/**
	 * @param resveTemp1230 the resveTemp1230 to set
	 */
	public void setResveTemp1230(String resveTemp1230) {
		this.resveTemp1230 = resveTemp1230;
	}
	/**
	 * @return the resveTemp1300
	 */
	public String getResveTemp1300() {
		return resveTemp1300;
	}
	/**
	 * @param resveTemp1300 the resveTemp1300 to set
	 */
	public void setResveTemp1300(String resveTemp1300) {
		this.resveTemp1300 = resveTemp1300;
	}
	/**
	 * @return the resveTemp1330
	 */
	public String getResveTemp1330() {
		return resveTemp1330;
	}
	/**
	 * @param resveTemp1330 the resveTemp1330 to set
	 */
	public void setResveTemp1330(String resveTemp1330) {
		this.resveTemp1330 = resveTemp1330;
	}
	/**
	 * @return the resveTemp1400
	 */
	public String getResveTemp1400() {
		return resveTemp1400;
	}
	/**
	 * @param resveTemp1400 the resveTemp1400 to set
	 */
	public void setResveTemp1400(String resveTemp1400) {
		this.resveTemp1400 = resveTemp1400;
	}
	/**
	 * @return the resveTemp1430
	 */
	public String getResveTemp1430() {
		return resveTemp1430;
	}
	/**
	 * @param resveTemp1430 the resveTemp1430 to set
	 */
	public void setResveTemp1430(String resveTemp1430) {
		this.resveTemp1430 = resveTemp1430;
	}
	/**
	 * @return the resveTemp1500
	 */
	public String getResveTemp1500() {
		return resveTemp1500;
	}
	/**
	 * @param resveTemp1500 the resveTemp1500 to set
	 */
	public void setResveTemp1500(String resveTemp1500) {
		this.resveTemp1500 = resveTemp1500;
	}
	/**
	 * @return the resveTemp1530
	 */
	public String getResveTemp1530() {
		return resveTemp1530;
	}
	/**
	 * @param resveTemp1530 the resveTemp1530 to set
	 */
	public void setResveTemp1530(String resveTemp1530) {
		this.resveTemp1530 = resveTemp1530;
	}
	/**
	 * @return the resveTemp1600
	 */
	public String getResveTemp1600() {
		return resveTemp1600;
	}
	/**
	 * @param resveTemp1600 the resveTemp1600 to set
	 */
	public void setResveTemp1600(String resveTemp1600) {
		this.resveTemp1600 = resveTemp1600;
	}
	/**
	 * @return the resveTemp1630
	 */
	public String getResveTemp1630() {
		return resveTemp1630;
	}
	/**
	 * @param resveTemp1630 the resveTemp1630 to set
	 */
	public void setResveTemp1630(String resveTemp1630) {
		this.resveTemp1630 = resveTemp1630;
	}
	/**
	 * @return the resveTemp1700
	 */
	public String getResveTemp1700() {
		return resveTemp1700;
	}
	/**
	 * @param resveTemp1700 the resveTemp1700 to set
	 */
	public void setResveTemp1700(String resveTemp1700) {
		this.resveTemp1700 = resveTemp1700;
	}
	/**
	 * @return the resveTemp1730
	 */
	public String getResveTemp1730() {
		return resveTemp1730;
	}
	/**
	 * @param resveTemp1730 the resveTemp1730 to set
	 */
	public void setResveTemp1730(String resveTemp1730) {
		this.resveTemp1730 = resveTemp1730;
	}
	/**
	 * @return the resveTemp1800
	 */
	public String getResveTemp1800() {
		return resveTemp1800;
	}
	/**
	 * @param resveTemp1800 the resveTemp1800 to set
	 */
	public void setResveTemp1800(String resveTemp1800) {
		this.resveTemp1800 = resveTemp1800;
	}
	/**
	 * @return the resveTemp1830
	 */
	public String getResveTemp1830() {
		return resveTemp1830;
	}
	/**
	 * @param resveTemp1830 the resveTemp1830 to set
	 */
	public void setResveTemp1830(String resveTemp1830) {
		this.resveTemp1830 = resveTemp1830;
	}
	/**
	 * @return the resveTemp1900
	 */
	public String getResveTemp1900() {
		return resveTemp1900;
	}
	/**
	 * @param resveTemp1900 the resveTemp1900 to set
	 */
	public void setResveTemp1900(String resveTemp1900) {
		this.resveTemp1900 = resveTemp1900;
	}
	/**
	 * @return the resveTemp1930
	 */
	public String getResveTemp1930() {
		return resveTemp1930;
	}
	/**
	 * @param resveTemp1930 the resveTemp1930 to set
	 */
	public void setResveTemp1930(String resveTemp1930) {
		this.resveTemp1930 = resveTemp1930;
	}
	/**
	 * @return the resveTemp2000
	 */
	public String getResveTemp2000() {
		return resveTemp2000;
	}
	/**
	 * @param resveTemp2000 the resveTemp2000 to set
	 */
	public void setResveTemp2000(String resveTemp2000) {
		this.resveTemp2000 = resveTemp2000;
	}
	/**
	 * @return the resveTemp2030
	 */
	public String getResveTemp2030() {
		return resveTemp2030;
	}
	/**
	 * @param resveTemp2030 the resveTemp2030 to set
	 */
	public void setResveTemp2030(String resveTemp2030) {
		this.resveTemp2030 = resveTemp2030;
	}
	/**
	 * @return the resveTemp2100
	 */
	public String getResveTemp2100() {
		return resveTemp2100;
	}
	/**
	 * @param resveTemp2100 the resveTemp2100 to set
	 */
	public void setResveTemp2100(String resveTemp2100) {
		this.resveTemp2100 = resveTemp2100;
	}
	/**
	 * @return the usidTemp
	 */
	public String getUsidTemp() {
		return usidTemp;
	}
	/**
	 * @param usidTemp the usidTemp to set
	 */
	public void setUsidTemp(String usidTemp) {
		this.usidTemp = usidTemp;
	}

}
