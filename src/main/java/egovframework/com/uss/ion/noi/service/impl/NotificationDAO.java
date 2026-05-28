package egovframework.com.uss.ion.noi.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.noi.service.NotificationVO;
import jakarta.annotation.Resource;

/**
 * 정보알림이를 위한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.08  한성곤          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.08
 * @version 1.0
 */
@Repository("NotificationDAO")
public class NotificationDAO {

	@Resource(name = "notificationMapper")
	private NotificationMapper mapper;

	/**
	 * 정보알림이 목록을 조회한다.
	 */
	public List<NotificationVO> selectNotificationInfs(NotificationVO notificationVO) throws Exception {
		return mapper.selectNotificationInfs(notificationVO);
	}

	/**
	 * 정보알림이 목록 숫자를 조회한다.
	 */
	public int selectNotificationInfsCnt(NotificationVO notificationVO) throws Exception {
		return mapper.selectNotificationInfsCnt(notificationVO);
	}

	/**
	 * 정보알림이 정보를 등록한다.
	 */
	public String insertNotificationInf(NotificationVO notificationVO) throws Exception {
		return Integer.toString(mapper.insertNotificationInf(notificationVO));
	}

	/**
	 * 정보알림이에 대한 상세정보를 조회한다.
	 */
	public NotificationVO selectNotificationInf(NotificationVO notificationVO) {
		return mapper.selectNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 정보를 수정한다.
	 */
	public void updateNotificationInf(NotificationVO notificationVO) throws Exception {
		mapper.updateNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 정보를 삭제한다.
	 */
	public void deleteNotificationInf(NotificationVO notificationVO) throws Exception {
		mapper.deleteNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 표시를 위한 대상 알림 정보를 얻는다.
	 */
	public List<NotificationVO> getNotificationData(NotificationVO notificationVO) throws Exception {
		return mapper.getNotificationData(notificationVO);
	}
}
