package egovframework.com.sym.log.lgm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : SysLog.java
 * @Description : 로그관리(시스템)를 위한 VO 클래스를 정의한다.
 * @Modification Information
 *
 *    수정일          수정자         수정내용
 *    -------         -------     -------------------
 *    2009. 3. 11.     이삼섭      최초생성
 *    2011. 7. 01.     이기하      패키지 분리(sym.log -> sym.log.lgm)
 *    2011.09.14       서준식      화면에 검색일자를 표시하기위한 멤버변수 추가.
 *    2017.09.19       이정은      검색시작일_화면용, 검색종료일_화면용 삭제
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

public class SysLog implements Serializable{


	private static final long serialVersionUID = 540569951549295059L;
	
	/**
	 * 에러횟수
	 */
	private int errorCo = 0;
	/**
	 * 에러코드
	 */
	private String errorCode = "";
	/**
	 * 에러코드 명
	 */
	private String errorCodeNm = "";
	/**
	 * 에러구분
	 */
	private String errorSe = "";
	/**
	 * 기관코드
	 */
	private String insttCode = "";
	/**
	 * 기관코드 명
	 */
	private String insttCodeNm = "";
	/**
	 * 업무구분코드
	 */
	private String jobSeCode = "";

	/**
	 * 업무구분코드명
	 */
	private String jobSeCodeNm = "";
	/**
	 * 메서드명
	 */
	private String methodNm = "";
	/**
	 * 발생일자
	 */
	private String occrrncDe = "";
	/**
	 * 처리횟수
	 */
	private int processCo = 0;
	/**
	 * 처리구분코드
	 */
	private String processSeCode = "";
	/**
	 * 처리구분코드명
	 */
	private String processSeCodeNm = "";
	/**
	 * 처리시간
	 */
	private String processTime = "";
	/**
	 * 요청아이디
	 */
	private String requstId = "";
	/**
	 * 요청자아이디
	 */
	private String rqesterId = "";
	/**
	 * 요청자 이름
	 */
	private String rqsterNm = "";
	/**
	 * 요청아이피
	 */
	private String rqesterIp = "";
	/**
	 * 응답코드
	 */
	private String rspnsCode = "";
	/**
	 * 응답코드 명
	 */
	private String rspnsCodeNm = "";
	/**
	 * 서비스명
	 */
	private String srvcNm = "";
	/**
	 * 대상메뉴명
	 */
	private String trgetMenuNm = "";
	/**
	 * 검색시작일
	 */
	private String searchBgnDe = "";
	/**
	 * 검색조건
	 */
	private String searchCnd = "";
	/**
	 * 검색종료일
	 */
	private String searchEndDe = "";
	/**
	 * 검색단어
	 */
	private String searchWrd = "";
	/**
	 * 정렬순서(DESC,ASC)
	 */
	private String sortOrdr = "";

	/** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo  */
	private int rowNo = 0;




	/**
	 * @return the errorCo
	 */
	public int getErrorCo() {
		return errorCo;
	}
	/**
	 * @param errorCo the errorCo to set
	 */
	public void setErrorCo(int errorCo) {
		this.errorCo = errorCo;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorCodeNm
	 */
	public String getErrorCodeNm() {
		return errorCodeNm;
	}
	/**
	 * @param errorCodeNm the errorCodeNm to set
	 */
	public void setErrorCodeNm(String errorCodeNm) {
		this.errorCodeNm = errorCodeNm;
	}
	/**
	 * @return the errorSe
	 */
	public String getErrorSe() {
		return errorSe;
	}
	/**
	 * @param errorSe the errorSe to set
	 */
	public void setErrorSe(String errorSe) {
		this.errorSe = errorSe;
	}
	/**
	 * @return the insttCode
	 */
	public String getInsttCode() {
		return insttCode;
	}
	/**
	 * @param insttCode the insttCode to set
	 */
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}
	/**
	 * @return the insttCodeNm
	 */
	public String getInsttCodeNm() {
		return insttCodeNm;
	}
	/**
	 * @param insttCodeNm the insttCodeNm to set
	 */
	public void setInsttCodeNm(String insttCodeNm) {
		this.insttCodeNm = insttCodeNm;
	}
	/**
	 * @return the jobSeCode
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * @param jobSeCode the jobSeCode to set
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}
	/**
	 * @return the jobSeCodeNm
	 */
	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}
	/**
	 * @param jobSeCodeNm the jobSeCodeNm to set
	 */
	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}
	/**
	 * @return the methodNm
	 */
	public String getMethodNm() {
		return methodNm;
	}
	/**
	 * @param methodNm the methodNm to set
	 */
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	/**
	 * @return the occrrncDe
	 */
	public String getOccrrncDe() {
		return occrrncDe;
	}
	/**
	 * @param occrrncDe the occrrncDe to set
	 */
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}
	/**
	 * @return the processCo
	 */
	public int getProcessCo() {
		return processCo;
	}
	/**
	 * @param processCo the processCo to set
	 */
	public void setProcessCo(int processCo) {
		this.processCo = processCo;
	}
	/**
	 * @return the processSeCode
	 */
	public String getProcessSeCode() {
		return processSeCode;
	}
	/**
	 * @param processSeCode the processSeCode to set
	 */
	public void setProcessSeCode(String processSeCode) {
		this.processSeCode = processSeCode;
	}
	/**
	 * @return the processSeCodeNm
	 */
	public String getProcessSeCodeNm() {
		return processSeCodeNm;
	}
	/**
	 * @param processSeCodeNm the processSeCodeNm to set
	 */
	public void setProcessSeCodeNm(String processSeCodeNm) {
		this.processSeCodeNm = processSeCodeNm;
	}
	/**
	 * @return the processTime
	 */
	public String getProcessTime() {
		return processTime;
	}
	/**
	 * @param processTime the processTime to set
	 */
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	/**
	 * @return the requstId
	 */
	public String getRequstId() {
		return requstId;
	}
	/**
	 * @param requstId the requstId to set
	 */
	public void setRequstId(String requstId) {
		this.requstId = requstId;
	}
	/**
	 * @return the rqesterId
	 */
	public String getRqesterId() {
		return rqesterId;
	}
	/**
	 * @param rqesterId the rqesterId to set
	 */
	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}
	/**
	 * @return the rqsterNm
	 */
	public String getRqsterNm() {
		return rqsterNm;
	}
	/**
	 * @param rqsterNm the rqsterNm to set
	 */
	public void setRqsterNm(String rqsterNm) {
		this.rqsterNm = rqsterNm;
	}
	/**
	 * @return the rqesterIp
	 */
	public String getRqesterIp() {
		return rqesterIp;
	}
	/**
	 * @param rqesterIp the rqesterIp to set
	 */
	public void setRqesterIp(String rqesterIp) {
		this.rqesterIp = rqesterIp;
	}
	/**
	 * @return the rspnsCode
	 */
	public String getRspnsCode() {
		return rspnsCode;
	}
	/**
	 * @param rspnsCode the rspnsCode to set
	 */
	public void setRspnsCode(String rspnsCode) {
		this.rspnsCode = rspnsCode;
	}
	/**
	 * @return the rspnsCodeNm
	 */
	public String getRspnsCodeNm() {
		return rspnsCodeNm;
	}
	/**
	 * @param rspnsCodeNm the rspnsCodeNm to set
	 */
	public void setRspnsCodeNm(String rspnsCodeNm) {
		this.rspnsCodeNm = rspnsCodeNm;
	}
	/**
	 * @return the srvcNm
	 */
	public String getSrvcNm() {
		return srvcNm;
	}
	/**
	 * @param srvcNm the srvcNm to set
	 */
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}
	/**
	 * @return the trgetMenuNm
	 */
	public String getTrgetMenuNm() {
		return trgetMenuNm;
	}
	/**
	 * @param trgetMenuNm the trgetMenuNm to set
	 */
	public void setTrgetMenuNm(String trgetMenuNm) {
		this.trgetMenuNm = trgetMenuNm;
	}

	/**
	 * @return the searchBgnDe
	 */
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	/**
	 * @param searchBgnDe the searchBgnDe to set
	 */
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}
	/**
	 * @return the searchCnd
	 */
	public String getSearchCnd() {
		return searchCnd;
	}
	/**
	 * @param searchCnd the searchCnd to set
	 */
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	/**
	 * @return the searchEndDe
	 */
	public String getSearchEndDe() {
		return searchEndDe;
	}
	/**
	 * @param searchEndDe the searchEndDe to set
	 */
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}
	/**
	 * @return the searchWrd
	 */
	public String getSearchWrd() {
		return searchWrd;
	}
	/**
	 * @param searchWrd the searchWrd to set
	 */
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	/**
	 * @return the sortOrdr
	 */
	public String getSortOrdr() {
		return sortOrdr;
	}
	/**
	 * @param sortOrdr the sortOrdr to set
	 */
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	/**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * @param pageUnit the pageUnit to set
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the firstIndex
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * @param firstIndex the firstIndex to set
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	/**
	 * @return the recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * @param recordCountPerPage the recordCountPerPage to set
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	/**
	 * @return the rowNo
	 */
	public int getRowNo() {
		return rowNo;
	}
	/**
	 * @param rowNo the rowNo to set
	 */
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	/**
	 *
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}


}
