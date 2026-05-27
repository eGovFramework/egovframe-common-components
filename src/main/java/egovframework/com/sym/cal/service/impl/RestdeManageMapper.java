package egovframework.com.sym.cal.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.sym.cal.service.Restde;
import egovframework.com.sym.cal.service.RestdeVO;

/**
 * 휴일관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("restdeManageMapper")
public interface RestdeManageMapper {

	List<EgovMap> selectNormalRestdePopup(Restde restde);
	List<EgovMap> selectAdministRestdePopup(Restde restde);
	List<EgovMap> selectNormalDayCal(Restde restde);
	List<EgovMap> selectNormalDayRestde(Restde restde);
	List<EgovMap> selectNormalMonthRestde(Restde restde);
	List<EgovMap> selectAdministDayCal(Restde restde);
	List<EgovMap> selectAdministDayRestde(Restde restde);
	List<EgovMap> selectAdministMonthRestde(Restde restde);
	void deleteRestde(Restde restde);
	void insertRestde(Restde restde);
	Restde selectRestdeDetail(Restde restde);
	List<EgovMap> selectRestdeList(RestdeVO searchVO);
	int selectRestdeListTotCnt(RestdeVO searchVO);
	void updateRestde(Restde restde);
}
