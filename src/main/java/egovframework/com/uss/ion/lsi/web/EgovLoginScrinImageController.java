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

import javax.annotation.Resource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.lsi.service.EgovLoginScrinImageService;
import egovframework.com.uss.ion.lsi.service.LoginScrinImage;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;


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

    @Autowired
	private DefaultBeanValidator beanValidator;

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

    	model.addAttribute("loginScrinImage", egovLoginScrinImageService.selectLoginScrinImage(loginScrinImageVO));
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
	 * @param loginScrinImage - 로그인화면이미지 model
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/lsi/addLoginScrinImage.do")
	public String insertLoginScrinImage(final MultipartHttpServletRequest multiRequest,
			                            @ModelAttribute("loginScrinImage") LoginScrinImage loginScrinImage,
			                            @ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(loginScrinImage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("loginScrinImageVO", loginScrinImageVO);
			return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageRegist";
		} else {

	    	List<FileVO> result = null;

	    	String uploadFolder = "";
	    	String image = "";
	    	String imageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    	    result = fileUtil.parseFileInf(files, "LSI_", 0, "", uploadFolder);
	    	    atchFileId = fileMngService.insertFileInfs(result);

	        	FileVO vo = result.get(0);
	        	Iterator<FileVO> iter = result.iterator();

	        	while (iter.hasNext()) {
	        	    vo = iter.next();
	        	    image = vo.getOrignlFileNm();
	        	    imageFile = vo.getStreFileNm();
	        	}
	    	}

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	loginScrinImage.setImageId(egovLoginScrinImageIdGnrService.getNextStringId());
	    	loginScrinImage.setImage(image);
	    	loginScrinImage.setImageFile(atchFileId);
	    	loginScrinImage.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	    	loginScrinImageVO.setImageId(loginScrinImage.getImageId());

	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	model.addAttribute("loginScrinImage", egovLoginScrinImageService.insertLoginScrinImage(loginScrinImage, loginScrinImageVO));

//	    	return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageUpdt";
	    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";

		}
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/lsi/updtLoginScrinImage.do")
	public String updateLoginScrinImage(final MultipartHttpServletRequest multiRequest,
			                            @ModelAttribute("loginScrinImage") LoginScrinImage loginScrinImage,
			                            BindingResult bindingResult,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {

		beanValidator.validate(loginScrinImage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("loginScrinImageVO", loginScrinImage);
			return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageUpdt";
		} else {

	    	List<FileVO> result = null;

	    	String uploadFolder = "";
	    	String image = "";
	    	String imageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
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
	        		loginScrinImage.setAtchFile(false);
	        	} else {
	        		loginScrinImage.setImage(image);
	        		loginScrinImage.setImageFile(atchFileId);
	        		loginScrinImage.setAtchFile(true);
	        	}
	    	} else {
	    		loginScrinImage.setAtchFile(false);
	    	}

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	loginScrinImage.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

	    	egovLoginScrinImageService.updateLoginScrinImage(loginScrinImage);
//	    	return "forward:/uss/ion/lsi/getLoginScrinImage.do";
	    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";

		}
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/lsi/removeLoginScrinImage.do")
	public String deleteLoginScrinImage(@RequestParam("imageId") String imageId,
			                            @ModelAttribute("loginScrinImage") LoginScrinImage loginScrinImage,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	loginScrinImage.setImageId(imageId);
    	egovLoginScrinImageService.deleteLoginScrinImage(loginScrinImage);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";
	}


	/**
	 * 기 등록된 로그인화면이미지정보 목록을 일괄 삭제한다.
	 * @param loginScrinImageIds String
	 * @param loginScrinImage LoginScrinImage
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uss/ion/lsi/removeLoginScrinImageList.do")
	public String deleteLoginScrinImageList(@RequestParam("imageIds") String imageIds,
			                                @ModelAttribute("loginScrinImage") LoginScrinImage loginScrinImage,
			                                 SessionStatus status,
			                                 ModelMap model) throws Exception {

    	String [] strImageIds = imageIds.split(";");

    	for(int i=0; i<strImageIds.length;i++) {
    		loginScrinImage.setImageId(strImageIds[i]);
    		egovLoginScrinImageService.deleteLoginScrinImage(loginScrinImage);
    	}

    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/lsi/selectLoginScrinImageList.do";
	}

	/**
	 * 기 등록된 로그인화면이미지정보의 이미지파일을 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 * @return String - 리턴 Url
	 */
	public String deleteLoginScrinImageFile(LoginScrinImage loginScrinImage){
		return "";
	}

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/lsi/getLoginScrinImageResult.do")
	public String selectLoginScrinImageResult(@ModelAttribute("loginScrinImageVO") LoginScrinImageVO loginScrinImageVO,
			                                   ModelMap model) throws Exception {

		List<LoginScrinImageVO> fileList = egovLoginScrinImageService.selectLoginScrinImageResult(loginScrinImageVO);
		model.addAttribute("fileList", fileList);

		return "egovframework/com/uss/ion/lsi/EgovLoginScrinImageView";
	}
}
