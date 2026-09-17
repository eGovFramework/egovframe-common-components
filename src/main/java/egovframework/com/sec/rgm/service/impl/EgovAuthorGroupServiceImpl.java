package egovframework.com.sec.rgm.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.AuthorGroupVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import jakarta.annotation.Resource;

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
 *   2024.10.29	LeeBaekHaeng	@Override 표기
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
	@Override
	public List<AuthorGroupVO> selectAuthorGroupList(AuthorGroupVO authorGroupVO) throws Exception{
		return authorGroupDAO.selectAuthorGroupList(authorGroupVO);
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	@Override
	public void insertAuthorGroup(AuthorGroup authorGroup) throws Exception{
		authorGroupDAO.insertAuthorGroup(authorGroup);
	}

	/**
	 * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	@Override
	public void updateAuthorGroup(AuthorGroup authorGroup) throws Exception{
		authorGroupDAO.updateAuthorGroup(authorGroup);
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param authorGroup AuthorGroup
	 * @exception Exception
	 */
	@Override
	public void deleteAuthorGroup(AuthorGroup authorGroup) throws Exception {
		authorGroupDAO.deleteAuthorGroup(authorGroup);
	}

	/**
	 * 여러 사용자에 대한 그룹 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 이 메서드 진입 시점에 한 번만 트랜잭션이 걸리므로(context-transaction.xml 의
	 * *Impl 메서드 대상 AOP), 항목 중간에 예외가 나면 목록 전체가 롤백된다.
	 * @param authorGroup AuthorGroup
	 * @param userIds 처리할 사용자 ID 배열
	 * @param authorCodes userIds 와 같은 순서의 권한코드 배열
	 * @param mberTyCodes userIds 와 같은 순서의 회원유형코드 배열
	 * @param regYns userIds 와 같은 순서의 배정여부 배열
	 * @exception Exception
	 */
	@Override
	public void updateAuthorGroupList(AuthorGroup authorGroup, String[] userIds, String[] authorCodes, String[] mberTyCodes, String[] regYns) throws Exception {
		for (int i = 0; i < userIds.length; i++) {
			authorGroup.setUniqId(userIds[i]);
			authorGroup.setAuthorCode(authorCodes[i]);
			authorGroup.setMberTyCode(mberTyCodes[i]);
			if ("N".equals(regYns[i])) {
				insertAuthorGroup(authorGroup);
			} else {
				updateAuthorGroup(authorGroup);
			}
		}
	}

	/**
	 * 여러 사용자에 대한 그룹 권한을 일괄 삭제한다. 위와 동일한 이유로 목록 전체가 한 트랜잭션이다.
	 * @param authorGroup AuthorGroup
	 * @param userIds 삭제할 사용자 ID 배열
	 * @exception Exception
	 */
	@Override
	public void deleteAuthorGroupList(AuthorGroup authorGroup, String[] userIds) throws Exception {
		for (String userId : userIds) {
			authorGroup.setUniqId(userId);
			deleteAuthorGroup(authorGroup);
		}
	}

    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorGroupVO AuthorGroupVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectAuthorGroupListTotCnt(AuthorGroupVO authorGroupVO) throws Exception {
		return authorGroupDAO.selectAuthorGroupListTotCnt(authorGroupVO);
    }

}