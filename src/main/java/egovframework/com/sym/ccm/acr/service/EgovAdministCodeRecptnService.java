package egovframework.com.sym.ccm.acr.service;

import java.util.List;

/**
 *
 * 법정동코드에 관한 서비스 인터페이스 클래스를 정의한다
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
public interface EgovAdministCodeRecptnService {

	/**
	 * 법정동코드수신을 처리한다.
	 * @param administCode
	 * @throws Exception
	 */
	void insertAdministCodeRecptn() throws Exception;

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * @param administCode
	 * @return AdministCode(법정동코드)
	 * @throws Exception
	 */
	AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn) throws Exception;

	/**
	 * 법정동코드수신 목록을 조회한다.
	 * @param searchVO
	 * @return List(법정동코드 목록)
	 * @throws Exception
	 */
	List<?> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO) throws Exception;

    /**
	 * 법정동코드수신 총 갯수를 조회한다.
     * @param searchVO
     * @return int(법정동코드 총 갯수)
     */
    int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) throws Exception;

	/**
	 * 법정동코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(법정동코드 목록)
	 * @throws Exception
	 */
	List<?> selectAdministCodeList(AdministCodeRecptnVO searchVO) throws Exception;

    /**
	 * 법정동코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(법정동코드 총 갯수)
     */
    int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO) throws Exception;
}
