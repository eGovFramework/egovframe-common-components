package egovframework.com.sec.rgm.service;

import java.util.List;


/**
 * 권한그룹에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public interface EgovAuthorGroupService {

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void deleteAuthorGroup(AuthorGroup authorGroup) throws Exception;

	/**
	 * 여러 사용자에 대한 그룹 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 항목마다 등록(regYn=N) 또는 수정(그 외)을 수행하며, 전체 목록을 하나의 트랜잭션으로 처리한다.
	 * @param authorGroup AuthorGroup(공통 필드가 채워진 커맨드 객체)
	 * @param userIds 처리할 사용자 ID 배열
	 * @param authorCodes userIds 와 같은 순서의 권한코드 배열
	 * @param mberTyCodes userIds 와 같은 순서의 회원유형코드 배열
	 * @param regYns userIds 와 같은 순서의 배정여부 배열
	 * @exception Exception
	 */
	public void updateAuthorGroupList(AuthorGroup authorGroup, String[] userIds, String[] authorCodes, String[] mberTyCodes, String[] regYns) throws Exception;

	/**
	 * 여러 사용자에 대한 그룹 권한을 일괄 삭제한다. 전체 목록을 하나의 트랜잭션으로 처리한다.
	 * @param authorGroup AuthorGroup(공통 필드가 채워진 커맨드 객체)
	 * @param userIds 삭제할 사용자 ID 배열
	 * @exception Exception
	 */
	public void deleteAuthorGroupList(AuthorGroup authorGroup, String[] userIds) throws Exception;

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void insertAuthorGroup(AuthorGroup authorGroup) throws Exception;

	/**
	 * 그룹별 할당된 권한 목록 조회
	 * @param authorGroupVO AuthorGroupVO
	 * @return List<AuthorGroupVO>
	 * @exception Exception
	 */
	public List<AuthorGroupVO> selectAuthorGroupList(AuthorGroupVO authorGroupVO) throws Exception;

	/**
	 * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void updateAuthorGroup(AuthorGroup authorGroup) throws Exception;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorGroupVO AuthorGroupVO
	 * @return int
	 * @exception Exception
	 */
	public int selectAuthorGroupListTotCnt(AuthorGroupVO authorGroupVO) throws Exception;		

}