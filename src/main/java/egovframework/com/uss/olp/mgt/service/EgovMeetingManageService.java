package egovframework.com.uss.olp.mgt.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 회의관리를 처리하기 위한 Service 구현 Class
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovMeetingManageService {

    /**
	 * 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> egovMeetingManageLisAuthorGroupPopup(ComDefaultVO searchVO) throws Exception;
    /**
	 * 아이디 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> egovMeetingManageLisEmpLyrPopup(ComDefaultVO searchVO) throws Exception;
    /**
	 * 회의정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectMeetingManageList(ComDefaultVO searchVO) throws Exception;

    /**
	 * 회의정보를 상세조회 한다.
	 * @param MeetingManageVO - 회의정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectMeetingManageDetail(MeetingManageVO meetingManageVO) throws Exception;

    /**
	 * 회의정보를 목록 전체 건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectMeetingManageListCnt(ComDefaultVO searchVO) throws Exception;

    /**
	 * 회의정보를 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	void  insertMeetingManage(MeetingManageVO meetingManageVO) throws Exception;

    /**
	 * 회의정보를 수정한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	void  updateMeetingManage(MeetingManageVO meetingManageVO) throws Exception;

    /**
	 * 회의정보를 삭제한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	void  deleteMeetingManage(MeetingManageVO meetingManageVO) throws Exception;


}
