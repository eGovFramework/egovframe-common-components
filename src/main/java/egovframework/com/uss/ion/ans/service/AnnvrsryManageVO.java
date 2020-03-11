package egovframework.com.uss.ion.ans.service;

import java.util.List;

/**
 * 개요
 * - 기념일관리에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 기념일관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

//public class AnnvrsryManageVO extends AnnvrsryManage implements Serializable {
public class AnnvrsryManageVO extends AnnvrsryManage{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 기념일관리 목록
	 */	
	List<AnnvrsryManageVO> annvrsryManageList;

	/**
	*  list 번호      
	*/ 
	private int rowCount;

	/**
	*  출력 변수	1
	*/ 
	private String annvrsryTemp1;

	/**
	*  출력 변수	2
	*/ 
	private String annvrsryTemp2;

	/**
	*  출력 변수	3
	*/ 
	private String annvrsryTemp3;

	/**
	*  출력 변수	4
	*/ 
	private String annvrsryTemp4;

	/**
	*  출력 변수	5
	*/ 
	private String annvrsryTemp5;

	/**
	 * @return the annvrsryManageList
	 */
	public List<AnnvrsryManageVO> getAnnvrsryManageList() {
		return annvrsryManageList;
	}
	/**
	 * @param bannerList the bannerList to set
	 */
	public void setAnnvrsryManageList(List<AnnvrsryManageVO> annvrsryManageList) {
		this.annvrsryManageList = annvrsryManageList;
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
	 * @return the annvrsryTemp1
	 */
	public String getAnnvrsryTemp1() {
		return annvrsryTemp1;
	}
	/**
	 * @param annvrsryTemp1 the annvrsryTemp1 to set
	 */
	public void setAnnvrsryTemp1(String annvrsryTemp1) {
		this.annvrsryTemp1 = annvrsryTemp1;
	}
	/**
	 * @return the annvrsryTemp2
	 */
	public String getAnnvrsryTemp2() {
		return annvrsryTemp2;
	}
	/**
	 * @param annvrsryTemp2 the annvrsryTemp2 to set
	 */
	public void setAnnvrsryTemp2(String annvrsryTemp2) {
		this.annvrsryTemp2 = annvrsryTemp2;
	}
	/**
	 * @return the annvrsryTemp3
	 */
	public String getAnnvrsryTemp3() {
		return annvrsryTemp3;
	}
	/**
	 * @param annvrsryTemp3 the annvrsryTemp3 to set
	 */
	public void setAnnvrsryTemp3(String annvrsryTemp3) {
		this.annvrsryTemp3 = annvrsryTemp3;
	}
	/**
	 * @return the annvrsryTemp4
	 */
	public String getAnnvrsryTemp4() {
		return annvrsryTemp4;
	}
	/**
	 * @param annvrsryTemp4 the annvrsryTemp4 to set
	 */
	public void setAnnvrsryTemp4(String annvrsryTemp4) {
		this.annvrsryTemp4 = annvrsryTemp4;
	}
	/**
	 * @return the annvrsryTemp5
	 */
	public String getAnnvrsryTemp5() {
		return annvrsryTemp5;
	}
	/**
	 * @param annvrsryTemp5 the annvrsryTemp5 to set
	 */
	public void setAnnvrsryTemp5(String annvrsryTemp5) {
		this.annvrsryTemp5 = annvrsryTemp5;
	}


}
