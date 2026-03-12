package egovframework.com.uss.ion.noi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.noi.service.EgovNotificationService;
import egovframework.com.uss.ion.noi.service.NotificationVO;
import jakarta.annotation.Resource;

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

    @Resource(name = "NotificationDAO")
    private NotificationDAO notificationDao;

    @Override
    public Map<String, Object> selectNotificationInfs(NotificationVO notificationVO) throws Exception {
        List<NotificationVO> result = notificationDao.selectNotificationInfs(notificationVO);
        int cnt = notificationDao.selectNotificationInfsCnt(notificationVO);

        Map<String, Object> map = new HashMap<>();
        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));
        return map;
    }

    @Override
    public void insertNotificationInf(NotificationVO notificationVO) throws Exception {
        StringBuffer time = new StringBuffer();
        time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
        time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
        time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
        time.append("00");
        notificationVO.setNtfcTime(time.toString());

        StringBuffer interval = new StringBuffer();
        String[] array = notificationVO.getBhNtfcIntrvl();
        if (array == null) {
            throw new RuntimeException("Method insertNotificationInf : array is null\n");
        }
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                interval.append(",");
            }
            interval.append(array[i]);
        }
        notificationVO.setBhNtfcIntrvlString(interval.toString());

        notificationDao.insertNotificationInf(notificationVO);
    }

    @Override
    public NotificationVO selectNotificationInf(NotificationVO notificationVO) throws Exception {
        return notificationDao.selectNotificationInf(notificationVO);
    }

    @Override
    public void updateNotifictionInf(NotificationVO notificationVO) throws Exception {
        StringBuffer time = new StringBuffer();
        time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
        time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
        time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
        time.append("00");
        notificationVO.setNtfcTime(time.toString());

        StringBuffer interval = new StringBuffer();
        String[] array = notificationVO.getBhNtfcIntrvl();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    interval.append(",");
                }
                interval.append(array[i]);
            }
        }
        notificationVO.setBhNtfcIntrvlString(interval.toString());

        notificationDao.updateNotificationInf(notificationVO);
    }

    @Override
    public void deleteNotifictionInf(NotificationVO notificationVO) throws Exception {
        notificationDao.deleteNotificationInf(notificationVO);
    }

    @Override
    public boolean checkNotification(NotificationVO notificationVO) throws Exception {
        StringBuffer time = new StringBuffer();
        time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
        time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
        time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
        time.append("00");

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

    @Override
    public List<NotificationVO> selectNotificationData() throws Exception {
        List<NotificationVO> result = new ArrayList<>();

        NotificationVO vo = new NotificationVO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        SimpleDateFormat other = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.add(Calendar.HOUR, -1);
        end.add(Calendar.HOUR, 1);

        vo.setStartDateTime(formatter.format(start.getTime()));
        vo.setEndDateTime(formatter.format(end.getTime()));

        List<NotificationVO> target = notificationDao.getNotificationData(vo);
        Calendar current = Calendar.getInstance();

        for (int i = 0; i < target.size(); i++) {
            NotificationVO item = target.get(i);
            String[] interval = ("0," + item.getBhNtfcIntrvlString()).split(",");

            for (String element : interval) {
                Calendar alarm = Calendar.getInstance();
                alarm.setTime(other.parse(item.getNtfcTime()));
                alarm.add(Calendar.MINUTE, -1 * Integer.parseInt(element));

                if (getDateTimeWithoutSec(current).equals(getDateTimeWithoutSec(alarm))) {
                    result.add(item);
                    break;
                }
            }
        }
        return result;
    }
}
