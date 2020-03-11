package egovframework.com.sec.rgm.service;

import egovframework.com.cmm.ComDefaultVO;


/**
 * 권한그룹에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public class AuthorGroup extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한그룹관리
	 */
	private AuthorGroup authorGroup;	
	/**
	 * 설정대상 사용자 ID
	 */
	private String userId;
	/**
	 * 설정대상 사용자 명
	 */
	private String userNm;
	/**
	 * 설정대상 그룹 ID
	 */
	private String groupId;	
	/**
	 * 설정대상 사용자 유형 코드
	 */
	private String mberTyCode;
	/**
	 * 설정대상 사용자 유형 명
	 */	
	private String mberTyNm;
	/**
	 * 권한코드
	 */
	private String authorCode;
	/**
	 * 등록 여부
	 */
	private String regYn;
	/**
	 * Uniq ID
	 */
	private String uniqId;
	
	/**
	 * authorGroup attribute 를 리턴한다.
	 * @return AuthorGroup
	 */
	public AuthorGroup getAuthorGroup() {
		return authorGroup;
	}
	/**
	 * authorGroup attribute 값을 설정한다.
	 * @param authorGroup AuthorGroup 
	 */
	public void setAuthorGroup(AuthorGroup authorGroup) {
		this.authorGroup = authorGroup;
	}
	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * @param userId String 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * userNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param userNm String 
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * groupId attribute 를 리턴한다.
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * groupId attribute 값을 설정한다.
	 * @param groupId String 
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * mberTyCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getMberTyCode() {
		return mberTyCode;
	}
	/**
	 * mberTyCode attribute 값을 설정한다.
	 * @param mberTyCode String 
	 */
	public void setMberTyCode(String mberTyCode) {
		this.mberTyCode = mberTyCode;
	}
	/**
	 * mberTyNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getMberTyNm() {
		return mberTyNm;
	}
	/**
	 * mberTyNm attribute 값을 설정한다.
	 * @param mberTyNm String 
	 */
	public void setMberTyNm(String mberTyNm) {
		this.mberTyNm = mberTyNm;
	}
	/**
	 * authorCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * @param authorCode String 
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	/**
	 * regYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * regYn attribute 값을 설정한다.
	 * @param regYn String 
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	/**
	 * uniqId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * @param uniqId String 
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	

	
	
}