package egovframework.com.uss.ion.ctn.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 경조관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 경조관리의 사용자ID,경조코드,신청일자,대상자명,생년월일,발생일자,관계,비고,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * - 경조관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class CtsnnManageVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 경조ID
	 */
	private String ctsnnId;

	/**
	 * 사용자ID (신청자)
	 */
	@EgovNullCheck
	private String usid;

	/**
	 * 경조코드
	 */
	@EgovNullCheck
	private String ctsnnCd;

	/**
	 * 신청일자
	 */
	private String reqstDe;

	/**
	 * 경조명
	 */
	@EgovNullCheck
	@Size(max = 255)
	private String ctsnnNm;

	/**
	 * 대상자명
	 */
	@EgovNullCheck
	@Size(max = 20)
	private String trgterNm;

	/**
	 * 생년월일
	 */
	@EgovNullCheck
	@Size(max = 10)
	private String brth;

	/**
	 * 발생일자
	 */
	@EgovNullCheck
	@Size(max = 10)
	private String occrrDe;

	/**
	 * 관계
	 */
	@EgovNullCheck
	private String relate;

	/**
	 * 비고
	 */
	private String remark;

	/**
	 * 결재자ID (결재권자)
	 */
	@EgovNullCheck
	private String sanctnerId;

	/**
	 * 승인여부
	 */
	private String confmAt;

	/**
	 * 결재일시
	 */
	private String sanctnDt;

	/**
	 * 반려사유
	 */
	private String returnResn;

	/**
	 * 약식결재ID
	 */
	private String infrmlSanctnId;

	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId;

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm;

	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;

	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;

	/**
	 * 경조 목록
	 */
	private List<CtsnnManageVO> ctsnnManageList;

	/**
	 * 신청자명
	 */
	private String usNm;

	/**
	 * 승인자명
	 */
	private String sanctnerNm;

	/**
	 * 경조코드명
	 */
	private String ctsnnCdNm;

	/**
	 * 사용자 소속명
	 */
	private String orgnztNm;

	/**
	 * 승인자 소속명
	 */
	private String sanctnerOrgnztNm;

	/**
	 * 검색시작일자
	 */
	private String searchFromDate;

	/**
	 * 검색종료일자
	 */
	private String searchToDate;

	/**
	 * 검색 성명
	 */
	private String searchNm;

	/**
	 * 검색 진행구분
	 */
	private String searchConfmAt;

	/**
	 * 가족관계명
	 */
	private String relateNm;

	/**
	 * searchToDateView
	 */
	private String searchToDateView;

	/**
	 * searchFromDateView
	 */
	private String searchFromDateView;

	public String getCtsnnId() {
		return ctsnnId;
	}

	public void setCtsnnId(String ctsnnId) {
		this.ctsnnId = ctsnnId;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getCtsnnCd() {
		return ctsnnCd;
	}

	public void setCtsnnCd(String ctsnnCd) {
		this.ctsnnCd = ctsnnCd;
	}

	public String getReqstDe() {
		return reqstDe;
	}

	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	public String getCtsnnNm() {
		return ctsnnNm;
	}

	public void setCtsnnNm(String ctsnnNm) {
		this.ctsnnNm = ctsnnNm;
	}

	public String getTrgterNm() {
		return trgterNm;
	}

	public void setTrgterNm(String trgterNm) {
		this.trgterNm = trgterNm;
	}

	public String getBrth() {
		return brth;
	}

	public void setBrth(String brth) {
		this.brth = brth;
	}

	public String getOccrrDe() {
		return occrrDe;
	}

	public void setOccrrDe(String occrrDe) {
		this.occrrDe = occrrDe;
	}

	public String getRelate() {
		return relate;
	}

	public void setRelate(String relate) {
		this.relate = relate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSanctnerId() {
		return sanctnerId;
	}

	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	public String getConfmAt() {
		return confmAt;
	}

	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	public String getSanctnDt() {
		return sanctnDt;
	}

	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	public String getReturnResn() {
		return returnResn;
	}

	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}

	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public List<CtsnnManageVO> getCtsnnManageList() {
		return ctsnnManageList;
	}

	public void setCtsnnManageList(List<CtsnnManageVO> ctsnnManageList) {
		this.ctsnnManageList = ctsnnManageList;
	}

	public String getUsNm() {
		return usNm;
	}

	public void setUsNm(String usNm) {
		this.usNm = usNm;
	}

	public String getSanctnerNm() {
		return sanctnerNm;
	}

	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}

	public String getCtsnnCdNm() {
		return ctsnnCdNm;
	}

	public void setCtsnnCdNm(String ctsnnCdNm) {
		this.ctsnnCdNm = ctsnnCdNm;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}

	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
	}

	public String getSearchFromDate() {
		return searchFromDate;
	}

	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}

	public String getSearchToDate() {
		return searchToDate;
	}

	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}

	public String getSearchNm() {
		return searchNm;
	}

	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}

	public String getSearchConfmAt() {
		return searchConfmAt;
	}

	public void setSearchConfmAt(String searchConfmAt) {
		this.searchConfmAt = searchConfmAt;
	}

	public String getRelateNm() {
		return relateNm;
	}

	public void setRelateNm(String relateNm) {
		this.relateNm = relateNm;
	}

	public String getSearchToDateView() {
		return searchToDateView;
	}

	public void setSearchToDateView(String searchToDateView) {
		this.searchToDateView = searchToDateView;
	}

	public String getSearchFromDateView() {
		return searchFromDateView;
	}

	public void setSearchFromDateView(String searchFromDateView) {
		this.searchFromDateView = searchFromDateView;
	}
}
