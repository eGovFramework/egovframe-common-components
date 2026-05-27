package egovframework.com.ssi.syi.iis.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.ssi.syi.iis.service.CntcInstt;
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcService;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystem;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;

/**
 * 연계기관에 대한 매퍼 인터페이스를 정의한다
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("CntcInsttMapper")
public interface CntcInsttMapper {

	/**
	 * 연계기관을 삭제한다.
	 * @param cntcInstt
	 */
	void deleteCntcInstt(CntcInstt cntcInstt);

	/**
	 * 연계시스템을 삭제한다.
	 * @param cntcSystem
	 */
	void deleteCntcSystem(CntcSystem cntcSystem);

	/**
	 * 연계서비스를 삭제한다.
	 * @param cntcService
	 */
	void deleteCntcService(CntcService cntcService);

	/**
	 * 연계기관을 등록한다.
	 * @param cntcInstt
	 */
	void insertCntcInstt(CntcInstt cntcInstt);

	/**
	 * 연계시스템을 등록한다.
	 * @param cntcSystem
	 */
	void insertCntcSystem(CntcSystem cntcSystem);

	/**
	 * 연계서비스를 등록한다.
	 * @param cntcService
	 */
	void insertCntcService(CntcService cntcService);

	/**
	 * 연계기관 상세항목을 조회한다.
	 * @param cntcInstt
	 * @return CntcInstt(연계기관)
	 */
	CntcInstt selectCntcInsttDetail(CntcInstt cntcInstt);

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * @param cntcSystem
	 * @return CntcSystem(연계시스템)
	 */
	CntcSystem selectCntcSystemDetail(CntcSystem cntcSystem);

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * @param cntcService
	 * @return CntcService(연계서비스)
	 */
	CntcService selectCntcServiceDetail(CntcService cntcService);

	/**
	 * 연계기관 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계기관 목록)
	 */
	List<EgovMap> selectCntcInsttList(CntcInsttVO searchVO);

	/**
	 * 연계기관 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계기관 총 개수)
	 */
	int selectCntcInsttListTotCnt(CntcInsttVO searchVO);

	/**
	 * 연계시스템 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계시스템 목록)
	 */
	List<EgovMap> selectCntcSystemList(CntcSystemVO searchVO);

	/**
	 * 연계시스템 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계시스템 총 개수)
	 */
	int selectCntcSystemListTotCnt(CntcSystemVO searchVO);

	/**
	 * 연계서비스 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계서비스 목록)
	 */
	List<EgovMap> selectCntcServiceList(CntcServiceVO searchVO);

	/**
	 * 연계서비스 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계서비스 총 개수)
	 */
	int selectCntcServiceListTotCnt(CntcServiceVO searchVO);

	/**
	 * 연계기관을 수정한다.
	 * @param cntcInstt
	 */
	void updateCntcInstt(CntcInstt cntcInstt);

	/**
	 * 연계시스템을 수정한다.
	 * @param cntcSystem
	 */
	void updateCntcSystem(CntcSystem cntcSystem);

	/**
	 * 연계서비스를 수정한다.
	 * @param cntcService
	 */
	void updateCntcService(CntcService cntcService);

}
