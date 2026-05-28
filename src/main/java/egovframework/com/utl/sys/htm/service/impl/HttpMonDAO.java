package egovframework.com.utl.sys.htm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.htm.service.HttpMon;
import egovframework.com.utl.sys.htm.service.HttpMonLog;
import egovframework.com.utl.sys.htm.service.HttpMonLogVO;
import egovframework.com.utl.sys.htm.service.HttpMonVO;

/**
 * 개요
 * - HTTP서비스모니터링에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:45
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.17  박종선           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("HttpMonDAO")
public interface HttpMonDAO {

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 *
	 * @param searchVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 */
	List<HttpMonVO> selectHttpMonList(HttpMonVO searchVO);

	/**
	 * HTTP서비스모니터링 목록 총 개수를 조회한다.
	 *
	 * @param searchVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 */
	int selectHttpMonTotCnt(HttpMonVO searchVO);

	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 *
	 * @param httpMon - HTTP서비스모니터링 Vo
	 * @return HttpMon - HTTP서비스모니터링 Vo
	 */
	HttpMon selectHttpMonDetail(HttpMon httpMon);

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 *
	 * @param httpMon - HTTP서비스모니터링 model
	 */
	void insertHttpMon(HttpMon httpMon);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 *
	 * @param httpMon - HTTP서비스모니터링 model
	 */
	void updateHttpMon(HttpMon httpMon);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 *
	 * @param httpMon - HTTP서비스모니터링 model
	 */
	void deleteHttpMon(HttpMon httpMon);

	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 *
	 * @param httpMonLogVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 */
	List<HttpMonLogVO> selectHttpMonLogList(HttpMonLogVO httpMonLogVO);

	/**
	 * HTTP서비스모니터링로그 목록 총 개수를 조회한다.
	 *
	 * @param httpMonLogVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 */
	int selectHttpMonLogTotCnt(HttpMonLogVO httpMonLogVO);

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 *
	 * @param httpMonLog - HTTP서비스모니터링 Vo
	 * @return HttpMonLog - HTTP서비스모니터링 Vo
	 */
	HttpMonLog selectHttpMonDetailLog(HttpMonLog httpMonLog);

	/**
	 * HTTP서비스모니터링로그 정보를 신규로 등록한다.
	 *
	 * @param httpMonLog - HTTP서비스모니터링 model
	 */
	void insertHttpMonLog(HttpMonLog httpMonLog);

	/**
	 * HTTP서비스모니터링 결과 정보를 수정한다.
	 *
	 * @param httpMon - HTTP서비스모니터링 model
	 */
	void updateHttpMonSttus(HttpMon httpMon);

}
