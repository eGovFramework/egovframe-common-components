package egovframework.com.uss.ion.ans.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.ans.service.AnnvrsryManage;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;

/**
 * 개요
 * - 기념일관리에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 기념일관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("annvrsryManageDAO")
public interface AnnvrsryManageDAO {

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return List - 기념일관리 목록
	 */
	List<AnnvrsryManageVO> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리목록 총 개수를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return int
	 */
	int selectAnnvrsryManageListTotCnt(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	void insertAnnvrsryManage(AnnvrsryManage annvrsryManage);

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	void updateAnnvrsryManage(AnnvrsryManage annvrsryManage);

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return List - 기념일관리 VO
	 */
	List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리 등록시 중복여부를 조회한다.
	 * @param annvrsryManage - 기념일관리 VO
	 * @return int
	 */
	int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	AnnvrsryManageVO selectAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO);

}
