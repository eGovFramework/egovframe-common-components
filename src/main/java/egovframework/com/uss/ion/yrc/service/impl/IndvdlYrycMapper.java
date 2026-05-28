package egovframework.com.uss.ion.yrc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.yrc.service.IndvdlYrycManage;

/**
 * 연차관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("indvdlYrycMapper")
public interface IndvdlYrycMapper {

	List<IndvdlYrycManage> selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage);

	int selectIndvdlYrycManageListTotCnt(IndvdlYrycManage indvdlYrycManage);

	void insertIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage);

	void updateIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage);

	void deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage);
}
