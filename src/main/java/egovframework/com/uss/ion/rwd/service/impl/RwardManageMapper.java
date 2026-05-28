package egovframework.com.uss.ion.rwd.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;

/**
 * 포상관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("rwardManageMapper")
public interface RwardManageMapper {

	List<RwardManageVO> selectRwardManageList(RwardManageVO rwardManageVO);

	int selectRwardManageListTotCnt(RwardManageVO rwardManageVO);

	RwardManageVO selectRwardManage(RwardManageVO rwardManageVO);

	void insertRwardManage(RwardManage rwardManage);

	void updtRwardManage(RwardManage rwardManage);

	void deleteRwardManage(RwardManage rwardManage);

	List<RwardManageVO> selectRwardManageConfmList(RwardManageVO rwardManageVO);

	int selectRwardManageConfmListTotCnt(RwardManageVO rwardManageVO);

	void updtRwardManageConfm(RwardManage rwardManage);
}
