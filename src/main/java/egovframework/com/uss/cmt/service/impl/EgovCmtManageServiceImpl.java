package egovframework.com.uss.cmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import com.ibm.icu.util.Calendar;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.uss.cmt.service.CmtDefaultVO;
import egovframework.com.uss.cmt.service.CmtManageVO;
import egovframework.com.uss.cmt.service.EgovCmtManageService;

/**
 * 출퇴근관리에 관한 비지니스 클래스를 정의한다.
 * @author 표준프레임워크 개발팀
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일          수정자       수정내용
 *  ----------    --------    ---------------------------
 *  2009.04.10     개발팀       최초 생성
 *
 * </pre>
 */
@Service("cmtManageService")
public class EgovCmtManageServiceImpl extends EgovAbstractServiceImpl implements EgovCmtManageService {

	/** cmtManageDAO */
	@Resource(name = "cmtManageDAO")
	private EgovCmtManageDAO cmtManageDAO;

	/** egovCmtManageIdGnrService */
	@Resource(name = "egovCmtManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 출퇴근정보 목록 화면에 출력
	 * @param  DeptInfo (부서별 - optional) 검색조건
	 * @return List<CmtManageVO> 업무사용자 목록정보
	 * @throws Exception
	 */
	@Override
	public List<CmtManageVO> selectCmtInfoList(CmtDefaultVO cmtSearchVO) throws Exception {
		List<CmtManageVO> result = (List<CmtManageVO>) cmtManageDAO.selectCmtInfoList(cmtSearchVO);
		return result;
	}

	/**
	 * 출근정보 입력, 디바이스를 통해 외부 연계입력가능
	 * @param cmtManageVO를 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public String insertWrkStartCmtInfo(CmtManageVO cmtManageVO) throws Exception {

		// Key
		String wrktmId = idgenService.getNextStringId();
		cmtManageVO.setWrktmId(wrktmId);
		// 출근시간
		String formattedStartTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		cmtManageVO.setWrkStartTime(formattedStartTime);

		return cmtManageDAO.insertWrkStartCmtInfo(cmtManageVO);
	}

	/**
	 * 퇴근 정보 입력을 위한 wrktm id 확인
	 * @param cmtManageVO 검색조건
	 * @return 총사용자개수(int)
	 * @throws Exception
	 */
	@Override
	public String selectWrktmId(CmtManageVO cmtManageVO) throws Exception {

		return cmtManageDAO.selectWrktmId(cmtManageVO);
	}

	/**
	 * 퇴근 정보 입력
	 * @param cmtManageVO를 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int insertWrkEndCmtInfo(CmtManageVO cmtManageVO) throws Exception {

		cmtManageVO = cmtManageDAO.selectWrkStartInfo(cmtManageVO);
		//퇴근시간
		String formattedEndTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		cmtManageVO.setWrkEndTime(formattedEndTime);
		//회사별 Rule 기반으로 workhour / overtime_workhour를 결정한다. ex) DB 연동활용
		cmtManageVO.setWrkHours("8");
		cmtManageVO.setOvtmwrkHours("0");
		//출퇴근시간 Rule 기반으로 출퇴근상태를 구분한다. ex) 정상/지각/조회
		String msg = egovMessageSource.getMessage("ussCmt.cmtManageServiceImpl.normal");
		cmtManageVO.setWrkStartStatus(msg);
		cmtManageVO.setWrkEndStatus(msg);

		return cmtManageDAO.insertWrkEndCmtInfo(cmtManageVO);
	}

}