package egovframework.com.utl.sys.ssy.web;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.ssy.service.EgovSynchrnServerService;
import egovframework.com.utl.sys.ssy.service.SynchrnServer;
import egovframework.com.utl.sys.ssy.service.SynchrnServerVO;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 동기화대상 서버관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 동기화대상 서버관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 동기화대상 서버관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:34
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.28   이문준           최초 생성
 *  2011.08.26   정진오           IncludedInfo annotation 추가
 *  2019.12.09   신용호           KISA 보안약점 조치 (위험한 형식 파일 업로드)
 * </pre>
 */

@Controller
public class EgovSynchrnServerController {

	@Resource(name = "egovSynchrnServerService")
	private EgovSynchrnServerService egovSynchrnServerService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "EgovCmmUseService")
	EgovCmmUseService EgovCmmUseService;

	/** ID Generation */
	@Resource(name = "egovSynchrnServerIdGnrService")
	private EgovIdGnrService egovSynchrnServerIdGnrService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	final static String uploadDir = EgovProperties.getProperty("Globals.SynchrnServerPath");
	// final static String uploadDir = "/product/jeus2/egovProps/tmp/upload/";
	// final static String uploadDir = "D:/ftp/";

	/**
	 * 동기화대상 서버관리 목록화면 이동
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/selectSynchrnServerListView.do")
	public String selectSynchrnServerListView(Model model) throws Exception {

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return "egovframework/com/utl/sys/ssy/EgovSynchrnServerList";
	}

	/**
	 * 동기화대상 서버정보를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "파일동기화(대상서버)", order = 2150, gid = 90)
	@RequestMapping(value = "/utl/sys/ssy/selectSynchrnServerList.do")
	public String selectSynchrnServerList(@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		ModelMap model) throws Exception {

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(synchrnServerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(synchrnServerVO.getPageUnit());
		paginationInfo.setPageSize(synchrnServerVO.getPageSize());

		synchrnServerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		synchrnServerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		synchrnServerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		synchrnServerVO.setSynchrnServerList(egovSynchrnServerService.selectSynchrnServerList(synchrnServerVO));

		model.addAttribute("synchrnServerList", synchrnServerVO.getSynchrnServerList());

		int totCnt = egovSynchrnServerService.selectSynchrnServerListTotCnt(synchrnServerVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("fileList", egovSynchrnServerService.getFileName(uploadDir));

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return "egovframework/com/utl/sys/ssy/EgovSynchrnServerList";
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/getSynchrnServer.do")
	public String selectSynchrnServer(@RequestParam("serverId") String serverId,
		@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		ModelMap model) throws Exception {

		synchrnServerVO.setServerId(serverId);
		synchrnServerVO = egovSynchrnServerService.selectSynchrnServer(synchrnServerVO);
		model.addAttribute("synchrnServer", synchrnServerVO);

		model.addAttribute("fileList", egovSynchrnServerService.selectSynchrnServerFiles(synchrnServerVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/utl/sys/ssy/EgovSynchrnServerDetail";
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/removeSynchrnServerFile.do")
	public String deleteSynchrnServerFile(@RequestParam("serverId") String serverId,
		@RequestParam("fileNm") String fileNm,
		@ModelAttribute("synchrnServer") SynchrnServerVO synchrnServerVO) throws Exception {

		synchrnServerVO.setServerId(serverId);
		synchrnServerVO = egovSynchrnServerService.selectSynchrnServer(synchrnServerVO);
		synchrnServerVO.setDeleteFileNm(fileNm);
		egovSynchrnServerService.deleteSynchrnServerFile(synchrnServerVO);
		return "forward:/utl/sys/ssy/getSynchrnServer.do";
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * @param serverId - 동기화대상 서버 ID
	 * @param fileNm - 다운로드 대상 파일
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/getSynchrnServerFile.do")
	public String downloadFtpFile(@RequestParam("serverId") String serverId,
		@RequestParam("fileNm") String fileNm,
		@ModelAttribute("synchrnServer") SynchrnServerVO synchrnServerVO) throws Exception {

		synchrnServerVO.setServerId(serverId);
		synchrnServerVO = egovSynchrnServerService.selectSynchrnServer(synchrnServerVO);
		synchrnServerVO.setFilePath(uploadDir);
		egovSynchrnServerService.downloadFtpFile(synchrnServerVO, fileNm);
		return "forward:/utl/sys/ssy/getSynchrnServer.do";
	}

	/**
	 * 동기화대상 서버정보 등록 화면으로 이동한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/addViewSynchrnServer.do")
	public String insertViewSynchrnServer(@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		ModelMap model) throws Exception {

		model.addAttribute("synchrnServer", synchrnServerVO);
		return "egovframework/com/utl/sys/ssy/EgovSynchrnServerRegist";
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/addSynchrnServer.do")
	public String insertSynchrnServer(@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		@ModelAttribute("synchrnServer") SynchrnServer synchrnServer,
		BindingResult bindingResult,
		ModelMap model) throws Exception {

		beanValidator.validate(synchrnServer, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
			model.addAttribute("synchrnServerVO", synchrnServerVO);
			return "egovframework/com/utl/sys/ssy/EgovSynchrnServerRegist";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); //KISA 보안취약점 조치 (2018-12-10, 이정은)

			if (!isAuthenticated) {
				return "egovframework/com/uat/uia/EgovLoginUsr";
			}
			synchrnServer.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			if (!EgovStringUtil.isNullToString(synchrnServer.getSynchrnLc()).endsWith("/")) {
				synchrnServer.setSynchrnLc(EgovStringUtil.isNullToString(synchrnServer.getSynchrnLc()).concat("/"));
			}
			synchrnServer.setReflctAt("N");
			synchrnServer.setServerId(egovSynchrnServerIdGnrService.getNextStringId());

			model.addAttribute("synchrnServer",
				egovSynchrnServerService.insertSynchrnServer(synchrnServer, synchrnServerVO));
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/utl/sys/ssy/EgovSynchrnServerDetail";
		}
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/updtViewSynchrnServer.do")
	public String updateViewSynchrnServer(@RequestParam("serverId") String serverId,
		@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		Model model) throws Exception {
		synchrnServerVO.setServerId(serverId);
		model.addAttribute("synchrnServer", egovSynchrnServerService.selectSynchrnServer(synchrnServerVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/utl/sys/ssy/EgovSynchrnServerUpdt";
	}

	/**
	 * 동기화대상 서버정보 등록 화면으로 이동한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/updtSynchrnServer.do")
	public String updateSynchrnServer(@ModelAttribute("synchrnServer") SynchrnServer synchrnServer,
		BindingResult bindingResult,
		SessionStatus status,
		ModelMap model) throws Exception {

		beanValidator.validate(synchrnServer, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
			model.addAttribute("synchrnServerVO", synchrnServer);
			return "egovframework/com/utl/sys/ssy/EgovSynchrnServerUpdt";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); //KISA 보안취약점 조치 (2018-12-10, 이정은)

			if (!isAuthenticated) {
				return "egovframework/com/uat/uia/EgovLoginUsr";
			}
			synchrnServer.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			if (!EgovStringUtil.isNullToString(synchrnServer.getSynchrnLc()).endsWith("/")) {
				synchrnServer.setSynchrnLc(EgovStringUtil.isNullToString(synchrnServer.getSynchrnLc()).concat("/"));
			}
			egovSynchrnServerService.updateSynchrnServer(synchrnServer);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/utl/sys/ssy/getSynchrnServer.do";
		}
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/removeSynchrnServer.do")
	public String deleteSynchrnServer(@RequestParam("serverId") String serverId,
		@ModelAttribute("synchrnServer") SynchrnServer synchrnServer,
		Model model) throws Exception {

		synchrnServer.setServerId(serverId);
		egovSynchrnServerService.deleteSynchrnServer(synchrnServer);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/utl/sys/ssy/selectSynchrnServerList.do";
	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/processSynchrn.do")
	public String processSynchrn(@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO,
		Model model) throws Exception {

		synchrnServerVO.setFilePath(uploadDir);
		File uploadFile = new File(uploadDir);
		File[] fileList = uploadFile.listFiles();

		synchrnServerVO.setReflctAt("N");

		if (fileList != null) {
			egovSynchrnServerService.processSynchrn(synchrnServerVO, fileList);
		}

		/*
		for(int i=0; i<fileList.length; i++) {
		    if(fileList[i].isFile()) {
		    	egovSynchrnServerService.processSynchrn(synchrnServerVO, fileList[i]);
		    }
		}
		*/

		return "forward:/utl/sys/ssy/selectSynchrnServerList.do";
	}

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/uploadFile.do")
	public String uploadFile(final MultipartHttpServletRequest multiRequest,
		@ModelAttribute("synchrnServer") SynchrnServerVO synchrnServerVO, Model model) throws Exception {

		MultipartFile multipartFile = multiRequest.getFile("file");
		if (multipartFile != null) {//2022.01 Possible null pointer dereference due to return value of called method
			String fileName = multipartFile.getOriginalFilename();
			String extension = EgovFileUploadUtil.getFileExtension(fileName);

			// 파일업로드 제한
			String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
			long maxFileSize = Long.parseLong(EgovProperties.getProperty("Globals.fileUpload.maxSize"));
			long fileSize = multipartFile.getSize();

			boolean resultFileExtention = EgovFileUploadUtil.checkFileExtension(fileName,
				whiteListFileUploadExtensions);
			boolean resultFileMaxSize = EgovFileUploadUtil.checkFileMaxSize(multipartFile, maxFileSize);

			if (resultFileExtention && resultFileMaxSize) { // true = 허용
				egovSynchrnServerService.writeFile(multipartFile, fileName, uploadDir, synchrnServerVO);
			} else {
				if (resultFileExtention == false) {
					model.addAttribute("fileUploadResultMessage", "* 허용되지 않는 확장자 입니다.[" + extension + "]");
				}
				if (resultFileMaxSize == false) {
					model.addAttribute("fileUploadResultMessage",
						"* 허용되지 않는 파일 사이즈 입니다.[" + fileName + " : " + fileSize + " bytes / " + maxFileSize + " bytes]");
				}
			}

		}

		return "forward:/utl/sys/ssy/selectSynchrnServerList.do";
	}

	/**
	 * 업로드 파일을 삭제한다.
	 * @param deleteFiles - 업로드 파일 목록
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/ssy/deleteFile.do")
	public String deleteFile(@RequestParam("deleteFiles") String deleteFiles,
		@ModelAttribute("synchrnServerVO") SynchrnServerVO synchrnServerVO) throws Exception {

		synchrnServerVO.setReflctAt("");
		egovSynchrnServerService.deleteFile(deleteFiles, uploadDir, synchrnServerVO);

		return "forward:/utl/sys/ssy/selectSynchrnServerList.do";
	}

}