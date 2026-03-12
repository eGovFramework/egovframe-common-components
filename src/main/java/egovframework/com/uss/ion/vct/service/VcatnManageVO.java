package egovframework.com.uss.ion.vct.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 휴가관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리의 신청자ID,휴가구분,시작일자,종료일자,신청일자,휴가사유,발생연도,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * - 휴가관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class VcatnManageVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	*  신청자ID	      
	*/ 
	private String applcntId;

	/**
	*  휴가구분	      
	*/ 
	@EgovNullCheck
	private String vcatnSe;

	/**
	*  시작일자
	*/ 
	@EgovNullCheck
	@Size(max = 8)
	private String bgnde;

	/**
	*  종료일자	      
	*/ 
	@EgovNullCheck
	@Size(max = 8)
	private String endde;
	
	/**
	*  신청일자	      
	*/ 
	private String reqstDe;

	/**
	*  휴가사유	      
	*/ 
	@EgovNullCheck
	@Size(max = 200)
	private String vcatnResn;
	
	/**
	*  발생연도	      
	*/ 
	private String occrrncYear;

	/**
	*  정오구분	      
	*/ 
	private String noonSe;
	
	/**
	*  결재자ID	      
	*/ 
	@NotBlank(message = "{comUssIonVct.vcatnManage.validate.sanctnerId.required}")
	private String sanctnerId;

	/**
	*  승인여부	      
	*/ 
	private String confmAt;

	/**
	*  결재일시	      
	*/ 
	private String sanctnDt;

	/**
	*  반려사유	      
	*/ 
	private String returnResn;

	/**
	*  약식결재ID	      
	*/ 
	private String infrmlSanctnId;	
	
	
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
	*  sanctnDtNm (승인권자 표시용)
	*/ 
	private String sanctnDtNm;
	
	/**
	*  orgnztNm	
	*/ 
	private String orgnztNm;

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
	 * @return the sanctnDtNm
	 */
	public String getSanctnDtNm() {
		return sanctnDtNm;
	}

	/**
	 * @param sanctnDtNm the sanctnDtNm to set
	 */
	public void setSanctnDtNm(String sanctnDtNm) {
		this.sanctnDtNm = sanctnDtNm;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}

	/**
	 * @param orgnztNm the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the applcntId
	 */
	public String getApplcntId() {
		return applcntId;
	}

	/**
	 * @param applcntId the applcntId to set
	 */
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	/**
	 * @return the vcatnSe
	 */
	public String getVcatnSe() {
		return vcatnSe;
	}

	/**
	 * @param vcatnSe the vcatnSe to set
	 */
	public void setVcatnSe(String vcatnSe) {
		this.vcatnSe = vcatnSe;
	}

	/**
	 * @return the bgnde
	 */
	public String getBgnde() {
		return bgnde;
	}

	/**
	 * @param bgnde the bgnde to set
	 */
	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}

	/**
	 * @return the endde
	 */
	public String getEndde() {
		return endde;
	}

	/**
	 * @param endde the endde to set
	 */
	public void setEndde(String endde) {
		this.endde = endde;
	}

	/**
	 * @return the reqstDe
	 */
	public String getReqstDe() {
		return reqstDe;
	}

	/**
	 * @param reqstDe the reqstDe to set
	 */
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	/**
	 * @return the vcatnResn
	 */
	public String getVcatnResn() {
		return vcatnResn;
	}

	/**
	 * @param vcatnResn the vcatnResn to set
	 */
	public void setVcatnResn(String vcatnResn) {
		this.vcatnResn = vcatnResn;
	}

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
	 * @return the sanctnerId
	 */
	public String getSanctnerId() {
		return sanctnerId;
	}

	/**
	 * @param sanctnerId the sanctnerId to set
	 */
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	/**
	 * @return the confmAt
	 */
	public String getConfmAt() {
		return confmAt;
	}

	/**
	 * @param confmAt the confmAt to set
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * @return the sanctnDt
	 */
	public String getSanctnDt() {
		return sanctnDt;
	}

	/**
	 * @param sanctnDt the sanctnDt to set
	 */
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	/**
	 * @return the returnResn
	 */
	public String getReturnResn() {
		return returnResn;
	}

	/**
	 * @param returnResn the returnResn to set
	 */
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

	/**
	 * @return the infrmlSanctnId
	 */
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}

	/**
	 * @param infrmlSanctnId the infrmlSanctnId to set
	 */
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
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
	 * @return the noonSe
	 */
	public String getNoonSe() {
		return noonSe;
	}

	/**
	 * @param noonSe the noonSe to set
	 */
	public void setNoonSe(String noonSe) {
		this.noonSe = noonSe;
	}

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
	 * @param vcatnManageList the vcatnManageList to set
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
