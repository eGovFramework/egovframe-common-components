package egovframework.com.sym.ccm.ccc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;

/**
 * 공통분류코드에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("cmmnClCodeManageMapper")
public interface CmmnClCodeManageMapper {

	int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO);

	List<CmmnClCodeVO> selectCmmnClCodeList(CmmnClCodeVO searchVO);

	CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode);

	void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO);
}
