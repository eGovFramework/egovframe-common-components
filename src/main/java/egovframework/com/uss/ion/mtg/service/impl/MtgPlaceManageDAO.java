package egovframework.com.uss.ion.mtg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResveVO;

/**
 * 개요
 * - 회의실관리에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
@EgovMapper("mtgPlaceManageDAO")
public interface MtgPlaceManageDAO {

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리목록 총 개수를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int
	 */
	int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 */
	void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 */
	void updtMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 */
	void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실 ID 정보 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	List<MtgPlaceManageVO> selectMtgPlaceIDList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실예약을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 목록
	 */
	MtgPlaceManageVO selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실예약 신청화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 * @param mtgPlaceResveVO - 회의실예약 VO
	 */
	void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 * @param mtgPlaceResveVO - 회의실예약 VO
	 */
	void updtMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
	 * @param mtgPlaceResveVO - 회의실예약 VO
	 */
	void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO);

}
