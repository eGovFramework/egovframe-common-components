package egovframework.com.sym.mnu.stm.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.stm.service.SiteMapngVO;

/**
 * 사이트맵 조회를 위한 MyBatis Mapper 인터페이스
 *
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
 *   2026.05.26  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("siteMapngDAO")
public interface SiteMapngMapper {

	/**
	 * 사이트맵을 조회한다.
	 *
	 * @param vo ComDefaultVO
	 * @return SiteMapngVO
	 */
	SiteMapngVO selectSiteMapng_D(ComDefaultVO vo);

	/**
	 * MapCreatId를 조회한다.
	 *
	 * @param vo ComDefaultVO
	 * @return String
	 */
	String selectSiteMapngByMapCreatID(ComDefaultVO vo);

}
