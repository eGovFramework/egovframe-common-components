package egovframework.com.sym.ccm.adc.service;

import java.util.List;

/**
 *
 * 행정코드에 관한 서비스 인터페이스 클래스를 정의한다
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
 * </pre>
 */
public interface EgovCcmAdministCodeManageService {

	/**
	 * 행정코드를 삭제한다.
	 * @param administCode
	 * @throws Exception
	 */
	void deleteAdministCode(AdministCode administCode) throws Exception;

	/**
	 * 행정코드를 등록한다.
	 * @param administCode
	 * @throws Exception
	 */
	void insertAdministCode(AdministCode administCode) throws Exception;

	/**
	 * 행정코드 상세항목을 조회한다.
	 * @param administCode
	 * @return AdministCode(행정코드)
	 * @throws Exception
	 */
	AdministCode selectAdministCodeDetail(AdministCode administCode) throws Exception;

	/**
	 * 행정코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(행정코드 목록)
	 * @throws Exception
	 */
	List<?> selectAdministCodeList(AdministCodeVO searchVO) throws Exception;

    /**
	 * 행정코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(행정코드 총 갯수)
     */
    int selectAdministCodeListTotCnt(AdministCodeVO searchVO) throws Exception;

	/**
	 * 행정코드를 수정한다.
	 * @param administCode
	 * @throws Exception
	 */
	void updateAdministCode(AdministCode administCode) throws Exception;

}
