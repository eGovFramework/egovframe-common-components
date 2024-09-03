package egovframework.com.cop.ncm.service;

import java.util.Map;

/**
 * 명함정보를 관리하기 위한 서비스 인터페이스 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.28  이삼섭          최초 생성
 *   2024.09.04  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovNcrdManageService {

	/**
	 * 명함 정보를 삭제한다.
	 * 
	 * @param nameCard
	 */

	public void deleteNcrdItem(NameCardVO namecardVO);

	/**
	 * 명함 정보 및 명함사용자 정보를 등록한다.
	 * 
	 * @param nameCard
	 */
	public void insertNcrdItem(NameCard nameCard) throws Exception;

	/**
	 * 명함사용자 정보를 등록한다.
	 * 
	 * @param ncrdUser
	 */
	public void insertNcrdUseInf(NameCardUser ncrdUser);

	/**
	 * 명함 정보에 대한 상세정보를 조회한다.
	 * 
	 * @param nameCard
	 * @return
	 */
	public NameCardVO selectNcrdItem(NameCardVO ncrdVO);

	/**
	 * 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCard
	 * @return
	 */
	public Map<String, Object> selectNcrdItems(NameCardVO ncrdVO);

	/**
	 * 명함 정보에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param ncrdUser
	 * @return
	 */
	public Map<String, Object> selectNcrdUseInfs(NameCardUser ncrdUser);

	/**
	 * 명함 정보를 수정한다.
	 * 
	 * @param nameCard
	 */
	public void updateNcrdItem(NameCard nameCard);

	/**
	 * 명함사용자 정보를 수정한다.
	 * 
	 * @param ncrdUser
	 */
	public void updateNcrdUseInf(NameCardUser ncrdUser);

	/**
	 * 내 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param ncrdVO
	 * @return
	 */
	public Map<String, Object> selectMyNcrdItems(NameCardVO ncrdVO);

}
