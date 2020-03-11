package egovframework.com.uss.ion.rsm.service;

import java.util.List;

/**
 * 최근검색어를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovRecentSrchwrdService {

	/**
	* 최근검색어관리 목록을 조회한다.
	* @param searchVO  조회할 정보가 담긴 VO
	* @return List
	* @throws Exception
	*/
	public List<?> selectRecentSrchwrdList(RecentSrchwrd searchVO) throws Exception;

	/**
	* 최근검색어관리를(을) 목록 전체 건수를(을) 조회한다.
	* @param searchVO  조회할 정보가 담긴 VO
	* @return int
	* @throws Exception
	*/
	public int selectRecentSrchwrdListCnt(RecentSrchwrd searchVO) throws Exception;

	/**
	 * 최근검색어관리를(을) 상세조회 한다.
	 * @param RecentSrchwrd  최근검색어관리 정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public RecentSrchwrd selectRecentSrchwrdDetail(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	* 최근검색어관리를(을) 등록한다.
	* @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	* @throws Exception
	*/
	void insertRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	* 최근검색어관리를(을) 수정한다.
	* @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	* @throws Exception
	*/
	void updateRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	 * 최근검색어관리를(을) 삭제한다.
	 * @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	 * @throws Exception
	 */
	void deleteRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	 * 최근검색어결과 목록을 조회한다.
	 * @param searchVO  조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectRecentSrchwrdResultInquire(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	 * 최근검색어결과 목록을 조회한다.
	 * @param searchVO  조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectRecentSrchwrdResultList(RecentSrchwrd searchVO) throws Exception;

	/**
	 * 최근검색어결과를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO  조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectRecentSrchwrdResultListCnt(RecentSrchwrd searchVO) throws Exception;

	/**
	 * 최근검색어결과를(을) 등록한다.
	 * @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	 * @throws Exception
	 */
	void insertRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	 * 최근검색어결과를(을) 건별로 삭제 한다.
	 * @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	 * @throws Exception
	 */
	void deleteRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) throws Exception;

	/**
	 * 최근검색어결과를(을) 관리별로 삭제 한다.
	 * @param recentSrchwrd  최근검색어관리 정보가 담김 VO
	 * @throws Exception
	 */
	void deleteRecentSrchwrdResultAll(RecentSrchwrd recentSrchwrd) throws Exception;

}
