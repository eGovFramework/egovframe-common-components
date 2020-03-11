package egovframework.com.uss.ion.rwd.service.impl;

import java.util.List;

import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.rwd.service.EgovRwardManageService;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 포상관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 포상관리에 대한 등록, 수정, 삭제, 조회, 승인처리 기능을 제공한다.
 * - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Service("egovRwardManageService")
public class EgovRwardManageServiceImpl extends EgovAbstractServiceImpl implements EgovRwardManageService {

	@Resource(name="rwardManageDAO")
    private RwardManageDAO rwardManageDAO;

    /** ID Generation */  
	@Resource(name="egovRwardManageIdGnrService")
	private EgovIdGnrService idgenRwardManageService;
	
	@Resource(name="EgovInfrmlSanctnService")
    protected EgovInfrmlSanctnService infrmlSanctnService;
	
	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageList(RwardManageVO rwardManageVO) throws Exception{
		rwardManageVO.setSearchFromDate(EgovStringUtil.removeMinusChar(rwardManageVO.getSearchFromDate()));
		rwardManageVO.setSearchToDate(EgovStringUtil.removeMinusChar(rwardManageVO.getSearchToDate()));
		List<RwardManageVO> result = rwardManageDAO.selectRwardManageList(rwardManageVO);

		
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	RwardManageVO rwardManageVO1 = result.get(i);
	    	rwardManageVO1.setRwardDe(EgovDateUtil.formatDate(rwardManageVO1.getRwardDe(), "-"));
	    	result.set(i, rwardManageVO1);
	    }	
		return result;
	}

	/**
	 * 포상관리목록 총 갯수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int - 포상관리 카운트 수
	 */
	public int selectRwardManageListTotCnt(RwardManageVO rwardManageVO) throws Exception {
		return rwardManageDAO.selectRwardManageListTotCnt(rwardManageVO);
	}
	
	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return RwardManageVO - 포상관리 VO
	 */
	public RwardManageVO selectRwardManage(RwardManageVO rwardManageVO) throws Exception {

		RwardManageVO rwardManageVOTemp = rwardManageDAO.selectRwardManage(rwardManageVO);
		rwardManageVOTemp.setRwardDe(EgovDateUtil.formatDate(rwardManageVOTemp.getRwardDe(), "-"));		
		
		return rwardManageVOTemp;
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void insertRwardManage(RwardManage rwardManage) throws Exception {

		/*
		 * 포상 승인처리  신청 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
		rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
       	InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(rwardManage)); //신청
		rwardManage.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		rwardManage.setConfmAt(infrmlSanctn.getConfmAt());

		String	sRwardId = idgenRwardManageService.getNextStringId();
		rwardManage.setRwardId(sRwardId);
		
		rwardManageDAO.insertRwardManage(rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManage(RwardManage rwardManage) throws Exception {
		rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
		rwardManageDAO.updtRwardManage(rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void deleteRwardManage(RwardManage rwardManage) throws Exception {
		/*
		 * 포상 승인처리  삭제 infrmlSanctnService.deleteInfrmlSanctn("000", vcatnManage);
		 */
		rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
        infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(rwardManage));  //삭제
		rwardManageDAO.deleteRwardManage(rwardManage);
	}


	
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageConfmList(RwardManageVO rwardManageVO) throws Exception{
		rwardManageVO.setSearchFromDate(EgovStringUtil.removeMinusChar(rwardManageVO.getSearchFromDate()));
		rwardManageVO.setSearchToDate(EgovStringUtil.removeMinusChar(rwardManageVO.getSearchToDate()));
		List<RwardManageVO> result = rwardManageDAO.selectRwardManageConfmList(rwardManageVO);

		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	RwardManageVO rwardManageVO1 = result.get(i);
	    	rwardManageVO1.setRwardDe(EgovDateUtil.formatDate(rwardManageVO1.getRwardDe(), "-"));
	    	result.set(i, rwardManageVO1);
	    }	
		return result;
	}

	/**
	 * 포상승인목록 총 갯수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int - 포상관리 카운트 수
	 */
	public int selectRwardManageConfmListTotCnt(RwardManageVO rwardManageVO) throws Exception {
		return rwardManageDAO.selectRwardManageConfmListTotCnt(rwardManageVO);
	}
	
	/**
	 * 포상정보를 승인/반려처리 한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManageConfm(RwardManage rwardManage) throws Exception {
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
		rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		if("C".equals(rwardManage.getConfmAt())){
			/*
			 * 승인처리
			 */
			infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(rwardManage));  //승인
			//infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm("002", rwardManage);
		}else if("R".equals(rwardManage.getConfmAt())){
			/*
			 * 반려처리
			 */
			infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(rwardManage));  //반려
			//infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn("002", rwardManage);
		}
		rwardManage.setSanctnDt(infrmlSanctn.getSanctnDt());
		rwardManage.setConfmAt(infrmlSanctn.getConfmAt());

		rwardManageDAO.updtRwardManageConfm(rwardManage);
	}

	/**
	 * RwardManage model을 InfrmlSanctn model로 변환한다.
	 * @param RwardManage
	 * @return InfrmlSanctn
	 * @param rwardManage
	 */
	private InfrmlSanctn converToInfrmlSanctnObject(RwardManage rwardManage) throws Exception{
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
    	infrmlSanctn.setJobSeCode("002");								// 업무구분코드 (공통코드 COM75)
    	infrmlSanctn.setApplcntId(rwardManage.getRwardManId());			// 포상자ID
    	infrmlSanctn.setReqstDe(rwardManage.getRwardDe());				// 포상일자
    	infrmlSanctn.setSanctnerId(rwardManage.getSanctnerId());		// 결재자ID
    	infrmlSanctn.setConfmAt(rwardManage.getConfmAt());				// 승인구분
    	infrmlSanctn.setSanctnDt(rwardManage.getSanctnDt());			// 결재일시
    	infrmlSanctn.setReturnResn(rwardManage.getReturnResn());		// 반려사유
    	infrmlSanctn.setFrstRegisterId(rwardManage.getFrstRegisterId());
    	infrmlSanctn.setFrstRegisterPnttm(rwardManage.getFrstRegisterId());
    	infrmlSanctn.setLastUpdusrId(rwardManage.getLastUpdusrId());
    	infrmlSanctn.setLastUpdusrPnttm(rwardManage.getLastUpdusrPnttm());
    	infrmlSanctn.setInfrmlSanctnId(rwardManage.getInfrmlSanctnId());// 약식결재ID
    	return infrmlSanctn;
	}
	
}
