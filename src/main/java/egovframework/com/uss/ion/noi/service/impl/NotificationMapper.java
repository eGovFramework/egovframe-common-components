package egovframework.com.uss.ion.noi.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.noi.service.NotificationVO;

/**
 * 정보알림이에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("notificationMapper")
public interface NotificationMapper {

	List<NotificationVO> selectNotificationInfs(NotificationVO notificationVO);

	int selectNotificationInfsCnt(NotificationVO notificationVO);

	int insertNotificationInf(NotificationVO notificationVO);

	NotificationVO selectNotificationInf(NotificationVO notificationVO);

	void updateNotificationInf(NotificationVO notificationVO);

	void deleteNotificationInf(NotificationVO notificationVO);

	List<NotificationVO> getNotificationData(NotificationVO notificationVO);
}
