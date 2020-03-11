package egovframework.com.sym.log.plg.service;

import java.util.Map;

/**
 * @Class Name : EgovPrivacyLogService.java
 * @Description : 개인정보 조회 이력 관리를 위한 인터페이스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2014.09.11	표준프레임워크		최초생성
* @author Vincent Han
 * @since 2014.09.11
 * @version 3.5
 */
public interface EgovPrivacyLogService {
	
	/**
	 * 개인정보 조회 로그정보를 생성한다.
	 *
	 * @param privacyLog
	 */
	public void innerInsertPrivacyLog(PrivacyLog privacyLog) throws Exception;
	
	/**
	 * 개인정보 조회 로그정보 목록을 조회한다.
	 *
	 * @param privacyLog
	 */
	public Map<String, Object> selectPrivacyLogList(PrivacyLog privacyLog) throws Exception;
	
	/**
	 * 개인정보 조회 로그 상세정보를 조회한다.
	 *
	 * @param privacyLog
	 * @return privacyLog
	 * @throws Exception
	 */
	public PrivacyLog selectPrivacyLog(PrivacyLog privacyLog) throws Exception;
}
