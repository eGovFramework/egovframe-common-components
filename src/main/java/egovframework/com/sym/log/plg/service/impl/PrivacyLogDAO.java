package egovframework.com.sym.log.plg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.plg.service.PrivacyLog;

@Repository("privacyLogDAO")
public class PrivacyLogDAO extends EgovComAbstractDAO {

	/**
	 * 개인정보조회 로그정보를 생성한다.
	 *
	 * @param
	 * @return
	 */
	public void insertPrivacyLog(PrivacyLog privacyLog) {
		insert("PrivacyLog.insertPrivacyLog", privacyLog);
	}

	/**
	 * 개인정보조회 로그정보 목록을 조회한다.
	 *
	 * @param privacyLog
	 * @return
	 */
	public List<PrivacyLog> selectPrivacyLogList(PrivacyLog privacyLog) {
		return selectList("PrivacyLog.selectPrivacyLogList", privacyLog);
	}
	
	/**
	 * 개인정보조회 로그정보 목록의 숫자를 조회한다.
	 * @param privacyLog
	 * @return
	 */
	public int selectPrivacyLogListCount(PrivacyLog privacyLog) {
		return (Integer)selectOne("PrivacyLog.selectPrivacyLogListCount", privacyLog);
	}

	/**
	 * 개인정보조회 로그정보 상세정보를 조회한다.
	 *
	 * @param privacyLog
	 * @return privacyLog
	 */
	public PrivacyLog selectPrivacyLog(PrivacyLog privacyLog) {
		return (PrivacyLog) selectOne("PrivacyLog.selectPrivacyLog", privacyLog);
	}

}
