package egovframework.com.uss.ion.evt.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 행사관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 행사관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */


public class EventManageVO extends EventManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 배너 목록
	 */
	List<EventManageVO> eventManageList;

	/**
	*  신청자ID
	*/
	private String applcntId;

	/**
	*  신청일자
	*/
	private String reqstDe;

	/**
	*  결재자ID
	*/
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
	*  eventTemp1
	*/
	private String eventTemp1;

	/**
	*  eventTemp1
	*/
	private String eventTemp2;
	/**
	*  eventTemp1
	*/

	private String eventTemp3;

	/**
	*  eventTemp4
	*/
	private String eventTemp4;

	/**
	*  eventTemp5
	*/
	private String eventTemp5;

	/**
	*  eventTemp6
	*/
	private String eventTemp6;

	/**
	*  eventTemp7
	*/
	private String eventTemp7;

	/**
	*  결재자명
	*/
	private String sanctnerNm;

	/**
	*  신청자명
	*/
	private String applcntNm;

	/**
	*  행사구분명
	*/
	private String eventSeNm;

	/**
	*  검색 연도
	*/
	private String searchYear;

	/**
	*  검색 월
	*/
	private String searchMonth;

	/**
	*  검색 명
	*/
	private String searchNm;

	/**
	*  검색 구분명
	*/
	private String searchSe;

	/**
	*  체크 행사접수기간 일수
	*/
	private int eventDayCount;

	/**
	*  체크 행사참여인원
	*/
	private int eventAtdrnCount;

	/**
	*  searchToDateView
	*/
	private String searchToDateView;

	/**
	*  searchFromDateView
	*/
	private String searchFromDateView;

	/**
	*  검색 승인여부
	*/
	private String searchConfmAt;

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
	 * @return the searchToDateView
	 */
	public String getSearchToDateView() {
		return searchToDateView;
	}
	/**
	 * @param searchToDateView the searchToDateView to set
	 */
	public void setSearchToDateView(String searchToDateView) {
		this.searchToDateView = searchToDateView;
	}
	/**
	 * @return the searchFromDateView
	 */
	public String getSearchFromDateView() {
		return searchFromDateView;
	}
	/**
	 * @param searchFromDateView the searchFromDateView to set
	 */
	public void setSearchFromDateView(String searchFromDateView) {
		this.searchFromDateView = searchFromDateView;
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
	 * @return the searchSe
	 */
	public String getSearchSe() {
		return searchSe;
	}

	/**
	 * @param searchSe the searchSe to set
	 */
	public void setSearchSe(String searchSe) {
		this.searchSe = searchSe;
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
	 * @return the eventSeNm
	 */
	public String getEventSeNm() {
		return eventSeNm;
	}

	/**
	 * @param eventSeNm the eventSeNm to set
	 */
	public void setEventSeNm(String eventSeNm) {
		this.eventSeNm = eventSeNm;
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
	 * @return the eventTemp1
	 */
	public String getEventTemp1() {
		return eventTemp1;
	}

	/**
	 * @param eventTemp1 the eventTemp1 to set
	 */
	public void setEventTemp1(String eventTemp1) {
		this.eventTemp1 = eventTemp1;
	}

	/**
	 * @return the eventTemp2
	 */
	public String getEventTemp2() {
		return eventTemp2;
	}

	/**
	 * @param eventTemp2 the eventTemp2 to set
	 */
	public void setEventTemp2(String eventTemp2) {
		this.eventTemp2 = eventTemp2;
	}

	/**
	 * @return the eventTemp3
	 */
	public String getEventTemp3() {
		return eventTemp3;
	}

	/**
	 * @param eventTemp3 the eventTemp3 to set
	 */
	public void setEventTemp3(String eventTemp3) {
		this.eventTemp3 = eventTemp3;
	}

	/**
	 * @return the eventTemp4
	 */
	public String getEventTemp4() {
		return eventTemp4;
	}

	/**
	 * @param eventTemp4 the eventTemp4 to set
	 */
	public void setEventTemp4(String eventTemp4) {
		this.eventTemp4 = eventTemp4;
	}

	/**
	 * @return the eventTemp5
	 */
	public String getEventTemp5() {
		return eventTemp5;
	}

	/**
	 * @param eventTemp5 the eventTemp5 to set
	 */
	public void setEventTemp5(String eventTemp5) {
		this.eventTemp5 = eventTemp5;
	}

	/**
	 * @return the eventTemp6
	 */
	public String getEventTemp6() {
		return eventTemp6;
	}

	/**
	 * @param eventTemp6 the eventTemp6 to set
	 */
	public void setEventTemp6(String eventTemp6) {
		this.eventTemp6 = eventTemp6;
	}

	/**
	 * @return the eventTemp7
	 */
	public String getEventTemp7() {
		return eventTemp7;
	}

	/**
	 * @param eventTemp7 the eventTemp7 to set
	 */
	public void setEventTemp7(String eventTemp7) {
		this.eventTemp7 = eventTemp7;
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
	 * @return the eventManageList
	 */
	public List<EventManageVO> getEventManageList() {
		return eventManageList;
	}
	/**
	 * @param eventManage the eventManage to set
	 */
	public void setEventManageList(List<EventManageVO> eventManageList) {
		this.eventManageList = eventManageList;
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
	 * @return the eventDayCount
	 */
	public int getEventDayCount() {
		return eventDayCount;
	}

	/**
	 * @param eventDayCount the eventDayCount to set
	 */
	public void setEventDayCount(int eventDayCount) {
		this.eventDayCount = eventDayCount;
	}

	/**
	 * @return the eventAtdrnCount
	 */
	public int getEventAtdrnCount() {
		return eventAtdrnCount;
	}

	/**
	 * @param eventAtdrnCount the eventAtdrnCount to set
	 */
	public void setEventAtdrnCount(int eventAtdrnCount) {
		this.eventAtdrnCount = eventAtdrnCount;
	}

}
