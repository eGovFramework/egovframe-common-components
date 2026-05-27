package egovframework.com.sym.ccm.adc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.sym.ccm.adc.service.AdministCode;
import egovframework.com.sym.ccm.adc.service.AdministCodeVO;

/**
 * 행정코드에 대한 Mapper 인터페이스
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
@EgovMapper("administCodeManageMapper")
public interface AdministCodeManageMapper {

	void deleteAdministCode(AdministCode administCode);

	void insertAdministCode(AdministCode administCode);

	AdministCode selectAdministCodeDetail(AdministCode administCode);

	List<EgovMap> selectAdministCodeList(AdministCodeVO searchVO);

	int selectAdministCodeListTotCnt(AdministCodeVO searchVO);

	void updateAdministCode(AdministCode administCode);
}
