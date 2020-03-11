package egovframework.com.utl.sys.nsm.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.utl.sys.nsm.service.EgovNtwrkSvcMntrngService;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrng;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLog;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLogVO;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * 네트워크서비스 모니터링대상에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@Service("EgovNtwrkSvcMntrngService")
public class EgovNtwrkSvcMntrngServiceImpl extends EgovAbstractServiceImpl implements EgovNtwrkSvcMntrngService {
	
	@Resource(name = "NtwrkSvcMntrngDAO")
    private NtwrkSvcMntrngDAO ntwrkSvcMntrngDAO;
	
	@Resource(name="egovNtwrkSvcMntrngLogIdGnrService")
	private EgovIdGnrService idgenServiceNtwrkSvcMntrng;
	/**
	 * 네트워크서비스 모니터링대상 목록을 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  Map<String, Object> - 네트워크서비스 모니터링 List
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public Map<String, Object> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		List<NtwrkSvcMntrngVO> result = ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO);
		int cnt = ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngListCnt(ntwrkSvcMntrngVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 네트워크서비스 모니터링대상을 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return ntwrkSvcMntrngDAO.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		ntwrkSvcMntrngDAO.updateNtwrkSvcMntrng(ntwrkSvcMntrng);
	}

	/**
	 * 네트워크서비스 모니터링대상을 등록한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		ntwrkSvcMntrng.setMntrngSttus("01");
		ntwrkSvcMntrngDAO.insertNtwrkSvcMntrng(ntwrkSvcMntrng);
	}
	
	/**
	 * 네트워크서비스 모니터링대상을 등록하기 위한 중복 조회를 수행한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  int 
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngCheck(ntwrkSvcMntrngVO);
	}
	
	/**
	 * 네트워크서비스 모니터링대상을 삭제한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		ntwrkSvcMntrngDAO.deleteNtwrkSvcMntrng(ntwrkSvcMntrng);
	}
	
	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		ntwrkSvcMntrngDAO.updateNtwrkSvcMntrngSttus(ntwrkSvcMntrng);
		
		NtwrkSvcMntrngLog ntwrkSvcMntrngLog = new NtwrkSvcMntrngLog();
		ntwrkSvcMntrngLog.setLogId(idgenServiceNtwrkSvcMntrng.getNextStringId());
		ntwrkSvcMntrngLog.setSysIp(ntwrkSvcMntrng.getSysIp());
		ntwrkSvcMntrngLog.setSysPort(ntwrkSvcMntrng.getSysPort());
		ntwrkSvcMntrngLog.setSysNm(ntwrkSvcMntrng.getSysNm());
		ntwrkSvcMntrngLog.setMntrngSttus(ntwrkSvcMntrng.getMntrngSttus());
		ntwrkSvcMntrngLog.setLogInfo(ntwrkSvcMntrng.getLogInfo());
		ntwrkSvcMntrngLog.setCreatDt(ntwrkSvcMntrng.getCreatDt());
		insertNtwrkSvcMntrngLog(ntwrkSvcMntrngLog);
	}
	
	/**
	 * 네트워크서비스 모니터링 로그 목록을 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  Map<String, Object> - 네트워크서비스 모니터링 로그 List
	 * 
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 */
	public Map<String, Object> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception{
		List<NtwrkSvcMntrngLogVO> result = ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngLogList(ntwrkSvcMntrngLogVO);
		int cnt = ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngLogListCnt(ntwrkSvcMntrngLogVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 네트워크서비스 모니터링 로그를 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * 
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception{
		return ntwrkSvcMntrngDAO.selectNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
	}
	
	/**
	 * 네트워크서비스 모니터링 로그를 등록한다.
	 * @param NtwrkSvcMntrngLog - 네트워크서비스 모니터링로그 model
	 * 
	 * @param ntwrkSvcMntrngLog - 네트워크서비스 모니터링로그 model
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLog ntwrkSvcMntrngLog) throws Exception{
		ntwrkSvcMntrngDAO.insertNtwrkSvcMntrngLog(ntwrkSvcMntrngLog);
	}
}