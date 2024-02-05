package egovframework.com.sec.ram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import lombok.RequiredArgsConstructor;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
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
 *   2024.02.06  이백행          보안약점 조치: 부적절한 예외 처리 (광범위한 예외객체 선언)
 *
 *      </pre>
 */

@Service("egovAuthorManageService")
@RequiredArgsConstructor
public class EgovAuthorManageServiceImpl extends EgovAbstractServiceImpl implements EgovAuthorManageService {

	@Resource(name = "authorManageDAO")
	private AuthorManageDAO authorManageDAO;

	private final EgovMessageSource egovMessageSource;

	/**
	 * 권한 목록을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	@Override
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) {
		return authorManageDAO.selectAuthorList(authorManageVO);
	}

	/**
	 * 권한을 등록한다.
	 * 
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	@Override
	public void insertAuthor(AuthorManage authorManage) {
		authorManageDAO.insertAuthor(authorManage);
	}

	/**
	 * 권한을 수정한다.
	 * 
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	@Override
	public void updateAuthor(AuthorManage authorManage) {
		authorManageDAO.updateAuthor(authorManage);
	}

	/**
	 * 권한을 삭제한다.
	 * 
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	@Override
	public void deleteAuthor(AuthorManage authorManage) {
		authorManageDAO.deleteAuthor(authorManage);
	}

	/**
	 * 권한을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 * @exception Exception
	 */
	@Override
	public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) {
		AuthorManageVO resultVO = authorManageDAO.selectAuthor(authorManageVO);
		if (resultVO == null) {
//			throw new BaseRuntimeException(egovMessageSource, "info.nodata.msg");
			throw new BaseRuntimeException(egovMessageSource.getMessage("info.nodata.msg"));
		}
		return resultVO;
	}

	/**
	 * 권한 목록 카운트를 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) {
		return authorManageDAO.selectAuthorListTotCnt(authorManageVO);
	}

	/**
	 * 모든 권한목록을 조회한다.
	 * 
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	@Override
	public List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) {
		return authorManageDAO.selectAuthorAllList(authorManageVO);
	}
}
