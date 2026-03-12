package egovframework.com.uss.ion.yrc.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

/**
 * 개요
 * - 개인별 연차관리에 대한 model 클래스를 정의한다.
 *
 * 상세내용
 * - 연차관리의 발생연도,사용자ID,연차발생개수,사용연차개수,잔여연차개수,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이기하
 * @version 1.0
 * @created 2014.11.14
 */

public class IndvdlYrycManage extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/**
	*  발생연도
	*/
	private String occrrncYear;

	/**
	*  사용자ID
	*/
	private String mberId;

	/**
	 *  사용자 이름
	 */
	private String mberNm;

	/**
	*  발생연차개수
	*/
	@EgovNullCheck
	@Positive
	@Min(1)
	private Double occrncYrycCo;

	/**
	*  사용연차개수
	*/
	@EgovNullCheck
	@Min(0)
	private Double useYrycCo;

	/**
	*  잔여연차개수
	*/
	private Double remndrYrycCo;

	/**
	 * 잔여연차 검증: 발생연차가 사용연차보다 크거나 같아야 함
	 * @return 검증 결과
	 */
	@AssertTrue(message = "{comUssIonYrc.indvdlYrycRegist.diffValue}")
	public boolean isValidRemndrYrycCo() {
		if (occrncYrycCo == null || useYrycCo == null) {
			return true;
		}
		return occrncYrycCo >= useYrycCo;
	}

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
	 * @return the mberId
	 */
	public String getMberId() {
		return mberId;
	}

	/**
	 * @param mberId the mberId to set
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}


	/**
	 * @return the mberNm
	 */
	public String getMberNm() {
		return mberNm;
	}

	/**
	 * @param mberId the mberNm to set
	 */
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
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
	public Double getOccrncYrycCo() {
		return occrncYrycCo;
	}

	/**
	 * @param occrncYrycCo the occrncYrycCo to set
	 */
	public void setOccrncYrycCo(Double occrncYrycCo) {
		this.occrncYrycCo = occrncYrycCo;
	}

	/**
	 * @return the useYrycCo
	 */
	public Double getUseYrycCo() {
		return useYrycCo;
	}

	/**
	 * @param useYrycCo the useYrycCo to set
	 */
	public void setUseYrycCo(Double useYrycCo) {
		this.useYrycCo = useYrycCo;
	}

	/**
	 * @return the remndrYrycCo
	 */
	public Double getRemndrYrycCo() {
		return remndrYrycCo;
	}

	/**
	 * @param remndrYrycCo the remndrYrycCo to set
	 */
	public void setRemndrYrycCo(Double remndrYrycCo) {
		this.remndrYrycCo = remndrYrycCo;
	}

}