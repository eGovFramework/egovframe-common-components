package egovframework.com.sym.log.plg.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.plg.service.PrivacyLog;

import org.springframework.stereotype.Repository;

@Repository("privacyLogDAO")
public class PrivacyLogDAO extends EgovComAbstractDAO {

	/**
	 * 개인정보조회 로그정보를 생성한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertPrivacyLog(PrivacyLog privacyLog) throws Exception{
		insert("PrivacyLog.insertPrivacyLog", privacyLog);
	}

	/**
	 * 개인정보조회 로그정보 목록을 조회한다.
	 *
	 * @param privacyLog
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PrivacyLog> selectPrivacyLogList(PrivacyLog privacyLog) throws Exception{
		return (List<PrivacyLog>) list("PrivacyLog.selectPrivacyLogList", privacyLog);
	}
	
	/**
	 * 개인정보조회 로그정보 목록의 숫자를 조회한다.
	 * @param privacyLog
	 * @return
	 * @throws Exception
	 */
	public int selectPrivacyLogListCount(PrivacyLog privacyLog) throws Exception{
		return (Integer)selectOne("PrivacyLog.selectPrivacyLogListCount", privacyLog);
	}

	/**
	 * 개인정보조회 로그정보 상세정보를 조회한다.
	 *
	 * @param privacyLog
	 * @return privacyLog
	 * @throws Exception
	 */
	public PrivacyLog selectPrivacyLog(PrivacyLog privacyLog) throws Exception{
		return (PrivacyLog) selectOne("PrivacyLog.selectPrivacyLog", privacyLog);
	}

}
