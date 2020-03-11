package egovframework.com.sym.log.tlg.service;

import java.io.Serializable;

/**
 * @Class Name : TrsmrcvLog.java
 * @Description : 송수신 로그 관리를 위한 VO 클래스를 정의한다.
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭        최초생성
 *    2011. 7. 01.   이기하        패키지 분리(sym.log -> sym.log.tlg)
 *    2011.09.14     서준식       화면에 검색일자를 표시하기위한 멤버변수 추가.
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
public class TrsmrcvLog implements Serializable {

	private static final long serialVersionUID = -4676693360868052168L;

	/**
	 * 요청아이디
	 */
	private String requstId = "";

	/**
	 * 발생일자
	 */
	private String occrrncDe = "";

	/**
	 * 송수신구분코드
	 */
	private String trsmrcvSeCode = "";

	/**
	 * 송수신구분코드 명
	 */
	private String trsmrcvSeCodeNm = "";

    /** 연계ID */
	private String cntcId;

    /** 제공기관ID */
	private String provdInsttId;

    /** 제공시스템ID */
	private String provdSysId;

    /** 제공서비스ID */
	private String provdSvcId;

    /** 요청기관ID */
	private String requstInsttId;

    /** 요청시스템ID */
	private String requstSysId;

    /** 요청송신시각 */
	private String requstTrnsmitTm;

    /** 요청수신시각 */
	private String requstRecptnTm;

    /** 응답송신시각 */
	private String rspnsTrnsmitTm;

    /** 응답수신시각 */
	private String rspnsRecptnTm;

    /** 결과코드 */
	private String resultCode;

    /** 결과메시지 */
	private String resultMessage;

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";

	/**
	 * 요청자아이디
	 */
	private String rqesterId = "";

	/**
	 * 요청자 이름
	 */
	private String rqsterNm = "";

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
	 * 검색시작일_화면용
	 */
	private String searchBgnDeView = "";//2011.09.14

	/**
	 * 검색종료일_화면용
	 */
	private String searchEndDeView = "";//2011.09.14

	public String getSearchEndDeView() {
		return searchEndDeView;
	}
	public void setSearchEndDeView(String searchEndDeView) {
		this.searchEndDeView = searchEndDeView;
	}
	public String getSearchBgnDeView() {
		return searchBgnDeView;
	}
	public void setSearchBgnDeView(String searchBgnDeView) {
		this.searchBgnDeView = searchBgnDeView;
	}

	public String getRequstId() {
		return requstId;
	}

	public void setRequstId(String requstId) {
		this.requstId = requstId;
	}

	public String getOccrrncDe() {
		return occrrncDe;
	}

	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	public String getTrsmrcvSeCode() {
		return trsmrcvSeCode;
	}

	public void setTrsmrcvSeCode(String trsmrcvSeCode) {
		this.trsmrcvSeCode = trsmrcvSeCode;
	}

	public String getTrsmrcvSeCodeNm() {
		return trsmrcvSeCodeNm;
	}

	public void setTrsmrcvSeCodeNm(String trsmrcvSeCodeNm) {
		this.trsmrcvSeCodeNm = trsmrcvSeCodeNm;
	}

	public String getcntcId() {
		return cntcId;
	}

	public void setcntcId(String cntcId) {
		this.cntcId = cntcId;
	}

	public String getProvdInsttId() {
		return provdInsttId;
	}

	public void setProvdInsttId(String provdInsttId) {
		this.provdInsttId = provdInsttId;
	}

	public String getProvdSysId() {
		return provdSysId;
	}

	public void setProvdSysId(String provdSysId) {
		this.provdSysId = provdSysId;
	}

	public String getProvdSvcId() {
		return provdSvcId;
	}

	public void setProvdSvcId(String provdSvcId) {
		this.provdSvcId = provdSvcId;
	}

	public String getRequstInsttId() {
		return requstInsttId;
	}

	public void setRequstInsttId(String requstInsttId) {
		this.requstInsttId = requstInsttId;
	}

	public String getRequstSysId() {
		return requstSysId;
	}

	public void setRequstSysId(String requstSysId) {
		this.requstSysId = requstSysId;
	}

	public String getRequstTrnsmitTm() {
		return requstTrnsmitTm;
	}

	public void setRequstTrnsmitTm(String requstTrnsmitTm) {
		this.requstTrnsmitTm = requstTrnsmitTm;
	}

	public String getRequstRecptnTm() {
		return requstRecptnTm;
	}

	public void setRequstRecptnTm(String requstRecptnTm) {
		this.requstRecptnTm = requstRecptnTm;
	}

	public String getRspnsTrnsmitTm() {
		return rspnsTrnsmitTm;
	}

	public void setRspnsTrnsmitTm(String rspnsTrnsmitTm) {
		this.rspnsTrnsmitTm = rspnsTrnsmitTm;
	}

	public String getRspnsRecptnTm() {
		return rspnsRecptnTm;
	}

	public void setRspnsRecptnTm(String rspnsRecptnTm) {
		this.rspnsRecptnTm = rspnsRecptnTm;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getRqesterId() {
		return rqesterId;
	}

	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	public String getRqsterNm() {
		return rqsterNm;
	}

	public void setRqsterNm(String rqsterNm) {
		this.rqsterNm = rqsterNm;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public String getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}


}
