package egovframework.com.sec.rgm.service.impl;

import java.util.List;

import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.AuthorGroupVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 권한그룹에 관한 ServiceImpl 클래스를 정의한다.
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
 *
 * </pre>
 */

@Service("egovAuthorGroupService")
public class EgovAuthorGroupServiceImpl  extends EgovAbstractServiceImpl implements EgovAuthorGroupService {
	
	@Resource(name="authorGroupDAO")
    private AuthorGroupDAO authorGroupDAO;

	/**
	 * 그룹별 할당된 권한 목록 조회
	 * @param authorGroupVO AuthorGroupVO
	 * @return List<AuthorGroupVO>
	 * @exception Exception
	 */
	public List<AuthorGroupVO> selectAuthorGroupList(AuthorGroupVO authorGroupVO) throws Exception{
		return authorGroupDAO.selectAuthorGroupList(authorGroupVO);
	}
	
	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void insertAuthorGroup(AuthorGroup authorGroup) throws Exception{
		authorGroupDAO.insertAuthorGroup(authorGroup);
	}
	
	/**
	 * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void updateAuthorGroup(AuthorGroup authorGroup) throws Exception{
		authorGroupDAO.updateAuthorGroup(authorGroup);
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	public void deleteAuthorGroup(AuthorGroup authorGroup) throws Exception {
		authorGroupDAO.deleteAuthorGroup(authorGroup);
	}
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorGroupVO AuthorGroupVO
	 * @return int
	 * @exception Exception
	 */
	public int selectAuthorGroupListTotCnt(AuthorGroupVO authorGroupVO) throws Exception {
		return authorGroupDAO.selectAuthorGroupListTotCnt(authorGroupVO);
    }
	
}