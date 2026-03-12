package egovframework.com.uss.ion.vct.service.impl;

import java.util.Calendar;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.vct.service.EgovVcatnManageService;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;

/**
 * <pre>
 * 개요
 * - 휴가관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 이용
 * @since 2010.06.15
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용           최초 생성
 *   2025.08.18  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("egovVcatnManageService")
public class EgovVcatnManageServiceImpl extends EgovAbstractServiceImpl implements EgovVcatnManageService {

	@Resource(name = "vcatnManageDAO")
	private VcatnManageDAO vcatnManageDAO;

	@Resource(name = "EgovInfrmlSanctnService")
	protected EgovInfrmlSanctnService infrmlSanctnService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovVcatnManageServiceImpl.class);

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	@Override
	public List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO) throws Exception {

		List<VcatnManageVO> result = vcatnManageDAO.selectVcatnManageList(vcatnManageVO);
		int num = result.size();

		for (int i = 0; i < num; i++) {
			VcatnManageVO vcatnManageVO1 = result.get(i);
			vcatnManageVO1.setBgnde(EgovDateUtil.formatDate(vcatnManageVO1.getBgnde(), "-"));
			vcatnManageVO1.setEndde(EgovDateUtil.formatDate(vcatnManageVO1.getEndde(), "-"));
			result.set(i, vcatnManageVO1);
		}
		return result;
	}

	/**
	 * 휴가관리목록 총 개수를 조회한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int - 휴가관리 카운트 수
	 */
	@Override
	public int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return vcatnManageDAO.selectVcatnManageListTotCnt(vcatnManageVO);
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	@Override
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {

		// VcatnManageVO vcatnManageVOTemp = new VcatnManageVO();

		VcatnManageVO vcatnManageVOTemp = vcatnManageDAO.selectVcatnManage(vcatnManageVO);
		if (vcatnManageVOTemp == null) {
			return null;
		}

		vcatnManageVOTemp.setBgnde(EgovDateUtil.formatDate(vcatnManageVOTemp.getBgnde(), "-"));
		vcatnManageVOTemp.setEndde(EgovDateUtil.formatDate(vcatnManageVOTemp.getEndde(), "-"));

		// 연차정보
		VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
		if (vcatnManageVO1 != null) {
			vcatnManageVOTemp.setOccrrncYear(vcatnManageVO1.getOccrrncYear());
			vcatnManageVOTemp.setUsid(vcatnManageVO1.getUsid());
			vcatnManageVOTemp.setOccrncYrycCo(vcatnManageVO1.getOccrncYrycCo());
			vcatnManageVOTemp.setUseYrycCo(vcatnManageVO1.getUseYrycCo());
			vcatnManageVOTemp.setRemndrYrycCo(vcatnManageVO1.getRemndrYrycCo());
		}

		return vcatnManageVOTemp;
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return String 01 : 입력성공, 02 : 연차휴가 등록실패(잔여연차 부족), 03: 반차휴가 등록실패(잔여연차 부족)
	 */
	@Override
	public String insertVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String sYear = Integer.toString(cal.get(java.util.Calendar.YEAR));
		String sMonth = Integer.toString(cal.get(java.util.Calendar.MONTH) + 1);
		if (sMonth.length() == 1) {
			sMonth = "0" + sMonth;
		}
		String sDay = Integer.toString(cal.get(java.util.Calendar.DATE));

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
		String uniqId = "";
		if (user != null) {
			uniqId = user.getUniqId();
		}
		vcatnManageVO.setOccrrncYear(sYear);
		vcatnManageVO.setReqstDe(sYear + sMonth + sDay);
		/*
		 * 휴가 승인처리 신청 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManageVO);
		 */
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
		vcatnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManageVO.getReqstDe()));
		InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(vcatnManageVO));
		// InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("003",
		// vcatnManageVO);
		vcatnManageVO.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(uniqId);
		double iUseYrycCo = vcatnManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차개수
		double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차개수
		double iCountYryc = 0.0;

		/*
		 * 시작일자 와 종료일자 사이의 일자 개수 - 공휴일 or 주말 제외
		 */
		// 휴가구분이 연차인 경우
		if ("01".equals(vcatnManageVO.getVcatnSe())) {
			// 연차 휴가 연도 체크
			if (!getVcatnYearSE(vcatnManageVO)) {
				return "09";
			}
			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());
			if (iCountYryc == 0) {
				return "99"; // 연차설정오류
			} else if ((iRemndrYrycCo - iCountYryc) < 0) {
				return "02";
			} else {
				vcatnManageDAO.insertVcatnManage(vcatnManageVO);
				VcatnManageVO indvdlYrycVO = new VcatnManageVO();
				indvdlYrycVO.setUseYrycCo(iUseYrycCo + iCountYryc);
				indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo - iCountYryc);
				indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());
				updtIndvdlYrycManage(indvdlYrycVO);
				return "01";
			}
		}
		// 휴가구분이 반차인 경우
		else if ("02".equals(vcatnManageVO.getVcatnSe())) {

			// 연차 휴가 연도 체크
			if (!getVcatnYearSE(vcatnManageVO)) {
				return "09";
			}
			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getBgnde()); // 반차는 시작일자 종료일자 동일함. 시작일자로만 체크
			if (iCountYryc == 0) {
				return "99"; // 연차설정오류
			} else if ((iRemndrYrycCo - 0.5) < 0) {
				return "03";
			} else {
				vcatnManageDAO.insertVcatnManage(vcatnManageVO);
				VcatnManageVO indvdlYrycVO = new VcatnManageVO();
				indvdlYrycVO.setUseYrycCo(iUseYrycCo + 0.5);
				indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo - 0.5);
				indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());
				updtIndvdlYrycManage(indvdlYrycVO);

				return "01";
			}
		} else {
			vcatnManageDAO.insertVcatnManage(vcatnManageVO);
			return "01";
		}
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	@Override
	public String updtVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		int iTemp = 0;
		String sTempMessage = null;
		String sTempApplcntId = vcatnManageVO.getApplcntId();
		String sTempVcatnSe = vcatnManageVO.getVcatnSe();
		String sTempBgnde = vcatnManageVO.getBgnde();
		String sTempEndde = vcatnManageVO.getEndde();

		/* 삭제처리 */
		VcatnManageVO deleteVO = new VcatnManageVO();
		deleteVO.setApplcntId(vcatnManageVO.getApplcntIdKey());
		deleteVO.setVcatnSe(vcatnManageVO.getVcatnSeKey());
		deleteVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgndeKey()));
		deleteVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEnddeKey()));

		deleteVcatnManage(deleteVO);
		/* 등록처리 */
		vcatnManageVO.setApplcntId(sTempApplcntId);
		vcatnManageVO.setVcatnSe(sTempVcatnSe);
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(sTempBgnde));
		String enddeNormalized = EgovStringUtil.removeMinusChar(sTempEndde);
		// 반차(02)는 종료일자=시작일자. endde가 비거나 짧으면 bgnde로 통일
		if ("02".equals(vcatnManageVO.getVcatnSe()) && (enddeNormalized == null || enddeNormalized.length() < 8)) {
			enddeNormalized = vcatnManageVO.getBgnde();
		}
		vcatnManageVO.setEndde(enddeNormalized);
		if (vcatnManageVO.getSanctnerId() != null) {
			vcatnManageVO.setConfmAt("A");
		}

		vcatnManageVO.setSearchKeyword(vcatnManageVO.getBgnde());
		// 시작일자 포함여부
		iTemp = selectVcatnManageDplctAt(vcatnManageVO);
		vcatnManageVO.setSearchKeyword(vcatnManageVO.getEndde());
		// 종료일자 포함여부
		iTemp += selectVcatnManageDplctAt(vcatnManageVO);

		if (iTemp == 0) {
			sTempMessage = insertVcatnManage(vcatnManageVO);
			LOGGER.info("updtVcatnManage 4:" + sTempMessage);
			return sTempMessage;
		} else {
			sTempMessage = "10";
			LOGGER.info("updtVcatnManage 5:" + sTempMessage);
			return sTempMessage;
		}
		/*
		 * vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
		 * vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
		 * vcatnManage.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManage.getReqstDe(
		 * ))); vcatnManageDAO.updtVcatnManage(vcatnManage);
		 * 
		 * return "01";
		 */
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	@Override
	@SuppressWarnings("unused")
	public void deleteVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		/*
		 * 휴가 승인처리 삭제 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManageVO);
		 */
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
		vcatnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManageVO.getReqstDe()));
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(vcatnManageVO));

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 개인연차조회
		VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
		double iUseYrycCo = vcatnManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차개수
		double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차개수
		double iCountYryc = 0.0;
		/*
		 * 시작일자 와 종료일자 사이의 일자 개수 - 공휴일 or 주말 제외
		 */
		// 휴가구분이 연차인 경우
		if ("01".equals(vcatnManageVO.getVcatnSe())) {

			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());
			VcatnManageVO indvdlYrycVO = new VcatnManageVO();
			indvdlYrycVO.setUseYrycCo(iUseYrycCo - iCountYryc);
			indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
			indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
			indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
			indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());

			updtIndvdlYrycManage(indvdlYrycVO);
			vcatnManageDAO.deleteVcatnManage(vcatnManageVO);

		}
		// 휴가구분이 반차인 경우
		else if ("02".equals(vcatnManageVO.getVcatnSe())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)

			VcatnManageVO indvdlYrycVO = new VcatnManageVO();
			indvdlYrycVO.setUseYrycCo(iUseYrycCo - 0.5);
			indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo + 0.5);
			indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
			indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
			indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());
			updtIndvdlYrycManage(indvdlYrycVO);
			vcatnManageDAO.deleteVcatnManage(vcatnManageVO);

		} else {
			vcatnManageDAO.deleteVcatnManage(vcatnManageVO);
		}
	}

	/**
	 * 휴가일자 중복여부 체크
	 * 
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
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	@Override
	public List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) throws Exception {

		List<VcatnManageVO> result = vcatnManageDAO.selectVcatnManageConfmList(vcatnManageVO);
		int num = result.size();

		for (int i = 0; i < num; i++) {
			VcatnManageVO vcatnManageVO1 = result.get(i);
			vcatnManageVO1.setBgnde(EgovDateUtil.formatDate(vcatnManageVO1.getBgnde(), "-"));
			vcatnManageVO1.setEndde(EgovDateUtil.formatDate(vcatnManageVO1.getEndde(), "-"));
			result.set(i, vcatnManageVO1);
		}
		return result;
	}

	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 개수를 조회한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int - 휴가관리 카운트 수
	 */
	@Override
	public int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return vcatnManageDAO.selectVcatnManageConfmListTotCnt(vcatnManageVO);
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	@Override
	public void updtVcatnManageConfm(VcatnManageVO vcatnManageVO) throws Exception {
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
		vcatnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(vcatnManageVO.getReqstDe()));

		// KISA 보안약점 조치 (2018-10-29, 윤창원)
		if ("C".equals(vcatnManageVO.getConfmAt())) {
			/*
			 * 승인처리
			 */
			infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(vcatnManageVO));

			vcatnManageVO.setSanctnDt(infrmlSanctn.getSanctnDt());
			vcatnManageVO.setConfmAt(infrmlSanctn.getConfmAt());

			vcatnManageDAO.updtVcatnManageConfm(vcatnManageVO);

		} else if ("R".equals(vcatnManageVO.getConfmAt())) {
			/*
			 * 반려처리
			 */
			infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(vcatnManageVO));
			vcatnManageVO.setSanctnDt(infrmlSanctn.getSanctnDt());
			vcatnManageVO.setConfmAt(infrmlSanctn.getConfmAt());

			// 연차 반환처리
			// 개인연차조회
			VcatnManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
			double iUseYrycCo = vcatnManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차개수
			double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차개수
			double iCountYryc = 0.0;

			/*
			 * 시작일자 와 종료일자 사이의 일자 개수 - 공휴일 or 주말 제외
			 */
			// 휴가구분이 연차인 경우
			if ("01".equals(vcatnManageVO.getVcatnSe())) {

				iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());

				VcatnManageVO indvdlYrycVO = new VcatnManageVO();
				indvdlYrycVO.setUseYrycCo(iUseYrycCo - iCountYryc);
				indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
				indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());

				updtIndvdlYrycManage(indvdlYrycVO);
			}
			// 휴가구분이 반차인 경우
			else if ("02".equals(vcatnManageVO.getVcatnSe())) {

				VcatnManageVO indvdlYrycVO = new VcatnManageVO();
				indvdlYrycVO.setUseYrycCo(iUseYrycCo - 0.5);
				indvdlYrycVO.setRemndrYrycCo(iRemndrYrycCo + 0.5);
				indvdlYrycVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycVO.setUsid(vcatnManageVO.getApplcntId());
				updtIndvdlYrycManage(indvdlYrycVO);
			}
			vcatnManageDAO.updtVcatnManageConfm(vcatnManageVO);
		}
	}

	/*** 연차관련 ***/
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	@Override
	public VcatnManageVO selectIndvdlYrycManage(String sUsid) throws Exception {

		VcatnManageVO vcatnManageVO = new VcatnManageVO();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String sYear = Integer.toString(cal.get(java.util.Calendar.YEAR));

		vcatnManageVO.setOccrrncYear(sYear);
		vcatnManageVO.setUsid(sUsid);

		return vcatnManageDAO.selectIndvdlYrycManage(vcatnManageVO);
	}

	/**
	 * 개인별 연차를 수정 처리한다.
	 * 
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	@Override
	public void updtIndvdlYrycManage(VcatnManageVO vcatnManageVO) throws Exception {
		vcatnManageDAO.updtIndvdlYrycManage(vcatnManageVO);
	}

	/****** 일수 계산 ******/
	/**
	 * 해당일자의 날짜사이 일수를 구한다
	 * 
	 * @param String fromDay, String toDay
	 * @return double
	 * @exception Exception
	 */
	private double getDateCalc(String fromDay, String toDay) throws Exception {

		// 시작일자
		int fromYear = Integer.parseInt(fromDay.substring(0, 4));
		int fromMonth = Integer.parseInt(fromDay.substring(4, 6)) - 1;
		int fromDate = Integer.parseInt(fromDay.substring(6, 8));
		// 종료일자
		int toYear = Integer.parseInt(toDay.substring(0, 4));
		int toMonth = Integer.parseInt(toDay.substring(4, 6)) - 1;
		int toDate = Integer.parseInt(toDay.substring(6, 8));

		Calendar startDay = Calendar.getInstance();
		startDay.set(fromYear, fromMonth, fromDate);

		Calendar endDay = Calendar.getInstance();
		endDay.set(toYear, toMonth, toDate);

		double count = 0.0;

		// 시작일자 부터 종료일자까지 while
		while (!startDay.after(endDay)) {
			// 토요일, 일요일은 일수 count에서 제외
			if (startDay.get(Calendar.DAY_OF_WEEK) != 1 && startDay.get(Calendar.DAY_OF_WEEK) != 7) {
				count++;
			}
			startDay.add(Calendar.DATE, 1);
		}

		return count;
	}

	/**
	 * 휴가일자 해당연차발생연도에 속하는지 여부 체크
	 * 
	 * @param VcatnManageVO vcatnManageVO
	 * @return boolean
	 * @exception Exception
	 */
	private boolean getVcatnYearSE(VcatnManageVO vcatnManageVO) throws Exception {

		boolean bRetrunValue = false;
		String bgnde = vcatnManageVO.getBgnde();
		String endde = vcatnManageVO.getEndde();
		// 시작일자 4자리(연도) 이상 필요
		if (bgnde == null || bgnde.length() < 4) {
			return false;
		}
		// 종료일자: 반차(02)는 시작일자와 동일하므로 endde가 비거나 짧으면 bgnde 사용
		if (endde == null || endde.length() < 4) {
			if ("02".equals(vcatnManageVO.getVcatnSe())) {
				endde = bgnde;
			} else {
				return false;
			}
		}

		java.util.Calendar cal = java.util.Calendar.getInstance();
		int iYear = cal.get(java.util.Calendar.YEAR);
		int iYearBgnVcatn = Integer.parseInt(bgnde.substring(0, 4));
		int iYearEndVcatn = Integer.parseInt(endde.substring(0, 4));
		if (iYear == iYearBgnVcatn && iYear == iYearEndVcatn) {
			bRetrunValue = true;
		}
		return bRetrunValue;
	}

	/**
	 * VcatnManageVO를 InfrmlSanctn model로 변환한다.
	 * 
	 * @param VcatnManageVO
	 * @return InfrmlSanctn
	 * @param vcatnManageVO
	 */
	private InfrmlSanctn converToInfrmlSanctnObject(VcatnManageVO vcatnManageVO) throws Exception {
		InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
		infrmlSanctn.setJobSeCode("003"); // 업무구분코드 (공통코드 COM75)
		infrmlSanctn.setApplcntId(vcatnManageVO.getApplcntId()); // 신청자ID
		infrmlSanctn.setReqstDe(vcatnManageVO.getReqstDe()); // 신청일자
		infrmlSanctn.setSanctnerId(vcatnManageVO.getSanctnerId()); // 결재자ID
		infrmlSanctn.setConfmAt(vcatnManageVO.getConfmAt()); // 승인구분
		infrmlSanctn.setSanctnDt(vcatnManageVO.getSanctnDt()); // 결재일시
		infrmlSanctn.setReturnResn(vcatnManageVO.getReturnResn()); // 반려사유
		infrmlSanctn.setFrstRegisterId(vcatnManageVO.getFrstRegisterId());
		infrmlSanctn.setFrstRegisterPnttm(vcatnManageVO.getFrstRegisterId());
		infrmlSanctn.setLastUpdusrId(vcatnManageVO.getLastUpdusrId());
		infrmlSanctn.setLastUpdusrPnttm(vcatnManageVO.getLastUpdusrPnttm());
		infrmlSanctn.setInfrmlSanctnId(vcatnManageVO.getInfrmlSanctnId());// 약식결재ID
		return infrmlSanctn;
	}

}
