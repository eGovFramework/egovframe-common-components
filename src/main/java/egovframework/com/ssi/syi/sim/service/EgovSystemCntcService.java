package egovframework.com.ssi.syi.sim.service;

import java.util.List;

/**
 *
 * 시스템연계에 관한 서비스 인터페이스 클래스를 정의한다
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
public interface EgovSystemCntcService {

	/**
	 * 시스템연계를 삭제한다.
	 * @param systemCntc
	 * @throws Exception
	 */
	void deleteSystemCntc(SystemCntc systemCntc) throws Exception;

	/**
	 * 시스템연계를 등록한다.
	 * @param systemCntc
	 * @throws Exception
	 */
	void insertSystemCntc(SystemCntc systemCntc) throws Exception;

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * @param systemCntc
	 * @return SystemCntc(시스템연계)
	 * @throws Exception
	 */
	SystemCntc selectSystemCntcDetail(SystemCntc systemCntc) throws Exception;

	/**
	 * 시스템연계 승인/승인취소한다.
	 * @param systemCntc
	 * @return SystemCntc(시스템연계)
	 * @throws Exception
	 */
	void confirmSystemCntc(SystemCntc systemCntc) throws Exception;

	/**
	 * 시스템연계 목록을 조회한다.
	 * @param searchVO
	 * @return List(시스템연계 목록)
	 * @throws Exception
	 */
	List<?> selectSystemCntcList(SystemCntcVO searchVO) throws Exception;

    /**
	 * 시스템연계 총 갯수를 조회한다.
     * @param searchVO
     * @return int(시스템연계 총 갯수)
     */
    int selectSystemCntcListTotCnt(SystemCntcVO searchVO) throws Exception;

	/**
	 * 시스템연계를 수정한다.
	 * @param systemCntc
	 * @throws Exception
	 */
	void updateSystemCntc(SystemCntc systemCntc) throws Exception;

}
