package egovframework.com.uss.ion.evt.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 행사참석자에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 행사참석자의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class EventAtdrnVO extends EventAtdrn implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 배너 목록
	 */
	List<EventAtdrnVO> eventAtdrnList;

	/**
	 * @return the eventAtdrnList
	 */
	public List<EventAtdrnVO> getEventAtdrnList() {
		return eventAtdrnList;
	}
	/**
	 * @param eventManage the eventManage to set
	 */
	public void setEventAtdrnList(List<EventAtdrnVO> eventAtdrnList) {
		this.eventAtdrnList = eventAtdrnList;
	}



}
