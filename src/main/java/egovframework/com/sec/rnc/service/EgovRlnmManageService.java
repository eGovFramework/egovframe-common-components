package egovframework.com.sec.rnc.service;


/**
 * 실명인증관리에 관한 인터페이스클래스를 정의한다.
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
 *
 * </pre>
 */
public interface EgovRlnmManageService {
    
    /**
     * 실명인증확인화면 호출(주민번호) 
     * @param ihidnum 주민등록번호
     * @param realname 실명
     * @param sbscrbTy 사용자 유형
     * @return result 결과메시지코드
     * @throws Exception
     */
	
	public String rlnmCnfirm(String ihidnum, String realname, String sbscrbTy)throws Exception;
	
	/**
     * 실명인증확인화면 호출(GPIN) 
     * @param pinId 공공아이핀 아이디
     * @param pinPassword 공공아이핀 패스워드
     * @param sbscrbTy 사용자 유형
     * @return result 결과메시지코드
     * @throws Exception
     */
	public String rlnmPinCnfirm(String pinId, String pinPassword, String sbscrbTy)throws Exception;
}