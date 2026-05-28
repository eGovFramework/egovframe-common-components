package egovframework.com.utl.sys.srm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.srm.service.ServerResrceMntrng;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrngVO;

/**
 * 개요
 * - 서버자원모니터링에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
 *
 * @author lee.m.j
 * @version 1.0
 * @created 06-9-2010 오전 11:24:00
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.09.06  lee.m.j          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("serverResrceMntrngDAO")
public interface ServerResrceMntrngDAO {

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return List - 서버자원모니터링의 로그 목록
	 */
	List<ServerResrceMntrngVO> selectServerResrceMntrngList(ServerResrceMntrngVO serverResrceMntrngVO);

	/**
	 * 서버자원모니터링의 로그정보 목록 총 개수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링의 로그 카운트 수
	 */
	int selectServerResrceMntrngListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO);

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return ServerResrceMntrngVO - 서버자원모니터링 Vo
	 */
	ServerResrceMntrngVO selectServerResrceMntrng(ServerResrceMntrngVO serverResrceMntrngVO);

	/**
	 * 서버자원모니터링 로그정보를 신규로 등록한다.
	 * @param serverResrceMntrng - 서버자원모니터링 model
	 */
	void insertServerResrceMntrng(ServerResrceMntrng serverResrceMntrng);

	/**
	 * 서버자원모니터링 대상서버의 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return List - 서버자원모니터링 대상서버 목록
	 */
	List<ServerResrceMntrngVO> selectMntrngServerList(ServerResrceMntrngVO serverResrceMntrngVO);

	/**
	 * 서버자원모니터링 대상서버 목록 총 개수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링의 로그 카운트 수
	 */
	int selectMntrngServerListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO);

}
