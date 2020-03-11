package egovframework.com.uss.ion.evt.service.impl;

import java.util.List;

import egovframework.com.uss.ion.evt.service.EgovEventManageService;
import egovframework.com.uss.ion.evt.service.EventAtdrn;
import egovframework.com.uss.ion.evt.service.EventManage;
import egovframework.com.uss.ion.evt.service.EventManageVO;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 행사관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 행사관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 *  
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 * 
 *  <pre>
 * << 개정이력(Modification Information) >> *   
 *   수정일       수정자           수정내용
 *  -------       --------    ---------------------------
 *  2011.8.11     정진오      Dependency 최소화를 위한 불필요 변수 선언 주석처리
 *   
 * </pre>
 */

@Service("egovEventManageService")
public class EgovEventManageServiceImpl extends EgovAbstractServiceImpl implements EgovEventManageService {

	@Resource(name="eventManageDAO")
    private EventManageDAO eventManageDAO;

    /** ID Generation */  
	@Resource(name="egovEventManageIdGnrService")
	private EgovIdGnrService idgenEventService;

	@Resource(name="EgovInfrmlSanctnService")
    protected EgovInfrmlSanctnService infrmlSanctnService;
	
	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	public List<EventManageVO> selectEventManageList(EventManageVO eventManageVO) throws Exception{

		List<EventManageVO> result = eventManageDAO.selectEventManageList(eventManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	EventManageVO eventManageVO1 = result.get(i);
	    	eventManageVO1.setEventBeginDe(EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
	    	eventManageVO1.setEventEndDe  (EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
	    	eventManageVO1.setRceptBeginDe(EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
	    	eventManageVO1.setRceptEndDe  (EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));
	    	result.set(i, eventManageVO1);
	    }	
		return result;
	}

	/**
	 * 행사관리목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사관리 카운트 수
	 */
	public int selectEventManageListTotCnt(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventManageListTotCnt(eventManageVO);
	}
	
	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	public EventManageVO selectEventManage(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventManage(eventManageVO);
	}

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	public void insertEventManage(EventManage eventManage) throws Exception {
		eventManage.setEventBeginDe(EgovStringUtil.removeMinusChar(eventManage.getEventBeginDe()));
		eventManage.setEventEndDe  (EgovStringUtil.removeMinusChar(eventManage.getEventEndDe()  ));
		eventManage.setRceptBeginDe(EgovStringUtil.removeMinusChar(eventManage.getRceptBeginDe()));
		eventManage.setRceptEndDe  (EgovStringUtil.removeMinusChar(eventManage.getRceptEndDe()  ));
	
		String	sEventId = idgenEventService.getNextStringId();
		eventManage.setEventId(sEventId);
		eventManageDAO.insertEventManage(eventManage);
	}

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * @param eventManage - 행사관리 model
	 */
	public void updtEventManage(EventManage eventManage) throws Exception {
		eventManage.setEventBeginDe(EgovStringUtil.removeMinusChar(eventManage.getEventBeginDe()));
		eventManage.setEventEndDe  (EgovStringUtil.removeMinusChar(eventManage.getEventEndDe()  ));
		eventManage.setRceptBeginDe(EgovStringUtil.removeMinusChar(eventManage.getRceptBeginDe()));
		eventManage.setRceptEndDe  (EgovStringUtil.removeMinusChar(eventManage.getRceptEndDe()  ));
		eventManageDAO.updtEventManage(eventManage);
	}

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	public void deleteEventManage(EventManage eventManage) throws Exception {
		eventManageDAO.deleteEventManage(eventManage);
	}

/***  행사접수관리  ****/
	/**
	 * 행사접수관리정보를 관리하기 위해 등록된 행사접수관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사접수관리 목록
	 */
	public List<EventManageVO> selectEventAtdrnList(EventManageVO eventManageVO) throws Exception{
		List<EventManageVO> result = eventManageDAO.selectEventAtdrnList(eventManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	EventManageVO eventManageVO1 = result.get(i);
	    	eventManageVO1.setEventBeginDe(EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
	    	eventManageVO1.setEventEndDe  (EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
	    	eventManageVO1.setRceptBeginDe(EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
	    	eventManageVO1.setRceptEndDe  (EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));
	    	result.set(i, eventManageVO1);
	    }	
		return result;
	}

	/**
	 * 행사접수관리목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사접수관리 카운트 수
	 */
	public int selectEventAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventAtdrnListTotCnt(eventManageVO);
	}

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사접수승인 목록
	 */
	public List<EventManageVO> selectEventRceptConfmList(EventManageVO eventManageVO) throws Exception{
		List<EventManageVO> result = eventManageDAO.selectEventRceptConfmList(eventManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	EventManageVO eventManageVO1 = result.get(i);
	    	eventManageVO1.setEventBeginDe(EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
	    	eventManageVO1.setEventEndDe  (EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
	    	result.set(i, eventManageVO1);
	    }	
		return result;
	}

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사접수승인 카운트 수
	 */
	public int selectEventRceptConfmListTotCnt(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventRceptConfmListTotCnt(eventManageVO);
	}
	
	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사명 목록
	 */
	public List<EventManageVO> selectEventNmList(EventManageVO eventManageVO) throws Exception{
		List<EventManageVO> result = eventManageDAO.selectEventNmList(eventManageVO);
		return result;
	}

	
	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO 
	 */
	public EventManageVO selectEventAtdrn(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventAtdrn(eventManageVO);
	}

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * @param eventManage - 행사접수관리 model
	 */
	public void insertEventAtdrn(EventAtdrn eventAtdrn) throws Exception {
		
		/*
		 * 행사접수 승인처리  신청 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
		InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(eventAtdrn)); //신청
		//InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("004", eventAtdrn);
		eventAtdrn.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		eventAtdrn.setConfmAt(infrmlSanctn.getConfmAt());
		eventManageDAO.insertEventAtdrn(eventAtdrn);
	}

	/**
	 * 기 등록된 행사접수관리정보를 삭제한다.
	 * @param eventManage - 행사접수관리 model
	 */
	public void deleteEventAtdrn(EventAtdrn eventAtdrn) throws Exception {
		eventAtdrn.setReqstDe(EgovStringUtil.removeMinusChar(eventAtdrn.getReqstDe()));
		/*
		 * 행사접수 승인처리  삭제 
		 */
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(eventAtdrn));  //삭제
		eventManageDAO.deleteEventAtdrn(eventAtdrn);
	}

	/**
	 * 기 등록된 행사접수관리정보를 승인/반려처리한다.
	 * @param eventManage - 행사접수관리 model
	 * @param String      - 승인/반려정보
	 */
	public void updtEventAtdrn(EventAtdrn eventAtdrn, String checkedEventRceptForConfm) throws Exception {
		
		//MtgPlaceFxtrs mtgPlaceFxtrs;	// 2011.8.11 수정분 mtg(회의실관리 컴포넌트)와의 의존성 제거
		//int insertCnt    = 0;			// 2011.8.11 수정분
		String [] eventRceptValues = checkedEventRceptForConfm.split("[$]");
		String [] sTempEventRcept;
		String    sTemp=null;
		for (int i=0; i<eventRceptValues.length ; i++){
			sTemp = eventRceptValues[i];
			sTempEventRcept = sTemp.split(",");
			eventAtdrn.setEventId(sTempEventRcept[0]); 
			eventAtdrn.setApplcntId(sTempEventRcept[1]);
			eventAtdrn.setInfrmlSanctnId(sTempEventRcept[2]); 
			eventAtdrn.setReqstDe(sTempEventRcept[3]);
 		    InfrmlSanctn infrmlSanctn = new InfrmlSanctn();

			if(eventAtdrn.getConfmAt().equals("C")){
				/*
				 * 승인처리
				 */
				infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(eventAtdrn));  //승인
				//infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm("004", eventAtdrn);
			}else if(eventAtdrn.getConfmAt().equals("R")){
				/*
				 * 반려처리
				 */
				infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(eventAtdrn));  //반려
				//infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn("004", eventAtdrn);
			}
			eventAtdrn.setSanctnDt(infrmlSanctn.getSanctnDt());
			eventAtdrn.setConfmAt(infrmlSanctn.getConfmAt());
			
			eventManageDAO.updtEventAtdrn(eventAtdrn);
		}
	}	
	
	/**
	 * 행사접수자 정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	public List<EventManageVO> selectEventReqstAtdrnList(EventManageVO eventManageVO) throws Exception{
		return eventManageDAO.selectEventReqstAtdrnList(eventManageVO);
	}

	/**
	 * 행사접수자 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사관리 카운트 수
	 */
	public int selectEventReqstAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception {
		return eventManageDAO.selectEventReqstAtdrnListTotCnt(eventManageVO);
	}

	/**
	 * CtsnnManage model을 InfrmlSanctn model로 변환한다.
	 * @param CtsnnManage
	 * @return InfrmlSanctn
	 */
	private InfrmlSanctn converToInfrmlSanctnObject(EventAtdrn eventAtdrn) throws Exception{
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
    	infrmlSanctn.setJobSeCode("004");								// 업무구분코드 (공통코드 COM75)
    	infrmlSanctn.setApplcntId(eventAtdrn.getApplcntId());			    // 사용자ID
    	infrmlSanctn.setReqstDe(eventAtdrn.getReqstDe());				// 신청일자
    	infrmlSanctn.setSanctnerId(eventAtdrn.getSanctnerId());		// 결재자ID
    	infrmlSanctn.setConfmAt(eventAtdrn.getConfmAt());				// 승인구분
    	infrmlSanctn.setSanctnDt(eventAtdrn.getSanctnDt());			// 결재일시
    	infrmlSanctn.setReturnResn(eventAtdrn.getReturnResn());		// 반려사유
    	infrmlSanctn.setFrstRegisterId(eventAtdrn.getFrstRegisterId());
    	infrmlSanctn.setFrstRegisterPnttm(eventAtdrn.getFrstRegisterId());
    	infrmlSanctn.setLastUpdusrId(eventAtdrn.getLastUpdusrId());
    	infrmlSanctn.setLastUpdusrPnttm(eventAtdrn.getLastUpdusrPnttm());
    	infrmlSanctn.setInfrmlSanctnId(eventAtdrn.getInfrmlSanctnId());// 약식결재ID
    	return infrmlSanctn;
	}
	
}
