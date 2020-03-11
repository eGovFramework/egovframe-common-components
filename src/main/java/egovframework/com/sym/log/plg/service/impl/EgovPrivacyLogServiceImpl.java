package egovframework.com.sym.log.plg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.sym.log.plg.service.EgovPrivacyLogService;
import egovframework.com.sym.log.plg.service.PrivacyLog;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovPrivacyLogServiceImpl.java
 * @Description : 개인정보 조회 이력 관리를 위한 구현 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2014.09.11	표준프레임워크		최초생성
* @author Vincent Han
 * @since 2014.09.11
 * @version 3.5
 */
@Service("egovPrivacyLogService")
public class EgovPrivacyLogServiceImpl extends EgovAbstractServiceImpl implements EgovPrivacyLogService {

	@Resource(name="privacyLogDAO")
	private PrivacyLogDAO privacyLogDAO;

    /** ID Generation */
	@Resource(name="egovPrivacyLogIdGnrService")
	private EgovIdGnrService egovPrivacyLogIdGnrService;


	/**
	 * 개인정보조회 로그정보를 생성한다.
	 *
	 * @param privacyLog
	 */
	@Override
	public void innerInsertPrivacyLog(PrivacyLog privacyLog) throws Exception {

		privacyLog.setRequestId(egovPrivacyLogIdGnrService.getNextStringId());

		privacyLogDAO.insertPrivacyLog(privacyLog);
	}

	/**
	 * 개인정보조회 로그정보 상제정보를 조회한다.
	 *
	 * @param privacyLog
	 * @return privacyLog
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectPrivacyLogList(PrivacyLog privacyLog) throws Exception {

		List<PrivacyLog> result = privacyLogDAO.selectPrivacyLogList(privacyLog);
		int count = privacyLogDAO.selectPrivacyLogListCount(privacyLog);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(count));

		return map;
	}

	/**
	 * 개인정보조회 로그정보 목록을 조회한다.
	 *
	 * @param privacyLog
	 */
	@Override
	public PrivacyLog selectPrivacyLog(PrivacyLog privacyLog) throws Exception {
		return privacyLogDAO.selectPrivacyLog(privacyLog);
	}

}
