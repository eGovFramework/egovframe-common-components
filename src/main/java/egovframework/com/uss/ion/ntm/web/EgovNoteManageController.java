package egovframework.com.uss.ion.ntm.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ntm.service.EgovNoteManageService;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 쪽지 관리(보내기)를 처리하는 Controller Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.08.04  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovNoteManageController {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** egovOnlinePollService */
	@Resource(name = "egovNoteManageService")
	private EgovNoteManageService egovNoteManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** 공통코드 서비스 */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** 파일첨부 관리 서비스 */
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	/** 파일첨부 Util */
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 쪽지 관리(보내기) 목록을 조회한다.
	 * 
	 * @param noteManage -쪽지관리 Model
	 * @param commandMap -Request Variable
	 * @param model      -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 */
	@IncludedInfo(name = "쪽지관리", order = 840, gid = 50)
	@RequestMapping(value = "/uss/ion/ntm/registEgovNoteManage.do")
	public String EgovNoteRecptnRegistForm(NoteManageVO noteManage, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 수신구분
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM050");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("recptnSe", listComCode);

		// 답변처리
		if (sCmd.equals("reply")) {
			model.addAttribute("cmd", sCmd);

			Map<?, ?> mapNoteManage = egovNoteManageService.selectNoteManage(noteManage);

			String noteSj = (String) mapNoteManage.get("noteSj");
			noteManage.setNoteSj("RE : " + noteSj);

			model.addAttribute("noteManage", noteManage);
			model.addAttribute("noteManageMap", mapNoteManage);
		} else {
			model.addAttribute("noteManage", new NoteManageVO());
		}

		return "egovframework/com/uss/ion/ntm/EgovNoteManage";

	}

	/**
	 * 쪽지 관리(보내기) 목록을 조회한다.(POST형식)
	 * 
	 * @param multiRequest  -Multipart Request
	 * @param commandMap    -Request Variable
	 * @param noteManage    -쪽지관리 Model
	 * @param bindingResult -Validator 하기위한 객체
	 * @param model         -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ntm/registEgovNoteManageActor.do")
	public String EgovNoteRecptnRegist(final MultipartHttpServletRequest multiRequest,
			@RequestParam Map<?, ?> commandMap, @Valid @ModelAttribute("noteManage") NoteManageVO noteManage, BindingResult bindingResult, ModelMap model)
			throws Exception {

		String sLocationUrl = "egovframework/com/uss/ion/ntm/EgovNoteManage";

		// 변수 설정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		noteManage.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		noteManage.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> fvoList = null;
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if (!files.isEmpty()) {
			fvoList = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(fvoList); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}
		noteManage.setAtchFileId(atchFileId);

		String recptnEmpList = (String) commandMap.get("recptnEmpList");
		if (recptnEmpList != null && recptnEmpList.trim().isEmpty()) {
			noteManage.setRecptnEmpList(null);
		} else if (recptnEmpList != null) {
			noteManage.setRecptnEmpList(recptnEmpList);
		}

		if (bindingResult.hasErrors()) {
			ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
			voComCode.setCodeId("COM050");
			List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
			model.addAttribute("recptnSe", listComCode);
	
			// 답변 모드일 경우 필요한 데이터 재설정
			if (sCmd.equals("reply")) {
				model.addAttribute("cmd", sCmd);
				// noteId가 noteManage에 없으면 commandMap에서 가져오기
				if (noteManage.getNoteId() == null || noteManage.getNoteId().trim().isEmpty()) {
					String noteIdFromMap = (String) commandMap.get("noteId");
					if (noteIdFromMap != null && !noteIdFromMap.trim().isEmpty()) {
						noteManage.setNoteId(noteIdFromMap);
					}
				}
				// noteId가 있으면 noteManageMap 조회
				if (noteManage.getNoteId() != null && !noteManage.getNoteId().trim().isEmpty()) {
					Map<?, ?> mapNoteManage = egovNoteManageService.selectNoteManage(noteManage);
					model.addAttribute("noteManageMap", mapNoteManage);
				}
			}
			
			model.addAttribute("noteManage", noteManage);
			return sLocationUrl;
		}

		// 쪽지등록
		egovNoteManageService.insertNoteManage(noteManage, commandMap);
		
		// 답변 모드가 아닌 경우 리스트로 리다이렉트
		if (!sCmd.equals("reply")) {
			return "redirect:/uss/ion/nts/listNoteTrnsmit.do";
		}
		
		return "redirect:/uss/ion/ntr/listNoteRecptn.do";
	}

	/**
	 * 쪽지 관리(보내기) 사용자 목록을 조회한다.
	 * 
	 * @param searchVO   -검색정보가 담긴 Model
	 * @param commandMap -Request Variable
	 * @param model      -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 */

	@RequestMapping(value = "/uss/ion/ntm/listEgovNoteEmpListPopup.do")
	public String EgovEgovNoteEmpList(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		List<EgovMap> resultList = egovNoteManageService.selectNoteEmpListPopup(searchVO);
		model.addAttribute("resultList", resultList);

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EgovMap> reusltList = egovNoteManageService.selectNoteEmpListPopup(searchVO);
		model.addAttribute("resultList", reusltList);

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		int totCnt = egovNoteManageService.selectNoteEmpListPopupCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ntm/EgovNoteEmpList";
	}

}
