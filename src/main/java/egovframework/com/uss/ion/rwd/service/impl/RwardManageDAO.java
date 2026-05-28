package egovframework.com.uss.ion.rwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 포상관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 포상관리에 대한 등록, 수정, 삭제, 조회, 승인처리 기능을 제공한다.
 * - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용              최초 생성
 *   2026.05.28  dasomel           @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 이용
 * @version 1.0
 */

@Repository("rwardManageDAO")
public class RwardManageDAO {

	@Resource(name = "rwardManageMapper")
	private RwardManageMapper mapper;

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageList(RwardManageVO rwardManageVO) throws Exception {
		return mapper.selectRwardManageList(rwardManageVO);
	}

	/**
	 * 포상관리목록 총 개수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectRwardManageListTotCnt(RwardManageVO rwardManageVO) throws Exception {
		return mapper.selectRwardManageListTotCnt(rwardManageVO);
	}

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return RwardManageVO - 포상관리 VO
	 */
	public RwardManageVO selectRwardManage(RwardManageVO rwardManageVO) throws Exception {
		return mapper.selectRwardManage(rwardManageVO);
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void insertRwardManage(RwardManage rwardManage) throws Exception {
		mapper.insertRwardManage(rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManage(RwardManage rwardManage) throws Exception {
		mapper.updtRwardManage(rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void deleteRwardManage(RwardManage rwardManage) throws Exception {
		mapper.deleteRwardManage(rwardManage);
	}

	/*** 승인처리관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageConfmList(RwardManageVO rwardManageVO) throws Exception {
		return mapper.selectRwardManageConfmList(rwardManageVO);
	}

	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록 총 개수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectRwardManageConfmListTotCnt(RwardManageVO rwardManageVO) throws Exception {
		return mapper.selectRwardManageConfmListTotCnt(rwardManageVO);
	}

	/**
	 * 포상정보를 승인/반려처리 한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManageConfm(RwardManage rwardManage) throws Exception {
		mapper.updtRwardManageConfm(rwardManage);
	}
}
