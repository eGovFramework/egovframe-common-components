package egovframework.com.utl.sys.htm.service;

import java.util.List;
import java.util.Map;

/**
 * 개요
 * - HTTP서비스 모니터링에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - HTTP서비스 모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - HTTP서비스 모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:43
 */
public interface EgovHttpMonService {

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 * 
	 * @param httpMonVO
	 */
	public List<HttpMon> selectHttpMonList(HttpMonVO searchVO) throws Exception;

	/**
	 * HTTP서비스모니터링 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 * 
	 * @param httpMonVO
	 */
	int selectHttpMonTotCnt(HttpMonVO searchVO) throws Exception;
	
	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 * 
	 * @param httpMonVO
	 */
	HttpMon selectHttpMonDetail(HttpMon httpMon) throws Exception;

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	void insertHttpMon(HttpMon httpMon) throws Exception;
	
	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	void updateHttpMon(HttpMon httpMon) throws Exception;

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	void deleteHttpMon(HttpMon httpMon) throws Exception;
	
	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 * 
	 * @param httpMonVO
	 */
	public Map<String, Object> selectHttpMonLogList(HttpMonLogVO httpMonLogVO) throws Exception;
	
	/**
	 * HTTP서비스모니터링로그 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 * 
	 * @param httpMonVO
	 */
	//int selectHttpMonLogTotCnt(HttpMonLogVO searchVO) throws Exception;	
	
	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 * 
	 * @param httpMonVO
	 */
	HttpMonLog selectHttpMonDetailLog(HttpMonLog httpMonLog) throws Exception;
	
	/**
	 * HTTP서비스모니터링로그 정보를 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	void insertHttpMonLog(HttpMonLog httpMonLog) throws Exception;
	
	/**
	 * HTTP서비스 모니터링 결과를 수정한다.
	 * @param HttpMon - HTTP서비스 모니터링대상 model
	 * 
	 * @param httpMon
	 */
	public void updateHttpMonSttus(HttpMon httpMon) throws Exception;	
	
}
