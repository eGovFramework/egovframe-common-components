package egovframework.com.uss.cmt.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.cmt.service.CmtDefaultVO;
import egovframework.com.uss.cmt.service.CmtManageVO;

import org.springframework.stereotype.Repository;

/**
 * 출퇴근관리에 관한 데이터 접근 클래스를 정의한다.
 * @author 표준프레임워크 개발팀
 * @since 2014.11.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자           수정내용
 *  -------       ----------    ---------------------------
 *   2014.11.10   개발팀     최초 생성
 *
 * </pre>
 */
@Repository("cmtManageDAO")
public class EgovCmtManageDAO extends EgovComAbstractDAO {

	public List<?> selectCmtInfoList(CmtDefaultVO cmtSearchVO) {
		return selectList("cmtManageDAO.selectCmtList_S", cmtSearchVO);
	}

	/**
	* 출근 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	* @param cmtManageVO 업무사용자 등록정보
	* @return String result 등록결과
	*/
	public String insertWrkStartCmtInfo(CmtManageVO cmtManageVO) {
		return Integer.toString(insert("cmtManageDAO.insertWrkStartCmtInfo_S", cmtManageVO));
	}

	/**
	* 퇴근 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	* @param cmtManageVO 업무사용자 등록정보
	* @return String result 등록결과
	*/
	public int insertWrkEndCmtInfo(CmtManageVO cmtManageVO) {
		return update("cmtManageDAO.insertWrkEndCmtInfo_S", cmtManageVO);
	}

	/**
	 * 퇴근정보 입력을 위한 출근정보 id 조회
	 * @param cmtManageVO
	 * @return String wrktmId
	 */

	public String selectWrktmId(CmtManageVO cmtManageVO) {
		return (String) selectOne("cmtManageDAO.selectWrktmId_S", cmtManageVO);
	}

	/**
	 * 퇴근정보 입력을 위한 출근정보조회
	 * @param cmtManageVO
	 * @return cmtManageVO
	 */

	public CmtManageVO selectWrkStartInfo(CmtManageVO cmtManageVO) {
		CmtManageVO cmtVO = (CmtManageVO) selectOne("cmtManageDAO.selectWrkStartInfo_S", cmtManageVO);

		return cmtVO;
	}

}