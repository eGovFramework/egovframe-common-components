package egovframework.com.uss.ion.ecc.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovEventCmpgnService {

	List<?> selectEventCmpgnList(EventCmpgnVO searchVO);

	int selectEventCmpgnListCnt(EventCmpgnVO searchVO);

	void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) throws FdlException;

	EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) throws Exception;

	void updateEventCmpgn(EventCmpgnVO eventCmpgnVO);

	void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO);

	List<?> selectTnextrlHrList(TnextrlHrVO searchVO);

	int selectTnextrlHrListCnt(TnextrlHrVO searchVO);

	void insertTnextrlHr(TnextrlHrVO tnextrlHrVO) throws FdlException;

	TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO) throws Exception;

	void updateTnextrlHr(TnextrlHrVO tnextrlHrVO);

	void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO);

}
