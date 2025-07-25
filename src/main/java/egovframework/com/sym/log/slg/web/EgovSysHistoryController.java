package egovframework.com.sym.log.slg.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.slg.service.EgovSysHistoryService;
import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;

/**
 * 시스템 이력관리를 위한 웹 컨트롤러 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.09
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.09  이삼섭          최초 작성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2018.09.28  정진오          updateSysHistory validation처리시 예외 수정
 *   2025.07.14  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovSysHistoryController {

	@Resource(name = "EgovSysHistoryService")
	private EgovSysHistoryService sysHistoryService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 시스템이력 등록
	 *
	 * @param history
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/InsertSysHistory.do")
	public String insertSysHistory(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("history") SysHistory history, BindingResult bindingResult, SessionStatus status,
			ModelMap model) throws Exception {

		beanValidator.validate(history, bindingResult);
		if (bindingResult.hasErrors()) {
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM002");
			List<CmmnDetailCode> resultCOM002List = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("resultList", resultCOM002List);
			return "egovframework/com/sym/log/slg/EgovSysHistRegist";
		}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (loginVO != null) {
			List<FileVO> fvoList = null;
			String atchFileId = "";
			// final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			if (!files.isEmpty()) {
				fvoList = fileUtil.parseFileInf(files, "SHF_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(fvoList);
			}
			history.setFrstRegisterId(loginVO.getUniqId());
			history.setAtchFileId(atchFileId);
			sysHistoryService.insertSysHistory(history);
		}

		status.setComplete();
		return "forward:/sym/log/slg/SelectSysHistoryList.do";
	}

	/**
	 * 시스템이력 등록 화면
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/AddSysHistory.do")
	public String addSysHistory(@ModelAttribute("searchVO") SysHistoryVO historyVO, ModelMap model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM002");
		List<CmmnDetailCode> resultCOM002List = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", resultCOM002List);
		return "egovframework/com/sym/log/slg/EgovSysHistRegist";
	}

	/**
	 * 시스템이력 수정
	 *
	 * @param history
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/UpdateSysHistory.do")
	public String updateSysHistory(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") SysHistoryVO historyVO, @ModelAttribute("history") SysHistory history,
			BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		beanValidator.validate(history, bindingResult);
		if (bindingResult.hasErrors()) {

			model.addAttribute("history", history);
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM002");
			List<CmmnDetailCode> resultCOM002List = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("resultList", resultCOM002List);
			return "egovframework/com/sym/log/slg/EgovSysHistUpdt";
		}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (loginVO != null) {
			String atchFileId = history.getAtchFileId();
			// final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
					List<FileVO> fvoList = fileUtil.parseFileInf(files, "SHF_", 0, atchFileId, "");
					atchFileId = fileMngService.insertFileInfs(fvoList);
					history.setAtchFileId(atchFileId);
				} else {
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(atchFileId);
					int fileKeyParam = fileMngService.getMaxFileSN(fvo);
					List<FileVO> fvoList = fileUtil.parseFileInf(files, "SHF_", fileKeyParam, atchFileId, "");
					fileMngService.updateFileInfs(fvoList);
				}
			}
			// model.addAttribute("history", history);
			sysHistoryService.updateSysHistory(history);
		}

		status.setComplete();

		return "forward:/sym/log/slg/SelectSysHistoryList.do";
	}

	/**
	 * 시스템이력 수정 화면
	 *
	 * @param historyVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/ModifySysHistory.do")
	public String modifySysHistory(@ModelAttribute("searchVO") SysHistoryVO historyVO, ModelMap model)
			throws Exception {

		SysHistoryVO history = sysHistoryService.selectSysHistory(historyVO);
		model.addAttribute("history", history);
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM002");
		List<CmmnDetailCode> resultCOM002List = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", resultCOM002List);
		return "egovframework/com/sym/log/slg/EgovSysHistUpdt";
	}

	/**
	 * 시스템이력 삭제
	 *
	 * @param history
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/DeleteSysHistory.do")
	public String deleteSysHistory(@ModelAttribute("history") SysHistory history, SessionStatus status, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (loginVO != null) {
			sysHistoryService.deleteSysHistory(history);
		}

		status.setComplete();
		return "forward:/sym/log/slg/SelectSysHistoryList.do";
	}

	/**
	 * 시스템이력 목록 조회
	 *
	 * @param history
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@IncludedInfo(name = "시스템이력관리", listUrl = "/sym/log/slg/SelectSysHistoryList.do", order = 1060, gid = 60)
	@RequestMapping(value = "/sym/log/slg/SelectSysHistoryList.do")
	public String selectSysHistoryList(@ModelAttribute("searchVO") SysHistoryVO historyVO, ModelMap model)
			throws Exception {

		historyVO.setPageUnit(propertyService.getInt("pageUnit"));
		historyVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(historyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(historyVO.getPageUnit());
		paginationInfo.setPageSize(historyVO.getPageSize());

		historyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		historyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		historyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		sysHistoryService.selectSysHistoryList(historyVO, model);
		int totCnt = (Integer) model.get("resultCnt");

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/log/slg/EgovSysHistList";
	}

	/**
	 * 시스템이력 상세 조회
	 *
	 * @param historyVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/log/slg/InqireSysHistory.do")
	public String selectSysHistory(@ModelAttribute("searchVO") SysHistoryVO historyVO,
			@RequestParam("histId") String histId, ModelMap model) throws Exception {

		historyVO.setHistId(histId.trim());

		SysHistoryVO vo = sysHistoryService.selectSysHistory(historyVO);
		model.addAttribute("result", vo);
		return "egovframework/com/sym/log/slg/EgovSysHistInqire";
	}

}
