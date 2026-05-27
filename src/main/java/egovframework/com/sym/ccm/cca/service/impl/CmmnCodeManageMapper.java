package egovframework.com.sym.ccm.cca.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;

/**
 * 공통코드에 대한 Mapper 인터페이스
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
@EgovMapper("cmmnCodeManageMapper")
public interface CmmnCodeManageMapper {

	int selectCmmnCodeListTotCnt(CmmnCodeVO searchVO);

	List<CmmnCodeVO> selectCmmnCodeList(CmmnCodeVO searchVO);

	CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCodeVO);

	void updateCmmnCode(CmmnCode cmmnCode);

	void insertCmmnCode(CmmnCode cmmnCode);

	void deleteCmmnCode(CmmnCode cmmnCode);
}
