package egovframework.com.sec.ram.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;

/**
 * 권한관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2024.08.06  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */

@Repository("authorManageDAO")
public class AuthorManageDAO extends EgovComAbstractDAO {

	/**
	 * 권한목록을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 */
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) {
		return selectList("authorManageDAO.selectAuthorList", authorManageVO);
	}

	/**
	 * 권한을 등록한다.
	 * 
	 * @param authorManage AuthorManage
	 */
	public void insertAuthor(AuthorManage authorManage) {
		insert("authorManageDAO.insertAuthor", authorManage);
	}

	/**
	 * 권한을 수정한다.
	 * 
	 * @param authorManage AuthorManage
	 */
	public void updateAuthor(AuthorManage authorManage) {
		update("authorManageDAO.updateAuthor", authorManage);
	}

	/**
	 * 권한을 삭제한다.
	 * 
	 * @param authorManage AuthorManage
	 */
	public void deleteAuthor(AuthorManage authorManage) {
		delete("authorManageDAO.deleteAuthor", authorManage);
	}

	/**
	 * 권한을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 */
	public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) {
		return (AuthorManageVO) selectOne("authorManageDAO.selectAuthor", authorManageVO);
	}

	/**
	 * 권한목록 총 개수를 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 */
	public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) {
		return (Integer) selectOne("authorManageDAO.selectAuthorListTotCnt", authorManageVO);
	}

	/**
	 * 모든 권한목록을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 */
	public List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) {
		return selectList("authorManageDAO.selectAuthorAllList", authorManageVO);
	}
}
