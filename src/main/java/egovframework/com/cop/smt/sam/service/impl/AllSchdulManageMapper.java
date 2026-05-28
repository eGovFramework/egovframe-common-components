package egovframework.com.cop.smt.sam.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 전체일정을 위한 Mapper 인터페이스
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 * </pre>
 */
@EgovMapper("allSchdulManageMapper")
public interface AllSchdulManageMapper {

	List<EgovMap> selectIndvdlSchdulManage(ComDefaultVO searchVO);

	int selectIndvdlSchdulManageCnt(ComDefaultVO searchVO);

}
