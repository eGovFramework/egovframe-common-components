package egovframework.com.uss.ion.noi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import egovframework.com.uss.ion.noi.service.EgovNotificationService;
import egovframework.com.uss.ion.noi.service.Notification;
import egovframework.com.uss.ion.noi.service.NotificationVO;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 정보알림이를 위한 서비스 구현 클래스
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
 *   2009.06.08  한성곤          최초 생성
 *
 * </pre>
 */
@Service("EgovNotificationService")
public class EgovNotificationServiceImpl extends EgovAbstractServiceImpl implements EgovNotificationService {

    @Resource(name="NotificationDAO")
    private NotificationDAO notificationDao;
    
    /**
     * 정보알림이 목록을 조회 한다.
     */
    public Map<String, Object> selectNotificationInfs(NotificationVO searchVO) throws Exception {
	List<NotificationVO> result = notificationDao.selectNotificationInfs(searchVO);
	int cnt = notificationDao.selectNotificationInfsCnt(searchVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }
    
    /**
     * 정보알림이 정보를 등록한다.
     */
    public void insertNotificationInf(Notification notification) throws Exception {
	//---------------------------------------
	// 알림일자 및 시작 지정
	//---------------------------------------
	StringBuffer time = new StringBuffer();
	
	time.append(notification.getNtfcDate().replaceAll("-", ""));
	time.append(notification.getNtfcHH().length() == 1 ? "0" + notification.getNtfcHH() : notification.getNtfcHH());
	time.append(notification.getNtfcMM().length() == 1 ? "0" + notification.getNtfcMM() : notification.getNtfcMM());
	time.append("00");
	
	notification.setNtfcTime(time.toString());
	
	//---------------------------------------
	// 사전 알림간격 지정
	//---------------------------------------
	StringBuffer interval = new StringBuffer();
	
	String[] array = notification.getBhNtfcIntrvl();
	
	//KISA 보안약점 조치 (2018-10-29, 윤창원)
	if (array == null) {
		throw new RuntimeException("Method insertNotificationInf : array is null\n");
	}
	
	for (int i = 0; i < array.length; i++) {
	    if (i != 0) {
		interval.append(",");
	    }
	    
	    interval.append(array[i]);
	}
	
	notification.setBhNtfcIntrvlString(interval.toString());
	
	//---------------------------------------
	// 등록 처리
	//---------------------------------------
	notificationDao.insertNotificationInf(notification);
    }
    
    /**
     * 알림메시지에 대한 상세정보를 조회한다.
     */
    public NotificationVO selectNotificationInf(NotificationVO searchVO) throws Exception {
	return notificationDao.selectNotificationInf(searchVO);
    }
    
    /**
     * 정보알림이 정보를 수정한다.
     */
    public void updateNotifictionInf(Notification notification) throws Exception {
	//---------------------------------------
	// 알림일자 및 시작 지정
	//---------------------------------------
	StringBuffer time = new StringBuffer();
	
	time.append(notification.getNtfcDate().replaceAll("-", ""));
	time.append(notification.getNtfcHH().length() == 1 ? "0" + notification.getNtfcHH() : notification.getNtfcHH());
	time.append(notification.getNtfcMM().length() == 1 ? "0" + notification.getNtfcMM() : notification.getNtfcMM());
	time.append("00");
	
	notification.setNtfcTime(time.toString());
	
	//---------------------------------------
	// 사전 알림간격 지정
	//---------------------------------------
	StringBuffer interval = new StringBuffer();
	
	String[] array = notification.getBhNtfcIntrvl();
	
	if (array != null) {

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				interval.append(",");
			}

			interval.append(array[i]);
		}
	}
	
	notification.setBhNtfcIntrvlString(interval.toString());
	
	//---------------------------------------
	// 수정 처리
	//---------------------------------------
	notificationDao.updateNotificationInf(notification);
    }
    
    /**
     * 정보알림이 정보를 삭제한다.
     */
    public void deleteNotifictionInf(Notification notification) throws Exception {
	notificationDao.deleteNotificationInf(notification);
    }
    
    /**
     * 정보알림이 알림시간 등에 대한 점검을 수행한다.
     */
    public boolean checkNotification(Notification notification) throws Exception {
	//---------------------------------------
	// 알림일자 및 시작 지정
	//---------------------------------------
	StringBuffer time = new StringBuffer();
	
	time.append(notification.getNtfcDate().replaceAll("-", ""));
	time.append(notification.getNtfcHH().length() == 1 ? "0" + notification.getNtfcHH() : notification.getNtfcHH());
	time.append(notification.getNtfcMM().length() == 1 ? "0" + notification.getNtfcMM() : notification.getNtfcMM());
	time.append("00");
	
	//---------------------------------------
	// 시간 지정
	//---------------------------------------
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
	Calendar alarm = Calendar.getInstance();
	alarm.setTime(formatter.parse(time.toString()));

	Calendar current = Calendar.getInstance();
	current.add(Calendar.MINUTE, -1);
	
	if (current.after(alarm)) {
	    return false;
	}

	return true;
    }
    
    private String getDateTimeWithoutSec(Calendar cal) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
	
	return formatter.format(cal.getTime()).substring(0, 12);
    }
    
    /**
     * 정보알림이 정보 표시를 수행한다.
     */
    public List<NotificationVO> selectNotificationData() throws Exception {
	List<NotificationVO> result = new ArrayList<NotificationVO>();
	
	//------------------------------------------
	// 검색 조건 지정
	//------------------------------------------
	NotificationVO vo = new NotificationVO();
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
	SimpleDateFormat other = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

	// 전후 1시간 조건 지정..
	Calendar start = Calendar.getInstance();
	Calendar end = Calendar.getInstance();
	
	start.add(Calendar.HOUR, -1);
	end.add(Calendar.HOUR, 1);
	
	vo.setStartDateTime(formatter.format(start.getTime()));
	vo.setEndDateTime(formatter.format(end.getTime()));
	////----------------------------------------

	List<NotificationVO> target = notificationDao.getNotificationData(vo);
	
	Calendar current = Calendar.getInstance();
	for (int i = 0; i < target.size(); i++) {
	    vo = target.get(i);
	    
	    String[] interval = ("0," + vo.getBhNtfcIntrvlString()).split(",");
	    
	    for (int j = 0; j < interval.length; j++) {
		Calendar alarm = Calendar.getInstance();
		alarm.setTime(other.parse(vo.getNtfcTime()));
		
		alarm.add(Calendar.MINUTE, -1 * Integer.parseInt(interval[j]));
		
		if (getDateTimeWithoutSec(current).equals(getDateTimeWithoutSec(alarm))) {
		    
		    result.add(vo);
		    break;
		}
	    }
	}
	
	return result;
    }
}
