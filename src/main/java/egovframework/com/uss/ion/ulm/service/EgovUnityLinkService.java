package egovframework.com.uss.ion.ulm.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 통합링크관리를 처리하는 Service Class 구현
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
public interface EgovUnityLinkService {

	/**
	 * 통합링크관리 메인 셈플 목록을 조회한다.
	 * @param unityLink  통합링크관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectUnityLinkSample(UnityLink unityLink) throws Exception;

	/**
	* 통합링크관리 목록을 조회한다.
	* @param searchVO  조회할 정보가 담긴 VO
	* @return List
	* @throws Exception
	*/
	public List<?> selectUnityLinkList(ComDefaultVO searchVO) throws Exception;

	/**
	* 통합링크관리를(을) 목록 전체 건수를(을) 조회한다.
	* @param searchVO  조회할 정보가 담긴 VO
	* @return int
	* @throws Exception
	*/
	public int selectUnityLinkListCnt(ComDefaultVO searchVO) throws Exception;

	/**
	 * 통합링크관리를(을) 상세조회 한다.
	 * @param unityLink  통합링크관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public UnityLink selectUnityLinkDetail(UnityLink unityLink) throws Exception;

	/**
	* 통합링크관리를(을) 등록한다.
	* @param unityLink  통합링크관리 정보 담김 VO
	* @throws Exception
	*/
	void insertUnityLink(UnityLink unityLink) throws Exception;

	/**
	* 통합링크관리를(을) 수정한다.
	* @param unityLink  통합링크관리 정보 담김 VO
	* @throws Exception
	*/
	void updateUnityLink(UnityLink unityLink) throws Exception;

	/**
	 * 통합링크관리를(을) 삭제한다.
	 * @param unityLink  통합링크관리 정보 담김 VO
	 * @throws Exception
	 */
	void deleteUnityLink(UnityLink unityLink) throws Exception;

}
