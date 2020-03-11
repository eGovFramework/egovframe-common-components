package egovframework.com.uss.ion.noi.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.noi.service.Notification;
import egovframework.com.uss.ion.noi.service.NotificationVO;

import org.springframework.stereotype.Repository;

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
     * 
     * @param NotificationVO
     */
    public List<NotificationVO> selectNotificationInfs(NotificationVO vo) throws Exception {
	return selectList("NotificationDAO.selectNotificationInfs", vo);
    }

    /**
     * 정보알림이 목록 숫자를 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectNotificationInfsCnt(NotificationVO vo) throws Exception {
	return (Integer)selectOne("NotificationDAO.selectNotificationInfsCnt", vo);
    }
    
    /**
     * 정보알림이 정보를 등록한다.
     * 
     * @param notification
     * @return
     * @throws Exception
     */
    public String insertNotificationInf(Notification notification) throws Exception {
	return Integer.toString(insert("NotificationDAO.insertNotificationInf", notification));
    }
    
    /**
     * 정보알림이에 대한 상세정보를 조회한다.
     * 
     * @param searchVO
     * @return
     */
    public NotificationVO selectNotificationInf(NotificationVO searchVO) {
	return (NotificationVO)selectOne("NotificationDAO.selectNotificationInf", searchVO);
    }
    
    /**
     * 정보알림이 정보를 수정한다.
     * 
     * @param notification
     * @return
     * @throws Exception
     */
    public void updateNotificationInf(Notification notification) throws Exception {
	update("NotificationDAO.updateNotificationInf", notification);
    }
    
    /**
     * 정보알림이 정보를 삭제한다.
     * 
     * @param notification
     * @throws Exception
     */
    public void deleteNotificationInf(Notification notification) throws Exception {
	update("NotificationDAO.deleteNotificationInf", notification);
    }
    
    /**
     * 정보알림이 표시를 위한 대상 알림 정보를 얻는다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<NotificationVO> getNotificationData(NotificationVO vo) throws Exception {
	return selectList("NotificationDAO.getNotificationData", vo);
    }
}
