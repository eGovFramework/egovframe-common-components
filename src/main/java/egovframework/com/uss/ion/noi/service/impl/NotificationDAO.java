package egovframework.com.uss.ion.noi.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.noi.service.NotificationVO;

/**
 * 정보알림이를 위한 데이터 접근 클래스
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
@Repository("NotificationDAO")
public class NotificationDAO extends EgovComAbstractDAO {

    /**
     * 정보알림이 목록을 조회한다.
     */
    public List<NotificationVO> selectNotificationInfs(NotificationVO notificationVO) throws Exception {
        return selectList("NotificationDAO.selectNotificationInfs", notificationVO);
    }

    /**
     * 정보알림이 목록 숫자를 조회한다
     */
    public int selectNotificationInfsCnt(NotificationVO notificationVO) throws Exception {
        return (Integer) selectOne("NotificationDAO.selectNotificationInfsCnt", notificationVO);
    }

    /**
     * 정보알림이 정보를 등록한다.
     */
    public String insertNotificationInf(NotificationVO notificationVO) throws Exception {
        return Integer.toString(insert("NotificationDAO.insertNotificationInf", notificationVO));
    }

    /**
     * 정보알림이에 대한 상세정보를 조회한다.
     */
    public NotificationVO selectNotificationInf(NotificationVO notificationVO) {
        return (NotificationVO) selectOne("NotificationDAO.selectNotificationInf", notificationVO);
    }

    /**
     * 정보알림이 정보를 수정한다.
     */
    public void updateNotificationInf(NotificationVO notificationVO) throws Exception {
        update("NotificationDAO.updateNotificationInf", notificationVO);
    }

    /**
     * 정보알림이 정보를 삭제한다.
     */
    public void deleteNotificationInf(NotificationVO notificationVO) throws Exception {
        update("NotificationDAO.deleteNotificationInf", notificationVO);
    }

    /**
     * 정보알림이 표시를 위한 대상 알림 정보를 얻는다.
     */
    public List<NotificationVO> getNotificationData(NotificationVO notificationVO) throws Exception {
        return selectList("NotificationDAO.getNotificationData", notificationVO);
    }
}
