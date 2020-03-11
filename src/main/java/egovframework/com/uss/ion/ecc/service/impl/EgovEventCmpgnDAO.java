package egovframework.com.uss.ion.ecc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.com.uss.ion.ecc.service.TnextrlHrVO;

@Repository("EgovEventCmpgnDAO")
public class EgovEventCmpgnDAO extends EgovComAbstractDAO {

	public List<?> selectEventCmpgnList(EventCmpgnVO searchVO) {
		return list("EventCmpgn.selectEventCmpgnList", searchVO);
	}

	public int selectEventCmpgnListCnt(EventCmpgnVO searchVO) {
		return (Integer) selectOne("EventCmpgn.selectEventCmpgnListCnt", searchVO);
	}

	public void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		insert("EventCmpgn.insertEventCmpgn", eventCmpgnVO);
	}

	public EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) {
		return (EventCmpgnVO) selectOne("EventCmpgn.selectEventCmpgnDetail", eventCmpgnVO);
	}

	public void updateEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		update("EventCmpgn.updateEventCmpgn", eventCmpgnVO);
	}

	public void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		delete("EventCmpgn.deleteEventCmpgn", eventCmpgnVO);
	}

	public List<?> selectTnextrlHrList(TnextrlHrVO searchVO) {
		return list("EventCmpgn.selectTnextrlHrList", searchVO);
	}

	public int selectTnextrlHrListCnt(TnextrlHrVO searchVO) {
		return (Integer) selectOne("EventCmpgn.selectTnextrlHrListCnt", searchVO);
	}

	public void insertTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		insert("EventCmpgn.insertTnextrlHr", tnextrlHrVO);
	}

	public TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO) {
		return (TnextrlHrVO) selectOne("EventCmpgn.selectTnextrlHrDetail", tnextrlHrVO);
	}

	public void updateTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		update("EventCmpgn.updateTnextrlHr", tnextrlHrVO);
	}

	public void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		delete("EventCmpgn.deleteTnextrlHr", tnextrlHrVO);
	}

	public void deleteEventCmpgnTnextrlHr(EventCmpgnVO eventCmpgnVO) {
		delete("EventCmpgn.deleteEventCmpgnTnextrlHr", eventCmpgnVO);
	}

}
