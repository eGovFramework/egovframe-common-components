package egovframework.com.sec.ram.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;
import egovframework.com.sec.ram.service.EgovAuthorRoleManageService;
import jakarta.annotation.Resource;

/**
 * 권한별 롤관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자              수정내용
 *  ----------   ---------   ---------------------------
 *  2009.03.11   이문준              최초 생성
 *  2021.02-09   신용호              updateAuthorRole 삭제
 *
 * </pre>
 */

@Service("egovAuthorRoleManageService")
public class EgovAuthorRoleManageServiceImpl extends EgovAbstractServiceImpl implements EgovAuthorRoleManageService {

	@Resource(name="authorRoleManageDAO")
    private AuthorRoleManageDAO authorRoleManageDAO;

	/**
	 * 권한 롤 관계정보 목록 조회
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return List<AuthorRoleManageVO>
	 * @exception Exception
	 */
	@Override
	public List<AuthorRoleManageVO> selectAuthorRoleList(AuthorRoleManageVO authorRoleManageVO) throws Exception {
		return authorRoleManageDAO.selectAuthorRoleList(authorRoleManageVO);
	}

	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param authorRoleManage AuthorRoleManage
	 * @exception Exception
	 */
	@Override
	public void insertAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
		authorRoleManageDAO.insertAuthorRole(authorRoleManage);
	}

	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param authorRoleManage AuthorRoleManage
	 * @exception Exception
	 */
	@Override
	public void deleteAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
		authorRoleManageDAO.deleteAuthorRole(authorRoleManage);
	}

	/**
	 * 여러 롤코드에 대한 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 이 메서드 진입 시점에 한 번만 트랜잭션이 걸리므로(context-transaction.xml 의
	 * *Impl 메서드 대상 AOP), 항목 중간에 예외가 나면 목록 전체가 롤백된다.
	 * @param authorRoleManage AuthorRoleManage
	 * @param roleCodes 처리할 롤코드 배열
	 * @param regYns roleCodes 와 같은 순서의 배정여부(Y/N) 배열
	 * @exception Exception
	 */
	@Override
	public void updateAuthorRoleList(AuthorRoleManage authorRoleManage, String[] roleCodes, String[] regYns) throws Exception {
		for (int i = 0; i < roleCodes.length; i++) {
			authorRoleManage.setRoleCode(roleCodes[i]);
			authorRoleManage.setRegYn(regYns[i]);
			deleteAuthorRole(authorRoleManage);
			if ("Y".equals(regYns[i])) {
				insertAuthorRole(authorRoleManage);
			}
		}
	}

    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectAuthorRoleListTotCnt(AuthorRoleManageVO authorRoleManageVO) throws Exception {
		return authorRoleManageDAO.selectAuthorRoleListTotCnt(authorRoleManageVO);
	}
}