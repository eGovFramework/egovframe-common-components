package egovframework.com.sym.log.plg.service;

import java.io.Serializable;

/**
 * @Class Name : PrivacyLog.java
 * @Description : 개인정보 조회 이력 관리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2014.09.11	표준프레임워크		최초생성
* @author Vincent Han
 * @since 2014.09.11
 * @version 3.5
 */
public class PrivacyLog implements Serializable {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 요청 ID (REQUST_ID) */
	private String requestId = "";
	
	/** 조회일시 (INQIRE_DT) */
	private String inquiryDatetime = "";

	/** 서비스 명 (SRVC_NM) */
	private String serviceName = "";
	
	/** 조회 정보 명 (INQIRE_INFO) */
	private String inquiryInfo = "";

	/** 요청자아이디 (RQESTER_ID) */
	private String requesterId = "";	

	/** 요청자 이름 (RQESTER_NM) */
	private String requesterName = "";

	/** 요청아이피 (RQESTER_IP) */
	private String requesterIp = "";
	
	/** 검색조건 */
	private String searchCondition = "";	

	/** 검색시작일 */
	private String searchBeginDate = "";

	/** 검색종료일 */
	private String searchEndDate = "";
	
	/** 검색시작일 (화면용) */
	private String searchBeginDateView = "";	// ex: 2014.09.14
	
	/** 검색종료일 (화면용) */
	private String searchEndDateView = "";	// ex: 2014.09.14	

	/** 검색단어 */
	private String searchWord = "";

	/** 정렬순서 (DESC, ASC) */
	private String sortOrder = "";
	
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
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getInquiryDatetime() {
		return inquiryDatetime;
	}

	public void setInquiryDatetime(String inquiryDatetime) {
		this.inquiryDatetime = inquiryDatetime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInquiryInfo() {
		return inquiryInfo;
	}

	public void setInquiryInfo(String inquiryInfo) {
		this.inquiryInfo = inquiryInfo;
	}

	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getRequesterIp() {
		return requesterIp;
	}

	public void setRequesterIp(String requesterIp) {
		this.requesterIp = requesterIp;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchBeginDate() {
		return searchBeginDate;
	}

	public void setSearchBeginDate(String searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getSearchBeginDateView() {
		return searchBeginDateView;
	}

	public void setSearchBeginDateView(String searchBeginDateView) {
		this.searchBeginDateView = searchBeginDateView;
	}

	public String getSearchEndDateView() {
		return searchEndDateView;
	}

	public void setSearchEndDateView(String searchEndDateView) {
		this.searchEndDateView = searchEndDateView;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
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
