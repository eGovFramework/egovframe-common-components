package egovframework.com.utl.sys.nsm.service;

import java.util.Map;

/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
public interface EgovNtwrkSvcMntrngService {

	/**
	 * 네트워크서비스 모니터링대상 목록을 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  Map<String, Object> - 네트워크서비스 모니터링 List
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public Map<String, Object> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception;

	/**
	 * 네트워크서비스 모니터링대상을 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception;

	/**
	 * 네트워크서비스 모니터링대상을 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception;

	/**
	 * 네트워크서비스 모니터링대상을 등록한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception;
	
	/**
	 * 네트워크서비스 모니터링대상을 등록하기 위한 중복 조회를 수행한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  int 
	 * 
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception;
	
	/**
	 * 네트워크서비스 모니터링대상을 삭제한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception;
	
	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * 
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 */
	public void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception;
	
	/**
	 * 네트워크서비스 모니터링 로그 목록을 조회한다.
	 * @param NtwrkSvcMntrngLogVO
	 * @return  Map<String, Object> - 네트워크서비스 모니터링 로그 List
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public Map<String, Object> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception;

	/**
	 * 네트워크서비스 모니터링 로그를 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * 
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception;
	
	/**
	 * 네트워크서비스 모니터링 로그를 등록한다.
	 * @param NtwrkSvcMntrngLog - 네트워크서비스 모니터링로그 model
	 * 
	 * @param ntwrkSvcMntrngLog - 네트워크서비스 모니터링로그 model
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLog ntwrkSvcMntrngLog) throws Exception;
}