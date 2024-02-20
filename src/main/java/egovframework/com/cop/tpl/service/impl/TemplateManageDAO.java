package egovframework.com.cop.tpl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.tpl.service.TemplateInf;
import egovframework.com.cop.tpl.service.TemplateInfVO;

/**
 * 템플릿 정보관리를 위한 데이터 접근 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.17   이삼섭           최초 생성
 *  2019.05.17   신용호           selectTemplateWhiteList() 추가
 *  2024.02.21   이백행           보안약점 조치: 부적절한 예외 처리 (광범위한 예외객체 선언)
 *
 *      </pre>
 */
@Repository("TemplateManageDAO")
public class TemplateManageDAO extends EgovComAbstractDAO {

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param tmplatInf @
	 */
	public void deleteTemplateInf(TemplateInf tmplatInf) {
		update("TemplateManageDAO.deleteTemplateInf", tmplatInf);
	}

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param tmplatInf @
	 */
	public void insertTemplateInf(TemplateInf tmplatInf) {
		insert("TemplateManageDAO.insertTemplateInf", tmplatInf);
	}

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param tmplatInf @
	 */
	public void updateTemplateInf(TemplateInf tmplatInf) {
		update("TemplateManageDAO.updateTemplateInf", tmplatInf);
	}

	/**
	 * 템플릿에 대한 화이트리스트 목록를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public List<TemplateInfVO> selectTemplateWhiteList() {
		return selectList("TemplateManageDAO.selectTemplateWhiteList");
	}

	/**
	 * 템플릿에 대한 목록를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public List<TemplateInfVO> selectTemplateInfs(TemplateInfVO tmplatInfVO) {
		return selectList("TemplateManageDAO.selectTemplateInfs", tmplatInfVO);
	}

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public int selectTemplateInfsCnt(TemplateInfVO tmplatInfVO) {
		return (Integer) selectOne("TemplateManageDAO.selectTemplateInfsCnt", tmplatInfVO);
	}

	/**
	 * 템플릿에 대한 상세정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public TemplateInfVO selectTemplateInf(TemplateInfVO tmplatInfVO) {
		return (TemplateInfVO) selectOne("TemplateManageDAO.selectTemplateInf", tmplatInfVO);

	}

	/**
	 * 템플릿에 대한 미리보기 정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public TemplateInfVO selectTemplatePreview(TemplateInfVO tmplatInfVO) {
		return null;
	}

	/**
	 * 템플릿 구분에 따른 목록을 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return @
	 */
	public List<TemplateInfVO> selectTemplateInfsByCode(TemplateInfVO tmplatInfVO) {
		return selectList("TemplateManageDAO.selectTemplateInfsByCode", tmplatInfVO);
	}

}
