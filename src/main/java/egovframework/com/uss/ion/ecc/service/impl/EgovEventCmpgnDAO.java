package egovframework.com.uss.ion.ecc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.com.uss.ion.ecc.service.TnextrlHrVO;

/**
 * 개요
 * - 행사캠페인에 대한 DAO 인터페이스를 정의한다.
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.xx.xx  최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("EgovEventCmpgnDAO")
public interface EgovEventCmpgnDAO {

	List<EventCmpgnVO> selectEventCmpgnList(EventCmpgnVO eventCmpgnVO);

	int selectEventCmpgnListCnt(EventCmpgnVO eventCmpgnVO);

	void insertEventCmpgn(EventCmpgnVO eventCmpgnVO);

	EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO);

	void updateEventCmpgn(EventCmpgnVO eventCmpgnVO);

	void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO);

	List<TnextrlHrVO> selectTnextrlHrList(TnextrlHrVO tnextrlHrVO);

	int selectTnextrlHrListCnt(TnextrlHrVO tnextrlHrVO);

	void insertTnextrlHr(TnextrlHrVO tnextrlHrVO);

	TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO);

	void updateTnextrlHr(TnextrlHrVO tnextrlHrVO);

	void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO);

	void deleteEventCmpgnTnextrlHr(EventCmpgnVO eventCmpgnVO);

}
