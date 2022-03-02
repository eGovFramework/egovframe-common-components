package egovframework.com.uss.ion.noi.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.noi.service.EgovNotificationService;
import egovframework.com.uss.ion.noi.service.Notification;
import egovframework.com.uss.ion.noi.service.NotificationVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 정보알림이 서비스 컨트롤러 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.08
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.6.8  한성곤          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovNotificationController {

    @Resource(name="EgovNotificationService")
    protected EgovNotificationService notificationService;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 정보알림이에 대한 목록을 조회한다.
     *
     * @param notificationVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="정보알림이", order = 730 ,gid = 50)
    @RequestMapping("/uss/ion/noi/selectNotificationList.do")
    public String selectNotificationList(@ModelAttribute("searchVO") NotificationVO notificationVO, ModelMap model) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	notificationVO.setUniqId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

	notificationVO.setPageUnit(propertyService.getInt("pageUnit"));
	notificationVO.setPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();

	paginationInfo.setCurrentPageNo(notificationVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(notificationVO.getPageUnit());
	paginationInfo.setPageSize(notificationVO.getPageSize());

	notificationVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	notificationVO.setLastIndex(paginationInfo.getLastRecordIndex());
	notificationVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = notificationService.selectNotificationInfs(notificationVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));

	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("paginationInfo", paginationInfo);
	
	return "egovframework/com/uss/ion/noi/EgovNotificationList";
    }  

    /**
     * 신규 정보알림이 등록을 위한 등록페이지로 이동한다.
     *
     * @param notificationVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/addNotification.do")
    public String addNotification(@ModelAttribute("searchVO") NotificationVO notificationVO, ModelMap model) throws Exception {

	Notification notification = new Notification();

	model.addAttribute("notification", notification);

	return "egovframework/com/uss/ion/noi/EgovNotificationRegist";
    }

    /**
     * 신규 정보알림이 정보를 등록한다.
     *
     * @param notificationVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/insertNotification.do")
    public String insertNotification(@ModelAttribute("searchVO") NotificationVO notificationVO, @ModelAttribute("notification") Notification notification,
	    BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	beanValidator.validate(notification, bindingResult);
	if (bindingResult.hasErrors()) {
	    return "egovframework/com/uss/ion/noi/EgovNotificationRegist";
	}

	if (! notificationService.checkNotification(notification)) {
	    model.addAttribute("msg", egovMessageSource.getMessage("ussIonNoi.notificationUpdt.validate.alertNtfcTime"));
	    return "egovframework/com/uss/ion/noi/EgovNotificationRegist";
	}

	if (isAuthenticated) {
	    notification.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    
	    if (EgovDoubleSubmitHelper.checkAndSaveToken()) {

	    	notificationService.insertNotificationInf(notification);
	    }
	}

	return "forward:/uss/ion/noi/selectNotificationList.do";
    }

    /**
     * 정보알림이에 대한 상세정보를 조회한다.
     *
     * @param notificationVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/selectNotification.do")
    public String selectNotification(@ModelAttribute("searchVO") NotificationVO notificationVO, ModelMap model) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	NotificationVO vo = notificationService.selectNotificationInf(notificationVO);

	model.addAttribute("sessionUniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	model.addAttribute("result", vo);

	return "egovframework/com/uss/ion/noi/EgovNotificationDetail";
    }

    /**
     * 정보알림이 수정을 위해 수정페이지로 이동한다.
     *
     * @param notificationVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/forUpdateNotification.do")
    public String forUpdateNotificaiton(@ModelAttribute("searchVO") NotificationVO notificationVO, ModelMap model) throws Exception {
	NotificationVO vo = notificationService.selectNotificationInf(notificationVO);

	model.addAttribute("result", vo);

	return "egovframework/com/uss/ion/noi/EgovNotificationUpdt";
    }

    /**
     * 정보알림이 정보를 수정한다.
     *
     * @param notificationVO
     * @param notification
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/updateNotification.do")
    public String updateNotification(@ModelAttribute("searchVO") NotificationVO notificationVO, @ModelAttribute("notification") Notification notification,
	    BindingResult bindingResult, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
	beanValidator.validate(notification, bindingResult);
	if (bindingResult.hasErrors()) {
	    NotificationVO vo = notificationService.selectNotificationInf(notificationVO);
	    model.addAttribute("result", vo);
	    return "egovframework/com/uss/ion/noi/EgovNotificationUpdt";
	}

	if (!notificationService.checkNotification(notification)) {
	    model.addAttribute("msg", egovMessageSource.getMessage("ussIonNoi.notificationUpdt.validate.alertNtfcTime"));

	    NotificationVO vo = notificationService.selectNotificationInf(notificationVO);

	    model.addAttribute("result", vo);
	    return "egovframework/com/uss/ion/noi/EgovNotificationUpdt";
	}

	if (isAuthenticated) {
	    notification.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    
	    if (EgovDoubleSubmitHelper.checkAndSaveToken("EgovNotification")) {
	    	notificationService.updateNotifictionInf(notification);
	    }
	}

	return "forward:/uss/ion/noi/selectNotificationList.do";
    }

    /**
     * 정보알림이 정보를 삭제한다.
     *
     * @param notificationVO
     * @param notification
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/deleteNotification.do")
    public String deleteNotification(@ModelAttribute("searchVO") NotificationVO notificationVO, @ModelAttribute("notification") Notification notification,
	    SessionStatus status) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    notification.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    notificationService.deleteNotifictionInf(notification);
	}
	// status.setComplete();
	return "forward:/uss/ion/noi/selectNotificationList.do";
    }

    /**
     * 정보알림이 표시를 조회한다.
     *
     * @param notificationVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/noi/getNotifications.do")
    public String getNotifications(@ModelAttribute("searchVO") NotificationVO notificationVO, ModelMap model) throws Exception {
	//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    List<NotificationVO> list = notificationService.selectNotificationData();

	    model.addAttribute("list", list);
	}

	return "egovframework/com/uss/ion/noi/EgovNotificationData";
    }
}
