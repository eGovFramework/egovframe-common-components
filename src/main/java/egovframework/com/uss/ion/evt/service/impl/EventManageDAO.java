package egovframework.com.uss.ion.evt.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.evt.service.EventAtdrn;
import egovframework.com.uss.ion.evt.service.EventManage;
import egovframework.com.uss.ion.evt.service.EventManageVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 행사관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 행사관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("eventManageDAO")
public class EventManageDAO extends EgovComAbstractDAO {

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */	
	public List<EventManageVO> selectEventManageList(EventManageVO eventManageVO) throws Exception {
		return selectList("eventManageDAO.selectEventManageList", eventManageVO);
	}

    /**
	 * 행사관리목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectEventManageListTotCnt(EventManageVO eventManageVO) throws Exception {
        return (Integer)selectOne("eventManageDAO.selectEventManageListTotCnt", eventManageVO);
    }

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	public EventManageVO selectEventManage(EventManageVO eventManageVO)  throws Exception {
		return (EventManageVO) selectOne("eventManageDAO.selectEventManage", eventManageVO);
	}

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	public void insertEventManage(EventManage eventManage) throws Exception {
		insert("eventManageDAO.insertEventManage", eventManage);
	}

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * @param eventManage - 행사관리 model
	 */
	public void updtEventManage(EventManage eventManage) throws Exception {
		update("eventManageDAO.updateEventManage", eventManage);
	}

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	public void deleteEventManage(EventManage eventManage) throws Exception {
        delete("eventManageDAO.deleteEventManage",eventManage);
	}

	
	/** 행사접수관리 ***/
	/**
	 * 행사접수정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */	
	public List<EventManageVO> selectEventAtdrnList(EventManageVO eventManageVO) throws Exception {
		return selectList("eventManageDAO.selectEventAtdrnList", eventManageVO);
	}

    /**
	 * 행사접수관리목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectEventAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception {
        return (Integer)selectOne("eventManageDAO.selectEventAtdrnListTotCnt", eventManageVO);
    }

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */	
	public List<EventManageVO> selectEventRceptConfmList(EventManageVO eventManageVO) throws Exception {
		return selectList("eventManageDAO.selectEventRceptConfmList", eventManageVO);
	}

    /**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectEventRceptConfmListTotCnt(EventManageVO eventManageVO) throws Exception {
        return (Integer)selectOne("eventManageDAO.selectEventRceptConfmListTotCnt", eventManageVO);
    }

	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	public List<EventManageVO> selectEventNmList(EventManageVO eventManageVO) throws Exception {
		return selectList("eventManageDAO.selectEventNmList", eventManageVO);
	}
    
	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	public EventManageVO selectEventAtdrn(EventManageVO eventManageVO)  throws Exception {
		return (EventManageVO) selectOne("eventManageDAO.selectEventAtdrn", eventManageVO);
	}

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	public void insertEventAtdrn(EventAtdrn eventAtdrn) throws Exception {
		insert("eventManageDAO.insertEventAtdrn", eventAtdrn);
	}

	/**
	 * 기 등록된 행사접수관리정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	public void deleteEventAtdrn(EventAtdrn eventAtdrn) throws Exception {
        delete("eventManageDAO.deleteEventAtdrn",eventAtdrn);
	}

	/**
	 * 기 등록된 행사접수관리정보를 승인처리한다.
	 * @param eventManage - 행사관리 model
	 */
	public void updtEventAtdrn(EventAtdrn eventAtdrn) throws Exception {
		update("eventManageDAO.updtEventAtdrn", eventAtdrn);
	}

	
	/**
	 * 행사접수자 정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	
	public List<EventManageVO> selectEventReqstAtdrnList(EventManageVO eventManageVO) throws Exception {
		return selectList("eventManageDAO.selectEventReqstAtdrnList", eventManageVO);
	}

    /**
	 * 행사접수자 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectEventReqstAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception {
        return (Integer)selectOne("eventManageDAO.selectEventReqstAtdrnListTotCnt", eventManageVO);
    }
	
}
