package egovframework.com.sym.ccm.icr.service;

import java.util.List;

/**
 *
 * 기관코드에 관한 서비스 인터페이스 클래스를 정의한다
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
public interface EgovInsttCodeRecptnService {

	/**
	 * 기관코드수신을 처리한다.
	 * @param insttCode
	 * @throws Exception
	 */
	void insertInsttCodeRecptn() throws Exception;

	/**
	 * 기관코드 상세내역을 조회한다.
	 * @param insttCode
	 * @return InsttCode(기관코드)
	 * @throws Exception
	 */
	InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn) throws Exception;

	/**
	 * 기관코드수신 목록을 조회한다.
	 * @param searchVO
	 * @return List(기관코드 목록)
	 * @throws Exception
	 */
	List<?> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO) throws Exception;

    /**
	 * 기관코드수신 총 갯수를 조회한다.
     * @param searchVO
     * @return int(기관코드 총 갯수)
     */
    int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO) throws Exception;

	/**
	 * 기관코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(기관코드 목록)
	 * @throws Exception
	 */
	List<?> selectInsttCodeList(InsttCodeRecptnVO searchVO) throws Exception;

    /**
	 * 기관코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(기관코드 총 갯수)
     */
    int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO) throws Exception;
}
