package egovframework.com.sym.ccm.acr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;

/**
 * 법정동코드에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("administCodeRecptnMapper")
public interface AdministCodeRecptnMapper {

	void insertAdministCodeRecptn(AdministCodeRecptn administCodeRecptn);

	void insertAdministCode(AdministCodeRecptn administCodeRecptn);

	void updateAdministCode(AdministCodeRecptn administCodeRecptn);

	void deleteAdministCode(AdministCodeRecptn administCodeRecptn);

	AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn);

	List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO);

	int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO);

	List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO searchVO);

	int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO);
}
