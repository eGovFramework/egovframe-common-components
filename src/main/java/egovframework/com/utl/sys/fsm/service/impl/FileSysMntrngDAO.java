package egovframework.com.utl.sys.fsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.fsm.service.FileSysMntrng;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLog;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLogVO;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngVO;

/**
 * 개요
 * - 파일시스템 모니터링대상에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
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
@EgovMapper("FileSysMntrngDAO")
public interface FileSysMntrngDAO {

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상 목록을 불러온다.
	 * @param fileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return List - 파일시스템모니터링 대상 List
	 */
	List<FileSysMntrngVO> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상을 불러온다.
	 * @param fileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return FileSysMntrngVO - 파일시스템모니터링 대상 VO
	 */
	FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 대상 정보를 수정한다.
	 * @param fileSysMntrng - 파일시스템모니터링 대상 model
	 */
	void updateFileSysMntrng(FileSysMntrng fileSysMntrng);

	/**
	 * 파일시스템 모니터링 대상 정보를 등록한다.
	 * @param fileSysMntrng - 파일시스템모니터링 대상 model
	 */
	void insertFileSysMntrng(FileSysMntrng fileSysMntrng);

	/**
	 * 파일시스템 모니터링 대상 정보를 삭제한다.
	 * @param fileSysMntrng - 파일시스템모니터링 대상 model
	 */
	void deleteFileSysMntrng(FileSysMntrng fileSysMntrng);

	/**
	 * 파일시스템 모니터링대상 목록에 대한 전체 건수를 조회한다.
	 * @param fileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return int
	 */
	int selectFileSysMntrngListCnt(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 결과 정보를 수정한다.
	 * @param fileSysMntrng - 파일시스템모니터링 대상 model
	 */
	void updateFileSysMntrngSttus(FileSysMntrng fileSysMntrng);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그 목록을 불러온다.
	 * @param fileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * @return List - 파일시스템모니터링 로그 List
	 */
	List<FileSysMntrngLogVO> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링로그 목록에 대한 전체 건수를 조회한다.
	 * @param fileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * @return int
	 */
	int selectFileSysMntrngLogListCnt(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그를 불러온다.
	 * @param fileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * @return FileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 */
	FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링 로그 정보를 등록한다.
	 * @param fileSysMntrngLog - 파일시스템모니터링 로그 model
	 */
	void insertFileSysMntrngLog(FileSysMntrngLog fileSysMntrngLog);

}
