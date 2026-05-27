package egovframework.com.ssi.syi.iis.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.ssi.syi.iis.service.CntcInstt;
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcService;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystem;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;

/**
 * 연계기관에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CntcInsttDAO")
public class CntcInsttDAO {

	private final CntcInsttMapper cntcInsttMapper;

	public CntcInsttDAO(CntcInsttMapper cntcInsttMapper) {
		this.cntcInsttMapper = cntcInsttMapper;
	}

	/**
	 * 연계기관을 삭제한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	public void deleteCntcInstt(CntcInstt cntcInstt) throws Exception {
		cntcInsttMapper.deleteCntcInstt(cntcInstt);
	}

	/**
	 * 연계시스템을 삭제한다.
	 * @param cntcSystem
	 * @throws Exception
	 */
	public void deleteCntcSystem(CntcSystem cntcSystem) throws Exception {
		cntcInsttMapper.deleteCntcSystem(cntcSystem);
	}

	/**
	 * 연계서비스를 삭제한다.
	 * @param cntcService
	 * @throws Exception
	 */
	public void deleteCntcService(CntcService cntcService) throws Exception {
		cntcInsttMapper.deleteCntcService(cntcService);
	}

	/**
	 * 연계기관을 등록한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	public void insertCntcInstt(CntcInstt cntcInstt) throws Exception {
		cntcInsttMapper.insertCntcInstt(cntcInstt);
	}

	/**
	 * 연계시스템을 등록한다.
	 * @param cntcSystem
	 * @throws Exception
	 */
	public void insertCntcSystem(CntcSystem cntcSystem) throws Exception {
		cntcInsttMapper.insertCntcSystem(cntcSystem);
	}

	/**
	 * 연계서비스를 등록한다.
	 * @param cntcService
	 * @throws Exception
	 */
	public void insertCntcService(CntcService cntcService) throws Exception {
		cntcInsttMapper.insertCntcService(cntcService);
	}

	/**
	 * 연계기관 상세항목을 조회한다.
	 * @param cntcInstt
	 * @return CntcInstt(연계기관)
	 */
	public CntcInstt selectCntcInsttDetail(CntcInstt cntcInstt) throws Exception {
		return cntcInsttMapper.selectCntcInsttDetail(cntcInstt);
	}

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * @param cntcSystem
	 * @return CntcSystem(연계시스템)
	 */
	public CntcSystem selectCntcSystemDetail(CntcSystem cntcSystem) throws Exception {
		return cntcInsttMapper.selectCntcSystemDetail(cntcSystem);
	}

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * @param cntcService
	 * @return CntcService(연계서비스)
	 */
	public CntcService selectCntcServiceDetail(CntcService cntcService) throws Exception {
		return cntcInsttMapper.selectCntcServiceDetail(cntcService);
	}

	/**
	 * 연계기관 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계기관 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcInsttList(CntcInsttVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcInsttList(searchVO);
	}

	/**
	 * 연계기관 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계기관 총 개수)
	 */
	public int selectCntcInsttListTotCnt(CntcInsttVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcInsttListTotCnt(searchVO);
	}

	/**
	 * 연계시스템 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계시스템 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcSystemList(CntcSystemVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcSystemList(searchVO);
	}

	/**
	 * 연계시스템 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계시스템 총 개수)
	 */
	public int selectCntcSystemListTotCnt(CntcSystemVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcSystemListTotCnt(searchVO);
	}

	/**
	 * 연계서비스 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계서비스 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcServiceList(CntcServiceVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcServiceList(searchVO);
	}

	/**
	 * 연계서비스 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계서비스 총 개수)
	 */
	public int selectCntcServiceListTotCnt(CntcServiceVO searchVO) throws Exception {
		return cntcInsttMapper.selectCntcServiceListTotCnt(searchVO);
	}

	/**
	 * 연계기관을 수정한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	public void updateCntcInstt(CntcInstt cntcInstt) throws Exception {
		cntcInsttMapper.updateCntcInstt(cntcInstt);
	}

	/**
	 * 연계시스템을 수정한다.
	 * @param cntcSystem
	 * @throws Exception
	 */
	public void updateCntcSystem(CntcSystem cntcSystem) throws Exception {
		cntcInsttMapper.updateCntcSystem(cntcSystem);
	}

	/**
	 * 연계서비스를 수정한다.
	 * @param cntcService
	 * @throws Exception
	 */
	public void updateCntcService(CntcService cntcService) throws Exception {
		cntcInsttMapper.updateCntcService(cntcService);
	}

}
