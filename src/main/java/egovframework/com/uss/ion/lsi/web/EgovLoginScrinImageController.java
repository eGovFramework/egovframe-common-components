/**
 * 개요
 * - 인터넷서비스안내에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:02
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.8.3	lee.m.j          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

package egovframework.com.uss.ion.lsi.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.lsi.service.EgovLoginScrinImageService;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovLoginScrinImageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    /** Message ID Generation */
    @Resource(name="egovLoginScrinImageIdGnrService")
    private EgovIdGnrService egovLoginScrinImageIdGnrService;

    @Resource(name = "egovLoginScrinImageService")
    private EgovLoginScrinImageService egovLoginScrinImageService;

    /**
	 * 로그인화면이미지 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/lsi/selectLoginScrinImageListView.do")
    public String selectLoginScrinImageListView() throws Exception {

        return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageList";
    }

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="로그인화면이미지관리", order = 750 ,gid = 50)
    @RequestMapping(value="/uss/ion/lsi/selectLoginScrinImageList.do")
	public String selectLoginScrinImageList(@ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                                 ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(loginScrinImageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(loginScrinImageVO.getPageUnit());
		paginationInfo.setPageSize(loginScrinImageVO.getPageSize());

		loginScrinImageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		loginScrinImageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		loginScrinImageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		loginScrinImageVO.setLoginScrinImageList(egovLoginScrinImageService.selectLoginScrinImageList(loginScrinImageVO));

		model.addAttribute("loginScrinImageList", loginScrinImageVO.getLoginScrinImageList());


        int totCnt = egovLoginScrinImageService.selectLoginScrinImageListTotCnt(loginScrinImageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageList";
	}

	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/lsi/getLoginScrinImage.do")
	public String selectLoginScrinImage(@RequestParam("imageId") String imageId,
			                            @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                            ModelMap model) throws Exception {
    	loginScrinImageVO.setImageId(imageId);

    	LoginScrinImageVO resultVO = egovLoginScrinImageService.selectLoginScrinImage(loginScrinImageVO);
    	resultVO.setPageIndex(loginScrinImageVO.getPageIndex());
    	resultVO.setSearchCondition(loginScrinImageVO.getSearchCondition());
    	resultVO.setSearchKeyword(loginScrinImageVO.getSearchKeyword());
    	model.addAttribute("loginScrinImageVO", resultVO);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageUpdt";
	}

	/**
	 * 로그인화면이미지 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/lsi/addViewLoginScrinImage.do")
	public String insertViewLoginScrinImage(@ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO) throws Exception {
    	return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageRegist";
	}

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/lsi/addLoginScrinImage.do")
	public String insertLoginScrinImage(final MultipartHttpServletRequest multiRequest,
			                            @Valid @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageRegist";
		} else {

	    	List<FileVO> result = null;

	    	String uploadFolder = "";
	    	String image = "";
	    	String imageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    		// 파일이 실제로 업로드되었는지 확인 (파일명이 있는지 체크)
	    		boolean hasFile = false;
	    		for (MultipartFile file : files.values()) {
	    			if (file != null && !file.isEmpty()) {
	    				String originalFilename = file.getOriginalFilename();
	    				if (originalFilename != null && !originalFilename.trim().isEmpty()) {
	    					hasFile = true;
	    					break;
	    				}
	    			}
	    		}
	    		
	    		if (hasFile) {
		    	    result = fileUtil.parseFileInf(files, "LSI_", 0, "", uploadFolder);
		    	    atchFileId = fileMngService.insertFileInfs(result);

		        	FileVO vo = null;
		        	Iterator<FileVO> iter = result.iterator();

		        	while (iter.hasNext()) {
		        	    vo = iter.next();
		        	    image = vo.getOrignlFileNm();
		        	    imageFile = vo.getStreFileNm();
		        	}
		        	
		        	if (vo == null) {
		        		// 파일 처리 실패
		        		image = "";
		        		atchFileId = "";
		        	}
	    		} else {
	    			// 파일이 선택되지 않음
	    			image = "";
	    			atchFileId = "";
	    		}
	    	} else {
	    		// 파일이 선택되지 않음
	    		image = "";
	    		atchFileId = "";
	    	}

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	loginScrinImageVO.setImageId(egovLoginScrinImageIdGnrService.getNextStringId());
	    	loginScrinImageVO.setImage(image);
	    	loginScrinImageVO.setImageFile(atchFileId);
	    	loginScrinImageVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	model.addAttribute("loginScrinImageVO", egovLoginScrinImageService.insertLoginScrinImage(loginScrinImageVO));

	    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";

		}
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/lsi/updtLoginScrinImage.do")
	public String updateLoginScrinImage(final MultipartHttpServletRequest multiRequest,
			                            @Valid @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageUpdt";
		} else {

	    	List<FileVO> result = null;

	    	String uploadFolder = "";
	    	String image = "";
	    	String imageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    		// 파일이 실제로 업로드되었는지 확인 (파일명이 있는지 체크)
	    		boolean hasFile = false;
	    		for (MultipartFile file : files.values()) {
	    			if (file != null && !file.isEmpty()) {
	    				String originalFilename = file.getOriginalFilename();
	    				if (originalFilename != null && !originalFilename.trim().isEmpty()) {
	    					hasFile = true;
	    					break;
	    				}
	    			}
	    		}
	    		
	    		if (hasFile) {
		    	    result = fileUtil.parseFileInf(files, "LSI_", 0, "", uploadFolder);
		    	    atchFileId = fileMngService.insertFileInfs(result);

		        	FileVO vo = null;
		        	Iterator<FileVO> iter = result.iterator();

		        	while (iter.hasNext()) {
		        	    vo = iter.next();
		        	    image = vo.getOrignlFileNm();
		        	    imageFile = vo.getStreFileNm();
		        	}

		        	if (vo == null) {
		        		loginScrinImageVO.setAtchFile(false);
		        	} else {
		        		loginScrinImageVO.setImage(image);
		        		loginScrinImageVO.setImageFile(atchFileId);
		        		loginScrinImageVO.setAtchFile(true);
		        	}
	    		} else {

	    			loginScrinImageVO.setAtchFile(false);
	    		}
	    	} else {
	    		loginScrinImageVO.setAtchFile(false);
	    	}

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	loginScrinImageVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

	    	egovLoginScrinImageService.updateLoginScrinImage(loginScrinImageVO);

	    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";

		}
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/lsi/removeLoginScrinImage.do")
	public String deleteLoginScrinImage(@RequestParam("imageId") String imageId,
			                            @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	loginScrinImageVO.setImageId(imageId);
    	egovLoginScrinImageService.deleteLoginScrinImage(loginScrinImageVO);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";
	}


	/**
	 * 기 등록된 로그인화면이미지정보 목록을 일괄 삭제한다.
	 * @param imageIds String
	 * @param loginScrinImageVO LoginScrinImageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uss/ion/lsi/removeLoginScrinImageList.do")
	public String deleteLoginScrinImageList(@RequestParam("imageIds") String imageIds,
			                                @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                                 SessionStatus status,
			                                 ModelMap model) throws Exception {
    	// 2026.03.23 kisa 보안점검 대응 조치
    	if (imageIds != null) {
	    	String [] strImageIds = imageIds.split(";");
	
	    	for (String strImageId : strImageIds) {
	    		loginScrinImageVO.setImageId(strImageId);
	    		egovLoginScrinImageService.deleteLoginScrinImage(loginScrinImageVO);
	    	}
    	}
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";
	}

	/**
	 * 기 등록된 로그인화면이미지정보의 이미지파일을 삭제한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
	public String deleteLoginScrinImageFile(LoginScrinImageVO loginScrinImageVO){
		return "";
	}

}
