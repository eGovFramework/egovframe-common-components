package egovframework.com.sym.ccm.zip.web;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.sym.ccm.zip.service.EgovCcmRdnmadZipManageService;
import egovframework.com.sym.ccm.zip.service.EgovCcmZipManageService;
import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 우편번호에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.04.01   이중호            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2011.10.07   이기하            보안취약점 수정(파일 업로드시 엑셀파일만 가능하도록 추가)
 *  2011.11.21   이기하            도로명주소 추가(rdnmadZip)
 *  2021.02.16   신용호            WebUtils.getNativeRequest(request,MultipartHttpServletRequest.class);
 *
 * </pre>
 */

@Controller
public class EgovCcmZipManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCcmZipManageController.class);

	@Resource(name = "ZipManageService")
	private EgovCcmZipManageService zipManageService;

	@Resource(name = "RdnmadZipService")
	private EgovCcmRdnmadZipManageService rdnmadZipService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 우편번호 찾기 팝업 메인창을 호출한다.
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipSearchPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipSearchPopup.do")
	public String callNormalCalPopup(ModelMap model) throws Exception {
		return "egovframework/com/sym/ccm/zip/EgovCcmZipSearchPopup";
	}

	/**
	 * 우편번호 찾기 목록을 조회한다.
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipSearchList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipSearchList.do")
	public String selectZipSearchList(@ModelAttribute("searchVO") ZipVO searchVO, ModelMap model) throws Exception {
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

		String sList = "";

		if (searchVO.getSearchList() != null && searchVO.getSearchList() != "") {
			sList = searchVO.getSearchList().substring(0, 1);
		}
		model.addAttribute("searchList", sList);

		if (!sList.equals("2")) {
			List<?> CmmnCodeList = zipManageService.selectZipList(searchVO);
			model.addAttribute("resultList", CmmnCodeList);

			int totCnt = zipManageService.selectZipListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
		} else {
			List<?> CmmnCodeList = rdnmadZipService.selectZipList(searchVO);
			model.addAttribute("resultList", CmmnCodeList);

			int totCnt = rdnmadZipService.selectZipListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
		}

		return "egovframework/com/sym/ccm/zip/EgovCcmZipSearchList";
	}

	/**
	 * 우편번호를 삭제한다.
	 * @param loginVO
	 * @param zip
	 * @param model
	 * @return "forward:/sym/ccm/zip/EgovCcmZipList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipRemove.do")
	public String deleteZip(@ModelAttribute("loginVO") LoginVO loginVO, Zip zip, ZipVO searchVO, ModelMap model)
		throws Exception {
		model.addAttribute("searchList", searchVO.getSearchList());
		if (searchVO.getSearchList().equals("1")) {
			zipManageService.deleteZip(zip);
		} else {
			rdnmadZipService.deleteZip(zip);
		}
		return "forward:/sym/ccm/zip/EgovCcmZipList.do";
	}

	/**
	 * 우편번호를 등록한다.
	 * @param loginVO
	 * @param zip
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipRegist.do")
	public String insertZip(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("zip") Zip zip, ZipVO searchVO,
		BindingResult bindingResult, ModelMap model)
		throws Exception {

		model.addAttribute("searchList", searchVO.getSearchList());

		if (zip.getZip() == null || zip.getZip().equals("")) {

			return "egovframework/com/sym/ccm/zip/EgovCcmZipRegist";
		}

		if (searchVO.getSearchList().equals("1")) {
			beanValidator.validate(zip, bindingResult);
			if (bindingResult.hasErrors()) {
				return "egovframework/com/sym/ccm/zip/EgovCcmZipRegist";
			}

			zip.setFrstRegisterId(loginVO.getUniqId());
			zipManageService.insertZip(zip);
		} else {
			/*beanValidator.validate(zip, bindingResult);
			if (bindingResult.hasErrors()){
				return "egovframework/com/sym/ccm/zip/EgovCcmZipRegist";
			}*/

			zip.setFrstRegisterId(loginVO.getUniqId());
			rdnmadZipService.insertZip(zip);
		}
		return "forward:/sym/ccm/zip/EgovCcmZipList.do";
	}

	/**
	 * 엑셀파일을 업로드하여 우편번호를 등록한다.
	 * @param loginVO
	 * @param request
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmExcelZipRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmExcelZipRegist.do")
	public String insertExcelZip(@ModelAttribute("loginVO") LoginVO loginVO, final HttpServletRequest request,
		@RequestParam Map<String, Object> commandMap, ZipVO searchVO, Model model)
		throws Exception {
		model.addAttribute("searchList", searchVO.getSearchList());

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if (sCmd.equals("")) {
			return "egovframework/com/sym/ccm/zip/EgovCcmExcelZipRegist";
		}

		//final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartHttpServletRequest multiRequest = WebUtils.getNativeRequest(request,
			MultipartHttpServletRequest.class);
		if (multiRequest != null) { //2022.01 Possible null pointer dereference due to return value of called method 조치
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			InputStream fis = null;

			//String sResult = "";

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				if (file.getOriginalFilename() != null && !"".equals(file.getOriginalFilename())) {
					// 업로드 파일에 대한 확장자를 체크
					if (file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")
						|| file.getOriginalFilename().endsWith(".XLS")
						|| file.getOriginalFilename().endsWith(".XLSX")) {

						//zipManageService.deleteAllZip();
						//excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file.getInputStream(), 2);
						try {
							fis = file.getInputStream();
							if (searchVO.getSearchList().equals("1")) {
								zipManageService.insertExcelZip(fis);
							} else {
								rdnmadZipService.insertExcelZip(fis);
							}
						} finally {
							EgovResourceCloseHelper.close(fis);
						}

					} else {
						LOGGER.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
						return "egovframework/com/sym/ccm/zip/EgovCcmExcelZipRegist";
					}
					// *********** 끝 ***********

				}
			}
		}

		return "forward:/sym/ccm/zip/EgovCcmZipList.do";
	}

	/**
	 * 우편번호 상세항목을 조회한다.
	 * @param loginVO
	 * @param zip
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipDetail.do")
	public String selectZipDetail(@ModelAttribute("loginVO") LoginVO loginVO, Zip zip, ZipVO searchVO, ModelMap model)
		throws Exception {
		if (searchVO.getSearchList().equals("1")) {
			Zip vo = zipManageService.selectZipDetail(zip);
			model.addAttribute("result", vo);
			model.addAttribute("searchList", searchVO.getSearchList());
		} else {
			Zip vo = rdnmadZipService.selectZipDetail(zip);
			model.addAttribute("result", vo);
			model.addAttribute("searchList", searchVO.getSearchList());
		}

		return "egovframework/com/sym/ccm/zip/EgovCcmZipDetail";
	}

	/**
	 * 우편번호 목록을 조회한다.
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "우편번호관리", listUrl = "/sym/ccm/zip/EgovCcmZipList.do", order = 1000, gid = 50)
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipList.do")
	public String selectZipList(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") ZipVO searchVO,
		ModelMap model) throws Exception {
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

		if (!searchVO.getSearchList().equals("2")) {
			List<?> CmmnCodeList = zipManageService.selectZipList(searchVO);
			model.addAttribute("resultList", CmmnCodeList);

			int totCnt = zipManageService.selectZipListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
		} else {
			List<?> CmmnCodeList = rdnmadZipService.selectZipList(searchVO);
			model.addAttribute("resultList", CmmnCodeList);

			int totCnt = rdnmadZipService.selectZipListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
		}

		return "egovframework/com/sym/ccm/zip/EgovCcmZipList";
	}

	/**
	 * 우편번호를 수정한다.
	 * @param loginVO
	 * @param zip
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/sym/ccm/zip/EgovCcmZipModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovCcmZipModify.do")
	public String updateZip(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("zip") Zip zip, ZipVO searchVO,
		BindingResult bindingResult, @RequestParam Map<String, Object> commandMap,
		ModelMap model) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		model.addAttribute("searchList", searchVO.getSearchList());
		if (sCmd.equals("")) {
			if (searchVO.getSearchList().equals("1")) {
				Zip vo = zipManageService.selectZipDetail(zip);
				model.addAttribute("zip", vo);
			} else {
				Zip vo = rdnmadZipService.selectZipDetail(zip);
				model.addAttribute("zip", vo);
			}
			return "egovframework/com/sym/ccm/zip/EgovCcmZipModify";
		} else if (sCmd.equals("Modify")) {
			if (searchVO.getSearchList().equals("1")) {
				beanValidator.validate(zip, bindingResult);
				if (bindingResult.hasErrors()) {
					return "egovframework/com/sym/ccm/zip/EgovCcmZipModify";
				}

				zip.setLastUpdusrId(loginVO.getUniqId());
				zipManageService.updateZip(zip);
			} else {
				/*beanValidator.validate(zip, bindingResult);
				if (bindingResult.hasErrors()){
					return "egovframework/com/sym/ccm/zip/EgovCcmZipModify";
				}*/

				zip.setLastUpdusrId(loginVO.getUniqId());
				rdnmadZipService.updateZip(zip);
			}

			return "forward:/sym/ccm/zip/EgovCcmZipList.do";
		} else {
			return "forward:/sym/ccm/zip/EgovCcmZipList.do";
		}
	}

	/**
	 * 주소정보연계 팝업을 위한 입력 페이지를 호출한다.
	 *
	 * @return
	 */
	@RequestMapping(value = "/sym/ccm/zip/EgovAdressPop.do")
	public String selectAddPop() {
		return "egovframework/com/sym/ccm/zip/EgovAdressPop";
	}
}