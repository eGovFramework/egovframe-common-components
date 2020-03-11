package egovframework.com.uss.ion.vct.service.impl;

import java.util.Calendar;
import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.vct.service.EgovVcatnManageService;
import egovframework.com.uss.ion.vct.service.IndvdlYrycManage;
import egovframework.com.uss.ion.vct.service.VcatnManage;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 휴가관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Service("egovVcatnManageService")
public class EgovVcatnManageServiceImpl extends EgovAbstractServiceImpl implements EgovVcatnManageService {

	@Resource(name="vcatnManageDAO")
    private VcatnManageDAO vcatnManageDAO;

	@Resource(name="EgovInfrmlSanctnService")
    protected EgovInfrmlSanctnService infrmlSanctnService;

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	@Override
	public List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO) throws Exception{

		List<VcatnManageVO> result = vcatnManageDAO.selectVcatnManageList(vcatnManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	VcatnManageVO vcatnManageVO1 = result.get(i);
	    	vcatnManageVO1.setBgnde(EgovDateUtil.formatDate(vcatnManageVO1.getBgnde(), "-"));
	    	vcatnManageVO1.setEndde(EgovDateUtil.formatDate(vcatnManageVO1.getEndde(), "-"));
	    	result.set(i, vcatnManageVO1);
	    }
		return result;
	}

	/**
	 * 휴가관리목록 총 갯수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int - 휴가관리 카운트 수
	 */
	@Override
	public int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return vcatnManageDAO.selectVcatnManageListTotCnt(vcatnManageVO);
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	@Override
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {

		//VcatnManageVO vcatnManageVOTemp = new VcatnManageVO();

		VcatnManageVO vcatnManageVOTemp = vcatnManageDAO.selectVcatnManage(vcatnManageVO);
    	vcatnManageVOTemp.setBgnde(EgovDateUtil.formatDate(vcatnManageVOTemp.getBgnde(), "-"));
    	vcatnManageVOTemp.setEndde(EgovDateUtil.formatDate(vcatnManageVOTemp.getEndde(), "-"));

    	// 연차정보
    	VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
    	vcatnManageVOTemp.setOccrrncYear(vcatnManageVO1.getOccrrncYear());
    	vcatnManageVOTemp.setUsid(vcatnManageVO1.getUsid());
    	vcatnManageVOTemp.setOccrncYrycCo(vcatnManageVO1.getOccrncYrycCo());
    	vcatnManageVOTemp.setUseYrycCo(vcatnManageVO1.getUseYrycCo());
    	vcatnManageVOTemp.setRemndrYrycCo(vcatnManageVO1.getRemndrYrycCo());

		return vcatnManageVOTemp;
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * @param vcatnManage - 휴가관리 model
	 * @return String
	 * 01 : 입력성공, 02 : 연차휴가 등록실패(잔여연차 부족),  03: 반차휴가 등록실패(잔여연차 부족)
	 */
	@Override
	public String insertVcatnManage(VcatnManage vcatnManage, VcatnManageVO vcatnManageVO) throws Exception {
		java.util.Calendar cal = java.util.Calendar.getInstance();
    	String  sYear  =Integer.toString(cal.get(java.util.Calendar.YEAR));
    	String  sMonth =Integer.toString(cal.get(java.util.Calendar.MONTH)+1);
    	if(sMonth.length() == 1) sMonth = "0"+sMonth;
    	String  sDay   =Integer.toString(cal.get(java.util.Calendar.DATE));

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	// KISA 보안취약점 조치 (2018-12-10, 신용호)
		String uniqId = "";
		if (user!=null){
			uniqId=user.getUniqId();		
		}
    	vcatnManage.setOccrrncYear(sYear);
    	vcatnManage.setReqstDe(sYear+sMonth+sDay);
		/*
		 * 휴가 승인처리  신청 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
    	vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
    	vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
    	vcatnManage.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManage.getReqstDe()));
    	InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(vcatnManage));
		//InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("003", vcatnManage);
		vcatnManage.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(uniqId);
		double iUseYrycCo    = vcatnManageVO1.getUseYrycCo(); //연차테이블의 사용 연차갯수
		double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); //연차테이블의 잔여 연차갯수
		double iCountYryc = 0.0;
		
		/*
		 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
		 */
	    // 휴가구분이 연차인 경우
		if( "01".equals(vcatnManage.getVcatnSe()) ){
	    	// 연차 휴가 연도 체크
	    	if(!getVcatnYearSE(vcatnManage)){
	    		return "09";
	    	}
	    	iCountYryc = getDateCalc(vcatnManage.getBgnde(), vcatnManage.getEndde());
	    	if(iCountYryc == 0) return "99";  //연차설정오류
	    	else if((iRemndrYrycCo - iCountYryc) < 0) return "02";
		    else{
			   vcatnManageDAO.insertVcatnManage(vcatnManage);
			   IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
			   indvdlYrycManage.setUseYrycCo(iUseYrycCo+iCountYryc);
			   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo - iCountYryc);
			   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
			   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
			   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());
			   updtIndvdlYrycManage(indvdlYrycManage);
			   return "01";
		    }
	    }
	    // 휴가구분이 반차인 경우
	    else if( "02".equals(vcatnManage.getVcatnSe()) ){

	    	// 연차 휴가 연도 체크
	    	if(!getVcatnYearSE(vcatnManage)){
	    		return "09";
	    	}
	    	iCountYryc = getDateCalc(vcatnManage.getBgnde(), vcatnManage.getBgnde()); //반차는 시작일자 종료일자 동일함. 시작일자로만 체크
	    	if(iCountYryc == 0) return "99";  //연차설정오류
	    	else if((iRemndrYrycCo - 0.5) < 0) return "03";
		    else{
			   vcatnManageDAO.insertVcatnManage(vcatnManage);
			   IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
			   indvdlYrycManage.setUseYrycCo(iUseYrycCo+0.5);
			   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo - 0.5);
			   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
			   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
			   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());
			   updtIndvdlYrycManage(indvdlYrycManage);

			   return "01";
		    }
	    }
	    else{
		   vcatnManageDAO.insertVcatnManage(vcatnManage);
		   return "01";
	    }		
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	@Override
	public String updtVcatnManage(VcatnManage vcatnManage, VcatnManageVO vcatnManageVO) throws Exception {
		int iTemp =0;
		String sTempMessage = null;
		String sTempApplcntId= vcatnManage.getApplcntId();
		String sTempVcatnSe  = vcatnManage.getVcatnSe();
		String sTempBgnde    = vcatnManage.getBgnde();
		String sTempEndde    = vcatnManage.getEndde();

		/* 삭제처리 */
		vcatnManage.setApplcntId(vcatnManageVO.getApplcntIdKey());
		vcatnManage.setVcatnSe(vcatnManageVO.getVcatnSeKey());
		vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgndeKey()));
		vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEnddeKey()));

		deleteVcatnManage(vcatnManage);
		/* 등록처리 */
		vcatnManage.setApplcntId(sTempApplcntId);
		vcatnManage.setVcatnSe(sTempVcatnSe);
		vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(sTempBgnde));
		vcatnManage.setEndde(EgovStringUtil.removeMinusChar(sTempEndde));
		if(vcatnManage.getSanctnerId() != null) vcatnManage.setConfmAt("A");

		vcatnManageVO.setSearchKeyword(vcatnManage.getBgnde());
		//시작일자  포함여부
		iTemp = selectVcatnManageDplctAt(vcatnManageVO);
		vcatnManageVO.setSearchKeyword(vcatnManage.getEndde());
		//종료일자  포함여부
		iTemp += selectVcatnManageDplctAt(vcatnManageVO);

	    if(iTemp == 0){
	    	sTempMessage =  insertVcatnManage(vcatnManage,vcatnManageVO);
			System.out.println("updtVcatnManage 4:"+sTempMessage);
	    	return sTempMessage;
	    }else{
	    	sTempMessage = "10";
			System.out.println("updtVcatnManage 5:"+sTempMessage);
			return sTempMessage;
	    }
/*
    	vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
    	vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
		vcatnManage.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManage.getReqstDe()));
		vcatnManageDAO.updtVcatnManage(vcatnManage);

		 return "01";
*/
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	@Override
	@SuppressWarnings("unused")
	public void deleteVcatnManage(VcatnManage vcatnManage) throws Exception {
		/*
		 * 휴가 승인처리  삭제 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
    	vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
    	vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
		vcatnManage.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManage.getReqstDe()));
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(vcatnManage));

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 개인연차조회
		VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManage.getApplcntId());
		double iUseYrycCo    = vcatnManageVO1.getUseYrycCo(); //연차테이블의 사용 연차갯수
		double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); //연차테이블의 잔여 연차갯수
		double iCountYryc = 0.0;
		/*
		 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
		 */
	    // 휴가구분이 연차인 경우
	    if("01".equals(vcatnManage.getVcatnSe())){

	       iCountYryc = getDateCalc(vcatnManage.getBgnde(), vcatnManage.getEndde());
		   IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
		   indvdlYrycManage.setUseYrycCo(iUseYrycCo-iCountYryc);
		   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
		   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
		   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
		   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());


		   updtIndvdlYrycManage(indvdlYrycManage);
		   vcatnManageDAO.deleteVcatnManage(vcatnManage);

	    }
	    // 휴가구분이 반차인 경우
	    else if("02".equals(vcatnManage.getVcatnSe())){//KISA 보안약점 조치 (2018-10-29, 윤창원)

		   IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
		   indvdlYrycManage.setUseYrycCo(iUseYrycCo-0.5);
		   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo + 0.5);
		   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
		   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
		   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());
		   updtIndvdlYrycManage(indvdlYrycManage);
		   vcatnManageDAO.deleteVcatnManage(vcatnManage);

	    }
	    else{
		   vcatnManageDAO.deleteVcatnManage(vcatnManage);
	    }
	}

    /**
	 * 휴가일자 중복여부 체크
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO) throws Exception {
		return vcatnManageDAO.selectVcatnManageDplctAt(vcatnManageVO);
	}


    /*** 승인처리관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	@Override
	public List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) throws Exception{

		List<VcatnManageVO> result = vcatnManageDAO.selectVcatnManageConfmList(vcatnManageVO);
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	VcatnManageVO vcatnManageVO1 = result.get(i);
	    	vcatnManageVO1.setBgnde(EgovDateUtil.formatDate(vcatnManageVO1.getBgnde(), "-"));
	    	vcatnManageVO1.setEndde(EgovDateUtil.formatDate(vcatnManageVO1.getEndde(), "-"));
	    	result.set(i, vcatnManageVO1);
	    }
		return result;
	}

	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 갯수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int - 휴가관리 카운트 수
	 */
	@Override
	public int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return vcatnManageDAO.selectVcatnManageConfmListTotCnt(vcatnManageVO);
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	@Override
	public void updtVcatnManageConfm(VcatnManage vcatnManage) throws Exception {
		 InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
		 vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
		 vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
		 vcatnManage.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManage.getReqstDe()));

		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		 if("C".equals(vcatnManage.getConfmAt())){
			/*
			 * 승인처리
			 */
			 infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(vcatnManage));

			 vcatnManage.setSanctnDt(infrmlSanctn.getSanctnDt());
			 vcatnManage.setConfmAt(infrmlSanctn.getConfmAt());

			 vcatnManageDAO.updtVcatnManageConfm(vcatnManage);

		 }else if("R".equals(vcatnManage.getConfmAt())){
			/*
			 * 반려처리
			 */
			infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(vcatnManage));
			vcatnManage.setSanctnDt(infrmlSanctn.getSanctnDt());
			vcatnManage.setConfmAt(infrmlSanctn.getConfmAt());

			// 연차 반환처리
			// 개인연차조회
			VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManage.getApplcntId());
			double iUseYrycCo    = vcatnManageVO1.getUseYrycCo(); //연차테이블의 사용 연차갯수
			double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); //연차테이블의 잔여 연차갯수
			double iCountYryc = 0.0;

			/*
			 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
			 */
		    // 휴가구분이 연차인 경우
		    if("01".equals(vcatnManage.getVcatnSe())){

		       iCountYryc = getDateCalc(vcatnManage.getBgnde(), vcatnManage.getEndde());

		       IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
			   indvdlYrycManage.setUseYrycCo(iUseYrycCo-iCountYryc);
			   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
			   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
			   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
			   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());

			   updtIndvdlYrycManage(indvdlYrycManage);
		    }
		    // 휴가구분이 반차인 경우
		    else if("02".equals(vcatnManage.getVcatnSe())){

			   IndvdlYrycManage indvdlYrycManage = new IndvdlYrycManage();
			   indvdlYrycManage.setUseYrycCo(iUseYrycCo-0.5);
			   indvdlYrycManage.setRemndrYrycCo(iRemndrYrycCo + 0.5);
			   indvdlYrycManage.setLastUpdusrId(vcatnManage.getApplcntId());
			   indvdlYrycManage.setOccrrncYear(vcatnManage.getOccrrncYear());
			   indvdlYrycManage.setUsid(vcatnManage.getApplcntId());
			   updtIndvdlYrycManage(indvdlYrycManage);
		    }
		    vcatnManageDAO.updtVcatnManageConfm(vcatnManage);
		 }
	}


    /*** 연차관련 ***/
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	@Override
	public VcatnManageVO selectIndvdlYrycManage(String sUsid)  throws Exception {

		VcatnManageVO vcatnManageVO = new VcatnManageVO();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String  sYear  = Integer.toString(cal.get(java.util.Calendar.YEAR));

    	vcatnManageVO.setOccrrncYear(sYear);
    	vcatnManageVO.setUsid(sUsid);

		return vcatnManageDAO.selectIndvdlYrycManage(vcatnManageVO);
	}

	/**
	 * 개인별 연차를 수정 처리한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	@Override
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
    	vcatnManageDAO.updtIndvdlYrycManage(indvdlYrycManage);
	}



/****** 일수 계산 ******/
	/**
	 * 해당일자의 날짜사이 일수를 구한다
	 * @param  String fromDay, String toDay
	 * @return double
	 * @exception Exception
	 */
	private double getDateCalc(String fromDay, String toDay) throws Exception {

        // 시작일자
		int fromYear  = Integer.parseInt(fromDay.substring(0,4));
		int fromMonth = Integer.parseInt(fromDay.substring(4,6))-1;
		int fromDate  = Integer.parseInt(fromDay.substring(6,8));
		// 종료일자
		int toYear  = Integer.parseInt(toDay.substring(0,4));
		int toMonth = Integer.parseInt(toDay.substring(4,6))-1;
		int toDate  = Integer.parseInt(toDay.substring(6,8));

		Calendar startDay = Calendar.getInstance();
		startDay.set(fromYear,fromMonth,fromDate);

		Calendar endDay = Calendar.getInstance();
		endDay.set(toYear,toMonth,toDate);

		double Count = 0.0;

		// 시작일자 부터 종료일자까지 while
		while(!startDay.after(endDay))
		{
			// 토요일, 일요일은  일수 count에서 제외
			if(startDay.get(Calendar.DAY_OF_WEEK)!= 1 && startDay.get(Calendar.DAY_OF_WEEK)!=7)	Count++;
		    startDay.add(Calendar.DATE, 1);
		}

		return Count;
	}

	/**
	 * 휴가일자 해당연차발생연도에 속하는지 여부 체크
	 * @param  VcatnManage vcatnManage
	 * @return boolean
	 * @exception Exception
	 */
	private boolean getVcatnYearSE(VcatnManage vcatnManage) throws Exception {

		boolean bRetrunValue = false;
		java.util.Calendar cal = java.util.Calendar.getInstance();

		int iYear  = cal.get(java.util.Calendar.YEAR);
        // 시작일자
		int iYearBgnVcatn = Integer.parseInt(vcatnManage.getBgnde().substring(0,4));
        // 종료일자
		int iYearEndVcatn = Integer.parseInt(vcatnManage.getEndde().substring(0,4));
		if(iYear == iYearBgnVcatn && iYear == iYearEndVcatn) bRetrunValue = true;
		return bRetrunValue;
	}

	/**
	 * VcatnManage model을 InfrmlSanctn model로 변환한다.
	 * @param VcatnManage
	 * @return InfrmlSanctn
	 * @param vcatnManage
	 */
	private InfrmlSanctn converToInfrmlSanctnObject(VcatnManage vcatnManage) throws Exception{
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
    	infrmlSanctn.setJobSeCode("003");								// 업무구분코드 (공통코드 COM75)
    	infrmlSanctn.setApplcntId(vcatnManage.getApplcntId());			// 신청자ID
    	infrmlSanctn.setReqstDe(vcatnManage.getReqstDe());				// 신청일자
    	infrmlSanctn.setSanctnerId(vcatnManage.getSanctnerId());		// 결재자ID
    	infrmlSanctn.setConfmAt(vcatnManage.getConfmAt());				// 승인구분
    	infrmlSanctn.setSanctnDt(vcatnManage.getSanctnDt());			// 결재일시
    	infrmlSanctn.setReturnResn(vcatnManage.getReturnResn());		// 반려사유
    	infrmlSanctn.setFrstRegisterId(vcatnManage.getFrstRegisterId());
    	infrmlSanctn.setFrstRegisterPnttm(vcatnManage.getFrstRegisterId());
    	infrmlSanctn.setLastUpdusrId(vcatnManage.getLastUpdusrId());
    	infrmlSanctn.setLastUpdusrPnttm(vcatnManage.getLastUpdusrPnttm());
    	infrmlSanctn.setInfrmlSanctnId(vcatnManage.getInfrmlSanctnId());// 약식결재ID
    	return infrmlSanctn;
	}


}
