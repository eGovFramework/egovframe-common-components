package egovframework.com.uss.ion.ecc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.ecc.service.EgovEventCmpgnService;
import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.com.uss.ion.ecc.service.TnextrlHrVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovEventCmpgnService")
public class EgovEventCmpgnServiceImpl extends EgovAbstractServiceImpl implements EgovEventCmpgnService {

	@Resource(name="EgovEventCmpgnDAO")
	private EgovEventCmpgnDAO egovEventCmpgnDao;

	@Resource(name="egovEventInfoIdGnrService")
	private EgovIdGnrService idgenService1;

	@Resource(name="egovExtrlhrInfoIdGnrService")
	private EgovIdGnrService idgenService2;
	
	@Override
	public List<?> selectEventCmpgnList(EventCmpgnVO searchVO) {
		return egovEventCmpgnDao.selectEventCmpgnList(searchVO);
	}

	@Override
	public int selectEventCmpgnListCnt(EventCmpgnVO searchVO) {
		return egovEventCmpgnDao.selectEventCmpgnListCnt(searchVO);
	}

	@Override
	public void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) throws FdlException {
		String eventId = idgenService1.getNextStringId();
		eventCmpgnVO.setEventId(eventId);
		
		egovEventCmpgnDao.insertEventCmpgn(eventCmpgnVO);
	}

	@Override
	public EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) throws Exception {
		EventCmpgnVO resultVO = egovEventCmpgnDao.selectEventCmpgnDetail(eventCmpgnVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		egovEventCmpgnDao.updateEventCmpgn(eventCmpgnVO);
	}

	@Override
	public void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		//행사/이벤트/캠페인에 속한 외부인사정보를 삭제한다.
		egovEventCmpgnDao.deleteEventCmpgnTnextrlHr(eventCmpgnVO);

		//행사/이벤트/캠페인을 삭제한다.
		egovEventCmpgnDao.deleteEventCmpgn(eventCmpgnVO);
		
	}

	@Override
	public List<?> selectTnextrlHrList(TnextrlHrVO searchVO) {
		return egovEventCmpgnDao.selectTnextrlHrList(searchVO);
	}

	@Override
	public int selectTnextrlHrListCnt(TnextrlHrVO searchVO) {
		return egovEventCmpgnDao.selectTnextrlHrListCnt(searchVO);
	}

	@Override
	public void insertTnextrlHr(TnextrlHrVO tnextrlHrVO) throws FdlException {
		String extrlHrId = idgenService2.getNextStringId();
		tnextrlHrVO.setExtrlHrId(extrlHrId);
		
		egovEventCmpgnDao.insertTnextrlHr(tnextrlHrVO);
	}

	@Override
	public TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO) throws Exception {
		TnextrlHrVO resultVO = egovEventCmpgnDao.selectTnextrlHrDetail(tnextrlHrVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		egovEventCmpgnDao.updateTnextrlHr(tnextrlHrVO);
	}

	@Override
	public void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		egovEventCmpgnDao.deleteTnextrlHr(tnextrlHrVO);
	}

}
