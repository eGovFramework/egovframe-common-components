package egovframework.com.uss.ion.evt.service;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

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

@Getter
@Setter
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

}
