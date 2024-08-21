package egovframework.com.cop.tpl.service;

import java.util.List;
import java.util.Map;

/**
 * 템플릿 관리를 위한 서비스 인터페이스 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자           수정내용
 *  ----------   --------   ---------------------------
 *   2009.05.17  이삼섭          최초 생성
 *   2019.05.17  신용호          selectTemplateWhiteList() 추가
 *   2024.08.22  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovTemplateManageService {

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param tmplatInf
	 */
	public void deleteTemplateInf(TemplateInf tmplatInf);

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param tmplatInf
	 */
	public void insertTemplateInf(TemplateInf tmplatInf) throws Exception;

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param tmplatInf
	 */
	public void updateTemplateInf(TemplateInf tmplatInf);

	/**
	 * 템플릿에 대한 화이트리스트 목록을 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return
	 */
	public List<TemplateInfVO> selectTemplateWhiteList();

	/**
	 * 템플릿에 대한 목록를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return
	 */
	public Map<String, Object> selectTemplateInfs(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿에 대한 상세정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return
	 */
	public TemplateInfVO selectTemplateInf(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿에 대한 미리보기 정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return
	 */
	public TemplateInfVO selectTemplatePreview(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿 구분에 따른 목록을 조회한다.
	 * 
	 * @param tmplatInfVO
	 * @return
	 */
	public List<TemplateInfVO> selectTemplateInfsByCode(TemplateInfVO tmplatInfVO);
}
