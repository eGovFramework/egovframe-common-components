package egovframework.com.uss.ion.vct.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 개인별 연차관리에 대한 model 클래스를 정의한다.
 *
 * 상세내용
 * - 연차관리의 발생연도,사용자ID,연차발생갯수,사용연차갯수,잔여연차갯수,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class IndvdlYrycManage extends ComDefaultVO {

	private static final long serialVersionUID = -6215242195948154486L;

	/**
	*  발생연도
	*/
	private String occrrncYear;

	/**
	*  사용자ID
	*/
	private String usid;

	/**
	*  발생연차갯수
	*/
	private double occrncYrycCo;

	/**
	*  사용연차갯수
	*/
	private double useYrycCo;

	/**
	*  잔여연차갯수
	*/
	private double remndrYrycCo;

	/**
	*  최초등록자ID
	*/
	private String frstRegisterId;

	/**
	*  최초등록시점
	*/
	private String frstRegisterPnttm;

	/**
	*  최종수정자ID
	*/
	private String lastUpdusrId;

	/**
	*  최종수정시점
	*/
	private String lastUpdusrPnttm;

	/**
	 * @return the occrrncYear
	 */
	public String getOccrrncYear() {
		return occrrncYear;
	}

	/**
	 * @param occrrncYear the occrrncYear to set
	 */
	public void setOccrrncYear(String occrrncYear) {
		this.occrrncYear = occrrncYear;
	}

	/**
	 * @return the usid
	 */
	public String getUsid() {
		return usid;
	}

	/**
	 * @param usid the usid to set
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * @return the occrncYrycCo
	 */
	public double getOccrncYrycCo() {
		return occrncYrycCo;
	}

	/**
	 * @param occrncYrycCo the occrncYrycCo to set
	 */
	public void setOccrncYrycCo(double occrncYrycCo) {
		this.occrncYrycCo = occrncYrycCo;
	}

	/**
	 * @return the useYrycCo
	 */
	public double getUseYrycCo() {
		return useYrycCo;
	}

	/**
	 * @param useYrycCo the useYrycCo to set
	 */
	public void setUseYrycCo(double useYrycCo) {
		this.useYrycCo = useYrycCo;
	}

	/**
	 * @return the remndrYrycCo
	 */
	public double getRemndrYrycCo() {
		return remndrYrycCo;
	}

	/**
	 * @param remndrYrycCo the remndrYrycCo to set
	 */
	public void setRemndrYrycCo(double remndrYrycCo) {
		this.remndrYrycCo = remndrYrycCo;
	}


}