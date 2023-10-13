package egovframework.com.sym.cal.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * 휴일에 관한 서비스 인터페이스 클래스를 정의한다
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
public interface EgovCalRestdeManageService {

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * @param restde
	 * @return List(일반달력 팝업 날짜정보)
	 * @throws Exception
	 */
	List<EgovMap> selectNormalRestdePopup(Restde restde)	throws Exception;

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * @param restde
	 * @return List(행정달력 팝업 날짜정보)
	 * @throws Exception
	 */
	List<EgovMap> selectAdministRestdePopup(Restde restde)	throws Exception;

	/**
	 * 일반달력 일간 정보를 조회한다.
	 * @param restde
	 * @return List(일반달력 일간 날짜정보)
	 * @throws Exception
	 */
	List<EgovMap> selectNormalDayCal(Restde restde)	throws Exception;

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 * @param restde
	 * @return List(일반달력 일간 휴일정보)
	 * @throws Exception
	 */
	List<EgovMap> selectNormalDayRestde(Restde restde)	throws Exception;

	/**
	 * 일반달력 월간 휴일을 조회한다.
	 * @param restde
	 * @return List(일반달력 월간 휴일정보)
	 * @throws Exception
	 */
	List<EgovMap> selectNormalMonthRestde(Restde restde)	throws Exception;

	/**
	 * 행정달력 일간 정보를 조회한다.
	 * @param restde
	 * @return List(행정달력 일간 날짜정보)
	 * @throws Exception
	 */
	List<EgovMap> selectAdministDayCal(Restde restde)	throws Exception;

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 * @param restde
	 * @return List(행정달력 일간 휴일정보)
	 * @throws Exception
	 */
	List<EgovMap> selectAdministDayRestde(Restde restde)	throws Exception;

	/**
	 * 행정달력 월간 휴일을 조회한다.
	 * @param restde
	 * @return List(행정달력 월간 휴일정보)
	 * @throws Exception
	 */
	List<?> selectAdministMonthRestde(Restde restde)	throws Exception;

	/**
	 * 휴일을 삭제한다.
	 * @param restde
	 * @throws Exception
	 */
	void deleteRestde(Restde restde) throws Exception;

	/**
	 * 휴일을 등록한다.
	 * @param restde
	 * @throws Exception
	 */
	void insertRestde(Restde restde) throws Exception;

	/**
	 * 휴일 상세항목을 조회한다.
	 * @param restde
	 * @return Restde(휴일)
	 * @throws Exception
	 */
	Restde selectRestdeDetail(Restde restde) throws Exception;

	/**
	 * 휴일 목록을 조회한다.
	 * @param searchVO
	 * @return List(휴일 목록)
	 * @throws Exception
	 */
	List<?> selectRestdeList(RestdeVO searchVO) throws Exception;

    /**
     * 휴일 총 개수를 조회한다.
     * @param searchVO
     * @return int(휴일 총 개수)
     */
    int selectRestdeListTotCnt(RestdeVO searchVO) throws Exception;

	/**
	 * 휴일을 수정한다.
	 * @param restde
	 * @throws Exception
	 */
	void updateRestde(Restde restde) throws Exception;

}
