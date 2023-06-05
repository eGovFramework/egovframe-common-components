package egovframework.com.uss.umt.service;

import java.util.List;

/**
 * 기업회원관리에 관한 인터페이스클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
public interface EgovEntrprsManageService {

	/**
	 * 기업회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param entrprsManageVO 기업회원등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public String insertEntrprsmber(EntrprsManageVO entrprsManageVO) throws Exception;

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param entrprsmberId 조회대상 기업회원아이디
	 * @return entrprsManageVO 기업회원정보
	 * @throws Exception
	 */
	public EntrprsManageVO selectEntrprsmber(String entrprsmberId) throws Exception;

	/**
	 * 화면에 조회된 기업회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param entrprsManageVO 기업회원수정정보
	 * @throws Exception
	 */
	public void updateEntrprsmber(EntrprsManageVO entrprsManageVO) throws Exception;

	/**
	 * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
	 * @param checkedIdForDel 삭제대상기업회원아이디
	 * @throws Exception
	 */
	public void deleteEntrprsmber(String checkedIdForDel) throws Exception;

	/**
	 * 기업회원용 약관정보 조회
	 * @param stplatId 기업회원약관아이디
	 * @return stplatList 기업회원약관정보
	 * @throws Exception
	 */
	public List<StplatVO> selectStplat(String stplatId) throws Exception;

	/**
	 * 기업회원암호수정
	 * @param entrprsManageVO 기업회원수정정보(비밀번호)
	 * @throws Exception
	 */
	public void updatePassword(EntrprsManageVO entrprsManageVO) throws Exception;

	/**
	 * 기업회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * @param passVO 기업회원암호 조회조건정보
	 * @return entrprsManageVO 기업회원암호정보
	 * @throws Exception
	 */
	public EntrprsManageVO selectPassword(EntrprsManageVO passVO) throws Exception;

	/**
	 * 기 등록된기업 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param userSearchVO 검색조건
	 * @return List<EntrprsManageVO> 기업회원목록정보
	 * @throws Exception
	 */
	public List<EntrprsManageVO> selectEntrprsMberList(UserDefaultVO userSearchVO) throws Exception;

    /**
     * 기업회원 총 개수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 사용자 총 개수(int)
     * @throws Exception
     */
    public int selectEntrprsMberListTotCnt(UserDefaultVO userSearchVO) throws Exception;


    /**
     * 로그인인증제한 해제 
     * @param entrprsManageVO 기업회원정보
     * @return void
     * @throws Exception
     */
    public void updateLockIncorrect(EntrprsManageVO entrprsManageVO) throws Exception;
}