package egovframework.com.uss.ion.ctn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.ctn.service.CtsnnManage;
import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;
import egovframework.com.uss.ion.ctn.service.EgovCtsnnManageService;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 경조관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Service("egovCtsnnManageService")
public class EgovCtsnnManageServiceImpl extends EgovAbstractServiceImpl implements EgovCtsnnManageService {

	@Resource(name="ctsnnManageDAO")
    private CtsnnManageDAO ctsnnManageDAO;
	
    /** ID Generation */  
	@Resource(name="egovCtsnnManageIdGnrService")
	private EgovIdGnrService idgenCtsnnManageService;

	
	@Resource(name="EgovInfrmlSanctnService")
    protected EgovInfrmlSanctnService infrmlSanctnService;
	
	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */
	public List<CtsnnManageVO> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO) throws Exception{
		ctsnnManageVO.setSearchFromDate(EgovStringUtil.removeMinusChar(ctsnnManageVO.getSearchFromDate()));
		ctsnnManageVO.setSearchToDate(EgovStringUtil.removeMinusChar(ctsnnManageVO.getSearchToDate()));
		List<CtsnnManageVO> result = ctsnnManageDAO.selectCtsnnManageList(ctsnnManageVO);

		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	CtsnnManageVO ctsnnManageVO1 = result.get(i);
	    	ctsnnManageVO1.setReqstDe(EgovDateUtil.formatDate(ctsnnManageVO1.getReqstDe(), "-"));
	    	ctsnnManageVO1.setOccrrDe(EgovDateUtil.formatDate(ctsnnManageVO1.getOccrrDe(), "-"));		
	    	result.set(i, ctsnnManageVO1);
	    }	
		return result;
	}

	/**
	 * 경조관리목록 총 개수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int - 경조관리 카운트 수
	 */
	public int selectCtsnnManageListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageDAO.selectCtsnnManageListTotCnt(ctsnnManageVO);
	}
	
	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return CtsnnManageVO - 경조관리 VO
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception {

		CtsnnManageVO ctsnnManageVOTemp = ctsnnManageDAO.selectCtsnnManage(ctsnnManageVO);
		ctsnnManageVOTemp.setReqstDe(EgovDateUtil.formatDate(ctsnnManageVOTemp.getReqstDe(), "-"));		
		ctsnnManageVOTemp.setOccrrDe(EgovDateUtil.formatDate(ctsnnManageVOTemp.getOccrrDe(), "-"));		
		ctsnnManageVOTemp.setBrth(EgovDateUtil.formatDate(ctsnnManageVOTemp.getBrth(), "-"));		
		return ctsnnManageVOTemp;
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void insertCtsnnManage(CtsnnManage ctsnnManage) throws Exception {

		java.util.Calendar cal = java.util.Calendar.getInstance();
    	String  sYear  =Integer.toString(cal.get(java.util.Calendar.YEAR));
    	String  sMonth =Integer.toString(cal.get(java.util.Calendar.MONTH)+1);
    	if(sMonth.length() == 1) sMonth = "0"+sMonth;
    	String  sDay   =Integer.toString(cal.get(java.util.Calendar.DATE));
    	if(sDay.length() == 1) sDay = "0"+sDay;
    	ctsnnManage.setReqstDe(sYear+sMonth+sDay);
    	
		/*
		 * 경조 승인처리  신청 
		 */
		//InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("001", ctsnnManage);

    	ctsnnManage.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManage.getReqstDe()));
    	ctsnnManage.setBrth(EgovStringUtil.removeMinusChar(ctsnnManage.getBrth()));
    	ctsnnManage.setOccrrDe(EgovStringUtil.removeMinusChar(ctsnnManage.getOccrrDe()));
		InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(ctsnnManage)); //신청
		ctsnnManage.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		ctsnnManage.setConfmAt(infrmlSanctn.getConfmAt());

		String	sCtsnnId = idgenCtsnnManageService.getNextStringId();
		ctsnnManage.setCtsnnId(sCtsnnId);
		
		ctsnnManageDAO.insertCtsnnManage(ctsnnManage);
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManage(CtsnnManage ctsnnManage) throws Exception {
		ctsnnManage.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManage.getReqstDe()));
    	ctsnnManage.setBrth(EgovStringUtil.removeMinusChar(ctsnnManage.getBrth()));
    	ctsnnManage.setOccrrDe(EgovStringUtil.removeMinusChar(ctsnnManage.getOccrrDe()));
		ctsnnManage.setBrth(EgovStringUtil.removeMinusChar(ctsnnManage.getBrth()));
		ctsnnManage.setOccrrDe(EgovStringUtil.removeMinusChar(ctsnnManage.getOccrrDe()));
		ctsnnManageDAO.updtCtsnnManage(ctsnnManage);
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void deleteCtsnnManage(CtsnnManage ctsnnManage) throws Exception {
		ctsnnManage.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManage.getReqstDe()));
    	ctsnnManage.setBrth(EgovStringUtil.removeMinusChar(ctsnnManage.getBrth()));
    	ctsnnManage.setOccrrDe(EgovStringUtil.removeMinusChar(ctsnnManage.getOccrrDe()));
		/*
		 * 포상 승인처리  삭제 infrmlSanctnService.deleteInfrmlSanctn("000", vcatnManage);
		 */
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(ctsnnManage));  //삭제
		//infrmlSanctnService.deleteInfrmlSanctn("001", ctsnnManage);
		ctsnnManageDAO.deleteCtsnnManage(ctsnnManage);
	}

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */
	public List<CtsnnManageVO> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO) throws Exception{
		ctsnnManageVO.setSearchFromDate(EgovStringUtil.removeMinusChar(ctsnnManageVO.getSearchFromDate()));
		ctsnnManageVO.setSearchToDate(EgovStringUtil.removeMinusChar(ctsnnManageVO.getSearchToDate()));
		List<CtsnnManageVO> result = ctsnnManageDAO.selectCtsnnManageConfmList(ctsnnManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	CtsnnManageVO ctsnnManageVO1 = result.get(i);
	    	ctsnnManageVO1.setReqstDe(EgovDateUtil.formatDate(ctsnnManageVO1.getReqstDe(), "-"));
	    	ctsnnManageVO1.setOccrrDe(EgovDateUtil.formatDate(ctsnnManageVO1.getOccrrDe(), "-"));	
	    	result.set(i, ctsnnManageVO1);
	    }	
		return result;
	}

	/**
	 * 경조승인목록 총 개수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int - 경조관리 카운트 수
	 */
	public int selectCtsnnManageConfmListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageDAO.selectCtsnnManageConfmListTotCnt(ctsnnManageVO);
	}
	
	/**
	 * 경조정보를 승인처리 한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManageConfm(CtsnnManage ctsnnManage) throws Exception {
		 InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
		 ctsnnManage.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManage.getReqstDe()));
	     ctsnnManage.setBrth(EgovStringUtil.removeMinusChar(ctsnnManage.getBrth()));
	     ctsnnManage.setOccrrDe(EgovStringUtil.removeMinusChar(ctsnnManage.getOccrrDe()));
	   //KISA 보안약점 조치 (2018-10-29, 윤창원)
		 if("C".equals(ctsnnManage.getConfmAt())){
			/*
			 * 승인처리
			 */
			 infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(ctsnnManage));  //승인
			 //infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm("001", ctsnnManage);
		 }else if("R".equals(ctsnnManage.getConfmAt())){
			/*
			 * 반려처리
			 */
			 //infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn("001", ctsnnManage);
			 infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(ctsnnManage));
		 }
		 ctsnnManage.setSanctnDt(infrmlSanctn.getSanctnDt());
		 ctsnnManage.setConfmAt(infrmlSanctn.getConfmAt());

		 ctsnnManageDAO.updtCtsnnManageConfm(ctsnnManage);
	}

	/**
	 * CtsnnManage model을 InfrmlSanctn model로 변환한다.
	 * @param CtsnnManage
	 * @return InfrmlSanctn
	 * @param ctsnnManage
	 */
	private InfrmlSanctn converToInfrmlSanctnObject(CtsnnManage ctsnnManage) throws Exception{
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
    	infrmlSanctn.setJobSeCode("001");								// 업무구분코드 (공통코드 COM75)
    	infrmlSanctn.setApplcntId(ctsnnManage.getUsid());			    // 사용자ID
    	infrmlSanctn.setReqstDe(ctsnnManage.getReqstDe());				// 신청일자
    	infrmlSanctn.setSanctnerId(ctsnnManage.getSanctnerId());		// 결재자ID
    	infrmlSanctn.setConfmAt(ctsnnManage.getConfmAt());				// 승인구분
    	infrmlSanctn.setSanctnDt(ctsnnManage.getSanctnDt());			// 결재일시
    	infrmlSanctn.setReturnResn(ctsnnManage.getReturnResn());		// 반려사유
    	infrmlSanctn.setFrstRegisterId(ctsnnManage.getFrstRegisterId());
    	ctsnnManage.setFrstRegisterPnttm(ctsnnManage.getFrstRegisterId());
    	infrmlSanctn.setLastUpdusrId(ctsnnManage.getLastUpdusrId());
    	infrmlSanctn.setLastUpdusrPnttm(ctsnnManage.getLastUpdusrPnttm());
    	infrmlSanctn.setInfrmlSanctnId(ctsnnManage.getInfrmlSanctnId());// 약식결재ID
    	return infrmlSanctn;
	}

}
