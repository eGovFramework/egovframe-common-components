package  egovframework.com.sym.mnu.stm.service;

import egovframework.com.cmm.ComDefaultVO;

/** 
 * 메뉴사이트맵에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */
public interface EgovSiteMapngService {

	/**
	 * 사이트맵 조회
	 * @param vo ComDefaultVO   
	 * @return SiteMapngVO
	 * @exception Exception
	 */
	SiteMapngVO selectSiteMapng(ComDefaultVO vo) throws Exception;
}