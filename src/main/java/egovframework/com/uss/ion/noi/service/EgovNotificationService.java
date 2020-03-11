package egovframework.com.uss.ion.noi.service;

import java.util.List;
import java.util.Map;

/**
 * 정보알림이를 위한 서비스 인터페이스 클래스
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
 *
 * </pre>
 */
public interface EgovNotificationService {
    /**
     * 정보알림이 목록을 조회 한다.
     * 
     * @param BoardMasterVO
     */
    public Map<String, Object> selectNotificationInfs(NotificationVO searchVO) throws Exception;
    
    /**
     * 정보알림이 정보를 등록한다.
     * 
     * @param notification
     * @throws Exception
     */
    public void insertNotificationInf(Notification notification) throws Exception;
    
    /**
     * 정보알림이에 대한 상세정보를 조회한다.
     * 
     * @param searchVO
     * @return
     * @throws Exception
     */
    public NotificationVO selectNotificationInf(NotificationVO searchVO) throws Exception;
    
    /**
     * 정보알림이 정보를 수정한다.
     * 
     * @param notification
     * @throws Exception
     */
    public void updateNotifictionInf(Notification notification) throws Exception;
    
    /**
     * 정보알림이 정보를 삭제한다.
     * 
     * @param notification
     * @throws Exception
     */
    public void deleteNotifictionInf(Notification notification) throws Exception;
    
    /**
     * 정보알림이 알림시간 등에 대한 점검을 수행한다.
     * 
     * @param notification
     * @return
     * @throws Exception
     */
    public boolean checkNotification(Notification notification) throws Exception;
    
    /**
     * 정보알림이 정보 표시를 수행한다.
     * 
     * @return
     * @throws Exception
     */
    public List<NotificationVO> selectNotificationData() throws Exception;
}
