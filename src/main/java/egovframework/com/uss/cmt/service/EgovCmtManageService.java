package egovframework.com.uss.cmt.service;

import java.util.List;

/**
 * 출퇴근관리에 관한 인터페이스클래스를 정의한다.
 * @author 표준프레임워크 개발팀
 * @since 2014.08.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.08.29  개발팀          최초 생성
 *
 * </pre>
 */
public interface EgovCmtManageService {

	/**
	 * 출퇴근정보 목록 화면에 출력
	 * @param  DeptInfo (부서별 - optional) 검색조건
	 * @return List<CmtManageVO> 업무사용자 목록정보
	 * @throws Exception
	 */
	public List<?> selectCmtInfoList(CmtDefaultVO cmtSearchVO) throws Exception;

	/**
	 * 출근정보 입력, 디바이스를 통해 외부 연계입력가능
	 * @param cmtManageVO를 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public String insertWrkStartCmtInfo(CmtManageVO cmtManageVO) throws Exception;

	/**
	 * 퇴근정보 입력, 디바이스를 통해 외부 연계입력가능
	 * @param cmtManageVO를 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int insertWrkEndCmtInfo(CmtManageVO cmtManageVO) throws Exception;

	/**
	 * 퇴근 정보 입력을 위한 wrktm id 확인
	 * @param cmtManageVO 검색조건
	 * @return 총사용자갯수(int)
	 * @throws Exception
	 */
	public String selectWrktmId(CmtManageVO cmtManageVO) throws Exception;

}