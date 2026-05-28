package egovframework.com.utl.sys.nsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrng;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLog;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLogVO;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngVO;

/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  장철호           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("NtwrkSvcMntrngDAO")
public interface NtwrkSvcMntrngDAO {

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 대상 목록을 불러온다.
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return List - 네트워크서비스 모니터링 대상 List
	 */
	List<NtwrkSvcMntrngVO> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 대상을 불러온다.
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 */
	NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링 대상 정보를 수정한다.
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 */
	void updateNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng);

	/**
	 * 네트워크서비스 모니터링 대상 정보를 등록한다.
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 */
	void insertNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng);

	/**
	 * 네트워크서비스 모니터링대상 등록을 위한 중복 조회를 수행한다.
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return int
	 */
	int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링 대상 정보를 삭제한다.
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 */
	void deleteNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng);

	/**
	 * 네트워크서비스 모니터링대상 목록에 대한 전체 건수를 조회한다.
	 * @param ntwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return int
	 */
	int selectNtwrkSvcMntrngListCnt(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * @param ntwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 */
	void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrng ntwrkSvcMntrng);

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 로그 목록을 불러온다.
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return List - 네트워크서비스 모니터링 로그 List
	 */
	List<NtwrkSvcMntrngLogVO> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 네트워크서비스 모니터링 로그 목록에 대한 전체 건수를 조회한다.
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return int
	 */
	int selectNtwrkSvcMntrngLogListCnt(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 로그를 불러온다.
	 * @param ntwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 */
	NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 네트워크서비스 모니터링 로그 정보를 등록한다.
	 * @param ntwrkSvcMntrngLog - 네트워크서비스 모니터링 로그 model
	 */
	void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLog ntwrkSvcMntrngLog);

}
