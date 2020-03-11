package egovframework.com.ssi.syi.iis.service;

import java.util.List;

/**
 *
 * 연계기관에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovCntcInsttService {

	/**
	 * 연계기관을 삭제한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void deleteCntcInstt(CntcInstt cntcInstt) throws Exception;

	/**
	 * 연계시스템을 삭제한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void deleteCntcSystem(CntcSystem cntcSystem) throws Exception;

	/**
	 * 연계서비스를 삭제한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void deleteCntcService(CntcService cntcService) throws Exception;

	/**
	 * 연계기관을 등록한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void insertCntcInstt(CntcInstt cntcInstt) throws Exception;

	/**
	 * 연계시스템을 등록한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void insertCntcSystem(CntcSystem cntcSystem) throws Exception;

	/**
	 * 연계서비스를 등록한다.
	 * @param cntcService
	 * @throws Exception
	 */
	void insertCntcService(CntcService cntcService) throws Exception;

	/**
	 * 연계기관 상세항목을 조회한다.
	 * @param cntcInstt
	 * @return CntcInstt(연계기관)
	 * @throws Exception
	 */
	CntcInstt selectCntcInsttDetail(CntcInstt cntcInstt) throws Exception;

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * @param cntcInstt
	 * @return CntcInstt(연계기관)
	 * @throws Exception
	 */
	CntcSystem selectCntcSystemDetail(CntcSystem cntcSystem) throws Exception;

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * @param cntcInstt
	 * @return CntcInstt(연계기관)
	 * @throws Exception
	 */
	CntcService selectCntcServiceDetail(CntcService cntcService) throws Exception;

	/**
	 * 연계기관 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계기관 목록)
	 * @throws Exception
	 */
	List<?> selectCntcInsttList(CntcInsttVO searchVO) throws Exception;

    /**
	 * 연계기관 총 갯수를 조회한다.
     * @param searchVO
     * @return int(연계기관 총 갯수)
     */
    int selectCntcInsttListTotCnt(CntcInsttVO searchVO) throws Exception;

	/**
	 * 연계시스템 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계시스템 목록)
	 * @throws Exception
	 */
	List<?> selectCntcSystemList(CntcSystemVO searchVO) throws Exception;

    /**
	 * 연계시스템 총 갯수를 조회한다.
     * @param searchVO
     * @return int(연계시스템 총 갯수)
     */
    int selectCntcSystemListTotCnt(CntcSystemVO searchVO) throws Exception;

	/**
	 * 연계서비스 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계시스템 목록)
	 * @throws Exception
	 */
	List<?> selectCntcServiceList(CntcServiceVO searchVO) throws Exception;

    /**
	 * 연계서비스 총 갯수를 조회한다.
     * @param searchVO
     * @return int(연계시스템 총 갯수)
     */
    int selectCntcServiceListTotCnt(CntcServiceVO searchVO) throws Exception;

    /**
	 * 연계기관을 수정한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void updateCntcInstt(CntcInstt cntcInstt) throws Exception;

	/**
	 * 연계시스템을 수정한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void updateCntcSystem(CntcSystem cntcSystem) throws Exception;

	/**
	 * 연계서비스 수정한다.
	 * @param cntcInstt
	 * @throws Exception
	 */
	void updateCntcService(CntcService cntcService) throws Exception;

}
