package egovframework.com.sec.ram.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;

/**
 * 권한관리에 대한 DAO 인터페이스를 정의한다.
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
 *   2009.03.11  이문준          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */

@EgovMapper("authorManageDAO")
public interface AuthorManageDAO {

    /**
	 * 권한목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 */
	List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO);

	/**
	 * 권한을 등록한다.
	 * @param authorManage AuthorManage
	 */
    void insertAuthor(AuthorManage authorManage);

    /**
	 * 권한을 수정한다.
	 * @param authorManage AuthorManage
	 */
    void updateAuthor(AuthorManage authorManage);

    /**
	 * 권한을 삭제한다.
	 * @param authorManage AuthorManage
	 */
    void deleteAuthor(AuthorManage authorManage);

    /**
	 * 권한을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 */
    AuthorManageVO selectAuthor(AuthorManageVO authorManageVO);

    /**
	 * 권한목록 총 개수를 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 */
    int selectAuthorListTotCnt(AuthorManageVO authorManageVO);

    /**
	 * 모든 권한목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 */
	List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO);
}
