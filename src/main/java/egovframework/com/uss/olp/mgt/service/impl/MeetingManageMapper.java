package egovframework.com.uss.olp.mgt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.mgt.service.MeetingManageVO;

/**
 * 회의관리를 위한 Mapper 인터페이스
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel     @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper
public interface MeetingManageMapper {

	/**
	 * 부서 목록을 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return List
	 */
	List<EgovMap> EgovMeetingManageLisAuthorGroupPopup(ComDefaultVO searchVO);

	/**
	 * 아이디 목록을 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return List
	 */
	List<EgovMap> EgovMeetingManageLisEmpLyrPopup(ComDefaultVO searchVO);

	/**
	 * 회의정보 목록을 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return List
	 */
	List<EgovMap> selectMeetingManage(ComDefaultVO searchVO);

	/**
	 * 회의정보 상세를 조회한다.
	 * @param meetingManageVO - 회의정보 VO
	 * @return List
	 */
	List<EgovMap> selectMeetingManageDetail(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보 목록 총 건수를 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return int
	 */
	int selectMeetingManageCnt(ComDefaultVO searchVO);

	/**
	 * 회의정보를 등록한다.
	 * @param meetingManageVO - 회의정보 VO
	 */
	void insertMeetingManage(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 수정한다.
	 * @param meetingManageVO - 회의정보 VO
	 */
	void updateMeetingManage(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 삭제한다.
	 * @param meetingManageVO - 회의정보 VO
	 */
	void deleteMeetingManage(MeetingManageVO meetingManageVO);

}
