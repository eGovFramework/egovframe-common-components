package egovframework.com.uss.ion.vct.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.vct.service.VcatnManageVO;

/**
 * 휴가관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("vcatnManageMapper")
public interface VcatnManageMapper {

	List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO);

	int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO);

	VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO);

	void insertVcatnManage(VcatnManageVO vcatnManageVO);

	void updateVcatnManage(VcatnManageVO vcatnManageVO);

	void deleteVcatnManage(VcatnManageVO vcatnManageVO);

	int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO);

	List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO);

	int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO);

	void updateVcatnManageConfm(VcatnManageVO vcatnManageVO);

	VcatnManageVO selectIndvdlYrycManage(VcatnManageVO vcatnManageVO);

	void updateIndvdlYrycManage(VcatnManageVO vcatnManageVO);
}
