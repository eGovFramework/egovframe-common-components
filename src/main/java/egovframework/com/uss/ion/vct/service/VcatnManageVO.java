package egovframework.com.uss.ion.vct.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 휴가관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class VcatnManageVO extends VcatnManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 휴가 목록
	 */
	List<VcatnManageVO> vcatnManageList;

	/**
	*  신청자명
	*/
	private String applcntNm;

	/**
	*  승인자명
	*/
	private String sanctnerNm;

	/**
	*  휴가구분명
	*/
	private String vcatnSeNm;

	/**
	*  사용자ID
	*/
	private String usid;

	/**
	*  발생연차개수
	*/
	private double occrncYrycCo = 0.0;

	/**
	*  사용연차개수
	*/
	private double useYrycCo = 0.0;

	/**
	*  잔여연차개수
	*/
	private double remndrYrycCo = 0.0;

	/**
	*  사용자 소속명

	private String orgnztNm;
	*/
	/**
	*  승인자 소속명
	*/
	private String sanctnerOrgnztNm;

	/**
	*  검색 연도
	*/
	private String searchYear;

	/**
	*  검색 월
	*/
	private String searchMonth;

	/**
	*  검색 성명
	*/
	private String searchNm;

	/**
	*  검색 진행구분
	*/
	private String searchConfmAt;

	/**
	*  sTempBgnde
	*/
	private String tempBgnde;

	/**
	*  sTempEndde
	*/
	private String tempEndde;

	/**
	*  tempUsNm
	*/
	private String tempUsNm;

	/**
	*  tempOrgnztNm
	*/
	private String tempOrgnztNm;


	/**
	*  신청자ID
	*/
	private String applcntIdKey;

	/**
	*  휴가구분
	*/
	private String vcatnSeKey;

	/**
	*  시작일자
	*/
	private String bgndeKey;

	/**
	*  종료일자
	*/
	private String enddeKey;

	/**
	 * @return the tempUsNm
	 */
	public String getTempUsNm() {
		return tempUsNm;
	}
	/**
	 * @param tempUsNm the tempUsNm to set
	 */
	public void setTempUsNm(String tempUsNm) {
		this.tempUsNm = tempUsNm;
	}
	/**
	 * @return the tempOrgnztNm
	 */
	public String getTempOrgnztNm() {
		return tempOrgnztNm;
	}
	/**
	 * @param tempOrgnztNm the tempOrgnztNm to set
	 */
	public void setTempOrgnztNm(String tempOrgnztNm) {
		this.tempOrgnztNm = tempOrgnztNm;
	}
	/**
	 * @return the tempBgnde
	 */
	public String getTempBgnde() {
		return tempBgnde;
	}
	/**
	 * @param tempBgnde the tempBgnde to set
	 */
	public void setTempBgnde(String tempBgnde) {
		this.tempBgnde = tempBgnde;
	}
	/**
	 * @return the tempEndde
	 */
	public String getTempEndde() {
		return tempEndde;
	}
	/**
	 * @param tempEndde the tempEndde to set
	 */
	public void setTempEndde(String tempEndde) {
		this.tempEndde = tempEndde;
	}
	/**
	 * @return the vcatnManageList
	 */
	public List<VcatnManageVO> getVcatnManageList() {
		return vcatnManageList;
	}
	/**
	 * @param VcatnManage the vcatnManage to set
	 */
	public void setVcatnManageList(List<VcatnManageVO> vcatnManageList) {
		this.vcatnManageList = vcatnManageList;
	}
	/**
	 * @return the applcntNm
	 */
	public String getApplcntNm() {
		return applcntNm;
	}
	/**
	 * @param applcntNm the applcntNm to set
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}
	/**
	 * @return the sanctnerNm
	 */
	public String getSanctnerNm() {
		return sanctnerNm;
	}

	/**
	 * @param sanctnerNm the sanctnerNm to set
	 */
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}

	/**
	 * @return the vcatnSeNm
	 */
	public String getVcatnSeNm() {
		return vcatnSeNm;
	}
	/**
	 * @param vcatnSeNm the vcatnSeNm to set
	 */
	public void setVcatnSeNm(String vcatnSeNm) {
		this.vcatnSeNm = vcatnSeNm;
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
	 * @return the orgnztNm

	public String getOrgnztNm() {
		return orgnztNm;
	}
		 */
	/**
	 * @param orgnztNm the orgnztNm to set

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}	 */
	/**
	 * @return the sanctnerOrgnztNm
	 */
	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}
	/**
	 * @param sanctnerOrgnztNm the sanctnerOrgnztNm to set
	 */
	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
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
	/**
	 * @return the searchYear
	 */
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * @param searchYear the searchYear to set
	 */
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	/**
	 * @return the searchMonth
	 */
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * @param searchMonth the searchMonth to set
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	/**
	 * @return the searchNm
	 */
	public String getSearchNm() {
		return searchNm;
	}
	/**
	 * @param searchNm the searchNm to set
	 */
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}
	/**
	 * @return the searchConfmAt
	 */
	public String getSearchConfmAt() {
		return searchConfmAt;
	}
	/**
	 * @param searchConfmAt the searchConfmAt to set
	 */
	public void setSearchConfmAt(String searchConfmAt) {
		this.searchConfmAt = searchConfmAt;
	}
	/**
	 * @return the applcntIdKey
	 */
	public String getApplcntIdKey() {
		return applcntIdKey;
	}
	/**
	 * @param applcntIdKey the applcntIdKey to set
	 */
	public void setApplcntIdKey(String applcntIdKey) {
		this.applcntIdKey = applcntIdKey;
	}
	/**
	 * @return the vcatnSeKey
	 */
	public String getVcatnSeKey() {
		return vcatnSeKey;
	}
	/**
	 * @param vcatnSeKey the vcatnSeKey to set
	 */
	public void setVcatnSeKey(String vcatnSeKey) {
		this.vcatnSeKey = vcatnSeKey;
	}
	/**
	 * @return the bgndeKey
	 */
	public String getBgndeKey() {
		return bgndeKey;
	}
	/**
	 * @param bgndeKey the bgndeKey to set
	 */
	public void setBgndeKey(String bgndeKey) {
		this.bgndeKey = bgndeKey;
	}
	/**
	 * @return the enddeKey
	 */
	public String getEnddeKey() {
		return enddeKey;
	}
	/**
	 * @param enddeKey the enddeKey to set
	 */
	public void setEnddeKey(String enddeKey) {
		this.enddeKey = enddeKey;
	}
}
