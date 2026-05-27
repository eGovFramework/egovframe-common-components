package egovframework.com.sym.ccm.icr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.sym.ccm.icr.service.InsttCodeRecptn;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptnVO;

/**
 * 기관코드에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("insttCodeRecptnMapper")
public interface InsttCodeRecptnMapper {

	void insertInsttCodeRecptn(InsttCodeRecptn insttCodeRecptn);
	void insertInsttCode(InsttCodeRecptn insttCodeRecptn);
	void updateInsttCode(InsttCodeRecptn insttCodeRecptn);
	void deleteInsttCode(InsttCodeRecptn insttCodeRecptn);
	InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn);
	List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO);
	int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO);
	List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO searchVO);
	int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO);
}
