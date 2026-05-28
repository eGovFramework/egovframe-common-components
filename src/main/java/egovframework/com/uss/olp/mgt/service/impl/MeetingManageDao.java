package egovframework.com.uss.olp.mgt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.mgt.service.MeetingManageVO;
import jakarta.annotation.Resource;

/**
 * 회의관리를 처리하기 위한 Dao 구현 Class
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
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@Repository("meetingManageDao")
public class MeetingManageDao {

    @Resource(name = "meetingManageMapper")
    private MeetingManageMapper meetingManageMapper;

    /**
     * 부서 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     */
    public List<EgovMap> egovMeetingManageLisAuthorGroupPopup(ComDefaultVO searchVO) {
        return meetingManageMapper.EgovMeetingManageLisAuthorGroupPopup(searchVO);
    }

    /**
     * 아이디 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     */
    public List<EgovMap> egovMeetingManageLisEmpLyrPopup(ComDefaultVO searchVO) {
        return meetingManageMapper.EgovMeetingManageLisEmpLyrPopup(searchVO);
    }

    /**
     * 회의정보 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     */
    public List<EgovMap> selectMeetingManageList(ComDefaultVO searchVO) {
        return meetingManageMapper.selectMeetingManage(searchVO);
    }

    /**
     * 회의정보를 상세조회 한다.
     * @param meetingManageVO - 회정정보가 담김 VO
     * @return List
     */
    public List<EgovMap> selectMeetingManageDetail(MeetingManageVO meetingManageVO) {
        return meetingManageMapper.selectMeetingManageDetail(meetingManageVO);
    }

    /**
     * 회의정보를 목록 전체 건수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     */
    public int selectMeetingManageListCnt(ComDefaultVO searchVO) {
        return meetingManageMapper.selectMeetingManageCnt(searchVO);
    }

    /**
     * 회의정보를 등록한다.
     * @param meetingManageVO - 회의정보가 담긴 VO
     */
    public void insertMeetingManage(MeetingManageVO meetingManageVO) {
        meetingManageMapper.insertMeetingManage(meetingManageVO);
    }

    /**
     * 회의정보를 수정한다.
     * @param meetingManageVO - 회의정보가 담긴 VO
     */
    public void updateMeetingManage(MeetingManageVO meetingManageVO) {
        meetingManageMapper.updateMeetingManage(meetingManageVO);
    }

    /**
     * 회의정보를 삭제한다.
     * @param meetingManageVO - 회의정보가 담긴 VO
     */
    public void deleteMeetingManage(MeetingManageVO meetingManageVO) {
        meetingManageMapper.deleteMeetingManage(meetingManageVO);
    }
}
