package egovframework.com.sym.bat.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.bat.service.BatchSchdul;
import egovframework.com.sym.bat.service.BatchScheduler;
import egovframework.com.sym.bat.service.EgovBatchSchdulService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 배치스케줄관리에 대한 controller 클래스
 *
 * @author 김진만
 * @since 2010.06.17
 * @version 1.0
 * @updated 17-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   김진만     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovBatchSchdulController {

	/** egovBatchSchdulService */
	@Resource(name = "egovBatchSchdulService")
	private EgovBatchSchdulService egovBatchSchdulService;

	/* Property 서비스 */
	@Resource(name = "propertiesService")
	private EgovPropertyService propertyService;

	/* 메세지 서비스 */
	@Resource(name = "egovMessageSource")
	private EgovMessageSource egovMessageSource;

	/* common  validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** ID Generation */
	@Resource(name = "egovBatchSchdulIdGnrService")
	private EgovIdGnrService idgenService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** 배치스케줄러 서비스 */
	@Resource(name = "batchScheduler")
	private BatchScheduler batchScheduler;

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchSchdulController.class);

	/**
	 * 배치스케줄을 삭제한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 삭제대상 배치스케줄model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/deleteBatchSchdul.do")
	public String deleteBatchSchdul(BatchSchdul batchSchdul, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 배치스케줄러에 스케줄정보반영
		batchScheduler.deleteBatchSchdul(batchSchdul);

		egovBatchSchdulService.deleteBatchSchdul(batchSchdul);

		return "forward:/sym/bat/getBatchSchdulList.do";
	}

	/**
	 * 배치스케줄을 등록한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 등록대상 배치스케줄model
	 * @param bindingResult	BindingResult
	 * @param model			ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/addBatchSchdul.do")
	public String insertBatchSchdul(BatchSchdul batchSchdul, BindingResult bindingResult, ModelMap model) throws Exception {
		LOGGER.debug(" 인서트 대상정보 : {}", batchSchdul);

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(batchSchdul, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			return "egovframework/com/sym/bat/EgovBatchSchdulRegist";
		} else {
			batchSchdul.setBatchSchdulId(idgenService.getNextStringId());
			//아이디 설정
			batchSchdul.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			batchSchdul.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			egovBatchSchdulService.insertBatchSchdul(batchSchdul);

			// 배치스케줄러에 스케줄정보반영
			BatchSchdul target = egovBatchSchdulService.selectBatchSchdul(batchSchdul);
			batchScheduler.insertBatchSchdul(target);

			//Exception 없이 진행시 등록성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/sym/bat/getBatchSchdulList.do";
	}

	/**
	 * 배치스케줄정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 조회대상 배치스케줄model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/getBatchSchdul.do")
	public String selectBatchSchdul(@ModelAttribute("searchVO") BatchSchdul batchSchdul, ModelMap model) throws Exception {
		LOGGER.debug(" 조회조건 : {}", batchSchdul);
		BatchSchdul result = egovBatchSchdulService.selectBatchSchdul(batchSchdul);
		model.addAttribute("resultInfo", result);
		LOGGER.debug(" 결과값 : {}", result);

		return "egovframework/com/sym/bat/EgovBatchSchdulDetail";
	}

	/**
	 * 등록화면을 위한 배치스케줄정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 조회대상 배치스케줄model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/getBatchSchdulForRegist.do")
	public String selectBatchSchdulForRegist(@ModelAttribute("searchVO") BatchSchdul batchSchdul, ModelMap model) throws Exception {
		referenceData(model);

		model.addAttribute("batchSchdul", batchSchdul);

		return "egovframework/com/sym/bat/EgovBatchSchdulRegist";
	}

	/**
	 * 수정화면을 위한 배치스케줄정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 조회대상 배치스케줄model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/getBatchSchdulForUpdate.do")
	public String selectBatchSchdulForUpdate(@ModelAttribute("searchVO") BatchSchdul batchSchdul, ModelMap model) throws Exception {
		referenceData(model);

		LOGGER.debug(" 조회조건 : {}", batchSchdul);
		BatchSchdul result = egovBatchSchdulService.selectBatchSchdul(batchSchdul);
		model.addAttribute("batchSchdul", result);
		LOGGER.debug(" 결과값 : {}", result);

		return "egovframework/com/sym/bat/EgovBatchSchdulUpdt";
	}

	/**
	 * Reference Data 를 설정한다.
	 * @param model   화면용spring Model객체
	 * @throws Exception
	 */
	private void referenceData(ModelMap model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		//DBMS종류코드목록을 코드정보로부터 조회
		vo.setCodeId("COM047");
		List<CmmnDetailCode> executCycleList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("executCycleList", executCycleList);
		//요일구분코드목록을 코드정보로부터 조회
		vo.setCodeId("COM074");
		List<CmmnDetailCode> executSchdulDfkSeList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("executSchdulDfkSeList", executSchdulDfkSeList);

		// 실행스케줄 시, 분, 초 값 설정.
		Map<String, String> executSchdulHourList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				executSchdulHourList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulHourList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulHourList", executSchdulHourList);
		Map<String, String> executSchdulMntList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				executSchdulMntList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulMntList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulMntList", executSchdulMntList);
		Map<String, String> executSchdulSecndList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				executSchdulSecndList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulSecndList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulSecndList", executSchdulSecndList);
	}

	/**
	 * 배치스케줄 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name = "스케줄처리", listUrl = "/sym/bat/getBatchSchdulList.do", order = 1140, gid = 60)
	@RequestMapping("/sym/bat/getBatchSchdulList.do")
	public String selectBatchSchdulList(@ModelAttribute("searchVO") BatchSchdul searchVO, ModelMap model) throws Exception {
		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<BatchSchdul> resultList = (List<BatchSchdul>) egovBatchSchdulService.selectBatchSchdulList(searchVO);
		int totCnt = egovBatchSchdulService.selectBatchSchdulListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/bat/EgovBatchSchdulList";
	}

	/**
	 * 배치스케줄을 수정한다.
	 * @return 리턴URL
	 *
	 * @param batchSchdul 수정대상 배치스케줄model
	 * @param bindingResult		BindingResult
	 * @param model				ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/bat/updateBatchSchdul.do")
	public String updateBatchSchdul(BatchSchdul batchSchdul, BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(batchSchdul, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			model.addAttribute("batchSchdul", batchSchdul);
			return "egovframework/com/sym/bat/EgovBatchSchdulUpdt";
		}

		// 정보 업데이트
		batchSchdul.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		egovBatchSchdulService.updateBatchSchdul(batchSchdul);

		// 배치스케줄러에 스케줄정보반영
		BatchSchdul target = egovBatchSchdulService.selectBatchSchdul(batchSchdul);
		batchScheduler.updateBatchSchdul(target);

		return "forward:/sym/bat/getBatchSchdulList.do";

	}

}