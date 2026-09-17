package egovframework.com.sec.drm.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.sec.drm.service.DeptAuthor;
import egovframework.com.sec.drm.service.DeptAuthorVO;
import egovframework.com.sec.drm.service.EgovDeptAuthorService;
import jakarta.annotation.Resource;

/**
 * 부서권한에 관한 ServiceImpl 클래스를 정의한다.
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

@Service("egovDeptAuthorService")
public class EgovDeptAuthorServiceImpl extends EgovAbstractServiceImpl implements EgovDeptAuthorService {

	@Resource(name="deptAuthorDAO")
    private DeptAuthorDAO deptAuthorDAO;

	/**
	 * 부서별 할당된 권한목록 조회
	 * @param deptAuthorVO DeptAuthorVO
	 * @return List<DeptAuthorVO>
	 * @exception Exception
	 */
	@Override
	public List<DeptAuthorVO> selectDeptAuthorList(DeptAuthorVO deptAuthorVO) throws Exception {
		return deptAuthorDAO.selectDeptAuthorList(deptAuthorVO);
	}

	/**
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * @param deptAuthor DeptAuthor
	 * @exception Exception
	 */
	@Override
	public void insertDeptAuthor(DeptAuthor deptAuthor) throws Exception {
		deptAuthorDAO.insertDeptAuthor(deptAuthor);
	}

	/**
	 * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param deptAuthor DeptAuthor
	 * @exception Exception
	 */
	@Override
	public void updateDeptAuthor(DeptAuthor deptAuthor) throws Exception {
		deptAuthorDAO.updateDeptAuthor(deptAuthor);
	}

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * @param deptAuthor DeptAuthor
	 * @exception Exception
	 */
	@Override
	public void deleteDeptAuthor(DeptAuthor deptAuthor) throws Exception {
		deptAuthorDAO.deleteDeptAuthor(deptAuthor);
	}

	/**
	 * 여러 사용자에 대한 부서 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 이 메서드 진입 시점에 한 번만 트랜잭션이 걸리므로(context-transaction.xml 의
	 * *Impl 메서드 대상 AOP), 항목 중간에 예외가 나면 목록 전체가 롤백된다.
	 * @param deptAuthor DeptAuthor
	 * @param userIds 처리할 사용자 ID 배열
	 * @param authorCodes userIds 와 같은 순서의 권한코드 배열
	 * @param regYns userIds 와 같은 순서의 배정여부 배열
	 * @exception Exception
	 */
	@Override
	public void updateDeptAuthorList(DeptAuthor deptAuthor, String[] userIds, String[] authorCodes, String[] regYns) throws Exception {
		for (int i = 0; i < userIds.length; i++) {
			deptAuthor.setUniqId(userIds[i]);
			deptAuthor.setAuthorCode(authorCodes[i]);
			if ("N".equals(regYns[i])) {
				insertDeptAuthor(deptAuthor);
			} else {
				updateDeptAuthor(deptAuthor);
			}
		}
	}

	/**
	 * 여러 사용자에 대한 부서 권한을 일괄 삭제한다. 위와 동일한 이유로 목록 전체가 한 트랜잭션이다.
	 * @param deptAuthor DeptAuthor
	 * @param userIds 삭제할 사용자 ID 배열
	 * @exception Exception
	 */
	@Override
	public void deleteDeptAuthorList(DeptAuthor deptAuthor, String[] userIds) throws Exception {
		for (String userId : userIds) {
			deptAuthor.setUniqId(userId);
			deleteDeptAuthor(deptAuthor);
		}
	}

    /**
	 * 부서권한 목록조회 카운트를 반환한다
	 * @param deptAuthorVO DeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectDeptAuthorListTotCnt(DeptAuthorVO deptAuthorVO) throws Exception {
		return deptAuthorDAO.selectDeptAuthorListTotCnt(deptAuthorVO);
	}

	/**
	 * 부서목록 조회
	 * @param deptAuthorVO DeptAuthorVO
	 * @return List<DeptAuthorVO>
	 * @exception Exception
	 */
	@Override
	public List<DeptAuthorVO> selectDeptList(DeptAuthorVO deptAuthorVO) throws Exception {
		return deptAuthorDAO.selectDeptList(deptAuthorVO);
	}

    /**
	 * 부서 목록조회 카운트를 반환한다
	 * @param deptAuthorVO DeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectDeptListTotCnt(DeptAuthorVO deptAuthorVO) throws Exception {
		return deptAuthorDAO.selectDeptListTotCnt(deptAuthorVO);
	}
}