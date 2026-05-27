package egovframework.com.uss.ion.evt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.evt.service.EventAtdrn;
import egovframework.com.uss.ion.evt.service.EventManage;
import egovframework.com.uss.ion.evt.service.EventManageVO;

/**
 * 개요
 * - 행사관리에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 행사관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("eventManageDAO")
public interface EventManageDAO {

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	List<EventManageVO> selectEventManageList(EventManageVO eventManageVO);

	/**
	 * 행사관리목록 총 개수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 */
	int selectEventManageListTotCnt(EventManageVO eventManageVO);

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	EventManageVO selectEventManage(EventManageVO eventManageVO);

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	void insertEventManage(EventManage eventManage);

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * @param eventManage - 행사관리 model
	 */
	void updtEventManage(EventManage eventManage);

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	void deleteEventManage(EventManage eventManage);

	/** 행사접수관리 **/

	/**
	 * 행사접수정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	List<EventManageVO> selectEventAtdrnList(EventManageVO eventManageVO);

	/**
	 * 행사접수관리목록 총 개수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 */
	int selectEventAtdrnListTotCnt(EventManageVO eventManageVO);

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	List<EventManageVO> selectEventRceptConfmList(EventManageVO eventManageVO);

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 개수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 */
	int selectEventRceptConfmListTotCnt(EventManageVO eventManageVO);

	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	List<EventManageVO> selectEventNmList(EventManageVO eventManageVO);

	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	EventManageVO selectEventAtdrn(EventManageVO eventManageVO);

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * @param eventAtdrn - 행사접수 model
	 */
	void insertEventAtdrn(EventAtdrn eventAtdrn);

	/**
	 * 기 등록된 행사접수관리정보를 삭제한다.
	 * @param eventAtdrn - 행사접수 model
	 */
	void deleteEventAtdrn(EventAtdrn eventAtdrn);

	/**
	 * 기 등록된 행사접수관리정보를 승인처리한다.
	 * @param eventAtdrn - 행사접수 model
	 */
	void updtEventAtdrn(EventAtdrn eventAtdrn);

	/**
	 * 행사접수자 정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	List<EventManageVO> selectEventReqstAtdrnList(EventManageVO eventManageVO);

	/**
	 * 행사접수자 목록 총 개수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 */
	int selectEventReqstAtdrnListTotCnt(EventManageVO eventManageVO);

}
